package eng.uab.tqs.game2048.model;

import eng.uab.tqs.game2048.model.mock.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  Game game;
  FileManagerMock fm;
  ScannerGameMock scan;
  ScannerMovementMock scannerMovement;
  GeneratorMock generator;

  @BeforeEach
  void setUp() {
    game = new Game();
    fm = new FileManagerMock();
    scan = new ScannerGameMock();
    game.setManager(fm);
    game.setScanner(scan);
    scannerMovement = new ScannerMovementMock();
    generator = new GeneratorMock();
  }

  @Test
  void saveScoreWithout() throws Exception {
    fm.setConfig("EMPTY_FILE");
    scan.setConfig("inputName");
    BoardMock board = new BoardMock();
    board.setScore(400);
    GameMatch gm = game.getGameMatch();
    scannerMovement.setConfig("d");
    gm.setScanner(scannerMovement);
    board.setGenerator(generator);
    generator.setConfig("game_match_win");
    board.setConfig("default");
    gm.setBoard(board);
    game.playGame();

    fm.setConfig("ONE_SCORE");
    game.showScores();

    List<String> s = game.getScores();
    assertEquals(1, s.size());
    assertEquals("TestUser:400", s.get(0));
  }

  @Test
  void saveScoreWith2() throws Exception {
    scan.setConfig("inputName");
    BoardMock board = new BoardMock();
    board.setScore(2000);
    GameMatch gm = game.getGameMatch();
    scannerMovement.setConfig("d");
    gm.setScanner(scannerMovement);
    board.setGenerator(generator);
    generator.setConfig("game_match_win");
    board.setConfig("default");
    gm.setBoard(board);
    game.playGame();

    fm.setConfig("TWO_SCORES");
    game.showScores();

    List<String> s = game.getScores();

    assertEquals(3, s.size());
    assertEquals("TestUser:2000", s.get(0));
    assertEquals("Alice:1500", s.get(1));
    assertEquals("Bob:900", s.get(2));
  }

  @Test
  void menu2() throws Exception {
    scan.setConfig("2");
    game.menu();
    assertNotEquals(game.getScores(), null);
  }

  @Test
  void menu3() throws Exception {
    assertFalse(game.getExit());
    scan.setConfig("3");
    game.menu();
    assertTrue(game.getExit());
  }

  @Test
  void loopSimpleTest()
  {
    //avoid loop
    fm.setConfig("EMPTY_FILE");
    assertDoesNotThrow(() -> game.showScores());
    List<String> s = game.getScores();
    assertNotNull(s);
    assertEquals(0, s.size());

    //one iteration
    fm.setConfig("ONE_SCORE");
    game.showScores();
    s = game.getScores();
    assertEquals(1, s.size());
    assertEquals("TestUser:400", s.get(0));

    //two iterations
    fm.setConfig("two");
    game.showScores();
    List<String> s2 = game.getScores();
    assertEquals(2, s2.size());

    //Some iterations
    fm.setConfig("some");
    game.showScores();
    List<String> s3 = game.getScores();
    assertEquals(4, s3.size());

    //ten iterations
    fm.setConfig("ten");
    game.showScores();
    List<String> s4 = game.getScores();
    assertEquals(10, s4.size());

  }
}
