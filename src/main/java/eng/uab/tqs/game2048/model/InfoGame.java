package eng.uab.tqs.game2048.model;

//This class represents all the information
//that will be in the game
public class InfoGame {
  //The colors that the blocks can have
  public static enum Color{
    NONE(0),
    GREEN(1),
    PINK(2),
    YELLOW(3),
    ORANGE(4),
    RED(5),
    BLUE(6),
    PURPLE(7),
    BROWN(8),
    GREY(9),
    NAVY(10),
    BLACK(11);

    private final int value;

    Color(int value) {
      //precondition
      assert(value >= 0 && value <= 11);

      this.value = value;

      //postcondition
      assert (value == this.value && this.value >= 0 && this.value <= 11);
    }

    public int getValue() {
      return value;
    }
  }

  public final static int N_COL_BOARD = 4;
  public final static int N_ROW_BOARD = 4;
  public final static int SIZE = 4;
}
