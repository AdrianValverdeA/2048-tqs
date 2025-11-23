package eng.uab.tqs.game2048.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  Board board;

  @BeforeEach
  void setUp() {
    board = new Board();
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

}