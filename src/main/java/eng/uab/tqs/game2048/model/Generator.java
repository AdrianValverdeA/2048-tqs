package eng.uab.tqs.game2048.model;

import java.util.Random;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;

public class Generator {

  public int genRandom() {
    int x = new Random().nextInt(SIZE);
    return x;
  }

  public void randomInicialize(Block[][] board) {
    int x1, y1, x2, y2;

    x1 = genRandom();
    y1 = genRandom();

    do {
      x2 = genRandom();
      y2 = genRandom();
    } while (x1 == x2 && y1 == y2);

    board[x1][y1] = new Block(2, InfoGame.Color.GREEN);
    board[x2][y2] = new Block(2, InfoGame.Color.GREEN);
  }
}
