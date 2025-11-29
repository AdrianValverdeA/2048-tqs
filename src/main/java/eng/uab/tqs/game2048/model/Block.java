package eng.uab.tqs.game2048.model;

public class Block
{
  private int value;
  private InfoGame.Color color;

  public Block()
  {
    this.value = 0;
    this.color = InfoGame.Color.NONE;
  }

  public Block(int value, InfoGame.Color color)
  {
    this.value = value;
    this.color = color;
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
    this.value = 0;
    this.color = InfoGame.Color.NONE;
  }

  public void mix() {
    if (this.value > 0 && this.value < 2048) {
      this.value = this.value * 2;
      this.color = InfoGame.Color.values()[this.color.getValue() + 1];
    }
  }
}
