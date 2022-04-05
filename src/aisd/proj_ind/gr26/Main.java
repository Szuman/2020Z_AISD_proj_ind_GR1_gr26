package aisd.proj_ind.gr26;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Wstaw ścieżkę do pliku: ");
        String filePath = sc.nextLine();
        Solver solver = new Solver(filePath);
        solver.calculate();
    }
}
