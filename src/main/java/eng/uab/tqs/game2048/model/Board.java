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

  public int[] moveBlock(int i, int j, char c) {
    int i2 = i;
    int j2 = j;
    switch (c) {
      case 'a':
        j2 = j-1;
        break;

      case 'w':
        i2 = i-1;
        break;

      case 'd':
        j2 = j+1;
        break;

      case 's':
        i2 = i+1;
        break;
    }
    board[i2][j2] = new Block(board[i][j].getValue(), board[i][j].getColor());
    board[i][j].resetBlock();
    int[] coords = {i2,j2};
    return coords;
  }

  public boolean canMove(int i, int j, char c) {
    boolean result = false;
    switch (c) {
      case 'a':
        if (j > 0) {
          result = (0 == board[i][j - 1].getValue());
        }
        break;

      case 'w':
        if (i > 0) {
          result = (0 == board[i - 1][j].getValue());
        }
        break;

      case 'd':
        if (j < SIZE - 1) {
          result = (0 == board[i][j + 1].getValue());
        }
        break;

      case 's':
        if (i < SIZE - 1) {
          result = (0 == board[i + 1][j].getValue());
        }
        break;
    }
    return result;
  }
}
