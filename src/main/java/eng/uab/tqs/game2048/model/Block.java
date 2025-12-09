package eng.uab.tqs.game2048.model;

//This class represents the blocks that will be
//in the board, having value and color
//this blocks will mix with each other if they
// have the same value
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

  //this method sets the values of
  //the block to 0 and None
  //to represents that the block has been eliminated
  public void resetBlock() {
    assert(invariant());
    this.value = 0;
    this.color = InfoGame.Color.NONE;

    //postconditions
    assert(this.value == 0);
    assert(this.color == InfoGame.Color.NONE);
    assert(invariant());
  }

  //this method mixes two blocks
  //of the same value
  public void mix() {
    //preconditions
    assert(invariant());
    assert(this.color != InfoGame.Color.NONE);
    assert(this.value > 0);
    assert(this.value < 2048);

    //We duplicate the value of the block and
    //set the color to the next one
    this.value = this.value * 2;
    this.color = InfoGame.Color.values()[this.color.getValue() + 1];

    //postconditions
    assert(this.color != InfoGame.Color.NONE);
    assert(this.value > 0);
    assert(this.value <=  2048);
    assert(invariant());
  }

  //An auxiliar function to check if the value
  //corresponds to the number of the color
  private int log2(int x) {
    if ( x!=0 )
    {
      return (int) (Math.log(x) / Math.log(2));
    }
    return 0;
  }
}
