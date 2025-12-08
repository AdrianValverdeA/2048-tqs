package eng.uab.tqs.game2048.model;

import java.util.Scanner;

public class GameMatch {
  Board board = new Board();
  ScannerMovement scanner = new ScannerMovement();
  char moveChar;

  private boolean invariant() {
    if (board == null) return false;
    if (scanner == null) return false;
    return true;
  }

  public void startGame() {
    assert(invariant());
    if (board.getRestart()) {
      System.out.println("GAME IS RESTARTING");
      board = new Board();
    }
    if (board.getGenerator() == null) {
      board.setGenerator(new Generator());
    }
    board.randomInicialize();
    board.drawBoard();
    while (!board.isGameOver() && !board.isGameWinned()) {
      System.out.println("\n");
      System.out.print("Input your movement: (w/a/s/d): ");
      String input = scanner.scan();
      System.out.println(input);
      moveChar = input.charAt(0);
      board.moveBoard(moveChar);
      board.drawBoard();
    }
    board.setRestart(true);
    if (board.isGameOver()) {
      System.out.println("Game Over!");
    }
    else {
      System.out.println("GG WIN!");
    }
    assert(invariant());
    assert board.getRestart();
    assert board.isGameOver() || board.isGameWinned();
  }

  public void setScanner(ScannerMovement scanner) {
    assert(scanner != null);
    this.scanner = scanner;
    assert(invariant());
    assert (this.scanner == scanner);
  }

  public void setBoard(Board board) {
    assert(board != null);
    this.board = board;
    assert (this.board == board);
  }

  public Board getBoard() {
    assert(invariant());
    return board;
  }

  public void startGameFX() {
    assert(invariant());
    if (board.getRestart()) {
      board = new Board();
    }
    if (board.getGenerator() == null) {
      board.setGenerator(new Generator());
    }
    board.randomInicialize();
    board.setRestart(true);
    assert board.getRestart();
    assert(invariant());
  }
}
