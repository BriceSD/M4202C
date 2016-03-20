/*
 * LinearSystemTest.java
 * SAUNIER DEBES Brice
 * 20/03/16
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

//@RunWith(HierarchicalContextRunner.class)
public class LinearSystemTest {
  LinearSystem linearSystem;

  @Before
  public void setUp() throws Exception {
    //this.linearSystem = null;
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

    this.linearSystem = new LinearSystem(partialEcoFunction, partialConstraints, nbrVariables);
  }


  @Test
  public void whenInitialise_shouldBeInitialised() throws Exception {
    instantiateExo2();
    linearSystem.printTab();

  }

  @After
  public void tearDown() throws Exception {

  }
}
