public class Main {

// --------------------------- main() method ---------------------------

  public static void main(String[] args) {
    LinearSystem ls = new LinearSystem();
    ls.printTab();

    Simplex simplex = new Simplex(ls);
    System.out.println("\n");
    simplex.getStep(0).printTab();
    System.out.println();
    simplex.solveLinearProblem();
  }
}
