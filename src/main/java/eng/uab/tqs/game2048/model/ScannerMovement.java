package eng.uab.tqs.game2048.model;

import java.util.Scanner;
//this class is used for doing IOC and making possible
// to inject a mock. The full class is java.util functions
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
