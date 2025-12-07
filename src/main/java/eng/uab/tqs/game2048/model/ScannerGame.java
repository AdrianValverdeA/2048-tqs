package eng.uab.tqs.game2048.model;

import java.util.Scanner;

public class ScannerGame {
  Scanner scanner;

  public ScannerGame() {
    this.scanner = new Scanner(System.in);
  }

  public String scanName() {
    String input = scanner.nextLine().trim().toLowerCase();
    return input;
  }

  public Character scanOptions() {
    String input = scanner.nextLine().trim().toLowerCase();
    return input.charAt(0);
  }

  public String nextLine() {
    return scanner.nextLine();
  }
}