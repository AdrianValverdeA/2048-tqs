package eng.uab.tqs.game2048.model.mock;

import eng.uab.tqs.game2048.model.ScannerMovement;

public class ScannerMovementMock extends ScannerMovement
{
  String[] strings;
  int counter;

  public ScannerMovementMock()
  {
    super();
    strings = new String[] {"d","w","a","s"};
    counter = strings.length;
  }

  public String scan() {
    if (counter >= strings.length) {
      counter = 0;
    }
    return strings[counter++];
  }

  public void setConfig(String config) {
    counter = 0;

    switch (config) {
      case "default":
        this.strings = new String[] {"d","w","a","s"};
        counter = strings.length;
        break;

      case "d":
        this.strings = new String[] {"d"};
        counter = strings.length;
        break;
    }
  }
}