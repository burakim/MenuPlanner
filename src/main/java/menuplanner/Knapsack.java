package menuplanner;

import jmetal.core.Problem;
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
    private Double[] weightGlobal = {4.0,2.0,5.0,6.0,7.0,8.0,2.0,1.0,5.0};
    private Double[] values = {5.0,10.0,12.0,5.0,7.0,12.0,15.0,18.0,11.0};
    private Double[] rate = {3.0,4.0,2.0,1.0,0.0,2.0,4.0,5.0,2.0};
    private int[] length = {1,1,1,1,1,1,1,1,1};
    double storageSize=30;


    public Knapsack(SolutionType solutionType) {
        super(solutionType);
    }

    public Knapsack() {
        super();
    }

    public Knapsack(String SolutionType, Integer numberofVariables, Integer numberofObjectives, Integer numberofConstraints)
    {
        length_=length;
        numberOfVariables_= numberofVariables;
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
        double target=0.0;
        double interest = 0.0;
        for (int var = 0; var < numberOfVariables_; var++) {
            if(variables[var].toString().compareTo("1") == 0){
                target = target + values[var];
                interest = interest + rate[var];


            }
        } // for

        solution.setObjective(0, -1*target);
        solution.setObjective(1, -1*interest);
    }

    @Override
    public void evaluateConstraints(Solution solution) throws JMException {
        Variable[] variables = solution.getDecisionVariables();
        int number = 0;
        double total = 0.0;
        double[] weight = new double[numberOfConstraints_];
        double[] constraint = new double[numberOfConstraints_];

        for (int var = 0; var < this.numberOfVariables_; var++) {
           // System.out.println(variables[var]);
          //  System.out.println(variables[var].toString());
            if(variables[var].toString().compareTo("1") == 0) {

                weight[0] = weight[0] + weightGlobal[var];

            }
        }

            constraint[0] =   this.storageSize-weight[0];


        System.out.println(constraint[0]+ " = "+this.storageSize +" - "+weight[0]);
//        else if(this.storageSize>weight[0])
//        {
//            constraint[0] = weight[0] -this.storageSize;
//          //  constraint[0] = -1*constraint[0];
//        }
        for (int i = 0; i < this.numberOfConstraints_; i++) {
            if(solution.getObjective(0) == -95.0)
            {
                System.out.println("deneme");
            }
            if (constraint[0] < 0.0) {
                // This is exceeding constraint problem.
                number++;
                total +=constraint[i];
            }



        }
         solution.setOverallConstraintViolation(total);
        solution.setNumberOfViolatedConstraint(number);

    }
}



