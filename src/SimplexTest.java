/*
 * SimplexTest.java
 * SAUNIER DEBES Brice
 * 20/03/16
 */

import org.junit.Test;

import java.math.BigDecimal;

//@RunWith(HierarchicalContextRunner.class)
public class SimplexTest {
  Simplex simplex;

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

    LinearSystem linearSystem = new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
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

    LinearSystem linearSystem = new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }


  private void instantiateExampleCoursDivisionParZero() {
    //Solution : Z = 24, x1 = 4, x2 = 1 et x3 = 6.
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

    LinearSystem linearSystem = new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
    this.simplex = new Simplex(linearSystem);
  }

  /*
  @Test
  public void whenInstantiate_shouldBeInstantiated() throws Exception {
   instantiateExo2();
    simplex.solveLinearProblem();

    instantiateExo3();
    simplex.solveLinearProblem();
  }
  */


  @Test
  public void whenDivisionParZero_shouldWork() throws Exception {
    instantiateExampleCoursDivisionParZero();
    simplex.solveLinearProblem();
  }

}
