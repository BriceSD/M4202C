public class Main {

// --------------------------- main() method ---------------------------

  public static void main(String[] args) {
    LinearSystemConstructor lsc = new LinearSystemConstructor();

    Simplex simplex = new Simplex(lsc.execute());
    simplex.solveLinearProblem();
  }
}
