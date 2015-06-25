package Models;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.lang.model.type.NullType;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by burak on 08/03/15.
 */
public class Food implements Comparable<Food>{

    private String foodName;
    private HashMap<Nutrition,Double> ingredient;
    private HashMap<Nutrition,String> ingredient_description;

    private double cost;

public Food()
{


}

    @Override
    public String toString() {
        String returnedVal = "";
        returnedVal += foodName;
        returnedVal += " -> ";
        returnedVal += " Energy = ";
        returnedVal +=  getIngredient().get(Nutrition.Energy);
        return  returnedVal;
    }


    public int compareTo(Food o) {
        if (this.getIngredient().get(Nutrition.Energy)>o.getIngredient().get(Nutrition.Energy))
        {
            return -1;
        }
        else if(this.getIngredient().get(Nutrition.Energy)<o.getIngredient().get(Nutrition.Energy))
        {
            return 1;
        }
        return 0;
    }

    private double rate;



    public Food(Row row)
    {
        Iterator<Cell> cellIterator = row.cellIterator();
        while(cellIterator.hasNext())
        {
            Cell cell = cellIterator.next();
            switch (cell.getColumnIndex()) {
                case 0:
                    //String tempdata = cell.getStringCellValue();
                    //  System.out.print(tempdata + " created.");
                    break;
                case 1:
                    setFoodName(cell.getStringCellValue());
                    break;
                case 2:
                    this.getIngredient().put(Nutrition.Water,cell.getNumericCellValue());
                    break;
                case 3:
                    this.getIngredient().put(Nutrition.Energy,cell.getNumericCellValue());
                    break;
                case 4:
                    this.getIngredient().put(Nutrition.Protein, cell.getNumericCellValue());
                    break;
                case 5:
                    this.getIngredient().put(Nutrition.Lipid, cell.getNumericCellValue());
                    break;
                case 6:
                    this.getIngredient().put(Nutrition.Ash, cell.getNumericCellValue());
                    break;
                case 7:
                    this.getIngredient().put(Nutrition.Carbohydrate, cell.getNumericCellValue());
                    break;
                case 8:
                    this.getIngredient().put(Nutrition.Fiber, cell.getNumericCellValue());
                    break;
                case 9:
                    this.getIngredient().put(Nutrition.Sugar, cell.getNumericCellValue());
                    break;
                case 10:
                    this.getIngredient().put(Nutrition.Calcium, cell.getNumericCellValue());
                    break;
                case 11:
                    this.getIngredient().put(Nutrition.Iron, cell.getNumericCellValue());
                    break;
                case 12:
                    this.getIngredient().put(Nutrition.Magnesium, cell.getNumericCellValue());
                    break;
                case 13:
                    this.getIngredient().put(Nutrition.Phosphorus, cell.getNumericCellValue());
                    break;
                case 14:
                    this.getIngredient().put(Nutrition.Potassium, cell.getNumericCellValue());
                    break;
                case 15:
                    this.getIngredient().put(Nutrition.Sodium, cell.getNumericCellValue());
                    break;
                case 16:
                    this.getIngredient().put(Nutrition.Zinc, cell.getNumericCellValue());
                    break;
                case 17:
                    this.getIngredient().put(Nutrition.Copper, cell.getNumericCellValue());
                    break;
                case 18:
                    this.getIngredient().put(Nutrition.Manganese, cell.getNumericCellValue());
                    break;
                case 19:
                    this.getIngredient().put(Nutrition.Selenium, cell.getNumericCellValue());
                    break;
                case 20:
                    this.getIngredient().put(Nutrition.VitaminC, cell.getNumericCellValue());
                    break;
                case 21:
                    this.getIngredient().put(Nutrition.Thiamine, cell.getNumericCellValue());
                    break;
                case 22:
                    this.getIngredient().put(Nutrition.Riboflavin, cell.getNumericCellValue());
                    break;
                case 23:
                    this.getIngredient().put(Nutrition.Niacin, cell.getNumericCellValue());
                    break;
                case 24:
                    this.getIngredient().put(Nutrition.PantoAcid, cell.getNumericCellValue());
                    break;
                case 25:
                    this.getIngredient().put(Nutrition.VitaminB6, cell.getNumericCellValue());
                    break;
                case 26:
                    this.getIngredient().put(Nutrition.FolateTot, cell.getNumericCellValue());
                    break;
                case 27:
                    this.getIngredient().put(Nutrition.FolicAcid, cell.getNumericCellValue());
                    break;
                case 28:
                    this.getIngredient().put(Nutrition.FoodFolate, cell.getNumericCellValue());
                    break;
                case 29:
                    this.getIngredient().put(Nutrition.FloateDFE, cell.getNumericCellValue());
                    break;
                case 30:
                    this.getIngredient().put(Nutrition.CholineTot, cell.getNumericCellValue());
                    break;
                case 31:
                    this.getIngredient().put(Nutrition.VitaminB12, cell.getNumericCellValue());
                    break;
                case 32:
                    this.getIngredient().put(Nutrition.VitaminA_IU, cell.getNumericCellValue());
                    break;
                case 33:
                    this.getIngredient().put(Nutrition.VitaminA_RAE, cell.getNumericCellValue());
                    break;
                case 34:
                    this.getIngredient().put(Nutrition.Retinol, cell.getNumericCellValue());
                    break;
                case 35:
                    this.getIngredient().put(Nutrition.AlphaCarot, cell.getNumericCellValue());
                    break;
                case 36:
                    this.getIngredient().put(Nutrition.BetaCarot, cell.getNumericCellValue());
                    break;
                case 37:
                    this.getIngredient().put(Nutrition.BetaCrypt, cell.getNumericCellValue());
                    break;
                case 38:
                    this.getIngredient().put(Nutrition.Lycopene, cell.getNumericCellValue());
                    break;
                case 39:
                    this.getIngredient().put(Nutrition.LutZEA, cell.getNumericCellValue());
                    break;
                case 40:
                    this.getIngredient().put(Nutrition.VitaminE, cell.getNumericCellValue());
                    break;
                case 41:
                    this.getIngredient().put(Nutrition.VitaminD, cell.getNumericCellValue());
                    break;
                case 42:
// VitaminD_IU eksik
                   break;
                case 43:
                    this.getIngredient().put(Nutrition.VitaminK, cell.getNumericCellValue());
                    break;
                case 44:
                    this.getIngredient().put(Nutrition.FatSaturated, cell.getNumericCellValue());
                    break;
                case 45:
                    this.getIngredient().put(Nutrition.FatMono, cell.getNumericCellValue());
                    break;
                case 46:
                    this.getIngredient().put(Nutrition.FatPoly, cell.getNumericCellValue());
                    break;
                case 47:
                    this.getIngredient().put(Nutrition.Cholesterol, cell.getNumericCellValue());
                    break;
                case 48:
                    this.getIngredient().put(Nutrition.GmWt_1, cell.getNumericCellValue());
                    break;
                case 49:
                    this.getIngredient_description().put(Nutrition.GmWt_1_Desc1, cell.getStringCellValue());
                    break;
                case 50:
                    this.getIngredient().put(Nutrition.GmWt_2, cell.getNumericCellValue());
                    break;
                case 51:
                    this.getIngredient_description().put(Nutrition.GmWt_2_Desc2, cell.getStringCellValue());
                    break;
                case 52:
                    this.getIngredient().put(Nutrition.RefusePct, cell.getNumericCellValue());
                    break;
                default:
                    System.out.println(cell.getRowIndex() +"th row has undefined cell number which is "+cell.getColumnIndex());
                    break;
            }
        }

    }

    public double getDataDouble(Cell cell)
    {
        double returnedval = -1;
        switch (cell.getCellType())
        {
            case Cell.CELL_TYPE_NUMERIC:
            {
                returnedval =  cell.getNumericCellValue();

            }
            case Cell.CELL_TYPE_STRING:
            {
                String temp = cell.getStringCellValue();
                DecimalFormat decimalFormat = new DecimalFormat();
                DecimalFormatSymbols symbols  = new DecimalFormatSymbols();

                symbols.setDecimalSeparator(',');
                decimalFormat.setDecimalFormatSymbols(symbols);


                //NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
                Double converted = -1.0 ;
                try {
                    //  converted=   nf.parse(temp).doubleValue();
                    converted =  decimalFormat.parse(temp).doubleValue();
                }
                catch (ParseException pe)
                {
                    System.out.println(pe.toString());
                }
                returnedval =   converted;
            }
        }
        return returnedval;
    }
    public String getFoodName() {
        return foodName;
    }



    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public HashMap<Nutrition, Double> getIngredient() {
        return ingredient;
    }

    public void setIngredient(HashMap<Nutrition, Double> ingredient) {
        this.ingredient = ingredient;
    }

    public HashMap<Nutrition, String> getIngredient_description() {
        return ingredient_description;
    }

    public void setIngredient_description(HashMap<Nutrition, String> ingredient_description) {
        this.ingredient_description = ingredient_description;
    }
}
