package eng.uab.tqs.game2048.controller;

import eng.uab.tqs.game2048.model.mock.GameMock;
import eng.uab.tqs.game2048.view.mock.GameViewFXMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerFXTest {

  //execution flow check in this class
  @Test
  void testOnStartGame() {
    GameMock model = new GameMock();
    GameViewFXMock view = new GameViewFXMock();

    GameControllerFX controller = new GameControllerFX(model, view);
    controller.setTestMode(true);
    controller.onStartGame();

    assertTrue(model.playGameCalled);
    assertTrue(view.showGameCalled);
    assertNotNull(view.lastBoard);
  }

  @Test
  void testOnShowScores() {
    GameMock model = new GameMock();
    GameViewFXMock view = new GameViewFXMock();

    GameControllerFX controller = new GameControllerFX(model, view);

    controller.onShowScores();

    assertTrue(view.showScoresCalled);
    assertEquals("Alice:1000", view.lastScores.get(0));
  }
}
