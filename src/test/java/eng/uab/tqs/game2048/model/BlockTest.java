package eng.uab.tqs.game2048.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

  @Test
  void constructorDefaultTest() {
    Block block = new Block();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);
  }

  @Test
  void constructorTest() {
    Block block = new Block(2, InfoGame.Color.GREEN);
    assertEquals(block.getValue(), 2);
    assertEquals(block.getColor(), InfoGame.Color.GREEN);

    block = new Block(4, InfoGame.Color.PINK);
    assertEquals(block.getValue(), 4);
    assertEquals(block.getColor(), InfoGame.Color.PINK);

    block = new Block(8, InfoGame.Color.YELLOW);
    assertEquals(block.getValue(), 8);
    assertEquals(block.getColor(), InfoGame.Color.YELLOW);

    block = new Block(16, InfoGame.Color.ORANGE);
    assertEquals(block.getValue(), 16);
    assertEquals(block.getColor(), InfoGame.Color.ORANGE);

    block = new Block(32, InfoGame.Color.RED);
    assertEquals(block.getValue(), 32);
    assertEquals(block.getColor(), InfoGame.Color.RED);

    block = new Block(64, InfoGame.Color.BLUE);
    assertEquals(block.getValue(), 64);
    assertEquals(block.getColor(), InfoGame.Color.BLUE);

    block = new Block(128, InfoGame.Color.PURPLE);
    assertEquals(block.getValue(), 128);
    assertEquals(block.getColor(), InfoGame.Color.PURPLE);

    block = new Block(256, InfoGame.Color.BROWN);
    assertEquals(block.getValue(), 256);
    assertEquals(block.getColor(), InfoGame.Color.BROWN);

    block = new Block(512, InfoGame.Color.GREY);
    assertEquals(block.getValue(), 512);
    assertEquals(block.getColor(), InfoGame.Color.GREY);

    block = new Block(1024, InfoGame.Color.NAVY);
    assertEquals(block.getValue(), 1024);
    assertEquals(block.getColor(), InfoGame.Color.NAVY);

    block = new Block(2048, InfoGame.Color.BLACK);
    assertEquals(block.getValue(), 2048);
    assertEquals(block.getColor(), InfoGame.Color.BLACK);
  }
}