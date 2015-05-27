package menuplanner;

import Models.Food;
import database.MongoManager;
import jmetal.core.Problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import jmetal.core.Solution;
import jmetal.core.SolutionType;
import jmetal.core.Variable;

import jmetal.encodings.solutionType.BinarySolutionType;
import jmetal.encodings.solutionType.IntSolutionType;

import jmetal.util.JMException;

import javax.sound.midi.SysexMessage;

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
    public static ArrayList<Food> databank;


//    private int[] length = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    double storageSize=5000;

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

    public Knapsack(String SolutionType, Integer numberofVariables, Integer numberofObjectives, Integer numberofConstraints)
    {
//        for (int i =0; i<length.length;i++)
//            length[i]=1;
//        weightGlobal = readTextFile("/Users/burak/Bitirme Odevi/menuplanner/weights.txt")    ;
//        values = readTextFile("/Users/burak/Bitirme Odevi/menuplanner/values.txt")    ;
//        rate = readTextFile("/Users/burak/Bitirme Odevi/menuplanner/ratings.txt")    ;
        MongoManager mongoManager =new MongoManager();
        databank = mongoManager.getAllElements();
                          length = new int[databank.size()];

        for(int i=0; i<databank.size();i++)
        {
            length[i] = 1;
        }
        length_=length;
        //numberOfVariables_= numberofVariables;
        numberOfVariables_ = databank.size();
        length = new int[numberOfVariables_];
        lowerLimit_ = new double[numberOfVariables_];
        upperLimit_ = new double[numberOfVariables_];
        numberOfConstraints_ = numberofConstraints;
        numberOfObjectives_ = numberofObjectives;
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
                interest = interest + databank.get(var).getRate();


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
        int number = 0;
        double total = 0.0;
        double[] constraints = new double[numberOfConstraints_];
        for (int var = 0; var < this.numberOfVariables_; var++) {
            if(variables[var].toString().compareTo("1") == 0) {

               constraints[0] = constraints[0] +  databank.get(var).getCalcium();
                constraints[1] = constraints[1] + databank.get(var).getProtein();
                constraints[2] = constraints[2] + databank.get(var).getCarbonhydrt();
                constraints[3] = constraints[3] + databank.get(var).getVitaminA_IU();
                constraints[4] = constraints[4] + databank.get(var).getVitaminC();
                constraints[5] = constraints[5] + databank.get(var).getVitaminD();
                constraints[6] = constraints[6] + databank.get(var).getVitaminE();
                constraints[7] = constraints[7] + databank.get(var).getThiamin();
                constraints[8] = constraints[8] + databank.get(var).getRiboflavin();
                constraints[9] = constraints[9] + databank.get(var).getNiacin();
                constraints[10] = constraints[10] + databank.get(var).getVitaminB6();
//                constraints[11] = constraints[11]  + databank.get(var).getFolateTot();
                constraints[11] = constraints[11] + databank.get(var).getVitaminB12();
                constraints[12] = constraints[12] + databank.get(var).getCopper();
//                constraints[14] = constraints[14] + databank.get(var).get(); Iodine
                constraints[13] = constraints[13] + databank.get(var).getIron();
                constraints[14] = constraints[14] + databank.get(var).getMagnesium();
//                constraints[17] = constraints[17] + databank.get(var).getM(); Molybdenum
                constraints[15] = constraints[15] + databank.get(var).getPhosphorus();
                constraints[16] = constraints [16] + databank.get(var).getSelenium();
                constraints[17] = constraints [17] + databank.get(var).getZinc();

            }
        }
             double[] maxVal  = {2500,650 ,120 ,3000 ,2000 ,100 ,1000 ,-2 ,-2    ,35  ,100   ,-2 ,10     ,45 ,350  ,4000 ,400 ,40};
        double[] recommendVal = {800 ,100 ,43  ,625  ,75   ,10  ,12   ,1  ,101   ,12  ,1.1   ,2  ,0.7    ,6  ,330  ,580  ,45  ,9.4};
        double[] constraintViolation = new double[18];
        double tolarance = 0.15;
        for(int j=0;j<maxVal.length;j++)
        {
if(maxVal[j] ==-2)
{
    if(constraints[j] < (recommendVal[j] - tolarance * recommendVal[j]))
    {
        constraintViolation[j] = constraints[j] - recommendVal[j];
    }
}
else{
            if (constraints[j] >= (recommendVal[j] - tolarance * recommendVal[j]) && (constraints[j] <= maxVal[j] + tolarance * recommendVal[j])) {
                constraintViolation[j] = 0;

            } else if (constraints[j] > recommendVal[j]) {
                constraintViolation[j] = recommendVal[j] - constraints[j];
            } else {
                constraintViolation[j] = constraints[j] - recommendVal[j];

            }
        }
        }

        for (int i = 0; i < maxVal.length; i++) {

            if (constraintViolation[i] < 0.0) {
                double ratio;
                if(maxVal[i]!=-2)
                ratio = constraintViolation[i] / ((recommendVal[i]+maxVal[i])/2);
                else
                ratio = constraintViolation[i] / ((recommendVal[i]));
                BigDecimal bd = new BigDecimal(ratio);
                bd = bd.setScale(0, RoundingMode.HALF_UP);
                number = number + (-1*bd.intValue())+1;
;                // This is exceeding constraint problem.
                number = number ;
                total +=constraintViolation[i];
            }



        }
         solution.setOverallConstraintViolation(total);
//        if(number<13)
//            System.out.println("13 ten kucuk var");
        solution.setNumberOfViolatedConstraint(number);

    }
}



