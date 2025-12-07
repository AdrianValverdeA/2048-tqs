package eng.uab.tqs.game2048.model;

import java.util.Scanner;

public class GameMatch {
  Board board = new Board();
  ScannerMovement scanner = new ScannerMovement();
  char moveChar;

  public void startGame() {
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
    if (board.isGameOver()) {
      System.out.println("Game Over!");
    }
    else {
      System.out.println("GG WIN!");
    }
  }

  public void setScanner(ScannerMovement scanner) {
    this.scanner = scanner;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public Board getBoard() {
    return board;
  }
}
