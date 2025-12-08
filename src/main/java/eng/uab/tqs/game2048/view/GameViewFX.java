package eng.uab.tqs.game2048.view;

import eng.uab.tqs.game2048.model.Board;
import eng.uab.tqs.game2048.model.Block;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class GameViewFX {

  private Scene gameScene;
  private Stage stage;
  private GridPane grid;

  public interface Listener {
    void onStartGame();
    void onShowScores();
    void onExit();
  }

  private Listener listener;

  public void setListener(Listener l) {
    this.listener = l;
  }

  public GameViewFX(Stage stage) {
    this.stage = stage;
    this.grid = new GridPane();
  }

  public void showMenu() {
    Button bPlay = new Button("Play Game");
    Button bScores = new Button("Show Scores");
    Button bExit = new Button("Exit");

    VBox layout = new VBox(20, bPlay, bScores, bExit);
    layout.setAlignment(Pos.CENTER);
    layout.setStyle("-fx-padding: 40;");

    Scene menuScene = new Scene(layout, 400, 300);
    stage.setScene(menuScene);
    stage.show();

    bPlay.setOnAction(e -> listener.onStartGame());
    bScores.setOnAction(e -> listener.onShowScores());
    bExit.setOnAction(e -> listener.onExit());
  }

  public void showScoresFX(List<String> scores) {
    VBox box = new VBox(15);
    box.setAlignment(Pos.CENTER);
    box.setStyle("-fx-padding: 20;");

    Label title = new Label("High Scores");
    title.setStyle("-fx-font-size: 22; -fx-font-weight: bold;");
    box.getChildren().add(title);

    VBox scoresBox = new VBox(5);
    scoresBox.setAlignment(Pos.CENTER_LEFT);

    if (scores == null || scores.isEmpty()) {
      scoresBox.getChildren().add(new Label("No scores recorded."));
    } else {
      for (String s : scores) {
        scoresBox.getChildren().add(new Label(s));
      }
    }

    ScrollPane scrollPane = new ScrollPane(scoresBox);
    scrollPane.setFitToWidth(true);
    scrollPane.setPrefViewportHeight(300);
    scrollPane.setPrefViewportWidth(350);

    Button back = new Button("Back to Menu");
    back.setOnAction(e -> showMenu());

    VBox layout = new VBox(20, box, scrollPane, back);
    layout.setAlignment(Pos.CENTER);

    Scene scoreScene = new Scene(layout, 400, 400);
    stage.setScene(scoreScene);
    stage.show();
  }

  public void showGame(Board board) {
    if (gameScene == null) {
      grid.setAlignment(Pos.CENTER);
      grid.setHgap(10);
      grid.setVgap(10);

      gameScene = new Scene(grid, 450, 450);
    }

    stage.setScene(gameScene);
    board.drawBoard();
    update(board);
  }

  public void update(Board board) {
    grid.getChildren().clear();
    Block[][] b = board.getBoard();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        int val = b[i][j].getValue();
        ImageView img = new ImageView(loadImage(val));
        img.setFitWidth(90);
        img.setFitHeight(90);
        grid.add(img, j, i);
      }
    }
  }

  private Image loadImage(int value) {
    if (value == 0) return null;
    return new Image(getClass().getResourceAsStream("/images/" + value + ".png"));
  }

  public Scene getScene() {
    return stage.getScene();
  }

  public interface NameListener {
    void onNameEntered(String name);
  }

  public void showEndGameAndAskName(String message, NameListener listener) {
    VBox box = new VBox(15);
    box.setAlignment(Pos.CENTER);
    box.setStyle("-fx-padding: 40;");

    Label lblMessage = new Label(message);
    lblMessage.setStyle("-fx-font-size: 22; -fx-font-weight: bold;");

    TextField nameField = new TextField();
    nameField.setMaxWidth(200);
    nameField.setPromptText("Enter your name");

    Button submit = new Button("Submit");
    submit.setOnAction(e -> {
      String name = nameField.getText().trim();
      if (!name.isEmpty()) {
        listener.onNameEntered(name);
      }
    });

    box.getChildren().addAll(lblMessage, nameField, submit);

    Scene endScene = new Scene(box, 400, 300);
    stage.setScene(endScene);
    stage.show();
  }
}
