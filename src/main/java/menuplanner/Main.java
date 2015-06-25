package menuplanner;
import jmetal.core.Algorithm;
import jmetal.core.Operator;

import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.metaheuristics.nsgaII.NSGAII;
import jmetal.metaheuristics.nsgaII.pNSGAII;
import jmetal.metaheuristics.spea2.SPAE2Parallel;
import jmetal.metaheuristics.spea2.SPEA2;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.problems.ProblemFactory;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import jmetal.util.parallel.IParallelEvaluator;
import jmetal.util.parallel.MultithreadedEvaluator;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Created by burak on 15/03/15.
 */
public class Main
{

    public static Logger logger_ ;      // Logger object
    public static FileHandler fileHandler_ ; // FileHandler object

    /**
     * @param args Command line arguments.
     * @throws jmetal.util.JMException
     * @throws java.io.IOException
     * @throws SecurityException
     * Usage: three options
     *      - jmetal.metaheuristics.nsgaII.NSGAII_main
     *      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName
     *      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName paretoFrontFile
     */
    public static void main(String [] args) throws
            JMException,
            SecurityException,
            IOException,
            ClassNotFoundException {
        Problem problem   ; // The problem to solve
        Algorithm algorithm ; // The algorithm to use
        Operator crossover ; // Crossover operator
        Operator  mutation  ; // Mutation operator
        Operator  selection ; // Selection operator

        HashMap parameters ; // Operator parameters

        QualityIndicator indicators ; // Object to get quality indicators

        // Logger object and file to store log messages
        logger_      = Configuration.logger_ ;
        fileHandler_ = new FileHandler("NSGAII_main.log");
        logger_.addHandler(fileHandler_) ;

        indicators = null ;
//        if (args.length == 1) {
////            Object [] params = {"Real"};
////            problem = (new ProblemFactory()).getProblem(args[0],params);
//        } // if
//        else if (args.length == 2) {
////            Object [] params = {"Real"};
////            problem = (new ProblemFactory()).getProblem(args[0],params);
////            indicators = new QualityIndicator(problem, args[1]) ;
//        } // if
        // Default problem
            //problem = new Kursawe("Real", 3);
            //problem = new Kursawe("BinaryReal", 3);
           // problem = new Water("Real");

        //            problem = new Knapsack("Binary", 3059,2,25);


        problem = new Knapsack("Binary", 3060,2,25,0.25);
//        Knapsack knapsack = (Knapsack)problem;
            //problem = new ConstrEx("Real");
            //problem = new DTLZ1("Real");
            //problem = new OKA2("Real") ;
         // else

        //algorithm = new NSGAII(problem);
//        algorithm = new SPEA2(problem);
     //   algorithm = new NSGAII(problem);
        IParallelEvaluator parallelEvaluator = new MultithreadedEvaluator(105);
        algorithm = new SPAE2Parallel(problem,parallelEvaluator);

        // Algorithm parameters
        algorithm.setInputParameter("populationSize",1000);
        algorithm.setInputParameter("maxEvaluations",1000000);
        algorithm.setInputParameter("archiveSize", 1000);
//        algorithm.setInputParameter("databank",knapsack.getDatabank());




//        algorithm.setInputParameter("populationSize",Integer.parseInt(args[0]));
//        algorithm.setInputParameter("maxEvaluations",Integer.parseInt(args[1]));
//        algorithm.setInputParameter("maxEvaluations",1000);
//        algorithm.setInputParameter("archiveSize",100);

        // Mutation and Crossover for Real codification
        parameters = new HashMap() ;
        parameters.put("probability", 0.75) ; // 1.0
        parameters.put("distributionIndex", 20.0) ;
        crossover = CrossoverFactory.getCrossoverOperator("SinglePointCrossover", parameters);

        parameters = new HashMap() ;
        parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
        parameters.put("distributionIndex", 20.0) ;
        mutation = MutationFactory.getMutationOperator("BitFlipMutation", parameters);

        // Selection Operator
        parameters = null ;
        selection = SelectionFactory.getSelectionOperator("BinaryTournament", parameters) ;

        // Add the operators to the algorithm
        algorithm.addOperator("crossover",crossover);
        algorithm.addOperator("mutation",mutation);
        algorithm.addOperator("selection",selection);

        // Add the indicator object to the algorithm
        algorithm.setInputParameter("indicators", indicators) ;

        // Execute the Algorithm
        long initTime = System.currentTimeMillis();
        SolutionSet population = algorithm.execute();
        long estimatedTime = System.currentTimeMillis() - initTime;

        // Result messages
        logger_.info("Total execution time: " + estimatedTime + "ms");
        logger_.info("Variables values have been writen to file VAR");
        population.printVariablesToFile("VAR");
        logger_.info("Objectives values have been writen to file FUN");
        population.printObjectivesToFile("FUN");
        population.printFoodLists();
        if (estimatedTime < Integer.MIN_VALUE || estimatedTime > Integer.MAX_VALUE) {
            population.sendResultWithEmail(-1);
        }
        else
        population.sendResultWithEmail((int)estimatedTime);

        if (indicators != null) {
            logger_.info("Quality indicators") ;
            logger_.info("Hypervolume: " + indicators.getHypervolume(population)) ;
            logger_.info("GD         : " + indicators.getGD(population)) ;
            logger_.info("IGD        : " + indicators.getIGD(population)) ;
            logger_.info("Spread     : " + indicators.getSpread(population)) ;
            logger_.info("Epsilon    : " + indicators.getEpsilon(population)) ;

            int evaluations = ((Integer)algorithm.getOutputParameter("evaluations")).intValue();
            logger_.info("Speed      : " + evaluations + " evaluations") ;
        } // if
    } //main
} // NSGAII_main


