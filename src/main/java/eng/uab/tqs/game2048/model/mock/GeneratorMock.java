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
    this.values = new int[]{2, 2};
    this.colors = new InfoGame.Color[]{InfoGame.Color.GREEN, InfoGame.Color.GREEN};
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

      case "game_over_not_1": // not 2,0
        this.sequence = new int[]{0,0, 0,1, 0,2, 0,3, 1,0, 1,1, 1,2, 1,3, 2,1, 2,2, 2,3, 3,0, 3,1, 3,2, 3,3};
        this.colors = new InfoGame.Color[]{InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN};
        this.values = new int[]{2, 4, 2, 4, 4, 2, 4, 2, 4, 2, 4, 4, 2, 4, 2};
        break;

      case "not_game_over": // 2,1 equal to up, left,right, down
        this.sequence = new int[]{0,0, 0,1, 0,2, 0,3, 1,0, 1,1, 1,2, 1,3, 2,0, 2,1, 2,2, 2,3, 3,0, 3,1, 3,2, 3,3};
        this.colors = new InfoGame.Color[]{InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN,
            InfoGame.Color.GREEN, InfoGame.Color.GREEN, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN};
        this.values = new int[]{2, 4, 2, 4, 4, 2, 4, 2, 2, 2, 2, 4, 4, 2, 4, 2};
        break;

      case "not_game_over_horizontal": // 2,1 equal to 2,2
        this.sequence = new int[]{0,0, 0,1, 0,2, 0,3, 1,0, 1,1, 1,2, 1,3, 2,0, 2,1, 2,2, 2,3, 3,0, 3,1, 3,2, 3,3};
        this.colors = new InfoGame.Color[]{InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN,
            InfoGame.Color.GREEN, InfoGame.Color.YELLOW, InfoGame.Color.YELLOW, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN};
        this.values = new int[]{2, 4, 2, 4, 4, 2, 4, 2, 2, 8, 8, 4, 4, 2, 4, 2};
        break;

      case "not_game_over_vertical": // 2,1 equal to 3,1
        this.sequence = new int[]{0,0, 0,1, 0,2, 0,3, 1,0, 1,1, 1,2, 1,3, 2,0, 2,1, 2,2, 2,3, 3,0, 3,1, 3,2, 3,3};
        this.colors = new InfoGame.Color[]{InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN,
            InfoGame.Color.GREEN, InfoGame.Color.YELLOW, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.YELLOW, InfoGame.Color.PINK, InfoGame.Color.GREEN};
        this.values = new int[]{2, 4, 2, 4, 4, 2, 4, 2, 2, 8, 2, 4, 4, 8, 4, 2};
        break;

      case "game_over":
        this.sequence = new int[]{0,0, 0,1, 0,2, 0,3, 1,0, 1,1, 1,2, 1,3, 2,0, 2,1, 2,2, 2,3, 3,0, 3,1, 3,2, 3,3};
        this.colors = new InfoGame.Color[]{InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN,
            InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK,
            InfoGame.Color.PINK, InfoGame.Color.GREEN, InfoGame.Color.PINK, InfoGame.Color.GREEN};
        this.values = new int[]{2, 4, 2, 4, 4, 2, 4, 2, 2, 4, 2, 4, 4, 2, 4, 2};
        break;
    }
  }
}