package eng.uab.tqs.game2048.model;

import eng.uab.tqs.game2048.model.mock.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMatchTest {

  BoardMock board;
  GeneratorMock gen;
  ScannerMovementMock scanner;
  GameMatch gameMatch;

  @BeforeEach
  void setUp() {
    board = new BoardMock();
    gen = new GeneratorMock();
    scanner = new ScannerMovementMock();
  }

  @Test
  void startGameTest() {
    gameMatch = new GameMatch();
    gameMatch.setScanner(scanner);
    board.setGenerator(gen);
    gen.setConfig("game_match_lose");
    board.setConfig("loose");
    gameMatch.setBoard(board);
    gameMatch.startGame();
    assertEquals(board.isGameOver(), true);
    assertEquals(board.isGameWinned(), false);

    gameMatch = new GameMatch();
    board = new BoardMock();
    gen = new GeneratorMock();
    scanner = new ScannerMovementMock();
    scanner.setConfig("d");
    gameMatch.setScanner(scanner);
    board.setGenerator(gen);
    gen.setConfig("game_match_win");
    board.setConfig("default");
    gameMatch.setBoard(board);
    gameMatch.startGame();
    assertEquals(board.isGameOver(), false);
    assertEquals(board.isGameWinned(), true);
  }

  @Test
  void startGameFXTest() {
    gameMatch = new GameMatch();

    board = new BoardMock();
    gen = new GeneratorMock();

    board.setRestart(true);

    board.setGenerator(null);

    gameMatch.setBoard(board);

    gameMatch.startGameFX();

    Board resultBoard = gameMatch.getBoard();

    assertNotSame(board, resultBoard);

    assertNotNull(resultBoard.getGenerator());

    assertTrue(resultBoard.getRestart());
  }

  @Test
  void startGame_initialRestartAndGeneratorNullTest() {
    gameMatch = new GameMatch();
    board = new BoardMock();
    gen = new GeneratorMock();
    scanner = new ScannerMovementMock();
    scanner.setConfig("d");
    gameMatch.setScanner(scanner);
    board.setGenerator(gen);
    gen.setConfig("game_match_win");
    board.setConfig("default");
    gameMatch.setBoard(board);
    gameMatch.startGame();

    Board resultBoard = gameMatch.getBoard();

    assertNotNull(resultBoard.getGenerator());
    assertTrue(resultBoard.getRestart());

    gen = new GeneratorMock();
    scanner = new ScannerMovementMock();
    scanner.setConfig("d");
    gameMatch.setScanner(scanner);
    board.setGenerator(gen);
    gen.setConfig("game_match_win");
    board.setConfig("default");
    gameMatch.setBoard(board);
    //gameMatch.startGame(); // comented because it does infinite loop impossible to do by test
  }
}