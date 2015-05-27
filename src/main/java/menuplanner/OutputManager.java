package menuplanner;

import Models.Food;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.DrawingContext;
import de.erichseifert.gral.io.plots.DrawableWriter;
import de.erichseifert.gral.io.plots.DrawableWriterFactory;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.util.Insets2D;
import jmetal.core.Solution;
import jmetal.core.Variable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This class handles output process of genetic algorithm.
 * Created by BurakMac on 26/05/15.
 */
public class OutputManager {
    private List<Solution> databank;
    private File foodListFolder;

    ArrayList<String> fileList;
private String executionNumber;
    public OutputManager(List<Solution> databank)
    {
    this.databank = databank;
    Calendar cal  =Calendar.getInstance();
        int timeHashCode  = (int) cal.getTimeInMillis();
        setExecutionNumber(Integer.toString(timeHashCode*-1));
    this.setFoodListFolder(new File(getExecutionNumber()));
        getFoodListFolder().mkdir();
         fileList = new ArrayList<String>();


    }

    /**
     * archiveResults zips recursively all of output folder files.
     */
    public String archiveResults()
    {

        String zipFile = getFoodListFolder().getPath()+"/temp.zip";
        String OUTPUT_FOLDER = getFoodListFolder().getPath();

        addFiles(new File(OUTPUT_FOLDER));
        byte[] buffer = new byte[1024];
        try{
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);

            for(String file : this.fileList){

                System.out.println("File Added : " + file);
                ZipEntry ze= new ZipEntry(file);
                zos.putNextEntry(ze);

                FileInputStream in =
                        new FileInputStream(OUTPUT_FOLDER + File.separator + file);

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                in.close();
            }

            zos.closeEntry();
            //remember close it
            zos.close();

            System.out.println("Done");
        }
        catch (Exception ex)
        {
ex.printStackTrace();
        }
        return zipFile;
    }

    private void addFiles(File node)
    {
        if(node.isFile()){
                    fileList.add(node.getAbsoluteFile().toString().substring(getFoodListFolder().getAbsolutePath().length() + 1, node.getAbsoluteFile().toString().length()));
        }

        if(node.isDirectory()){
            String[] subNote = node.list();
            for(String filename : subNote){
                addFiles(new File(node, filename));
            }
        }
    }

    public void print2ObjectiveValues() {
        try {
            int numberofObjectives = databank.get(0).getNumberOfObjectives();

            if(numberofObjectives !=2) {
                throw new Exception("Error To plot data number of objective must be 2");
            }

            DataTable dataTable = new DataTable(Double.class,Double.class);
        for (Solution solution : databank) {
        dataTable.add(solution.getObjective(0),-1.0*solution.getObjective(1));
        }




            XYPlot plot = new XYPlot(dataTable);
            double insetsTop = 20.0,
                    insetsLeft = 60.0,
                    insetsBottom = 60.0,
                    insetsRight = 40.0;
            plot.setInsets(new Insets2D.Double(
                    insetsTop, insetsLeft, insetsBottom, insetsRight));
//            plot.getAxis(XYPlot.AXIS_X).setRange(5.0,110.0);
            plot.getAxis(XYPlot.AXIS_X).setAutoscaled(true);
//            plot.getAxis(XYPlot.AXIS_Y).setRange(20.0, 200.0);
            plot.getAxis(XYPlot.AXIS_X).setAutoscaled(true);
            plot.getAxisRenderer(XYPlot.AXIS_X).setTickLabelFormat(DecimalFormat.getInstance());
            plot.getAxisRenderer(XYPlot.AXIS_Y).setTickLabelFormat(DecimalFormat.getInstance());

            BufferedImage bImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) bImage.getGraphics();
            DrawingContext context = new DrawingContext(g2d);
            plot.draw(context);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DrawableWriter wr = DrawableWriterFactory.getInstance().get("image/png");

            wr.write(plot, baos, 800, 600);
            baos.flush();
//            FileOutputStream fileOutputStream = new FileOutputStream(foodListFolder.getPath()+"/result.jpg");
//            fileOutputStream.write(baos.toByteArray());
//            fileOutputStream.close();
            baos.close();
            InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
            BufferedImage bufferedImage =  ImageIO.read(inputStream);

            ImageIO.write(bufferedImage, "png", new File(getFoodListFolder().getPath() + "/result.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printFoodLists()
    {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(getFoodListFolder().getPath()+"/result.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for(int j=0; j<databank.size(); j++)
            {
                bufferedWriter.write("---- Begining of "+j+" th result ----");
                bufferedWriter.newLine();
                bufferedWriter.write( databank.get(j).getObjective(0) +" "+databank.get(j).getObjective(1) + " - Constrain Num = "+databank.get(j).getNumberOfViolatedConstraint()+" - Total constraint = "+databank.get(j).getOverallConstraintViolation());
                bufferedWriter.newLine();

                FileOutputStream fileOutputStream_local = new FileOutputStream(getFoodListFolder().getPath()+"/"+j+".txt");
                OutputStreamWriter outputStreamWriter_local = new OutputStreamWriter(fileOutputStream_local);
                BufferedWriter bw_local = new BufferedWriter(outputStreamWriter_local);
                ArrayList<Food> foodArrayList = new ArrayList<Food>();
                bw_local.write(j + " ith solution");
                bw_local.newLine();
                bw_local.write("Total constraint violation number = " + databank.get(j).getNumberOfViolatedConstraint());
                bw_local.newLine();
                bw_local.write("Total constraint violation amount = " + databank.get(j).getOverallConstraintViolation());
                bw_local.newLine();

                bufferedWriter.newLine();
                bw_local.write("Choosen Foods");
                bw_local.newLine();
                int a=0;
                for(Variable var: databank.get(j).getDecisionVariables())
                {
                    a++;
                    if(var.toString().compareTo("1") == 0)
                    {
                        Food tempfood = Knapsack.databank.get(a);
                        foodArrayList.add(tempfood);
                        String temp = tempfood.getFoodName();
                        bw_local.write(temp);
                        bw_local.newLine();
                    }
                }

                double carbonhydrate=0.0, fiber=0.0, sugar=0.0, magnesium=0.0, phosphorus =0.0, rate = 0.0, potassium=0.0,
                        protein=0.0, water=0.0, vitaminC=0.0, sodium=0.0, zinc=0.0, manganese=0.0, selenium=0.0, niacin=0.0,
                        folicAcid=0.0,cholineTot=0.0,vitaminA_IU=0.0,retinol=0.0,
                        alphaCarot=0.0,betaCarot=0.0,betaCyrpt=0.0,lycopene=0.0,lutZea=0.0,faSat=0.0,
                        vitaminD=0.0, vitaminE=0.0, vitaminK=0.0, thiamin=0.0, riboflavin=0.0,
                        vitaminB6=0.0,folateTot=0.0,vitaminB12=0.0,PantoAcid=0.0,CholineTot=0.0,
                        calcium=0.0,copper=0.0,iron=0.0;
                for(int i=0;i<foodArrayList.size();i++)
                {
                    carbonhydrate += foodArrayList.get(i).getCarbonhydrt();
                    fiber += foodArrayList.get(i).getFiber();
                    protein += foodArrayList.get(i).getProtein();
                    water += foodArrayList.get(i).getWater();
                    vitaminA_IU += foodArrayList.get(i).getVitaminA_IU();
                    thiamin += foodArrayList.get(i).getThiamin();
                    riboflavin += foodArrayList.get(i).getRiboflavin();
                    niacin += foodArrayList.get(i).getNiacin();
                    copper += foodArrayList.get(i).getCopper();
                    magnesium += foodArrayList.get(i).getMagnesium();
                    phosphorus += foodArrayList.get(i).getPhosphorus();
                    selenium += foodArrayList.get(i).getPhosphorus();
                    zinc += foodArrayList.get(i).getZinc();
                    vitaminC += foodArrayList.get(i).getVitaminC();
                    vitaminD +=foodArrayList.get(i).getVitaminD();
                    vitaminE += foodArrayList.get(i).getVitaminE();
                    vitaminK += foodArrayList.get(i).getVitaminK();
                    thiamin += foodArrayList.get(i).getThiamin();
                    riboflavin += foodArrayList.get(i).getRiboflavin();
                    vitaminB6 += foodArrayList.get(i).getVitaminB6();
                    folateTot += foodArrayList.get(i).getFolateTot();
                    vitaminB12 += foodArrayList.get(i).getVitaminB12();
                    PantoAcid += foodArrayList.get(i).getPantoAcid();
                    CholineTot += foodArrayList.get(i).getCholineTot();
                    calcium += foodArrayList.get(i).getCalcium();
                    copper += foodArrayList.get(i).getCopper();
                    iron += foodArrayList.get(i).getIron();
                }

                String result = "Result carbonhydrate = "+carbonhydrate +" fiber= "+fiber+" protein= "+protein+
                        " vitaminA_IU = "+vitaminA_IU+" thiamin = "+thiamin+" riboflavin = "+riboflavin+" niacin = "+niacin+" magnesium = "+magnesium+
                        " phosphorous = "+phosphorus+" selenium = "+selenium+" zin = "+zinc+
                        " water= "+water+" vitaminC= "+vitaminC+ " vitaminD= "+ vitaminD
                        +" vitaminE= "+vitaminE+ " vitaminK= "+vitaminK + " thiamin= "+thiamin+
                        " riboflavin= "+riboflavin+" vitaminB6= "+vitaminB6+" folateTot= "+folateTot+" vitaminB12= "+
                        vitaminB12+" pantoAcid= "+PantoAcid+" cholineTot= "+CholineTot+ " calcium= "+calcium+ " copper= "+copper+ " iron= "+iron;


                bufferedWriter.write(result);
                bufferedWriter.newLine();
                bufferedWriter.write("---- End of "+j+" th result ----");
                bufferedWriter.newLine();

bw_local.close();
            }
            bufferedWriter.close();
        }
        catch (FileNotFoundException exception)
        {
            System.err.println("Hashed output file not found exception => "+exception.getLocalizedMessage());
            exception.printStackTrace();
        }
        catch (IOException exception)
        {
            System.err.println("IO exception => "+exception.getLocalizedMessage());
            exception.printStackTrace();
        }

    }


    public String getExecutionNumber() {
        return executionNumber;
    }

    public void setExecutionNumber(String executionNumber) {
        this.executionNumber = executionNumber;
    }

    public File getFoodListFolder() {
        return foodListFolder;
    }

    public void setFoodListFolder(File foodListFolder) {
        this.foodListFolder = foodListFolder;
    }
}
