package eng.uab.tqs.game2048.model;
import java.util.Random;

public class Board {

  private final int SIZE = 4;
  private Block[][] board;
  private int score;

  public Board() {
    board = new Block[SIZE][SIZE];
    score = 0;

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
    for (int i = 0; i < 2; i++) {
      int x = new Random().nextInt(SIZE);
      int y = new Random().nextInt(SIZE);
      board[x][y] = new Block(2, InfoGame.Color.GREEN);
    }
  }
}
