package eng.uab.tqs.game2048.model.mock;

import eng.uab.tqs.game2048.model.Block;
import eng.uab.tqs.game2048.model.Generator;
import eng.uab.tqs.game2048.model.InfoGame;

public class GeneratorMock extends Generator
{
  private int counter = 0;
  private int[] sequence;
  private Block[][] board;
  private int[] values;
  private InfoGame.Color[] colors;

  public GeneratorMock() {
    this.sequence = new int[]{1, 2, 3, 3};
  }

  public int genRandom() {
    if (counter >= sequence.length) {
      counter = 0;
    }
    return sequence[counter++];
  }

  public void randomInicialize(Block[][] board) {
    this.board = board;
    int x, y;
    for (int i = 0; i < values.length; i++) {

      x = genRandom();
      y = genRandom();
      board[x][y] = new Block(values[i],colors[i]);
    }
  }

  public void setConfig(String config) {
    counter = 0;

    switch (config) {
      case "default":
        this.sequence = new int[]{1, 2, 3, 3};
        break;

      case "complex_movement":
        this.sequence = new int[]{1, 1, 1, 2, 2, 2, 2, 1, 0, 1};
        this.colors = new InfoGame.Color[]{InfoGame.Color.GREEN, InfoGame.Color.GREEN,  InfoGame.Color.PINK, InfoGame.Color.YELLOW, InfoGame.Color.ORANGE};
        this.values = new int[]{2, 2, 4, 8, 16};
        break;
    }
  }
}