package eng.uab.tqs.game2048.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoGameTest {

  // we can't do limit testing because enums can't be created with different values
  @Test
  void getColorTest()
  {
    InfoGame.Color color = InfoGame.Color.NONE;
    assertEquals(0,color.getValue());

    color = InfoGame.Color.GREEN;
    assertEquals(1,color.getValue());

    color = InfoGame.Color.PINK;
    assertEquals(2,color.getValue());

    color = InfoGame.Color.YELLOW;
    assertEquals(3,color.getValue());

    color = InfoGame.Color.ORANGE;
    assertEquals(4,color.getValue());

    color = InfoGame.Color.RED;
    assertEquals(5,color.getValue());

    color = InfoGame.Color.BLUE;
    assertEquals(6,color.getValue());

    color = InfoGame.Color.PURPLE;
    assertEquals(7,color.getValue());

    color = InfoGame.Color.BROWN;
    assertEquals(8,color.getValue());

    color = InfoGame.Color.GREY;
    assertEquals(9,color.getValue());

    color = InfoGame.Color.NAVY;
    assertEquals(10,color.getValue());

    color = InfoGame.Color.BLACK;
    assertEquals(11,color.getValue());
  }

  @Test
  void testCICD() {
    fail("fail");
  }
}
