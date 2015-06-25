package jmetal.metaheuristics.spea2;

import jmetal.core.*;
import jmetal.util.JMException;
import jmetal.util.Ranking;
import jmetal.util.Spea2Fitness;
import jmetal.util.parallel.IParallelEvaluator;
import menuplanner.RepairConstraint;

import java.util.List;
import java.util.Random;

/**
 * Created by BurakMac on 20/06/15.
 */
public class SPAE2Parallel extends Algorithm {

    public static final int TOURNEMENT_ROUNDS = 1;
    private RepairConstraint repairConstraint;
    private IParallelEvaluator parallelEvaluator;
    public SPAE2Parallel(Problem problem, IParallelEvaluator parallelEvaluator) {
        super(problem) ;
        this.parallelEvaluator = parallelEvaluator;
    }

    public SolutionSet execute() throws JMException, ClassNotFoundException
    {
        int populationSize,archiveSize,maxEvaluations,evaluations;
        Operator crossoverOperator, mutationOperator, selectionOperator;
        SolutionSet solutionSet,archive, offSpringSolutionSet;


        populationSize = ((Integer) getInputParameter("populationSize"));
        archiveSize = ((Integer) getInputParameter("archiveSize"));
        maxEvaluations = ((Integer) getInputParameter("maxEvaluations"));

        crossoverOperator = operators_.get("crossover");
        mutationOperator = operators_.get("mutation");
        selectionOperator = operators_.get("selection");
        parallelEvaluator.startEvaluator(problem_);
        solutionSet = new SolutionSet(populationSize);
        archive = new SolutionSet(archiveSize);
        evaluations = 0;

        Solution newSolution;
        for(int i=0; i<populationSize; i++)
        {
            newSolution = new Solution(problem_);
            parallelEvaluator.addSolutionForEvaluation(newSolution);

        }
        List<Solution> solutionList = parallelEvaluator.parallelEvaluation();
        for(Solution solution: solutionList)
        {
            solutionSet.add(solution);
      //      evaluations++;
        }

        while(evaluations< maxEvaluations)
        {
         int chance = new Random().nextInt(101);
            SolutionSet union  = ((SolutionSet) solutionSet).union(archive);
            Spea2Fitness spea2Fitness = new Spea2Fitness(union);
            spea2Fitness.fitnessAssign();
            archive = spea2Fitness.environmentalSelection(archiveSize);

            offSpringSolutionSet = new SolutionSet(populationSize);
            Solution[] parents = new Solution[2];
            while(offSpringSolutionSet.size() < populationSize)
            {
                int j=0;
                do{
                    j++;
                    parents[0] = (Solution) selectionOperator.execute(archive);
                }
                while(j< SPEA2.TOURNAMENTS_ROUNDS);
                int k =0;
                do{
                    k++;
                    parents[1] = (Solution)selectionOperator.execute(archive);
                }while (k< SPEA2.TOURNAMENTS_ROUNDS);
                Solution[] offSpring = (Solution[]) crossoverOperator.execute(parents);
                mutationOperator.execute(offSpring[0]);
                parallelEvaluator.addSolutionForEvaluation(offSpring[0]);
                evaluations++;

            List<Solution> solutionList1 = parallelEvaluator.parallelEvaluation();
            for(Solution solution :solutionList1)
            {
                offSpringSolutionSet.add(solution);
            }
            }
            solutionSet = offSpringSolutionSet;
            System.out.println("Evaluation number = "+evaluations);

        }
        Ranking ranking = new Ranking(archive);
        return ranking.getSubfront(0);

    }
}
