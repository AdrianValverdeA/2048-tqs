package eng.uab.tqs.game2048.model;
import java.util.ArrayList;
import java.util.Random;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;

public class Board {

  private Block[][] board;
  private int score;
  private Generator gen;

  public Board() {
    board = new Block[SIZE][SIZE];
    score = 0;
    gen = new Generator();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = new Block(0, InfoGame.Color.NONE);
      }
    }

  }

  public int getScore() {
    return score;
  }

  public Block[][] getBoard() {
    return board;
  }

  public void drawBoard() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        System.out.print(board[i][j].getValue());
      }
      System.out.println("");
    }
  }

  public void randomInicialize() {
    int x1, y1, x2, y2;

    x1 = gen.genRandom();
    y1 = gen.genRandom();

    do {
      x2 = gen.genRandom();
      y2 = gen.genRandom();
    } while (x1 == x2 && y1 == y2);

    board[x1][y1] = new Block(2, InfoGame.Color.GREEN);
    board[x2][y2] = new Block(2, InfoGame.Color.GREEN);
  }

  public void setGenerator(Generator gen) {
    this.gen = gen;
  }
}
