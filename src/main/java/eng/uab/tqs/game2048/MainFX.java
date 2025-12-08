package eng.uab.tqs.game2048;

import eng.uab.tqs.game2048.controller.GameControllerFX;
import eng.uab.tqs.game2048.model.Game;
import eng.uab.tqs.game2048.view.GameViewFX;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application {

  @Override
  public void start(Stage primaryStage) {
    Game model = new Game();
    GameViewFX view = new GameViewFX(primaryStage);
    new GameControllerFX(model, view);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
