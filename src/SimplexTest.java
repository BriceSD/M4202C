/*
 * SimplexTest.java
 * SAUNIER DEBES Brice
 * 20/03/16
 */

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

//@RunWith(HierarchicalContextRunner.class)
public class SimplexTest {
 @Rule
  public final ExpectedException exception = ExpectedException.none();

  Simplex simplex;

  private void instantiateExo1() {
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 2;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[3][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(2);
    partialEcoFunction[1] = BigDecimal.valueOf(1);
    partialEcoFunction[2] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(1);
    partialConstraints[0][1] = BigDecimal.valueOf(-1);
    partialConstraints[0][2] = BigDecimal.valueOf(1);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(0);
    partialConstraints[1][2] = BigDecimal.valueOf(2);

    partialConstraints[2][0] = BigDecimal.valueOf(0);
    partialConstraints[2][1] = BigDecimal.valueOf(1);
    partialConstraints[2][2] = BigDecimal.valueOf(2);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExo2() {
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 2;
    partialEcoFunction = new BigDecimal[3];
    partialConstraints = new BigDecimal[3][3];

    partialEcoFunction[0] = BigDecimal.valueOf(40.0);
    partialEcoFunction[1] = BigDecimal.valueOf(60.0);
    partialEcoFunction[2] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(2);
    partialConstraints[0][1] = BigDecimal.valueOf(1);
    partialConstraints[0][2] = BigDecimal.valueOf(70);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(1);
    partialConstraints[1][2] = BigDecimal.valueOf(40);

    partialConstraints[2][0] = BigDecimal.valueOf(1);
    partialConstraints[2][1] = BigDecimal.valueOf(3);
    partialConstraints[2][2] = BigDecimal.valueOf(90);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExo3() {
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 3;
    partialEcoFunction = new BigDecimal[4];
    partialConstraints = new BigDecimal[4][4];

    partialEcoFunction[0] = BigDecimal.valueOf(5);
    partialEcoFunction[1] = BigDecimal.valueOf(4);
    partialEcoFunction[2] = BigDecimal.valueOf(3);
    partialEcoFunction[3] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(2);
    partialConstraints[0][1] = BigDecimal.valueOf(3);
    partialConstraints[0][2] = BigDecimal.valueOf(1);
    partialConstraints[0][3] = BigDecimal.valueOf(5);

    partialConstraints[1][0] = BigDecimal.valueOf(4);
    partialConstraints[1][1] = BigDecimal.valueOf(1);
    partialConstraints[1][2] = BigDecimal.valueOf(2);
    partialConstraints[1][3] = BigDecimal.valueOf(11);

    partialConstraints[2][0] = BigDecimal.valueOf(3);
    partialConstraints[2][1] = BigDecimal.valueOf(4);
    partialConstraints[2][2] = BigDecimal.valueOf(2);
    partialConstraints[2][3] = BigDecimal.valueOf(8);

    partialConstraints[3][0] = BigDecimal.valueOf(4);
    partialConstraints[3][1] = BigDecimal.valueOf(1);
    partialConstraints[3][2] = BigDecimal.valueOf(0);
    partialConstraints[3][3] = BigDecimal.valueOf(5);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExo4() {
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 4;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[3][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(7);
    partialEcoFunction[1] = BigDecimal.valueOf(9);
    partialEcoFunction[2] = BigDecimal.valueOf(18);
    partialEcoFunction[3] = BigDecimal.valueOf(7);
    partialEcoFunction[4] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(2);
    partialConstraints[0][1] = BigDecimal.valueOf(4);
    partialConstraints[0][2] = BigDecimal.valueOf(5);
    partialConstraints[0][3] = BigDecimal.valueOf(7);
    partialConstraints[0][4] = BigDecimal.valueOf(42);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(1);
    partialConstraints[1][2] = BigDecimal.valueOf(2);
    partialConstraints[1][3] = BigDecimal.valueOf(2);
    partialConstraints[1][4] = BigDecimal.valueOf(17);

    partialConstraints[2][0] = BigDecimal.valueOf(1);
    partialConstraints[2][1] = BigDecimal.valueOf(2);
    partialConstraints[2][2] = BigDecimal.valueOf(2);
    partialConstraints[2][3] = BigDecimal.valueOf(3);
    partialConstraints[2][4] = BigDecimal.valueOf(24);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExo11() {
    //Solutions multiples
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 2;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[2][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(2);
    partialEcoFunction[1] = BigDecimal.valueOf(1);
    partialEcoFunction[2] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(-1);
    partialConstraints[0][1] = BigDecimal.valueOf(1);
    partialConstraints[0][2] = BigDecimal.valueOf(1);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(-2);
    partialConstraints[1][2] = BigDecimal.valueOf(2);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExo12() {
    //Pas de solution
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 2;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[3][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(6);
    partialEcoFunction[1] = BigDecimal.valueOf(3);
    partialEcoFunction[2] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(2);
    partialConstraints[0][1] = BigDecimal.valueOf(1);
    partialConstraints[0][2] = BigDecimal.valueOf(6);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(-1);
    partialConstraints[1][2] = BigDecimal.valueOf(2);

    partialConstraints[2][0] = BigDecimal.valueOf(0);
    partialConstraints[2][1] = BigDecimal.valueOf(1);
    partialConstraints[2][2] = BigDecimal.valueOf(3);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExo14() {
    //Dégénérescence
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 2;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[3][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(2);
    partialEcoFunction[1] = BigDecimal.valueOf(1);
    partialEcoFunction[2] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(3);
    partialConstraints[0][1] = BigDecimal.valueOf(1);
    partialConstraints[0][2] = BigDecimal.valueOf(6);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(-1);
    partialConstraints[1][2] = BigDecimal.valueOf(2);

    partialConstraints[2][0] = BigDecimal.valueOf(0);
    partialConstraints[2][1] = BigDecimal.valueOf(1);
    partialConstraints[2][2] = BigDecimal.valueOf(3);

    LinearSystem linearSystem = new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExo16() {
    //Cyclage
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 4;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[3][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(0.75);
    partialEcoFunction[1] = BigDecimal.valueOf(-20);
    partialEcoFunction[2] = BigDecimal.valueOf(0.5);
    partialEcoFunction[3] = BigDecimal.valueOf(-6);
    partialEcoFunction[4] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(0.25);
    partialConstraints[0][1] = BigDecimal.valueOf(-8);
    partialConstraints[0][2] = BigDecimal.valueOf(-1);
    partialConstraints[0][3] = BigDecimal.valueOf(9);
    partialConstraints[0][4] = BigDecimal.valueOf(0);

    partialConstraints[1][0] = BigDecimal.valueOf(0.5);
    partialConstraints[1][1] = BigDecimal.valueOf(-12);
    partialConstraints[1][2] = BigDecimal.valueOf(-0.5);
    partialConstraints[1][3] = BigDecimal.valueOf(3);
    partialConstraints[1][4] = BigDecimal.valueOf(0);

    partialConstraints[2][0] = BigDecimal.valueOf(0);
    partialConstraints[2][1] = BigDecimal.valueOf(0);
    partialConstraints[2][2] = BigDecimal.valueOf(1);
    partialConstraints[2][3] = BigDecimal.valueOf(0);
    partialConstraints[2][4] = BigDecimal.valueOf(1);

    LinearSystem linearSystem = new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExempleCours1() {
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 2;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[4][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(3);
    partialEcoFunction[1] = BigDecimal.valueOf(1);
    partialEcoFunction[2] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(1);
    partialConstraints[0][1] = BigDecimal.valueOf(1);
    partialConstraints[0][2] = BigDecimal.valueOf(12);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(-1);
    partialConstraints[1][2] = BigDecimal.valueOf(3);

    partialConstraints[2][0] = BigDecimal.valueOf(-2);
    partialConstraints[2][1] = BigDecimal.valueOf(1);
    partialConstraints[2][2] = BigDecimal.valueOf(3);

    partialConstraints[3][0] = BigDecimal.valueOf(1);
    partialConstraints[3][1] = BigDecimal.valueOf(0);
    partialConstraints[3][2] = BigDecimal.valueOf(6);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExempleCours2() {
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 3;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[4][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(1);
    partialEcoFunction[1] = BigDecimal.valueOf(2);
    partialEcoFunction[2] = BigDecimal.valueOf(3);
    partialEcoFunction[3] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(1);
    partialConstraints[0][1] = BigDecimal.valueOf(0);
    partialConstraints[0][2] = BigDecimal.valueOf(0);
    partialConstraints[0][3] = BigDecimal.valueOf(4);

    partialConstraints[1][0] = BigDecimal.valueOf(0);
    partialConstraints[1][1] = BigDecimal.valueOf(1);
    partialConstraints[1][2] = BigDecimal.valueOf(0);
    partialConstraints[1][3] = BigDecimal.valueOf(2);

    partialConstraints[2][0] = BigDecimal.valueOf(0);
    partialConstraints[2][1] = BigDecimal.valueOf(0);
    partialConstraints[2][2] = BigDecimal.valueOf(1);
    partialConstraints[2][3] = BigDecimal.valueOf(6);

    partialConstraints[3][0] = BigDecimal.valueOf(2);
    partialConstraints[3][1] = BigDecimal.valueOf(6);
    partialConstraints[3][2] = BigDecimal.valueOf(3);
    partialConstraints[3][3] = BigDecimal.valueOf(32);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }


  private void instantiateExempleCours3() {
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 2;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[4][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(3);
    partialEcoFunction[1] = BigDecimal.valueOf(3);
    partialEcoFunction[2] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(1);
    partialConstraints[0][1] = BigDecimal.valueOf(1);
    partialConstraints[0][2] = BigDecimal.valueOf(12);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(-1);
    partialConstraints[1][2] = BigDecimal.valueOf(3);

    partialConstraints[2][0] = BigDecimal.valueOf(-2);
    partialConstraints[2][1] = BigDecimal.valueOf(1);
    partialConstraints[2][2] = BigDecimal.valueOf(3);

    partialConstraints[3][0] = BigDecimal.valueOf(1);
    partialConstraints[3][1] = BigDecimal.valueOf(0);
    partialConstraints[3][2] = BigDecimal.valueOf(6);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  private void instantiateExempleCours4() {
    BigDecimal[]   partialEcoFunction;
    BigDecimal[][] partialConstraints;
    int            nbrVariables;

    nbrVariables = 2;
    partialEcoFunction = new BigDecimal[nbrVariables + 1];
    partialConstraints = new BigDecimal[5][nbrVariables + 1];

    partialEcoFunction[0] = BigDecimal.valueOf(3);
    partialEcoFunction[1] = BigDecimal.valueOf(1);
    partialEcoFunction[2] = BigDecimal.ZERO;

    partialConstraints[0][0] = BigDecimal.valueOf(1);
    partialConstraints[0][1] = BigDecimal.valueOf(1);
    partialConstraints[0][2] = BigDecimal.valueOf(12);

    partialConstraints[1][0] = BigDecimal.valueOf(1);
    partialConstraints[1][1] = BigDecimal.valueOf(-1);
    partialConstraints[1][2] = BigDecimal.valueOf(3);

    partialConstraints[2][0] = BigDecimal.valueOf(-2);
    partialConstraints[2][1] = BigDecimal.valueOf(1);
    partialConstraints[2][2] = BigDecimal.valueOf(3);

    partialConstraints[3][0] = BigDecimal.valueOf(1);
    partialConstraints[3][1] = BigDecimal.valueOf(0);
    partialConstraints[3][2] = BigDecimal.valueOf(6);

    partialConstraints[4][0] = BigDecimal.valueOf(2);
    partialConstraints[4][1] = BigDecimal.valueOf(1);
    partialConstraints[4][2] = BigDecimal.valueOf(18);

    LinearSystem linearSystem =
        new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  @Test
  public void exo1() throws Exception {
    System.out.println("\n\nExo 1 :");
    instantiateExo1();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 6.00\nx1 = 2.00\nx2 = 2.00\n", simplex.getSolution());
  }

  @Test
  public void exo2() throws Exception {
    System.out.println("\n\nExo 2 :");
    instantiateExo2();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 2100.00\nx1 = 15.00\nx2 = 25.00\n", simplex.getSolution());
  }

  @Test
  public void exo3() throws Exception {
    System.out.println("\n\nExo 3 :");
    instantiateExo3();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 12.62\nx1 = 1.25\nx2 = 0\nx3 = 2.12\n", simplex.getSolution());
  }

  @Test
  public void exo4() throws Exception {
    System.out.println("\n\nExo 4 :");
    instantiateExo4();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 151.20\nx1 = 0\nx2 = 0\nx3 = 8.40\nx4 = 0\n", simplex.getSolution());
  }

  @Test
  public void exo11() throws Exception {
    //Problème non borné
    System.out.println("\n\nExo 11, non borné :");
    instantiateExo11();
    exception.expectMessage("Problème non borné");
    simplex.solveLinearProblem();
  }

  @Test
  public void exo12() throws Exception {
    //Problème avec plusieurs solutions
    System.out.println("\n\nExo 12, plusieurs solutions possible :");
    instantiateExo12();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 18.00\nx1 = 2.67\nx2 = 0.67\n", simplex.getSolution());
  }

  @Test
  public void exo14() throws Exception {
    //Dégénérescence
    System.out.println("\n\nExo 14, dégénérescence : ");
    instantiateExo14();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 5.00\nx1 = 1.00\nx2 = 3.00\n", simplex.getSolution());
  }

  @Test
  public void exo16() throws Exception {
    //Dégénérescence
    System.out.println("\n\nExo 16, cyclage : ");
    instantiateExo16();
    //simplex.solveLinearProblem();
    //assertEquals("\nSolution : \nZ = 5.00\nx1 = 1.00\nx2 = 3.00\n", simplex.getSolution());
  }

  @Test
  public void exempleCours1() throws Exception {
    System.out.println("\n\nExemple cours 1 :");
    instantiateExempleCours1();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 24.00\nx1 = 6.00\nx2 = 6.00\n", simplex.getSolution());
  }

  @Test
  public void exempleCours2() throws Exception {
    System.out.println("\n\nExemple cours 2 :");
    instantiateExempleCours2();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 24.00\nx1 = 4.00\nx2 = 1.00\nx3 = 6.00\n", simplex.getSolution());
  }

  @Test
  public void exempleCours3() throws Exception {
    System.out.println("\n\nExemple cours 3 :");
    instantiateExempleCours3();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 36.00\nx1 = 6.00\nx2 = 6.00\n", simplex.getSolution());
  }

  @Test
  public void exempleCours4() throws Exception {
    System.out.println("\n\nExemple cours 4 :");
    instantiateExempleCours4();
    simplex.solveLinearProblem();
    assertEquals("\nSolution : \nZ = 24.00\nx1 = 6.00\nx2 = 6.00\n", simplex.getSolution());
  }
}
