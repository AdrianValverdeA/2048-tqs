package eng.uab.tqs.game2048.model;

import eng.uab.tqs.game2048.model.mock.GeneratorMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  Board board;
  GeneratorMock gen;

  @BeforeEach
  void setUp() {
    board = new Board();
    gen = new GeneratorMock();
  }

  @Test
  void constructorTest() {
    Block[][] b = board.getBoard();
    for (Block[] blockLine : b){
      for (Block block : blockLine){
        assertEquals(0, block.getValue());
        assertEquals(InfoGame.Color.NONE, block.getColor());
      }
    }
    assertEquals(board.getScore(),0);
  }

  @Test
  void randomInicializeTest() {
    board.randomInicialize();
    Block[][] b = board.getBoard();
    int values = 0;
    int counter = 0;
    for (Block[] blockLine : b){
      for (Block block : blockLine){
        int value = block.getValue();
        if(value!=0)
        {
          values+=value;
          counter++;
        }
      }
    }
    assertEquals(values,4);
    assertEquals(counter,2);
  }

  @Test
  void randomInicializeMockTest() {
    board.setGenerator(gen);
    board.randomInicialize();
    Block[][] b = board.getBoard();
    assertEquals(b[1][2].getValue(),2);
    assertEquals(b[1][2].getColor(),InfoGame.Color.GREEN);
    assertEquals(b[3][3].getValue(),2);
    assertEquals(b[3][3].getColor(),InfoGame.Color.GREEN);
  }

  @Test
  void moveBlockTest()
  {
    board.setGenerator(gen);
    board.randomInicialize();
    Block[][] b = board.getBoard();
    int[] newCoords = board.moveBlock(1,2,'w'); //up
    b = board.getBoard();
    assertEquals(b[0][2].getValue(),2);
    assertEquals(b[0][2].getColor(),InfoGame.Color.GREEN);
    assertEquals(b[1][2].getValue(),0);
    assertEquals(b[1][2].getColor(),InfoGame.Color.NONE);
    assertEquals(newCoords[0], 0);
    assertEquals(newCoords[1], 2);

    board = new Board();
    board.setGenerator(gen);
    board.randomInicialize();
    b = board.getBoard();
    newCoords = board.moveBlock(1,2,'a'); //left
    b = board.getBoard();
    assertEquals(b[1][1].getValue(),2);
    assertEquals(b[1][1].getColor(),InfoGame.Color.GREEN);
    assertEquals(b[1][2].getValue(),0);
    assertEquals(b[1][2].getColor(),InfoGame.Color.NONE);
    assertEquals(newCoords[0], 1);
    assertEquals(newCoords[1], 1);

    board = new Board();
    board.setGenerator(gen);
    board.randomInicialize();
    b = board.getBoard();
    newCoords = board.moveBlock(1,2,'s'); //down
    b = board.getBoard();
    assertEquals(b[2][2].getValue(),2);
    assertEquals(b[2][2].getColor(),InfoGame.Color.GREEN);
    assertEquals(b[1][2].getValue(),0);
    assertEquals(b[1][2].getColor(),InfoGame.Color.NONE);
    assertEquals(newCoords[0], 2);
    assertEquals(newCoords[1], 2);

    board = new Board();
    board.setGenerator(gen);
    board.randomInicialize();
    b = board.getBoard();
    newCoords = board.moveBlock(1,2,'d'); //right
    b = board.getBoard();
    assertEquals(b[1][3].getValue(),2);
    assertEquals(b[1][3].getColor(),InfoGame.Color.GREEN);
    assertEquals(b[1][2].getValue(),0);
    assertEquals(b[1][2].getColor(),InfoGame.Color.NONE);
    assertEquals(newCoords[0], 1);
    assertEquals(newCoords[1], 3);
    //out of index test
    /*
    board = new Board();
    board.setGenerator(gen);
    board.randomInicialize();
    try {
      newCoords = board.moveBlock(3,3,'d'); //right
    } catch (Exception e) {
      fail("Out of index");
    }
    b = board.getBoard();
    assertEquals(b[3][3].getValue(),2);

    board = new Board();
    board.setGenerator(gen);
    board.randomInicialize();
    try {
      newCoords = board.moveBlock(3,3,'s'); //down
    } catch (Exception e) {
      fail("Out of index");
    }
    b = board.getBoard();
    assertEquals(b[3][3].getValue(),2);
    */
  }

  @Test
  public void canMoveTest() {
    board.setGenerator(gen);
    board.randomInicialize();
    Block[][] b = board.getBoard();
    assertTrue(board.canMove(1,2,'w'));
    assertTrue(board.canMove(1,2,'a'));
    assertTrue(board.canMove(1,2,'s'));
    assertTrue(board.canMove(1,2,'d'));
    assertFalse(board.canMove(3,3,'s'));
    assertFalse(board.canMove(3,3,'d'));
    b[0][0] = new Block(2, InfoGame.Color.GREEN);
    assertFalse(board.canMove(0,0,'w'));
    assertFalse(board.canMove(0,0,'a'));

    //pairwise
    assertFalse(board.canMove(0,0,'w'));
    assertFalse(board.canMove(0,1,'a'));
    assertFalse(board.canMove(0,2,'s'));
    assertFalse(board.canMove(0,3,'d'));
    assertTrue(board.canMove(1,1,'s'));
    assertTrue(board.canMove(1,2,'d'));
    assertTrue(board.canMove(1,3,'w'));
    assertFalse(board.canMove(1,0,'a'));
    assertFalse(board.canMove(2,2,'w'));
    assertTrue(board.canMove(2,3,'a'));
    assertTrue(board.canMove(2,0,'s'));
    assertTrue(board.canMove(2,1,'d'));
    assertFalse(board.canMove(3,3,'s'));
    assertTrue(board.canMove(3,0,'d'));
    assertTrue(board.canMove(3,1,'w'));
    assertTrue(board.canMove(3,2,'a'));

  }

  @Test
  void joinTest() {
    board.setGenerator(gen);
    gen.setConfig("complex_movement");
    board.randomInicialize();
    Block[][] b = board.getBoard();

    // check if configuration has been applied correctly
    assertEquals(2, b[1][1].getValue());
    assertEquals(b[1][1].getColor(),InfoGame.Color.GREEN);
    assertEquals(2, b[1][2].getValue());
    assertEquals(b[1][2].getColor(),InfoGame.Color.GREEN);
    assertEquals(4, b[2][2].getValue());
    assertEquals(b[2][2].getColor(),InfoGame.Color.PINK);
    assertEquals(8, b[2][1].getValue());
    assertEquals(b[2][1].getColor(),InfoGame.Color.YELLOW);
    assertEquals(16, b[0][1].getValue());
    assertEquals(b[0][1].getColor(),InfoGame.Color.ORANGE);

    board.join(1,1,'d');
    assertEquals(0, b[1][1].getValue());
    assertEquals(InfoGame.Color.NONE, b[1][1].getColor());
    assertEquals(4, b[1][2].getValue());
    assertEquals(InfoGame.Color.PINK, b[1][2].getColor());

    board.join(1,2,'s');
    assertEquals(0, b[1][2].getValue());
    assertEquals(InfoGame.Color.NONE, b[1][2].getColor());
    assertEquals(8, b[2][2].getValue());
    assertEquals(InfoGame.Color.YELLOW, b[2][2].getColor());

    board.join(2,2,'a');
    assertEquals(0, b[2][2].getValue());
    assertEquals(InfoGame.Color.NONE, b[2][2].getColor());
    assertEquals(16, b[2][1].getValue());
    assertEquals(InfoGame.Color.ORANGE, b[2][1].getColor());

    board.moveBlock(2,1,'w');
    board.join(1,1,'w');
    assertEquals(0, b[1][1].getValue());
    assertEquals(InfoGame.Color.NONE, b[1][1].getColor());
    assertEquals(32, b[0][1].getValue());
    assertEquals(InfoGame.Color.RED, b[0][1].getColor());
  }

  @Test
  void moveTest(){
    board.setGenerator(gen);
    board.randomInicialize();
    Block[][] b = board.getBoard();
    board.moveBoard('w');
    assertEquals(b[0][2].getValue(), 2);
    assertEquals(b[1][2].getValue(), 2); //new block is generated
    assertEquals(b[0][2].getColor(), InfoGame.Color.GREEN);
    assertEquals(b[1][2].getColor(), InfoGame.Color.GREEN); //new block is generated
    assertEquals(b[0][3].getValue(), 2);
    assertEquals(b[3][3].getValue(), 0);
    assertEquals(b[0][3].getColor(), InfoGame.Color.GREEN);
    assertEquals(b[3][3].getColor(), InfoGame.Color.NONE);

    board.moveBoard('a');
    assertEquals(b[0][0].getValue(), 4);
    assertEquals(b[0][0].getColor(), InfoGame.Color.PINK);
    assertEquals(b[0][2].getColor(), InfoGame.Color.NONE);
    assertEquals(b[0][2].getValue(), 0);
    assertEquals(b[0][3].getValue(), 0);
    assertEquals(b[0][3].getColor(), InfoGame.Color.NONE);

    board = new Board();
    board.setGenerator(gen);
    board.randomInicialize();
    b = board.getBoard();
    board.moveBoard('s');
    assertEquals(b[3][2].getValue(), 2);
    assertEquals(b[3][2].getColor(), InfoGame.Color.GREEN);
    assertEquals(b[3][3].getValue(), 2);
    assertEquals(b[3][3].getColor(), InfoGame.Color.GREEN);

    board.moveBoard('d');
    assertEquals(b[3][3].getValue(), 4);
    assertEquals(b[3][3].getColor(), InfoGame.Color.PINK);
    assertEquals(b[3][2].getValue(), 0);
    assertEquals(b[3][2].getColor(), InfoGame.Color.NONE);
  }

  @Test
  void gameOverTest(){
    board.setGenerator(gen);
    gen.setConfig("game_over");
    board.randomInicialize();
    assertEquals(board.isGameOver(), true);


    Board board = new Board();
    gen.setConfig("game_over_not_1");
    board.setGenerator(gen);
    board.randomInicialize();
    assertEquals(board.isGameOver(), false);

    board = new Board();
    gen.setConfig("not_game_over");
    board.setGenerator(gen);
    board.randomInicialize();
    assertEquals(board.isGameOver(), false);

    board = new Board();
    gen.setConfig("not_game_over_horizontal");
    board.setGenerator(gen);
    board.randomInicialize();
    assertEquals(board.isGameOver(), false);

    board = new Board();
    gen.setConfig("not_game_over_vertical");
    board.setGenerator(gen);
    board.randomInicialize();
    assertEquals(board.isGameOver(), false);
  }

  @Test
  void boardCopyConstructorTest() {
    board.setGenerator(gen);
    board.randomInicialize();
    Block[][] b = board.getBoard();
    Board board2 =  new Board(board);

    assertEquals(board.getScore(), board2.getScore());
    assertEquals(board.getGenerator(), board2.getGenerator());

    Block[][] b2 = board2.getBoard();
    for (int i = 0; i < SIZE; i++)
    {
      for (int j = 0; j < SIZE; j++) {
        assertEquals(b[i][j].getValue(), b2[i][j].getValue());
        assertEquals(b[i][j].getColor(), b2[i][j].getColor());
      }
    }
  }

  @Test
  void isGameWinnedTest() {
    board.setGenerator(gen);
    gen.setConfig("win");
    board.randomInicialize();
    board.moveBoard('d');
    assertEquals(board.isGameWinned(), true);

    board = new Board();
    board.setGenerator(gen);
    gen.setConfig("default");
    board.randomInicialize();
    assertEquals(board.isGameWinned(), false);
  }

    @Test
    void scoreTest() {
        board.setGenerator(gen);
        board.randomInicialize();
        assertEquals(board.getScore(), 0);
        board.moveBoard('s');
        board.moveBoard('d');
        assertEquals(board.getScore(), 4);
        board.moveBoard('d');
        assertEquals(board.getScore(), 8);
        board.moveBoard('s');
        assertEquals(board.getScore(), 16);
    }
}