package eng.uab.tqs.game2048.model.mock;

import eng.uab.tqs.game2048.model.*;
import java.util.*;

public class GameMock extends Game {

  public boolean playGameCalled = false;
  public boolean saveScoreCalled = false;
  public String savedName = null;

  private Board board;
  private GameMatch match;
  private List<String> scores = Arrays.asList("Alice:1000", "Bob:600");

  public GameMock() {
    board = new Board();
    board.randomInicialize();

    match = new GameMatch() {
      @Override
      public Board getBoard() {
        return board;
      }
    };
  }

  @Override
  public void playGameFX() {
    playGameCalled = true;
  }

  @Override
  public GameMatch getGameMatch() {
    return match;
  }

  @Override
  public List<String> getScores() {
    return scores;
  }

  @Override
  public void saveScore(String name) {
    saveScoreCalled = true;
    savedName = name;
  }
}
