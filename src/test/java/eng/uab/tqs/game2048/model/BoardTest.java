package eng.uab.tqs.game2048.model;

import eng.uab.tqs.game2048.model.mock.GeneratorMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  Board board;
  Generator gen;

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
}