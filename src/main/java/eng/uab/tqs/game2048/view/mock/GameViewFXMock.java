package eng.uab.tqs.game2048.view.mock;

import eng.uab.tqs.game2048.model.Board;
import eng.uab.tqs.game2048.view.GameViewFX;
import javafx.scene.Scene;

import java.util.List;

//gameview mock
public class GameViewFXMock extends GameViewFX {

  public boolean showGameCalled = false;
  public boolean showScoresCalled = false;
  public boolean updateCalled = false;
  public boolean endGameCalled = false;

  public Board lastBoard = null;
  public List<String> lastScores = null;
  public String lastEndGameMessage = null;

  public NameListener savedListener = null;

  public GameViewFXMock() {
    super(null);
  }

  @Override
  public void showMenu() {
  }

  @Override
  public void showGame(Board board) {
    showGameCalled = true;
    lastBoard = board;
  }

  @Override
  public void showScoresFX(List<String> scores) {
    showScoresCalled = true;
    lastScores = scores;
  }

  @Override
  public void update(Board board) {
    updateCalled = true;
    lastBoard = board;
  }

  @Override
  public void showEndGameAndAskName(String message, NameListener listener) {
    endGameCalled = true;
    lastEndGameMessage = message;
    savedListener = listener;
  }

  @Override
  public Scene getScene() {
    return new Scene(new javafx.scene.layout.Pane());
  }
}
