package menuplanner;

import Models.Food;
import Models.Nutrition;
import jmetal.core.Operator;
import jmetal.core.Solution;
import jmetal.core.SolutionSet;
import jmetal.core.Variable;
import jmetal.encodings.variable.Binary;
import jmetal.util.JMException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by BurakMac on 29/05/15.
 */
public class RepairConstraint  {


    public RepairConstraint()
    {

    }
    public Solution repair(Solution solution) {
        HashMap<Nutrition, Double[]> violatedConstraints = (HashMap) solution.getConstaintViolationMap();
        Object[] key =  violatedConstraints.keySet().toArray();
        Nutrition mostViolatedKey = null;
        double temp = 0;
        double tempOverallConstraintViolation = 0;
        int randomindex = new Random().nextInt(violatedConstraints.size()+1);
        mostViolatedKey = (Nutrition)key[randomindex];
        temp = violatedConstraints.get(key[randomindex])[0];

//        for (int i = 0; i < violatedConstraints.size(); i++) {
//            if (temp < violatedConstraints.get(key[i])[0]) {
//                temp = violatedConstraints.get(key[i])[0];
//                mostViolatedKey = (Nutrition)key[i];
//            }
//        }
        System.out.println("I found nasty constraint, Dont worry I've got undercontrol");
tempOverallConstraintViolation = violatedConstraints.get(mostViolatedKey)[1];
        boolean isEnough = false;
        if (mostViolatedKey != null) {
//            while (!isEnough) {
                for (int j = 0; j < solution.getDecisionVariables().length; j++) {
                    if ("1".compareTo(solution.getDecisionVariables()[j].toString()) == 0) {


                        if(tempOverallConstraintViolation <0)
                        {
                            Food food = Knapsack.getDatabank().get(j);

                            tempOverallConstraintViolation += food.getIngredient().get(mostViolatedKey);
System.out.println("Seeking "+j+" th decision");

//                            (Binary)solution.getDecisionVariables()[j].
                            Binary solutionBinary = (Binary) solution.getDecisionVariables()[j];
                            solutionBinary.setIth(0,false);
                        }

                        else if(tempOverallConstraintViolation >= 0)
                        {
//                            isEnough = true;
                            System.out.println("Nearly I repaired them.");

                            int violatationNum = solution.getNumberOfViolatedConstraint();
                            double totalviolation = solution.getOverallConstraintViolation();
                            Double constraintViolationAmount[] = solution.getConstaintViolationMap().get(mostViolatedKey);
                            totalviolation += constraintViolationAmount[1];
                            violatationNum -= constraintViolationAmount[0];
                            solution.setOverallConstraintViolation(totalviolation);
                            solution.setNumberOfViolatedConstraint(violatationNum);
                            break;
                        }
                    }
                }
//            }

        }
        System.out.println("I calculated objective function again");

        Variable[] variables = solution.getDecisionVariables();
        double cost=0,interest=0;
        for (int var = 0; var < variables.length; var++) {
            if(variables[var].toString().compareTo("1") == 0){
                //targetcal = targetcal + databank.get(var).getEnergy();
                cost = cost+ Knapsack.getDatabank().get(var).getCost();
                interest = interest + Knapsack.getDatabank().get(var).getRate();


            }
        } // for

        solution.setObjective(0, cost);
        solution.setObjective(1, -1*interest);
        System.out.println("I repaired constraint Everythings ok.");

        return solution;
    }


}
