/*
 * Simplex.java
 * SAUNIER DEBES Brice
 * 27/02/16
 */


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

public class Simplex {

// ------------------------------ FIELDS ------------------------------

  private static RoundingMode ROUND_EVEN  = RoundingMode.HALF_EVEN;
  private static int          ROUND_SCALE = 15;
  private static MathContext  PRECISION   = new MathContext(15);
  private List<LinearSystem> system;

// --------------------------- CONSTRUCTORS ---------------------------

  public Simplex(LinearSystem linearSystem) {
    this.system = new ArrayList<LinearSystem>();
    this.system.add(linearSystem);
  }

// -------------------------- OTHER METHODS --------------------------

  private int getIndexOfColumnToPutIn() {
    int                indexOfColumnToPutIn = 0;
    final BigDecimal[] ecoFunction          = getCurrentStep().getEcoFunction();

    for (int i = 0; i < ecoFunction.length - 1; i++) {
      if (isLowerThan(ecoFunction[indexOfColumnToPutIn], ecoFunction[i]))
        indexOfColumnToPutIn = i;
    }
    return indexOfColumnToPutIn;
  }

  private boolean isLowerThan(BigDecimal value, BigDecimal valueToCompare) {
    return value.compareTo(valueToCompare) == -1;
  }

  public void solveLinearProblem() {
      printTab();

      while (!isOptimalSolution() && isBornee()) {
        computeNextStep();
        printTab();
      }
      printSolution();
  }

  private boolean isOptimalSolution() {
    final BigDecimal[] ecoFunction = getCurrentStep().getEcoFunction();

    for (int i = 0; i < ecoFunction.length - 1; i++) {
      if (isGreaterThan(ecoFunction[i], BigDecimal.ZERO))
        return false;
    }
    return true;
  }

  private boolean isBornee() {
    boolean isBornee = !(getIndexOfLineToExtract(getIndexOfColumnToPutIn()) == -1);
    //getCurrentStep().setBornee(isBornee);
    if (!isBornee) {
      System.out.println("Erreur : problème non borné.");
      fail("Problème non borné");
    }
    return true;
  }

  private boolean isGreaterThan(BigDecimal value, BigDecimal valueToCompare) {
    return value.compareTo(valueToCompare) == 1;
  }

  private void computeNextStep() {
    final int indexOfColumnToPutIn = getIndexOfColumnToPutIn();
    final int indexOfLineToExtract = getIndexOfLineToExtract(indexOfColumnToPutIn);

    LinearSystem currentStep = getCurrentStep();
    LinearSystem nextStep    = new LinearSystem(getCurrentStep());

    BigDecimal coefficient = BigDecimal.ONE
        .divide(currentStep.getConstraints()[indexOfLineToExtract][indexOfColumnToPutIn],
            ROUND_SCALE, ROUND_EVEN);

    for (int i = 0; i < nextStep.getEcoFunction().length; i++) {
      nextStep.getEcoFunction()[i] = nextStep.getEcoFunction()[i].subtract(
          coefficient.multiply(currentStep.getEcoFunction()[indexOfColumnToPutIn], PRECISION)
            .multiply(currentStep.getConstraints()[indexOfLineToExtract][i], PRECISION), PRECISION);
    }

    for (int i = 0; i < currentStep.getConstraints().length; i++) {
      for (int j = 0; j < currentStep.getConstraints()[0].length; j++) {
        if (i != indexOfLineToExtract)
          nextStep.getConstraints()[i][j] = nextStep.getConstraints()[i][j].subtract(
              coefficient.multiply(currentStep.getConstraints()[i][indexOfColumnToPutIn], PRECISION)
                  .multiply(currentStep.getConstraints()[indexOfLineToExtract][j], PRECISION),
              PRECISION);
        else
          nextStep.getConstraints()[i][j] = nextStep.getConstraints()[i][j]
              .divide(currentStep.getConstraints()[indexOfLineToExtract][indexOfColumnToPutIn],
                  ROUND_SCALE, ROUND_EVEN);
      }
    }

    if (indexOfColumnToPutIn < currentStep.getNbrVariables())
      nextStep.getSolutionValues()[indexOfColumnToPutIn] = indexOfLineToExtract;

    this.system.add(nextStep);

  }

  private int getIndexOfLineToExtract(int indexOfColumnToPutIn) {
    int                  initialIndexOfLineToExtract;
    final BigDecimal[][] constraints = getCurrentStep().getConstraints();

    initialIndexOfLineToExtract = initialiseIndexOfLineToExtract(constraints, indexOfColumnToPutIn);
    if (initialIndexOfLineToExtract == -1)
      return -1;

    return getSmallestRatiosLine(constraints, indexOfColumnToPutIn, initialIndexOfLineToExtract);
  }

  private int initialiseIndexOfLineToExtract(BigDecimal[][] constraints, int indexOfColumnToPutIn) {
    int initialIndexOfLineToExtract = 0;

    while (isLowerOrEqualTo(constraints[initialIndexOfLineToExtract][indexOfColumnToPutIn], BigDecimal.ZERO)) {
      initialIndexOfLineToExtract++;
      if (initialIndexOfLineToExtract >= constraints.length)
        return -1;
    }

    return initialIndexOfLineToExtract;
  }

  private boolean isLowerOrEqualTo(BigDecimal variable, BigDecimal variableToCompare) {
    return variable.compareTo(variableToCompare) == -1
        || variable.compareTo(variableToCompare) == 0;
  }

  private BigDecimal getRatio(BigDecimal[][] constraints, int line, int column) {
    return constraints[line][constraints[0].length - 1]
        .divide(constraints[line][column], ROUND_SCALE, ROUND_EVEN);
  }

  private int getSmallestRatiosLine(BigDecimal[][] constraints, int indexOfColumnToPutIn,
      int indexOfLineToExtract) {
    final int  nbLines = constraints.length;
    BigDecimal newRatio, oldRatio;

    for (int i = 0; i < nbLines; i++) {
      if (isDifferentOfZero(constraints[i][indexOfColumnToPutIn])) {
        newRatio = getRatio(constraints, i, indexOfColumnToPutIn);
        oldRatio = getRatio(constraints, indexOfLineToExtract, indexOfColumnToPutIn);
        if (isLowerThan(newRatio, oldRatio) && isGreaterThan(newRatio, BigDecimal.ZERO))
          indexOfLineToExtract = i;
      }
    }
    return indexOfLineToExtract;
  }

  private boolean isDifferentOfZero(BigDecimal variable) {
    return variable.compareTo(BigDecimal.ZERO) != 0;
  }

  private LinearSystem getCurrentStep() {
    return system.get(system.size() - 1);
  }

  public String getSolution() {
    return getCurrentStep().getSolution();
  }

  private void printTab() {
    String str = getCurrentStepNumber() == 0 ? "Tableau initial : " : "Tableau numéro " + getCurrentStepNumber() + " : ";

    System.out.println(str);
    getCurrentStep().printTab();
  }

  private int getCurrentStepNumber() {
    return system.size() - 1;
  }

  private void printSolution() {
    getCurrentStep().printSolution();
  }
}
