package Models;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by burak on 08/03/15.
 */
public class Food implements Comparable<Food>{
    private String foodName;
    private double water;
    private double energy;
    private double protein;
    private double lipid;
    private double ash;

    @Override
    public String toString() {
        String returnedVal = "";
        returnedVal += foodName;
        returnedVal += " -> ";
        returnedVal += " Energy = ";
        returnedVal +=  getEnergy();
        return  returnedVal;
    }

    @Override
    public int compareTo(Food o) {
        if (this.getEnergy()>o.getEnergy())
        {
            return -1;
        }
        else if(this.getEnergy()<o.getEnergy())
        {
            return 1;
        }
        return 0;
    }

    private double carbonhydrt;
    private double fiber;  // turkish mean lif
    private double sugar;
    private double calcium;
    private double iron;
    private double magnesium;
    private double phosphorus;
    private double potassium;
    private double sodium;
    private double zinc;
    private double copper;
    private double manganese;
    private double selenium;
    private double vitaminC;
    private double thiamin;
    private double riboflavin;
    private double niacin;
    private double pantoAcid;
    private double vitaminB6;
    private double folateTot;
    private double folicAcid;
    private double food_folate;
    private double floateDFE;
    private double cholineTot;
    private double vitaminB12;
    private double vitaminA_IU;
    private double vitaminA_RAE;
    private double retinol;
    private double aphaCarot;
    private double betaCarot;
    private double betaCrypt;
    private double lycopene;
    private double lutZea;
    private double vitaminE;
    private double vitaminD;
    private double vitaminD_IU;
    private double vitaminK;
    private double faSat;
    private double faMono;
    private double faPoly;
    private double cholestrl;
    private double gmWt_1;
    private String gmWt_Desc1;
    private double gmWt_2;
    private String gmWt_Desc2;
    private double refusePct;



    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getLipid() {
        return lipid;
    }

    public void setLipid(double lipid) {
        this.lipid = lipid;
    }

    public double getAsh() {
        return ash;
    }

    public void setAsh(double ash) {
        this.ash = ash;
    }

    public double getCarbonhydrt() {
        return carbonhydrt;
    }

    public void setCarbonhydrt(double carbonhydrt) {
        this.carbonhydrt = carbonhydrt;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getZinc() {
        return zinc;
    }

    public void setZinc(double zinc) {
        this.zinc = zinc;
    }

    public double getCopper() {
        return copper;
    }

    public void setCopper(double copper) {
        this.copper = copper;
    }

    public double getManganese() {
        return manganese;
    }

    public void setManganese(double manganese) {
        this.manganese = manganese;
    }

    public double getSelenium() {
        return selenium;
    }

    public void setSelenium(double selenium) {
        this.selenium = selenium;
    }

    public double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }

    public double getThiamin() {
        return thiamin;
    }

    public void setThiamin(double thiamin) {
        this.thiamin = thiamin;
    }

    public double getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(double riboflavin) {
        this.riboflavin = riboflavin;
    }

    public double getNiacin() {
        return niacin;
    }

    public void setNiacin(double niacin) {
        this.niacin = niacin;
    }

    public double getPantoAcid() {
        return pantoAcid;
    }

    public void setPantoAcid(double pantoAcid) {
        this.pantoAcid = pantoAcid;
    }

    public double getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(double vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public double getFolateTot() {
        return folateTot;
    }

    public void setFolateTot(double folateTot) {
        this.folateTot = folateTot;
    }

    public double getFolicAcid() {
        return folicAcid;
    }

    public void setFolicAcid(double folicAcid) {
        this.folicAcid = folicAcid;
    }

    public double getFood_folate() {
        return food_folate;
    }

    public void setFood_folate(double food_folate) {
        this.food_folate = food_folate;
    }

    public double getFloateDFE() {
        return floateDFE;
    }

    public void setFloateDFE(double floateDFE) {
        this.floateDFE = floateDFE;
    }

    public double getCholineTot() {
        return cholineTot;
    }

    public void setCholineTot(double cholineTot) {
        this.cholineTot = cholineTot;
    }

    public double getVitaminB12() {
        return vitaminB12;
    }

    public void setVitaminB12(double vitaminB12) {
        this.vitaminB12 = vitaminB12;
    }

    public double getVitaminA_IU() {
        return vitaminA_IU;
    }

    public void setVitaminA_IU(double vitaminA_IU) {
        this.vitaminA_IU = vitaminA_IU;
    }

    public double getVitaminA_RAE() {
        return vitaminA_RAE;
    }

    public void setVitaminA_RAE(double vitaminA_RAE) {
        this.vitaminA_RAE = vitaminA_RAE;
    }

    public double getRetinol() {
        return retinol;
    }

    public void setRetinol(double retinol) {
        this.retinol = retinol;
    }

    public double getAphaCarot() {
        return aphaCarot;
    }

    public void setAphaCarot(double aphaCarot) {
        this.aphaCarot = aphaCarot;
    }

    public double getBetaCarot() {
        return betaCarot;
    }

    public void setBetaCarot(double betaCarot) {
        this.betaCarot = betaCarot;
    }

    public double getBetaCrypt() {
        return betaCrypt;
    }

    public void setBetaCrypt(double betaCrypt) {
        this.betaCrypt = betaCrypt;
    }

    public double getLycopene() {
        return lycopene;
    }

    public void setLycopene(double lycopene) {
        this.lycopene = lycopene;
    }

    public double getLutZea() {
        return lutZea;
    }

    public void setLutZea(double lutZea) {
        this.lutZea = lutZea;
    }

    public double getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(double vitaminE) {
        this.vitaminE = vitaminE;
    }

    public double getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(double vitaminD) {
        this.vitaminD = vitaminD;
    }

    public double getVitaminD_IU() {
        return vitaminD_IU;
    }

    public void setVitaminD_IU(double vitaminD_IU) {
        this.vitaminD_IU = vitaminD_IU;
    }

    public double getVitaminK() {
        return vitaminK;
    }

    public void setVitaminK(double vitaminK) {
        this.vitaminK = vitaminK;
    }

    public double getFaSat() {
        return faSat;
    }

    public void setFaSat(double faSat) {
        this.faSat = faSat;
    }

    public double getFaMono() {
        return faMono;
    }

    public void setFaMono(double faMono) {
        this.faMono = faMono;
    }

    public double getFaPoly() {
        return faPoly;
    }

    public void setFaPoly(double faPoly) {
        this.faPoly = faPoly;
    }

    public double getCholestrl() {
        return cholestrl;
    }

    public void setCholestrl(double cholestrl) {
        this.cholestrl = cholestrl;
    }

    public double getGmWt_1() {
        return gmWt_1;
    }

    public void setGmWt_1(double gmWt_1) {
        this.gmWt_1 = gmWt_1;
    }

    public String getGmWt_Desc1() {
        return gmWt_Desc1;
    }

    public void setGmWt_Desc1(String gmWt_Desc1) {
        this.gmWt_Desc1 = gmWt_Desc1;
    }

    public double getGmWt_2() {
        return gmWt_2;
    }

    public void setGmWt_2(double gmWt_2) {
        this.gmWt_2 = gmWt_2;
    }

    public String getGmWt_Desc2() {
        return gmWt_Desc2;
    }

    public void setGmWt_Desc2(String gmWt_Desc2) {
        this.gmWt_Desc2 = gmWt_Desc2;
    }

    public double getRefusePct() {
        return refusePct;
    }

    public void setRefusePct(double refusePct) {
        this.refusePct = refusePct;
    }

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
                    setWater(cell.getNumericCellValue());
                    break;
                case 3:
                    setEnergy(cell.getNumericCellValue());
                    break;
                case 4:
                    setProtein(cell.getNumericCellValue());
                    break;
                case 5:
                    setLipid(cell.getNumericCellValue());
                    break;
                case 6:
                    setAsh(cell.getNumericCellValue());
                    break;
                case 7:
                    setCarbonhydrt(cell.getNumericCellValue());
                    break;
                case 8:
                    setFiber(cell.getNumericCellValue());
                    break;
                case 9:
                    setSugar(cell.getNumericCellValue());
                    break;
                case 10:
                    setCalcium(cell.getNumericCellValue());
                    break;
                case 11:
                    setIron(cell.getNumericCellValue());
                    break;
                case 12:
                    setMagnesium(cell.getNumericCellValue());
                    break;
                case 13:
                    setPhosphorus(cell.getNumericCellValue());
                    break;
                case 14:
                    setPotassium(cell.getNumericCellValue());
                    break;
                case 15:
                    setSodium(cell.getNumericCellValue());
                    break;
                case 16:
                    setZinc(cell.getNumericCellValue());
                    break;
                case 17:
                    setCopper(cell.getNumericCellValue());
                    break;
                case 18:
                    setManganese(cell.getNumericCellValue());
                    break;
                case 19:
                    setSelenium(cell.getNumericCellValue());
                    break;
                case 20:
                    setVitaminC(cell.getNumericCellValue());
                    break;
                case 21:
                    setThiamin(cell.getNumericCellValue());
                    break;
                case 22:
                    setRiboflavin(cell.getNumericCellValue());
                    break;
                case 23:
                    setNiacin(cell.getNumericCellValue());
                    break;
                case 24:
                    setPantoAcid(cell.getNumericCellValue());
                    break;
                case 25:
                    setVitaminB6(cell.getNumericCellValue());
                    break;
                case 26:
                    setFolateTot(cell.getNumericCellValue());
                    break;
                case 27:
                    setFolicAcid(cell.getNumericCellValue());
                    break;
                case 28:
                    setFood_folate(cell.getNumericCellValue());
                    break;
                case 29:
                    setFloateDFE(cell.getNumericCellValue());
                    break;
                case 30:
                    setCholineTot(cell.getNumericCellValue());
                    break;
                case 31:
                    setVitaminB12(cell.getNumericCellValue());
                    break;
                case 32:
                    setVitaminA_IU(cell.getNumericCellValue());
                    break;
                case 33:
                    setVitaminA_RAE(cell.getNumericCellValue());
                    break;
                case 34:
                    setRetinol(cell.getNumericCellValue());
                    break;
                case 35:
                    setAphaCarot(cell.getNumericCellValue());
                    break;
                case 36:
                    setBetaCarot(cell.getNumericCellValue());
                    break;
                case 37:
                    setBetaCrypt(cell.getNumericCellValue());
                    break;
                case 38:
                    setLycopene(cell.getNumericCellValue());
                    break;
                case 39:
                    setLutZea(cell.getNumericCellValue());
                    break;
                case 40:
                    setVitaminE(cell.getNumericCellValue());
                    break;
                case 41:
                    setVitaminD(cell.getNumericCellValue());
                    break;
                case 42:
                    setVitaminD_IU(cell.getNumericCellValue());
                    break;
                case 43:
                    setVitaminK(cell.getNumericCellValue());
                    break;
                case 44:
                    setFaSat(cell.getNumericCellValue());
                    break;
                case 45:
                    setFaMono(cell.getNumericCellValue());
                    break;
                case 46:
                    setFaPoly(cell.getNumericCellValue());
                    break;
                case 47:
                    setCholestrl(cell.getNumericCellValue());
                    break;
                case 48:
                    setGmWt_1(cell.getNumericCellValue());
                    break;
                case 49:
                    setGmWt_Desc1(cell.getStringCellValue());
                    break;
                case 50:
                    setGmWt_2(cell.getNumericCellValue());
                    break;
                case 51:
                    setGmWt_Desc2(cell.getStringCellValue());
                    break;
                case 52:
                    setRefusePct(cell.getNumericCellValue());
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

    public void  AddAverage(Food food)
    {
        this.setEnergy((this.getEnergy() + food.getEnergy()));
        this.setRefusePct((this.getRefusePct() + food.getRefusePct()) );
        this.setGmWt_2((this.getGmWt_2() + food.getGmWt_2()) );
        this.setGmWt_1((this.getGmWt_1() + food.getGmWt_1()) );
        this.setAphaCarot((this.getAphaCarot() + food.getAphaCarot()) );
        this.setAsh((this.getAsh() + food.getAsh()) );
        this.setBetaCarot((this.getBetaCarot() + food.getBetaCarot() ));
        this.setBetaCrypt((this.getBetaCrypt() + food.getBetaCrypt()) );
        this.setCalcium((this.getCalcium() + food.getCalcium()) );
        this.setCarbonhydrt((this.getCarbonhydrt() + food.getCarbonhydrt()) );
        this.setCholineTot((this.getCholineTot() + food.getCholineTot()) );
        this.setCholestrl((this.getCholestrl() + food.getCholestrl()) );
        this.setCopper((this.getCopper() + food.getCopper()) );
        this.setFaMono((this.getFaPoly() + food.getFaMono()) );
        this.setFaSat((this.getFaSat() + food.getFaSat()) );
        this.setFiber((this.getFloateDFE() + food.getFloateDFE()) );
        this.setFolateTot((this.getFolateTot() + food.getFolateTot()) );
        this.setFloateDFE((this.getFloateDFE() + food.getFloateDFE()) );
        this.setFaPoly((this.getFaPoly() + food.getFaPoly()) );
        this.setFolicAcid((this.getFolicAcid() + food.getFolicAcid()) );
        this.setIron((this.getIron() + food.getIron() ));
        this.setFood_folate((this.getFood_folate() + food.getFood_folate()) );
        this.setLipid((this.getLipid() + food.getLipid()) );
        this.setLutZea((this.getLutZea() + food.getLutZea()) );
        this.setLycopene((this.getLycopene() + food.getLycopene()) );
        this.setMagnesium((this.getMagnesium() + food.getMagnesium()));
        this.setManganese((this.getManganese() + food.getManganese()));
        this.setNiacin((this.getNiacin() + food.getNiacin()));
        this.setPantoAcid((this.getPantoAcid() + food.getPantoAcid()));
        this.setPhosphorus((this.getPhosphorus() + food.getPhosphorus()));
        this.setProtein((this.getProtein() + food.getProtein()));
        this.setRiboflavin((this.getRiboflavin() + food.getRiboflavin()));
        this.setSelenium((this.getSelenium() + food.getSelenium()));
        this.setSodium((this.getSodium() + food.getSodium()));
        this.setSugar((this.getSugar() + food.getSugar()));
        this.setRetinol((this.getRetinol() + food.getRetinol()) );
        this.setThiamin((this.getThiamin() + food.getThiamin()));
        this.setVitaminA_IU((this.getVitaminA_IU() + food.getVitaminA_IU()));
        this.setVitaminK((this.getVitaminK() + food.getVitaminK()));
        this.setVitaminD_IU((this.getVitaminD_IU() + food.getVitaminD_IU()));
        this.setVitaminD((this.getVitaminD() + food.getVitaminD()));
        this.setVitaminE((this.getVitaminE() + food.getVitaminE()));
        this.setVitaminD((this.getVitaminD() + food.getVitaminD()));
        this.setVitaminA_RAE((this.getVitaminA_RAE() + food.getVitaminA_RAE()));
        this.setVitaminB12((this.getVitaminB12() + food.getVitaminB12()));
        this.setVitaminB6((this.getVitaminB6() + food.getVitaminB6()));
        this.setVitaminC((this.getVitaminC() + food.getVitaminC()));
        this.setWater((this.getWater() + food.getWater()));
        this.setZinc((this.getZinc() + food.getZinc()));


    }

    public void  divideThemAll( int cointerSize)
    {
        this.setEnergy((this.getEnergy())/cointerSize);
        this.setRefusePct((this.getRefusePct()) / cointerSize);
        this.setGmWt_2((this.getGmWt_2() ) /cointerSize);
        this.setGmWt_1((this.getGmWt_1()) /cointerSize);
        this.setAphaCarot((this.getAphaCarot() ) /cointerSize);
        this.setAsh((this.getAsh() ) /cointerSize);
        this.setBetaCarot((this.getBetaCarot()  /cointerSize));
        this.setBetaCrypt((this.getBetaCrypt() ) /cointerSize);
        this.setCalcium((this.getCalcium()) /cointerSize);
        this.setCarbonhydrt((this.getCarbonhydrt() ) /cointerSize);
        this.setCholineTot((this.getCholineTot()) /cointerSize);
        this.setCholestrl((this.getCholestrl() ) /cointerSize);
        this.setCopper((this.getCopper() ) /cointerSize);
        this.setFaMono((this.getFaPoly() ) /cointerSize);
        this.setFaSat((this.getFaSat()) /cointerSize);
        this.setFiber((this.getFloateDFE() ) /cointerSize);
        this.setFolateTot((this.getFolateTot()) /cointerSize);
        this.setFloateDFE((this.getFloateDFE()) /cointerSize);
        this.setFaPoly((this.getFaPoly() ) /cointerSize);
        this.setFolicAcid((this.getFolicAcid()) /cointerSize);
        this.setIron((this.getIron() /cointerSize));
        this.setFood_folate((this.getFood_folate()) /cointerSize);
        this.setLipid((this.getLipid()) /cointerSize);
        this.setLutZea((this.getLutZea() ) /cointerSize);
        this.setLycopene((this.getLycopene() ) /cointerSize);
        this.setMagnesium((this.getMagnesium() )/cointerSize);
        this.setManganese((this.getManganese())/cointerSize);
        this.setNiacin((this.getNiacin() )/cointerSize);
        this.setPantoAcid((this.getPantoAcid() )/cointerSize);
        this.setPhosphorus((this.getPhosphorus() )/cointerSize);
        this.setProtein((this.getProtein() )/cointerSize);
        this.setRiboflavin((this.getRiboflavin() )/cointerSize);
        this.setSelenium((this.getSelenium() )/cointerSize);
        this.setSodium((this.getSodium() )/cointerSize);
        this.setSugar((this.getSugar() )/cointerSize);
        this.setRetinol((this.getRetinol() ) /cointerSize);
        this.setThiamin((this.getThiamin())/cointerSize);
        this.setVitaminA_IU((this.getVitaminA_IU() )/cointerSize);
        this.setVitaminK((this.getVitaminK() )/cointerSize);
        this.setVitaminD_IU((this.getVitaminD_IU() )/cointerSize);
        this.setVitaminD((this.getVitaminD() )/cointerSize);
        this.setVitaminE((this.getVitaminE() )/cointerSize);
        this.setVitaminD((this.getVitaminD() )/cointerSize);
        this.setVitaminA_RAE((this.getVitaminA_RAE() )/cointerSize);
        this.setVitaminB12((this.getVitaminB12() )/cointerSize);
        this.setVitaminB6((this.getVitaminB6() )/cointerSize);
        this.setVitaminC((this.getVitaminC() )/cointerSize);
        this.setWater((this.getWater() )/cointerSize);
        this.setZinc((this.getZinc() )/cointerSize);


    }
    public void  multiplyThemAll( int coefficient)
    {
        this.setEnergy((this.getEnergy())*coefficient);
        this.setRefusePct((this.getRefusePct()) *coefficient);
        this.setGmWt_2((this.getGmWt_2() ) *coefficient);
        this.setGmWt_1((this.getGmWt_1()) *coefficient);
        this.setAphaCarot((this.getAphaCarot() ) *coefficient);
        this.setAsh((this.getAsh() ) *coefficient);
        this.setBetaCarot((this.getBetaCarot()  *coefficient));
        this.setBetaCrypt((this.getBetaCrypt() ) *coefficient);
        this.setCalcium((this.getCalcium()) *coefficient);
        this.setCarbonhydrt((this.getCarbonhydrt() ) *coefficient);
        this.setCholineTot((this.getCholineTot()) *coefficient);
        this.setCholestrl((this.getCholestrl() ) *coefficient);
        this.setCopper((this.getCopper() ) *coefficient);
        this.setFaMono((this.getFaPoly() ) *coefficient);
        this.setFaSat((this.getFaSat()) *coefficient);
        this.setFiber((this.getFloateDFE() ) *coefficient);
        this.setFolateTot((this.getFolateTot()) *coefficient);
        this.setFloateDFE((this.getFloateDFE()) *coefficient);
        this.setFaPoly((this.getFaPoly() ) *coefficient);
        this.setFolicAcid((this.getFolicAcid()) *coefficient);
        this.setIron((this.getIron() *coefficient));
        this.setFood_folate((this.getFood_folate()) *coefficient);
        this.setLipid((this.getLipid()) *coefficient);
        this.setLutZea((this.getLutZea() ) *coefficient);
        this.setLycopene((this.getLycopene() ) *coefficient);
        this.setMagnesium((this.getMagnesium() )*coefficient);
        this.setManganese((this.getManganese())*coefficient);
        this.setNiacin((this.getNiacin() )*coefficient);
        this.setPantoAcid((this.getPantoAcid() )*coefficient);
        this.setPhosphorus((this.getPhosphorus() )*coefficient);
        this.setProtein((this.getProtein() )*coefficient);
        this.setRiboflavin((this.getRiboflavin() )*coefficient);
        this.setSelenium((this.getSelenium() )*coefficient);
        this.setSodium((this.getSodium() )*coefficient);
        this.setSugar((this.getSugar() )*coefficient);
        this.setRetinol((this.getRetinol() ) *coefficient);
        this.setThiamin((this.getThiamin())*coefficient);
        this.setVitaminA_IU((this.getVitaminA_IU() )*coefficient);
        this.setVitaminK((this.getVitaminK() )*coefficient);
        this.setVitaminD_IU((this.getVitaminD_IU() )*coefficient);
        this.setVitaminD((this.getVitaminD() )*coefficient);
        this.setVitaminE((this.getVitaminE() )*coefficient);
        this.setVitaminD((this.getVitaminD() )*coefficient);
        this.setVitaminA_RAE((this.getVitaminA_RAE() )*coefficient);
        this.setVitaminB12((this.getVitaminB12() )*coefficient);
        this.setVitaminB6((this.getVitaminB6() )*coefficient);
        this.setVitaminC((this.getVitaminC() )*coefficient);
        this.setWater((this.getWater() )*coefficient);
        this.setZinc((this.getZinc() )*coefficient);


    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
