package eng.uab.tqs.game2048.model;

public class Block
{
  private int value;
  private InfoGame.Color color;

  private boolean invariant() {
    return ((value >= 0 && value <= 2048) && (color.getValue() == log2(value)));
  }

  public Block()
  {
    this.value = 0;
    this.color = InfoGame.Color.NONE;

    //postconditions
    assert(this.value == 0);
    assert(this.color == InfoGame.Color.NONE);
    assert(this.invariant());
  }

  public Block(int value, InfoGame.Color color)
  {
    //preconditions
    assert(value >= 0 && value <= 2048);
    assert(color.getValue() == log2(value));

    this.value = value;
    this.color = color;

    //postconditions
    assert((value == 0 && color == InfoGame.Color.NONE) ||
        (value > 0 && color != InfoGame.Color.NONE));
    assert(this.invariant());
  }

  public int getValue()
  {
    return this.value;
  }

  public InfoGame.Color getColor()
  {
    return this.color;
  }

  public void resetBlock() {
    assert(invariant());
    this.value = 0;
    this.color = InfoGame.Color.NONE;

    //postconditions
    assert(this.value == 0);
    assert(this.color == InfoGame.Color.NONE);
    assert(invariant());
  }

  public void mix() {
    //preconditions
    assert(invariant());
    assert(this.color != InfoGame.Color.NONE);
    assert(this.value > 0);
    assert(this.value < 2048);

    this.value = this.value * 2;
    this.color = InfoGame.Color.values()[this.color.getValue() + 1];

    //postconditions
    assert(this.color != InfoGame.Color.NONE);
    assert(this.value > 0);
    assert(this.value <=  2048);
    assert(invariant());
  }

  private int log2(int x) {
    if ( x!=0 )
    {
      return (int) (Math.log(x) / Math.log(2));
    }
    return 0;
  }
}
