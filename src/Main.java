public class Main {

// --------------------------- main() method ---------------------------

  public static void main(String[] args) {
    LinearSystem ls = new LinearSystem();

    Simplex simplex = new Simplex(ls);
    simplex.solveLinearProblem();
  }
}
