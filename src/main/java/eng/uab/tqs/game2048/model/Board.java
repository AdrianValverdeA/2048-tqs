package eng.uab.tqs.game2048.model;
import java.util.ArrayList;
import java.util.Random;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;

public class Board {

  private Block[][] board;
  private int score;
  private Generator gen;
  private Character[] movements = {'w','a','s','d'};
  private boolean gameOver = false;

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

  public Board(Board other) {
    this.board = new Block[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        this.board[i][j] = new Block(
            other.board[i][j].getValue(),
            other.board[i][j].getColor()
        );
      }
    }

    this.score = other.getScore();
    this.gen = other.gen;
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
    gen.randomInicialize(getBoard());
  }

  public void random() {
    int x, y;
    do {
      x = gen.genRandom();
      y = gen.genRandom();
    } while (board[x][y].getValue() != 0);

    board[x][y] = new Block(2, InfoGame.Color.GREEN);
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
            join(currentI,currentJ,c);
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
            join(currentI,currentJ,c);
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
            join(currentI,currentJ,c);
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
            join(currentI,currentJ,c);
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
      Board checkBoard = new Board(this);
      movementDone = checkBoard.moveBoard(movements[i]);
      i++;
    }
    gameOver = !movementDone;
    return gameOver;
  }
}
