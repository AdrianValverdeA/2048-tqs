package eng.uab.tqs.game2048.model;
public class InfoGame {
  public static enum Key {
    UP, DOWN, LEFT, RIGHT
  }

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
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  public final static int N_COL_BOARD = 4;
  public final static int N_ROW_BOARD = 4;
  public final static int SIZE = 4;
}
