package eng.uab.tqs.game2048.model;

import java.util.Scanner;
public class ScannerMovement {
  Scanner scanner;
  public ScannerMovement() {
    this.scanner = new Scanner(System.in);
  }

  public String scan() {
    String input = scanner.nextLine().trim().toLowerCase();
    return input;
  }
}
