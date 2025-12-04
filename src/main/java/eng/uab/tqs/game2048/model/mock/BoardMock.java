package eng.uab.tqs.game2048.model.mock;

import eng.uab.tqs.game2048.model.*;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;
import java.util.ArrayList;
import java.util.Random;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;
public class BoardMock extends Board {
    private Block[][] board;
    private int score;
    private Generator gen;
    private Character[] movements = {'w','a','s','d'};
    private boolean gameOver = false;
    private boolean gameWinned = false;
    private boolean simulated;

    public BoardMock() {
      this.board = new Block[SIZE][SIZE];
      this.score = 0;
      this.gen = new Generator();
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          this.board[i][j] = new Block(0, InfoGame.Color.NONE);
        }
      }
      this.simulated = false;
    }

    public BoardMock(BoardMock other) {
      this.board = new Block[SIZE][SIZE];
      Block[][] b2 = other.getBoard();
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          this.board[i][j] = new Block(
              b2[i][j].getValue(),
              b2[i][j].getColor()
          );
        }
      }

      this.simulated = true;
      this.score = other.getScore();
      this.gen = other.getGenerator();
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
    return;
    }

    public void setConfig(String config) {
      int[] sequence;
      int[] values;
      InfoGame.Color[] colors;
      switch (config) {
        case "loose":
          sequence = new int[]{
              0, 0, 0, 1,
              1, 0, 1, 1, 1, 2, 1, 3,
              2, 0, 2, 1, 2, 2, 2, 3,
              3, 0, 3, 1, 3, 2, 3, 3
          };

          values = new int[]{
              2, 32,
              2, 8, 2, 8,
              32, 128, 64, 32,
              8, 2, 16, 4
          };
          colors = new InfoGame.Color[]{
              InfoGame.Color.GREEN, InfoGame.Color.ORANGE,
              InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
              InfoGame.Color.ORANGE, InfoGame.Color.RED, InfoGame.Color.BLUE, InfoGame.Color.ORANGE,
              InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.YELLOW, InfoGame.Color.PINK
          };

          for (int i = 0; i < sequence.length; i+=2) {
            board[sequence[i]][sequence[i+1]] = new Block(values[i/2], colors[i/2]);
          }
          break;

        default:
          sequence = new int[]{
              0, 0, 0, 1, 0, 2, 0, 3,
              1, 0, 1, 1, 1, 2, 1, 3,
              2, 0, 2, 1, 2, 2, 2, 3,
              3, 0, 3, 1, 3, 2, 3, 3
          };

          values = new int[]{
              2, 32, 1024, 1024,
              512, 8, 2, 8,
              32, 128, 64, 32,
              8, 2, 16, 4
          };

          colors = new InfoGame.Color[]{
              InfoGame.Color.GREEN, InfoGame.Color.ORANGE, InfoGame.Color.NAVY, InfoGame.Color.NAVY,
              InfoGame.Color.GREY, InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
              InfoGame.Color.ORANGE, InfoGame.Color.RED, InfoGame.Color.BLUE, InfoGame.Color.ORANGE,
              InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.YELLOW, InfoGame.Color.PINK
          };

          for (int i = 0; i < sequence.length; i+=2) {
            board[sequence[i]][sequence[i+1]] = new Block(values[i/2], colors[i/2]);
          }
          break;
      }
    }

    public void random() {
      if (!simulated) {
        int x, y;
        do {
          x = gen.genRandom();
          y = gen.genRandom();
        } while (board[x][y].getValue() != 0);

        board[x][y] = new Block(2, InfoGame.Color.GREEN);
      }
    }

  public void setGenerator(Generator gen) {
    this.gen = gen;
  }

  public Generator getGenerator()
  {
    return this.gen;
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

  public boolean join(int i, int j, char c) {
    boolean result = false;
    switch (c) {
      case 'w':
        if (i > 0) {
          if (board[i][j].getValue() == board[i-1][j].getValue()) {
            board[i-1][j].mix();
            result = true;
          }
        }
        break;

      case 'a':
        if (j > 0) {
          if (board[i][j].getValue() == board[i][j - 1].getValue()) {
            board[i][j-1].mix();
            result = true;
          }
        }
        break;

      case 's':
        if (i < SIZE - 1) {
          if (board[i][j].getValue() == board[i+1][j].getValue()) {
            board[i+1][j].mix();
            result = true;
          }
        }
        break;

      case 'd':
        if (j < SIZE - 1) {
          if (board[i][j].getValue() == board[i][j + 1].getValue()) {
            board[i][j+1].mix();
            result = true;
          }
        }
        break;
    }
    if (result) {
      board[i][j].resetBlock();
    }
    return result;
  }

  public boolean moveBoard(char c) {
    int i = 0;
    int j = 0;
    int currentI = 0;
    int currentJ = 0;
    boolean done = false;
    boolean joined = false;
    switch (c) {
      case 'w':
        i = 1;
        j = 0;
        while (i < SIZE)
        {
          while (j < SIZE)
          {
            if (board[i][j].getValue() == 0)
            {
              j++;
              continue;
            }
            currentI = i;
            currentJ = j;
            while(canMove(currentI, currentJ, c))
            {
              int[] coords = moveBlock(currentI, currentJ, c);
              currentI =  coords[0];
              currentJ = coords[1];
              done = true;
            }
            joined = join(currentI,currentJ,c);
            if (joined) {
              done = true;
            }
            j++;
          }
          i++;
          j = 0;
        }
        break;

      case 'a':
        i = 0;
        j = 1;
        while (j < SIZE)
        {
          while (i < SIZE)
          {
            if (board[i][j].getValue() == 0)
            {
              i++;
              continue;
            }
            currentI = i;
            currentJ = j;
            while(canMove(currentI, currentJ, c))
            {
              int[] coords = moveBlock(currentI, currentJ, c);
              currentI =  coords[0];
              currentJ = coords[1];
              done = true;
            }
            joined = join(currentI,currentJ,c);
            if (joined) {
              done = true;
            }
            i++;
          }
          j++;
          i = 0;
        }
        break;

      case 's':
        i = 2;
        j = 0;
        while (i >= 0)
        {
          while (j < SIZE)
          {
            if (board[i][j].getValue() == 0)
            {
              j++;
              continue;
            }
            currentI = i;
            currentJ = j;
            while(canMove(currentI, currentJ, c))
            {
              int[] coords = moveBlock(currentI, currentJ, c);
              currentI =  coords[0];
              currentJ = coords[1];
              done = true;
            }
            joined = join(currentI,currentJ,c);
            if (joined) {
              done = true;
            }
            j++;
          }
          i--;
          j = 0;
        }
        break;

      case 'd':
        i = 0;
        j = 2;
        while (j >= 0)
        {
          while (i < SIZE)
          {
            if (board[i][j].getValue() == 0)
            {
              i++;
              continue;
            }
            currentI = i;
            currentJ = j;
            while(canMove(currentI, currentJ, c))
            {
              int[] coords = moveBlock(currentI, currentJ, c);
              currentI =  coords[0];
              currentJ = coords[1];
              done = true;
            }
            joined = join(currentI,currentJ,c);
            if (joined) {
              done = true;
            }
            i++;
          }
          j--;
          i = 0;
        }
        break;
    }
    if (done)
    {
      random();
    }
    return done;
  }

  public boolean isGameOver() {
    boolean movementDone = false;
    int i = 0;
    for (i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j].getValue() == 0) {
          return false;
        }
      }
    }
    i = 0;
    while(!movementDone && i < movements.length) {
      BoardMock checkBoard = new BoardMock(this);
      movementDone = checkBoard.moveBoard(movements[i]);
      i++;
    }
    gameOver = !movementDone;
    return gameOver;
  }

  public boolean isGameWinned() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j].getValue() == 2048) {
          gameWinned = true;
          return true;
        }
      }
    }
    return gameWinned;
  }

}
