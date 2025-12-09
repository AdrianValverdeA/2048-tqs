package eng.uab.tqs.game2048.controller;

import eng.uab.tqs.game2048.model.Board;
import eng.uab.tqs.game2048.model.Game;
import eng.uab.tqs.game2048.view.GameViewFX;
import javafx.scene.Scene;

//controller class, it gets the values from model and show them using view
public class GameControllerFX implements GameViewFX.Listener {

  private final Game model;
  private final GameViewFX view;
  private boolean testMode = false;

  public GameControllerFX(Game model, GameViewFX view) {
    this.model = model;
    this.view = view;

    view.setListener(this);
    view.showMenu();
  }

  @Override
  public void onStartGame() {
    model.playGameFX();
    view.showGame(model.getGameMatch().getBoard());

    if (!testMode) {
      setupControls();
    }
  }

  @Override
  public void onShowScores() {
    view.showScoresFX(model.getScores());
  }

  @Override
  public void onExit() {
    System.exit(0);
  }

  public void setTestMode(boolean testMode) {
    this.testMode = testMode;
  }

  private void setupControls() {
    Scene scene = view.getScene();
    Board board = model.getGameMatch().getBoard();

    scene.setOnKeyPressed(key -> {
      char mv = switch (key.getCode()) {
        case W -> 'w';
        case A -> 'a';
        case S -> 's';
        case D -> 'd';
        default -> 0;
      };

      if (mv != 0) {
        board.moveBoard(mv);
        view.update(board);
      }

      if (board.isGameOver()) {
        view.showEndGameAndAskName("GAME OVER! Score: " + board.getScore(), name -> {
          model.saveScore(name);
          view.showMenu();
        });
      } else if (board.isGameWinned()) {
        view.showEndGameAndAskName("YOU WIN! Score: " + board.getScore(), name -> {
          model.saveScore(name);
          view.showMenu();
        });
      }

    });
  }
}
