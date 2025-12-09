package eng.uab.tqs.game2048.model;
import java.util.ArrayList;
import java.util.Random;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;

//This class represents the board of the game
//the board moves all the blocks that has in
//checking the positions of the board
//and looking if the blocks can be mixed
public class Board {

  private Block[][] board;
  private int score;
  private Generator gen;
  private Character[] movements = {'w','a','s','d'};
  private boolean gameOver = false;
  private boolean gameWinned = false;
  private boolean restart;

  private boolean invariant() {
    if (board == null || board.length != SIZE) return false;

    for (int i = 0; i < SIZE; i++) {
      if (board[i] == null || board[i].length != SIZE) return false;

      for (int j = 0; j < SIZE; j++) {
        Block block = board[i][j];
        if (block == null) return false;

        int value = block.getValue();
        InfoGame.Color color = block.getColor();

        if (value < 0 || value > 2048) return false;
        if ((value == 0 && color != InfoGame.Color.NONE) ||
            (value > 0 && color == InfoGame.Color.NONE)) return false;
      }
    }

    if (score < 0) return false;
    if (gen == null) return false;
    if (!(gameOver == true || gameOver == false)) return false;
    if (!(gameWinned == true || gameWinned == false)) return false;
    if (!(restart == true || restart == false)) return false;
    if (board.length != SIZE || board[0].length != SIZE) return false;
    return true;
  }

  public Board() {
    board = new Block[SIZE][SIZE];
    score = 0;
    gen = new Generator();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = new Block(0, InfoGame.Color.NONE);
      }
    }
    restart = false;

    //postconditions
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        assert board[i][j].getValue() == 0;
        assert board[i][j].getColor() == InfoGame.Color.NONE;
      }
    }
    assert score == 0;
    assert gen != null;
    assert !restart;
    assert invariant();
  }

  public Board(Board other) {
    assert other != null;
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

    //postconditions
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        assert this.board[i][j].getValue() == other.board[i][j].getValue();
        assert this.board[i][j].getColor() == other.board[i][j].getColor();
      }
    }

    assert this.score == other.getScore();
    assert(this.board != other.board);
    assert this.gen == other.gen;
    assert invariant();
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

  //this method is usefull to create situations
  //to check some specific cases
  public void randomInicialize() {
    assert invariant();
    gen.randomInicialize(getBoard());
    assert invariant();
  }

  //This method returns the position of
  //the block that will be generated
  public void random() {
    assert invariant();
    int x, y;
    do {
      x = gen.genRandom();
      y = gen.genRandom();
    } while (board[x][y].getValue() != 0);

    board[x][y] = new Block(2, InfoGame.Color.GREEN);

    //postconditions
    assert(x >= 0 && y >= 0);
    assert(x <= 3 && y <= 3);
    assert(board[x][y].getValue() == 2);
    assert(board[x][y].getColor() == InfoGame.Color.GREEN);
    assert invariant();
  }

  public void setGenerator(Generator gen) {
    this.gen = gen;
  }

  public Generator getGenerator()
  {
    return this.gen;
  }

  //This method moves the block
  //using the specific position
  public int[] moveBlock(int i, int j, char c) {
    assert invariant();
    //preconditions
    assert(i >= 0);
    assert(i < SIZE);
    assert(j >= 0 && j < SIZE);
    assert(board[i][j].getValue() > 0);
    assert(c == 'w' || c == 'a' || c == 's' || c == 'd');

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

      default:
        break;
    }
    board[i2][j2] = new Block(board[i][j].getValue(), board[i][j].getColor());
    board[i][j].resetBlock();
    int[] coords = {i2,j2};
    assert invariant();
    assert(board[i][j].getValue() == 0);
    assert(board[i2][j2].getValue() != 0);
    return coords;
  }

  //This method checks if a move can be done
  public boolean canMove(int i, int j, char c) {
    assert invariant();
    //preconditions
    assert(i >= 0 && i < SIZE);
    assert(j >= 0 && j < SIZE);
    assert(board[i][j].getValue() > 0);
    assert(c == 'w' || c == 'a' || c == 's' || c == 'd');

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

      default:
        break;
    }
    assert invariant();
    return result;
  }

  //This method checks if two blocks
  //can be mixed, if they can
  //they will be mixed
  //also modifies the score in each movement
  public boolean join(int i, int j, char c) {
    assert invariant();
    //preconditions
    assert(i >= 0 && i < SIZE);
    assert(j >= 0 && j < SIZE);
    assert(board[i][j].getValue() > 0);
    assert(c == 'w' || c == 'a' || c == 's' || c == 'd');

    boolean result = false;
    int scoreAdd = 0;
    switch (c) {
      case 'w':
        if (i > 0) {
          if (board[i][j].getValue() == board[i-1][j].getValue()) {
            board[i-1][j].mix();
            result = true;
            scoreAdd += board[i-1][j].getValue();
          }
        }
        break;

      case 'a':
        if (j > 0) {
          if (board[i][j].getValue() == board[i][j - 1].getValue()) {
            board[i][j-1].mix();
            result = true;
            scoreAdd += board[i][j-1].getValue();
          }
        }
        break;

      case 's':
        if (i < SIZE - 1) {
          if (board[i][j].getValue() == board[i+1][j].getValue()) {
            board[i+1][j].mix();
            result = true;
            scoreAdd += board[i+1][j].getValue();
          }
        }
        break;

      case 'd':
        if (j < SIZE - 1) {
          if (board[i][j].getValue() == board[i][j + 1].getValue()) {
            board[i][j+1].mix();
            result = true;
            scoreAdd += board[i][j+1].getValue();
          }
        }
        break;

      default:
        break;
    }
    if (result) {
      board[i][j].resetBlock();
      //postcondition
      assert score < scoreAdd+score;
      score += scoreAdd;
    }
    assert invariant();
    return result;
  }

  //This method moves all the blocks
  //in the board, it calls all the previous
  //method to check the position that the
  //block will be
  public boolean moveBoard(char c) {
    assert invariant();
    //preconditions
    assert(c == 'w' || c == 'a' || c == 's' || c == 'd');

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

      default:
        break;
    }
    if (done)
    {
      random();
    }
    assert invariant();
    return done;
  }

  //This method checks if a move can be done
  //if any move can't be done the game is over
  public boolean isGameOver() {
    assert invariant();
    boolean movementDone = false;
    int i = 0;
    for (i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j].getValue() == 0) {
          assert invariant();
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
    assert invariant();
    return gameOver;
  }

  //This method checks if a block
  //has the 2048 value, if its true
  //the game will end, and the player wins
  public boolean isGameWinned() {
    assert invariant();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j].getValue() == 2048) {
          gameWinned = true;
          assert invariant();
          return true;
        }
      }
    }
    assert invariant();
    return gameWinned;
  }

  public void setRestart(boolean restart) {
    this.restart = restart;
  }

  public boolean getRestart() {
    return restart;
  }
}
