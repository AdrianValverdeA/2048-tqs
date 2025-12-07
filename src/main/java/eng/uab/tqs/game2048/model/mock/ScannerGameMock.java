package eng.uab.tqs.game2048.model.mock;

import eng.uab.tqs.game2048.model.Block;
import eng.uab.tqs.game2048.model.InfoGame;
import eng.uab.tqs.game2048.model.ScannerGame;

import java.util.Scanner;

public class ScannerGameMock extends ScannerGame {
  private int counter = 0;
  private String[] values;

  public ScannerGameMock() {
    this.values = new String[]{};
  }

  public String scanName() {
    if (counter >= values.length) {
      counter = 0;
    }
    return values[counter++];
  }

  public Character scanOptions() {
    if (counter >= values.length) {
      counter = 0;
    }
    return values[counter++].charAt(0);
  }

  public String nextLine() {
    if (counter >= values.length) {
      counter = 0;
    }
    return values[counter++];
  }

  public void setConfig(String config) {
    switch (config) {
      case "1":
        this.values = new String[]{"1", "TestUser", "3"};
        break;
      case "2":
        this.values = new String[]{"2", "3"};
        break;
      case "3":
        this.values = new String[]{"3"};
        break;
      case "inputName":
        this.values = new String[]{"TestUser"};
        break;
    }
  }
}
