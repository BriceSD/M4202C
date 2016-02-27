/*
 * Simplex.java
 * SAUNIER DEBES Brice
 * 27/02/16
 */


import java.util.ArrayList;
import java.util.List;

public class Simplex {

// ------------------------------ FIELDS ------------------------------

  List<LinearSystem> system;

// --------------------------- CONSTRUCTORS ---------------------------

  public Simplex(LinearSystem linearSystem) {
    this.system = new ArrayList<LinearSystem>();
    this.system.add(linearSystem);
  }

// -------------------------- OTHER METHODS --------------------------

  private LinearSystem getCurrentStep() {
    return system.get(system.size() - 1);
  }

  private int getIndexOfColumnToPutIn() {
    int            indexOfColumnToPutIn = 0;
    final double[] ecoFunction          = getCurrentStep().getEcoFunction();

    for (int i = 0; i < ecoFunction.length - 1; i++) {
      if (ecoFunction[i] > ecoFunction[indexOfColumnToPutIn])
        indexOfColumnToPutIn = i;
    }
    return indexOfColumnToPutIn;
  }

  public LinearSystem getStep(int step) {
    return system.get(step);
  }

  public void solveLinearProblem() {
    while (!isOptimalSolution())
      computeNextStep();
  }

  private boolean isOptimalSolution() {
    final double[] ecoFunction = getCurrentStep().getEcoFunction();

    for (int i = 0; i < ecoFunction.length - 1; i++) {
      if (ecoFunction[i] > 0)
        return false;
    }
    return true;
  }

  private void computeNextStep() {
    final int indexOfColumnToPutIn = getIndexOfColumnToPutIn();
    final int indexOfLineToExtract = getIndexOfLineToExtract(indexOfColumnToPutIn);

    System.out.println("Step : " + getCurrentStepNumber() + ", line to extract : " + indexOfLineToExtract + ", column to put in : " + indexOfColumnToPutIn);



  }

  private int getIndexOfLineToExtract(int indexOfColumnToPutIn) {
    int              initialIndexOfLineToExtract;
    final double[][] constraints          = getCurrentStep().getConstraints();

    initialIndexOfLineToExtract = initialiseIndexOfLineToExtract(constraints, indexOfColumnToPutIn);

    return getSmallestRatiosLine(constraints, indexOfColumnToPutIn, initialIndexOfLineToExtract);
  }

  private int initialiseIndexOfLineToExtract(double[][] constraints, int indexOfColumnToPutIn) {
    int initialIndexOfLineToExtract = 0;

    while (getRatio(constraints, initialIndexOfLineToExtract, indexOfColumnToPutIn) <= 0)
      initialIndexOfLineToExtract++;

    return initialIndexOfLineToExtract;
  }

  private double getRatio(double[][] constraints, int line, int column) {
    return constraints[line][column] / constraints[line][constraints[0].length-1];
  }

  private int getSmallestRatiosLine(double[][] constraints, int indexOfColumnToPutIn, int indexOfLineToExtract) {
    final int nbLines = constraints.length;
    double newRatio, oldRatio;

    for (int i = 0; i < nbLines; i++) {
      newRatio = getRatio(constraints, i, indexOfColumnToPutIn);
      oldRatio = getRatio(constraints, indexOfLineToExtract, indexOfColumnToPutIn);
      if (newRatio < oldRatio && newRatio > 0)
        indexOfLineToExtract = i;
    }
    return indexOfLineToExtract;
  }

  private int getCurrentStepNumber() {
    return system.size() - 1;
  }
}
