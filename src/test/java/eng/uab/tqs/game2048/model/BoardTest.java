package eng.uab.tqs.game2048.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  @Test
  void constructorTest() {
    Board board = new Board();
    Block[][] b = board.getBoard();
    for (Block[] blockLine : b){
      for (Block block : blockLine){
        assertEquals(0, block.getValue());
        assertEquals(InfoGame.Color.NONE, block.getColor());
      }
    }
    assertEquals(board.getScore(),0);
  }


}