/*
 * LinearSystem.java
 * SAUNIER DEBES Brice
 * 25/02/16
 */


import java.util.Scanner;

public class LinearSystem {

// ------------------------------ FIELDS ------------------------------

  private double[]   ecoFunction;
  private double[][] constraints;
  private int        nbrVariables;

// --------------------------- CONSTRUCTORS ---------------------------

  public LinearSystem() {
    double[] partialEcoFunction = askEcoFunction();
    makeConstraints(askConstraints());
    makeEcoFunction(partialEcoFunction);
  }

  private double[] askEcoFunction() {
    Scanner  sc = new Scanner(System.in);
    double[] partialEcoFunction;

    System.out.println("Entrez le nombre de variables : ");
    nbrVariables = sc.nextInt();
    partialEcoFunction = new double[nbrVariables + 1];

    System.out.println("\nFonction économique : ");

    int    i = 0;
    double valeur;
    while (i < nbrVariables) {
      System.out.print("x" + (i + 1) + " : ");
      valeur = sc.nextDouble();
      partialEcoFunction[i] = valeur;
      i++;
    }
    partialEcoFunction[i] = 0;
    return partialEcoFunction;
  }

  private void makeConstraints(double[][] partialConstraints) {
    final int nbConstraints     = partialConstraints.length;
    final int constraintsLength = partialConstraints[0].length + nbConstraints;
    final int nbVariables       = partialConstraints[0].length - 1;
    constraints = new double[nbConstraints][constraintsLength];

    for (int i = 0; i < nbConstraints; i++) {
      for (int j = 0; j < constraintsLength; j++) {
        if (j >= nbVariables)//si variables déjà mis dans le tableau, on met une base
          constraints[i][j] = isInDiagonal(nbVariables, i, j) ? 1 : 0;
        else//sinon la variable est mise dans la contrainte finale
          constraints[i][j] = partialConstraints[i][j];
      }
      constraints[i][getConstraintsLength() - 1] =
          partialConstraints[0][partialConstraints[i].length - 1];
    }
  }

  private boolean isInDiagonal(int nbrVariables, int currentLine, int currentColumn) {
    return currentColumn - nbrVariables == currentLine;
  }

  private int getConstraintsLength() {return constraints[0].length;}

  private double[][] askConstraints() {
    Scanner    sc = new Scanner(System.in);
    double[][] partialConstraints;
    double     variable;
    int        nbrConstraints;
    int        i, j;

    System.out.print("\nNombre de contraintes : ");
    nbrConstraints = sc.nextInt();
    partialConstraints = new double[nbrConstraints][];

    for (i = 0; i < nbrConstraints; i++) {
      partialConstraints[i] = new double[nbrVariables + 1];
      for (j = 0; j < nbrVariables; j++) {
        System.out.print("x" + (j + 1) + " : ");
        variable = sc.nextInt();
        partialConstraints[i][j] = variable;
      }
      System.out.print("Valeur : ");
      variable = sc.nextInt();
      partialConstraints[i][j] = variable;
      System.out.println();
    }
    return partialConstraints;
  }

  private void makeEcoFunction(double[] partialEcoFunction) {
    this.ecoFunction = new double[getConstraintsLength()];

    for (int i = 0; i < ecoFunction.length; i++)
      ecoFunction[i] = i < partialEcoFunction.length - 1 ? partialEcoFunction[i] : 0;

    ecoFunction[ecoFunction.length - 1] = partialEcoFunction[partialEcoFunction.length - 1];
  }

// -------------------------- OTHER METHODS --------------------------

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
      str += "x" + (i + 1) + "\t\t";
    for (int i = nbrVariables; i < ecoFunction.length - 1; i++)
      str += "e" + (i - nbrVariables + 1) + "\t\t";
    str += "\n";
    return str;
  }

  private String getEcoFunctionLineAsString() {
    String str = "";
    for (double variable : ecoFunction)
      str += variable + "\t\t";
    str += "\n";
    return str;
  }

  private String getConstraintsLinesAsString() {
    String str = "";
    for (double[] constraint : constraints) {
      for (double variable : constraint)
        str += variable + "\t\t";
      str += "\n";
    }
    str += "\n";
    return str;
  }

  public double[] getEcoFunction() {
    return ecoFunction;
  }

  public void setEcoFunction(double[] ecoFunction) {
    this.ecoFunction = ecoFunction;
  }

  public double[][] getConstraints() {
    return constraints;
  }

  public void setConstraints(double[][] constraints) {
    this.constraints = constraints;
  }
}
