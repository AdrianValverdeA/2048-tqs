package eng.uab.tqs.game2048.model.mock;

import eng.uab.tqs.game2048.model.Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static eng.uab.tqs.game2048.model.InfoGame.SIZE;

public class GeneratorMock extends Generator
{
  private int counter = 0;
  private int[] genRandomArray = {1, 2, 3, 3};

  public int genRandom() {
    return genRandomArray[counter++];
  }

}