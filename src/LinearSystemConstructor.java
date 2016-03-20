/*
 * LinearSystemConstructor.java
 * SAUNIER DEBES Brice
 * 20/03/16
 */


import java.math.BigDecimal;
import java.util.Scanner;

public class LinearSystemConstructor {

  private int nbrVariables;

  public LinearSystemConstructor() {
  }

  public LinearSystem execute(){
    BigDecimal[] partialEcoFunction = askEcoFunction();
    BigDecimal[][] partialConstraints = askConstraints();
    return new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
  }

  private BigDecimal[] askEcoFunction() {
    Scanner      sc = new Scanner(System.in);
    BigDecimal[] partialEcoFunction;

    do {
      System.out.println("Entrez le nombre de variables : ");
      nbrVariables = sc.nextInt();
    }while (nbrVariables <= 0);
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

  private BigDecimal[][] askConstraints() {
    Scanner        sc = new Scanner(System.in);
    BigDecimal[][] partialConstraints;
    BigDecimal     variable;
    int            nbrConstraints;
    int            i, j;

    do {
      System.out.print("\nNombre de contraintes : ");
      nbrConstraints = sc.nextInt();
    }while (nbrConstraints <= 0);
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
}
