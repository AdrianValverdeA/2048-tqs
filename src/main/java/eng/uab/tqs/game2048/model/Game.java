package eng.uab.tqs.game2048.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Game {
  private String scoreFile = "scores.txt";
  private ScannerGame scanner;
  private GameMatch gameMatch;
  private FileManager fileManager;
  boolean exit;
  List<String> scores;

  public boolean invariant() {
    if (scanner == null) return false;
    if (fileManager == null) return false;
    if (gameMatch == null) return false;
    if (!(exit == true || exit == false)) return false;
    return true;
  }

  public Game() {
    this.scanner = new ScannerGame();
    this.fileManager = new FileManager();
    this.gameMatch = new GameMatch();
    this.exit = false;
  }

  public void menu() {
    assert(invariant());
    while (!exit) {
      System.out.println("\n=== 2048 GAME ===");
      System.out.println("Menu:");
      System.out.println("1. Play game");
      System.out.println("2. Show scores");
      System.out.println("3. Exit");
      System.out.print("Select an option: ");

      char option = scanner.scanOptions();

      switch (option) {
        case '1':
          playGame();
          break;
        case '2':
          showScores();
          break;
        case '3':
          exit = true;
          System.out.println("Thanks for playing! Goodbye!");
          break;
        default:
          System.out.println("Invalid option. Please select 1, 2, or 3.");
      }
    }
    assert(exit);
    assert(invariant());
  }

  public void showScores() {
    assert(invariant());
    System.out.println("\n=== HIGH SCORES ===");
    scores = new ArrayList<>();
    try {
      if (!fileManager.exists(scoreFile) || fileManager.size(scoreFile) == 0) {
        System.out.println("No scores recorded yet. Play a game to be the first!");
        assert(invariant());
        return;
      }

      scores = fileManager.readAll(scoreFile);

      if (scores.isEmpty()) {
        System.out.println("No scores recorded yet. Play a game to be the first!");
        assert(invariant());
        return;
      }

      System.out.println("Rank\tPlayer\t\tScore");
      System.out.println("--------------------------------");

      int rank = 1;
      for (String s : scores) {
        String[] parts = s.split(":");
        System.out.printf("%d\t%s\t\t%s\n", rank, parts[0].trim(), parts[1].trim());
        rank++;
      }

      System.out.println("\nPress Enter to return to menu...");
      //scanner.nextLine(); // commented because if not

    } catch (IOException e) {
      System.out.println("Error reading scores file: " + e.getMessage());
    }
    assert(invariant());
    assert(scores != null);
  }

  public void showScoresFX() {
    assert(invariant());
    scores = new ArrayList<>();
    try {
      if (!fileManager.exists(scoreFile) || fileManager.size(scoreFile) == 0) {
        assert(invariant());
        return;
      }
      scores = fileManager.readAll(scoreFile);
    } catch (IOException e) {
    }
    assert(invariant());
    assert(scores != null);
  }

  public void playGame()
  {
    assert(invariant());
    assert(gameMatch != null);
    gameMatch.startGame();
    saveScore("a");
    assert(gameMatch.getBoard() != null);
    assert(invariant());
  }
  public void playGameFX()
  {
    assert(invariant());
    assert(gameMatch != null);
    gameMatch.startGameFX();
    assert(gameMatch.getBoard() != null);
    assert(invariant());
  }

  public void saveScore(String playerName) {
    assert(invariant());
    if (playerName == null || playerName.isEmpty()) {
      playerName = "Anonymous";
    }
    Board board = gameMatch.getBoard();
    assert(board != null);
    try {
      scores = new ArrayList<>();
      if (fileManager.exists(scoreFile)) {
        scores = fileManager.readAll(scoreFile);
      }

      String newScore = playerName + ":" + board.getScore();
      scores.add(newScore);

      scores.sort((s1, s2) -> {
        int score1 = Integer.parseInt(s1.split(":")[1].trim());
        int score2 = Integer.parseInt(s2.split(":")[1].trim());
        assert(invariant());
        return Integer.compare(score2, score1);
      });

      fileManager.writeAll(scoreFile, scores);
    }
    catch (IOException e) {}

    System.out.println("Score from '" + playerName + "' saved correctly!");
    assert(invariant());
    assert(scores.contains(playerName + ":" + board.getScore()));
  }

  public void setManager(FileManager fileManager) {
    assert(fileManager != null);
    this.fileManager = fileManager;
    assert(this.fileManager == fileManager);
  }

  public void setScanner(ScannerGame scanner) {
    assert(scanner != null);
    this.scanner = scanner;
    assert(this.scanner == scanner);
  }

  public GameMatch getGameMatch() {
    assert(gameMatch != null);
    return gameMatch;
  }

  public List<String> getScores() {
    assert(invariant());
    showScoresFX();
    assert(scores != null);
    assert(invariant());
    return scores;
  }

  public boolean getExit() {
    return exit;
  }
}
