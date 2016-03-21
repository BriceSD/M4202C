/*
 * LinearSystem.java
 * SAUNIER DEBES Brice
 * 25/02/16
 */


import java.math.BigDecimal;
import java.math.RoundingMode;

public class LinearSystem {

// ------------------------------ FIELDS ------------------------------

  private int[]          solutionValues;
  private BigDecimal[]   ecoFunction;
  private BigDecimal[][] constraints;
  private int            nbrVariables;

// --------------------------- CONSTRUCTORS ---------------------------

  public LinearSystem(LinearSystem linearSystem) {
    this.ecoFunction = linearSystem.getEcoFunction().clone();
    this.solutionValues = linearSystem.getSolutionValues().clone();
    this.nbrVariables = linearSystem.getNbrVariables();
    this.constraints = new BigDecimal[linearSystem.getConstraints().length][];

    for (int i = 0; i < this.constraints.length; i++)
      this.constraints[i] = linearSystem.getConstraints()[i].clone();
  }

  public BigDecimal[] getEcoFunction() {
    return ecoFunction;
  }

  public int[] getSolutionValues() {
    return solutionValues;
  }

  public int getNbrVariables() {
    return nbrVariables;
  }

  public BigDecimal[][] getConstraints() {
    return constraints;
  }

  public LinearSystem(BigDecimal[] partialEcoFunction, BigDecimal[][] partialConstraints,
      int nbrVariables) {
    this.nbrVariables = nbrVariables;
    //FAIRE LES CONTRAINTES EN PREMIER
    makeConstraints(partialConstraints);
    makeEcoFunction(partialEcoFunction);
    initializeSolutionValues();
  }

  private void makeConstraints(BigDecimal[][] partialConstraints) {
    final int nbConstraints     = partialConstraints.length;
    final int constraintsLength = partialConstraints[0].length + nbConstraints;
    final int nbVariables       = partialConstraints[0].length - 1;
    constraints = new BigDecimal[nbConstraints][constraintsLength];

    for (int i = 0; i < nbConstraints; i++) {
      for (int j = 0; j < constraintsLength; j++) {
        if (j >= nbVariables)//si variables déjà mis dans le tableau, on met une base
          constraints[i][j] = isInDiagonal(nbVariables, i, j) ? BigDecimal.ONE : BigDecimal.ZERO;
        else//sinon la variable est mise dans la contrainte finale
          constraints[i][j] = partialConstraints[i][j];
      }
      constraints[i][getConstraintsLength() - 1] =
          partialConstraints[i][partialConstraints[i].length - 1];
    }
  }

// -------------------------- OTHER METHODS --------------------------

  private void makeEcoFunction(BigDecimal[] partialEcoFunction) {
    this.ecoFunction = new BigDecimal[getConstraintsLength()];

    for (int i = 0; i < ecoFunction.length; i++)
      ecoFunction[i] = i < partialEcoFunction.length - 1 ? partialEcoFunction[i] : BigDecimal.ZERO;

    ecoFunction[ecoFunction.length - 1] = partialEcoFunction[partialEcoFunction.length - 1];
  }

  private void initializeSolutionValues() {
    solutionValues = new int[nbrVariables];
    for (int i = 0; i < nbrVariables; i++)
      solutionValues[i] = -1;
  }

  private boolean isInDiagonal(int nbrVariables, int currentLine, int currentColumn) {
    return currentColumn - nbrVariables == currentLine;
  }

  private int getConstraintsLength() {return constraints[0].length;}

  public void printSolution() {
    System.out.println(getSolution());
  }

  public String getSolution() {
    String     str = "";
    int        i   = 0;
    BigDecimal z   = ecoFunction[ecoFunction.length - 1];

    str += "\nSolution : \n";
    str += "Z = " + z.negate().setScale(2, RoundingMode.HALF_EVEN).toPlainString() + "\n";

    for (int solutionValueIndex : solutionValues) {
      str += "x" + (++i) + " = ";
      str += solutionValueIndex != -1 ?
             getLineValue(solutionValueIndex).setScale(2, RoundingMode.HALF_EVEN).toPlainString() :
             "0";
      str += "\n";
    }
    return str;
  }

  public BigDecimal getLineValue(int line) {
    return constraints[line][getConstraintsLength() - 1];
  }

  public void printTab() {
    System.out.println(this.toString());
  }

  public String toString() {
    String str = "";

    str += getVariablesLineAsString();
    str += getEcoFunctionLineAsString();
    str += getConstraintsLinesAsString();

    return str;
  }

  private String getVariablesLineAsString() {
    String str = "";
    for (int i = 0; i < nbrVariables; i++)
      str += "x" + (i + 1) + "\t\t\t";
    for (int i = nbrVariables; i < ecoFunction.length - 1; i++)
      str += "e" + (i - nbrVariables + 1) + "\t\t\t";
    str += "\n";
    return str;
  }

  private String getEcoFunctionLineAsString() {
    String str = "";
    for (BigDecimal variable : ecoFunction)
      str += variable.setScale(2, RoundingMode.HALF_EVEN).toPlainString() + "\t\t";
    str += "\n";
    return str;
  }

  private String getConstraintsLinesAsString() {
    String str = "";
    for (BigDecimal[] constraint : constraints) {
      for (BigDecimal variable : constraint)
        str += variable.setScale(2, RoundingMode.HALF_EVEN).toPlainString() + "\t\t";
      str += "\n";
    }
    str += "\n";
    return str;
  }
}
