package eng.uab.tqs.game2048.model;

import java.util.Random;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;

public class Generator {

  public int genRandom() {
    int x = new Random().nextInt(SIZE);
    return x;
  }
}
