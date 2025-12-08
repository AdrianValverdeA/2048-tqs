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

    //All the blocks that can be created
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

  @Test
  public void resetBlockTest() {

    //Check all the reset cases
    //Unnecessary checking limit values
    //blocks with values different to normals
    //can't be created
    //we check 2048 value for future implementation

    Block block = new Block(2, InfoGame.Color.GREEN);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(4, InfoGame.Color.PINK);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(8, InfoGame.Color.YELLOW);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(16, InfoGame.Color.ORANGE);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(32, InfoGame.Color.RED);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(64, InfoGame.Color.BLUE);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(128, InfoGame.Color.PURPLE);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(256, InfoGame.Color.BROWN);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(512, InfoGame.Color.GREY);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(1024, InfoGame.Color.NAVY);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

    block = new Block(2048, InfoGame.Color.BLACK);
    block.resetBlock();
    assertEquals(block.getValue(), 0);
    assertEquals(block.getColor(), InfoGame.Color.NONE);

  }

  @Test
  public void mixTest(){

    //Check all the mix cases
    //Unnecessary checking limit values
    //blocks with values different to normals
    //can't be created

    Block block = new Block(2, InfoGame.Color.GREEN);
    block.mix();
    assertEquals(block.getValue(), 4);
    assertEquals(block.getColor(), InfoGame.Color.PINK);

    block.mix();
    assertEquals(block.getValue(), 8);
    assertEquals(block.getColor(), InfoGame.Color.YELLOW);

    block.mix();
    assertEquals(block.getValue(), 16);
    assertEquals(block.getColor(), InfoGame.Color.ORANGE);

    block.mix();
    assertEquals(block.getValue(), 32);
    assertEquals(block.getColor(), InfoGame.Color.RED);

    block.mix();
    assertEquals(block.getValue(), 64);
    assertEquals(block.getColor(), InfoGame.Color.BLUE);

    block.mix();
    assertEquals(block.getValue(), 128);
    assertEquals(block.getColor(), InfoGame.Color.PURPLE);

    block.mix();
    assertEquals(block.getValue(), 256);
    assertEquals(block.getColor(), InfoGame.Color.BROWN);

    block.mix();
    assertEquals(block.getValue(), 512);
    assertEquals(block.getColor(), InfoGame.Color.GREY);

    block.mix();
    assertEquals(block.getValue(), 1024);
    assertEquals(block.getColor(), InfoGame.Color.NAVY);

    block.mix();
    assertEquals(block.getValue(), 2048);
    assertEquals(block.getColor(), InfoGame.Color.BLACK);

    //check it is the last, no changes
    block.mix();
    assertEquals(block.getValue(), 2048);
    assertEquals(block.getColor(), InfoGame.Color.BLACK);

    //check it is none, no changes
    Block block0 = new Block(0, InfoGame.Color.NONE);
    block0.mix();
    assertEquals(block0.getValue(), 0);
    assertEquals(block0.getColor(), InfoGame.Color.NONE);
  }
}