package menuplanner;

import Models.ConstraintModel;
import Models.Food;
import Models.Nutrition;
import database.MongoManager;
import jmetal.core.Problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import jmetal.core.Solution;
import jmetal.core.SolutionType;
import jmetal.core.Variable;

import jmetal.encodings.solutionType.BinarySolutionType;

import jmetal.encodings.variable.Int;
import jmetal.util.JMException;
import org.bson.Document;

/**
* Created by burak on 15/03/15.
*/

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/


public class Knapsack extends Problem
{
//    private Double[] weightGlobal = {4.0,2.0,5.0,6.0,7.0,8.0,2.0,1.0,5.0,4.0,5.0,2.0,12.0,17.0,19.0,21.0,10.0,3.0};
//    private Double[] values = {5.0,10.0,12.0,5.0,7.0,12.0,15.0,18.0,11.0,5.0,1.0,5.0,3.0,6.0,10.0,20.0,21.0,10.0};
//    private Double[] rate = {3.0,4.0,2.0,1.0,0.0,2.0,4.0,5.0,2.0,3.0,1.0,10.0,2.0,3.0,0.0,8.0,9.0,2.0};
    private double[] weightGlobal;
    private double[] values ;
    private double[] rate ;
    private int[]  length;
    static double  tolarance = 0.15;

    double chanceofRepair;
    static   double[] maxVal  =              {2500,650 ,120 ,3000 ,2000 ,100 ,1000 ,-2 ,-2    ,35  ,100   ,-2  ,10     ,45 ,350  ,4000 ,400 ,40  };
    static  double[] recommendVal =          {800 ,100 ,43  ,625  ,75   ,10  ,12   ,1  ,101   ,12  ,1.1   ,2  ,0.7    ,6  ,330  ,580  ,45  ,9.4 };
    static  double[] constraintCoefficient = {100 ,100 ,100 ,100 ,100   ,100 ,100  ,100,100   ,100 ,100   ,100,100    ,100,100  ,100  ,100 ,100 };
    private static ArrayList<Food> databank;
    private static HashMap<Nutrition, ConstraintModel> constraintDatabank;



    //    private int[] length = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    double storageSize=5000;

    public static ArrayList<Food> getDatabank() {
        return databank;
    }

    public static void setDatabank(ArrayList<Food> databank) {
        Knapsack.databank = databank;
    }

    public double[] readTextFile(String inputfile)
    {
        double[] returnedArr = new double[1000];
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(inputfile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i=0;
        while(scanner.hasNextLine())
        {
            returnedArr[i++] = scanner.nextInt();
        }
        return returnedArr;
    }

    public Knapsack(SolutionType solutionType) {
        super(solutionType);
    }

    public Knapsack() {
        super();
    }

    public Knapsack(String SolutionType, Integer numberofVariables, Integer numberofObjectives, Integer numberofConstraints, double chanceofRepair)
    {
//        for (int i =0; i<length.length;i++)
//            length[i]=1;
//        weightGlobal = readTextFile("/Users/burak/Bitirme Odevi/menuplanner/weights.txt")    ;
//        values = readTextFile("/Users/burak/Bitirme Odevi/menuplanner/values.txt")    ;
//        rate = readTextFile("/Users/burak/Bitirme Odevi/menuplanner/ratings.txt")    ;
        MongoManager mongoManager =new MongoManager();
        setDatabank(mongoManager.getAllElements());
        constraintDatabank  = mongoManager.getAllConstraints();
                          length = new int[getDatabank().size()];

        for(int i=0; i< getDatabank().size();i++)
        {
            length[i] = 1;
        }
        length_=length;
        //numberOfVariables_= numberofVariables;
        numberOfVariables_ = getDatabank().size();
        length = new int[numberOfVariables_];
        lowerLimit_ = new double[numberOfVariables_];
        upperLimit_ = new double[numberOfVariables_];
        numberOfConstraints_ = numberofConstraints;
        numberOfObjectives_ = numberofObjectives;
        this.chanceofRepair = chanceofRepair;
        problemName_ = "Knapsack";

        if(SolutionType.equals("Binary"))
        {
            solutionType_ = new BinarySolutionType(this);
            for(int i =0; i<numberOfVariables_;i++)
            {
                // Reason for that is only support binary encoding.
                lowerLimit_[i]= 0.0;
                upperLimit_[i] = 1.0;
            }
        }
        else
        {
            System.out.println("This problem does not support rest of binary solution type");
            System.exit(-1);
        }
    }

    @Override
    public void evaluate(Solution solution) throws JMException {
        Variable variables[] = solution.getDecisionVariables();
       // System.out.println("Decision variables => "+variables[0].toString());
//        double targetcal=0.0;
        double cost=0.0;
        double interest = 0.0;
        for (int var = 0; var < numberOfVariables_; var++) {
            if(variables[var].toString().compareTo("1") == 0){
                //targetcal = targetcal + databank.get(var).getEnergy();
                cost = cost+ databank.get(var).getCost();
                interest = interest + getDatabank().get(var).getRate();


            }
        } // for
if(cost <0)
{
    System.out.println("denem");
}
        solution.setObjective(0, cost);
        solution.setObjective(1, -1*interest);

    }

    @Override
    public void evaluateConstraints(Solution solution) throws JMException {
        Variable[] variables = solution.getDecisionVariables();
        boolean isTime2Save;
        Random random = new Random();
        if(chanceofRepair * 100 <= random.nextInt(101))
        {
            isTime2Save = true;

        }
        else
        {
            isTime2Save = false;
        }
        int tempNum = random.nextInt(100);
        int number = 0;
        double total = 0.0;
        double[] constraints = new double[constraintDatabank.size()];
        HashMap<Nutrition,Double> constraintsMap = new HashMap<Nutrition, Double>();
        for (int var = 0; var < this.numberOfVariables_; var++) {
            if(variables[var].toString().compareTo("1") == 0) {


               constraints[0] = constraints[0] +  getDatabank().get(var).getIngredient().get(Nutrition.Calcium);
                constraints[1] = constraints[1] + getDatabank().get(var).getIngredient().get(Nutrition.Protein);
                constraints[2] = constraints[2] + getDatabank().get(var).getIngredient().get(Nutrition.Carbohydrate);

                constraints[3] = constraints[3] + getDatabank().get(var).getIngredient().get(Nutrition.Calorie);

                constraints[4] = constraints[4] + getDatabank().get(var).getIngredient().get(Nutrition.VitaminC);


                constraints[6] = constraints[6] + getDatabank().get(var).getIngredient().get(Nutrition.VitaminE);
                constraints[7] = constraints[7] + getDatabank().get(var).getIngredient().get(Nutrition.Thiamine);
                constraints[8] = constraints[8] + getDatabank().get(var).getIngredient().get(Nutrition.Riboflavin);
                constraints[9] = constraints[9] + getDatabank().get(var).getIngredient().get(Nutrition.Niacin);
                constraints[10] = constraints[10] + getDatabank().get(var).getIngredient().get(Nutrition.VitaminB6);
//                constraints[11] = constraints[11]  + databank.get(var).getFolateTot();
                constraints[11] = constraints[11] + getDatabank().get(var).getIngredient().get(Nutrition.VitaminB12);
                constraints[12] = constraints[12] + getDatabank().get(var).getIngredient().get(Nutrition.Copper);
//                constraints[14] = constraints[14] + databank.get(var).get(); Iodine
                constraints[13] = constraints[13] + getDatabank().get(var).getIngredient().get(Nutrition.Iron);
                constraints[14] = constraints[14] + getDatabank().get(var).getIngredient().get(Nutrition.Magnesium);
//                constraints[17] = constraints[17] + databank.get(var).getM(); Molybdenum
                constraints[15] = constraints[15] + getDatabank().get(var).getIngredient().get(Nutrition.Phosphorus);
                constraints[16] = constraints [16] + getDatabank().get(var).getIngredient().get(Nutrition.Selenium);
                constraints[17] = constraints [17] + getDatabank().get(var).getIngredient().get(Nutrition.Zinc);


                constraints[5] = constraints [5] + getDatabank().get(var).getIngredient().get(Nutrition.FolicAcid);

//                constraints[5] = constraints [5] + getDatabank().get(var).getIngredient().get(Nutrition.Iodine);

//                constraints[18] = constraints [18] + getDatabank().get(var).getIngredient().get(Nutrition.Molybdenum);


            }

        }
        constraintsMap.put(Nutrition.Calcium,constraints[0]);
        constraintsMap.put(Nutrition.Protein,constraints[1]);
        constraintsMap.put(Nutrition.Carbohydrate,constraints[2]);
        constraintsMap.put(Nutrition.Calorie,constraints[3]);
        constraintsMap.put(Nutrition.VitaminC,constraints[4]);
        constraintsMap.put(Nutrition.Iodine,constraints[5]);
        constraintsMap.put(Nutrition.VitaminE,constraints[6]);
        constraintsMap.put(Nutrition.Thiamine,constraints[7]);
        constraintsMap.put(Nutrition.Riboflavin,constraints[8]);
        constraintsMap.put(Nutrition.Niacin,constraints[9]);
        constraintsMap.put(Nutrition.VitaminB6,constraints[10]);
        constraintsMap.put(Nutrition.VitaminB12,constraints[11]);
        constraintsMap.put(Nutrition.Copper,constraints[12]);
        constraintsMap.put(Nutrition.Iron,constraints[13]);
        constraintsMap.put(Nutrition.Magnesium,constraints[14]);
        constraintsMap.put(Nutrition.Phosphorus,constraints[15]);
        constraintsMap.put(Nutrition.Selenium,constraints[16]);
        constraintsMap.put(Nutrition.Zinc,constraints[17]);
//        constraintsMap.put(Nutrition.Molybdenum,constraints[18]);
        constraintsMap.put(Nutrition.FolicAcid,constraints[5]);


        double[] constraintViolation = new double[constraints.length];
        double tolarableratio = 0.1;
        Object[] keys =  constraintDatabank.keySet().toArray();
        for(int i =0 ; i<keys.length;i++)
        {
            double upperLimit = constraintDatabank.get((Nutrition)keys[i]).getUpperLimit();
            double lowerLimit = constraintDatabank.get((Nutrition)keys[i]).getLowerLimit();

            if((constraintsMap.get(keys[i]) >= lowerLimit) && (constraintsMap.get(keys[i])<= upperLimit))
            {

                constraintViolation[i] = 0;

            }
            else if(constraintsMap.get(keys[i]) <= lowerLimit)
            {
                constraintViolation[i] = constraintsMap.get(keys[i]) - lowerLimit;
            }
            else
            {
                constraintViolation[i] = upperLimit - constraintsMap.get(keys[i]) ;
                if(solution== null)
                    System.out.print("");
                else if (keys[i] == null)
                    System.out.print("");
                else if(constraintViolation == null)
                    System.out.print("");
                if(solution.getConstaintViolationMap() == null)
                    System.out.print("");


            }
        }




        for (int i = 0; i < keys.length; i++) {

            if (constraintViolation[i] < 0.0) {
                Double[] constraintArray = new Double[2];
                 double ratio = constraintViolation[i] / (constraintDatabank.get(keys[i]).getUpperLimit() - constraintDatabank.get(keys[i]).getLowerLimit() );
if(ratio<0)
    ratio = ratio * -1;
                number += (int) ratio *constraintDatabank.get(keys[i]).getSignificance();

;                // This is exceeding constraint problem.
                total +=constraintViolation[i];
                constraintArray[0] = (int) ratio *constraintDatabank.get(keys[i]).getSignificance();
                constraintArray[1] = constraintViolation[i];
                solution.getConstaintViolationMap().put((Nutrition)keys[i],constraintArray);

            }



        }
         solution.setOverallConstraintViolation(total);
//        if(number<13)
//            System.out.println("13 ten kucuk var");
        solution.setNumberOfViolatedConstraint(number);

    }
}



