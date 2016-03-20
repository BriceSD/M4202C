/*
 * LinearSystem.java
 * SAUNIER DEBES Brice
 * 25/02/16
 */


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class LinearSystem {

// ------------------------------ FIELDS ------------------------------

  private int[]          solutionValues;
  private BigDecimal[]   ecoFunction;
  private BigDecimal[][] constraints;
  private int            nbrVariables;

// --------------------------- CONSTRUCTORS ---------------------------

  public LinearSystem() {
    BigDecimal[] partialEcoFunction = askEcoFunction();
    //FAIRE LES CONTRAINTES EN PREMIER
    makeConstraints(askConstraints());
    makeEcoFunction(partialEcoFunction);
    initializeSolutionValues();
  }

  public LinearSystem(BigDecimal[] partialEcoFunction, BigDecimal[][] partialConstraints,
      int nbrVariables) {
    this.nbrVariables = nbrVariables;
    //FAIRE LES CONTRAINTES EN PREMIER
    makeConstraints(partialConstraints);
    makeEcoFunction(partialEcoFunction);
    initializeSolutionValues();
  }

  private BigDecimal[] askEcoFunction() {
    Scanner      sc = new Scanner(System.in);
    BigDecimal[] partialEcoFunction;

    System.out.println("Entrez le nombre de variables : ");
    nbrVariables = sc.nextInt();
    partialEcoFunction = new BigDecimal[nbrVariables + 1];

    System.out.println("\nFonction économique : ");

    int        i = 0;
    BigDecimal valeur;
    while (i < nbrVariables) {
      System.out.print("x" + (i + 1) + " : ");
      valeur = sc.nextBigDecimal();
      partialEcoFunction[i] = valeur;
      i++;
    }

    partialEcoFunction[i] = BigDecimal.ZERO;


    return partialEcoFunction;
  }

  private void initializeSolutionValues() {
    solutionValues = new int[nbrVariables];
    for (int i = 0; i < nbrVariables; i++)
      solutionValues[i] = -1;
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

  private boolean isInDiagonal(int nbrVariables, int currentLine, int currentColumn) {
    return currentColumn - nbrVariables == currentLine;
  }

  private int getConstraintsLength() {return constraints[0].length;}

  public BigDecimal getLineValue(int line) {
    return constraints[line][getConstraintsLength() - 1];
  }

  private BigDecimal[][] askConstraints() {
    Scanner        sc = new Scanner(System.in);
    BigDecimal[][] partialConstraints;
    BigDecimal     variable;
    int            nbrConstraints;
    int            i, j;

    System.out.print("\nNombre de contraintes : ");
    nbrConstraints = sc.nextInt();
    partialConstraints = new BigDecimal[nbrConstraints][];

    for (i = 0; i < nbrConstraints; i++) {
      partialConstraints[i] = new BigDecimal[nbrVariables + 1];
      for (j = 0; j < nbrVariables; j++) {
        System.out.print("x" + (j + 1) + " : ");
        variable = sc.nextBigDecimal();
        partialConstraints[i][j] = variable;
      }
      System.out.print("Valeur : ");
      variable = sc.nextBigDecimal();
      partialConstraints[i][j] = variable;
      System.out.println();
    }
    return partialConstraints;
  }

  private void makeEcoFunction(BigDecimal[] partialEcoFunction) {
    this.ecoFunction = new BigDecimal[getConstraintsLength()];

    for (int i = 0; i < ecoFunction.length; i++)
      ecoFunction[i] = i < partialEcoFunction.length - 1 ? partialEcoFunction[i] : BigDecimal.ZERO;

    ecoFunction[ecoFunction.length - 1] = partialEcoFunction[partialEcoFunction.length - 1];
  }

  public LinearSystem(LinearSystem linearSystem) {
    this.ecoFunction = linearSystem.getEcoFunction().clone();
    this.solutionValues = linearSystem.getSolutionValues().clone();
    this.nbrVariables = linearSystem.getNbrVariables();
    this.constraints = new BigDecimal[linearSystem.getConstraints().length][];

    for (int i = 0; i < this.constraints.length; i++)
      this.constraints[i] = linearSystem.getConstraints()[i].clone();
  }


// -------------------------- OTHER METHODS --------------------------

  public BigDecimal[][] getConstraints() {
    return constraints;
  }

  public void setConstraints(BigDecimal[][] constraints) {
    this.constraints = constraints;
  }

  public BigDecimal[] getEcoFunction() {
    return ecoFunction;
  }

  public void setEcoFunction(BigDecimal[] ecoFunction) {
    this.ecoFunction = ecoFunction;
  }

  public int getNbrVariables() {
    return nbrVariables;
  }

  public int[] getSolutionValues() {
    return solutionValues;
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

  public void printSolution() {
    String     str = "";
    int        i   = 0;
    BigDecimal z   = ecoFunction[ecoFunction.length - 1];

    str += "\nSolution : \n";
    str += "Z = " + z.negate().setScale(2, RoundingMode.HALF_EVEN).toPlainString() + "\n";

    for (int solutionValueIndex : solutionValues) {
      str += "x" + (++i) + " = ";
      if (solutionValueIndex != -1)
        str += getLineValue(solutionValueIndex).setScale(2, RoundingMode.HALF_EVEN).toPlainString();
      else
        str += "0";
      str += "\n";
    }

    System.out.println(str);
  }
}
