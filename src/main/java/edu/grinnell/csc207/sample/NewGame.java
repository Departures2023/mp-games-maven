package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.IOUtils;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Flip tile game.
 */
public class NewGame {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default width.
   */
  static final int DEFAULT_WIDTH = 10;

  /**
   * The default number of rows.
   */
  static final int DEFAULT_HEIGHT = 10;


  // +----------------+----------------------------------------------
  // | Helper methods |
  // +----------------+

  /**
   * Set up a new board.
   *
   * @param width
   *   The width of the board.
   * @param height
   *   The height of the board.
   *
   * @return the newly created board
   */
  static Matrix<Integer> defaultBoard(int width, int height) {
    return new MatrixV0<Integer>(width, height, 0);
  } // method

  /**
   * @param board The board to flip.
   * @param row The row to flip.
   * @param col The col to flip.
   */
  static void flipBlock(Matrix<Integer> board, int row, int col) {
    if (row >= 0 && row < board.height() && col >= 0 && col < board.width()) {
      if (board.get(row, col) == 1) {
        board.set(row, col, 0);
      } else {
        board.set(row, col, 1);
      } // if
    } // if
  } // method


  /**
   * @param board The board to flip.
   */
  static void flipAll(Matrix<Integer> board) {
    for (int i = 0; i < board.height(); i++) {
      for (int j = 0; j < board.width(); j++) {
        flipBlock(board, i, j);
      } // for
    } // for
  } // method


  /**
   * @param board The board to check.
   * @return return if board is all zero.
   */
  static boolean checkAllZero(Matrix<Integer> board) {
    Matrix<Integer> successBoard = new MatrixV0<Integer>(board.width(), board.height(), 0);
    return board.equals(successBoard);
  } // method


  /**
   * @param board the board to check.
   * @return return if game success.
   */
  static boolean checkSuccess(Matrix<Integer> board) {
    Matrix<Integer> successBoard = new MatrixV0<Integer>(board.width(), board.height(), 1);
    return board.equals(successBoard);
  } // method



   /**
    * Process the board, eliminating any matching cells. (The efficiency
    * of this method could be improved.)
    * @param board The board to process.
    * @param targetRow The targetRow to process.
    * @param targetCol The targetCol to process.
    */
  static void process(Matrix<Integer> board, int targetRow, int targetCol) {
    // flip given block
    flipBlock(board, targetRow, targetCol);

    // flip down block
    flipBlock(board, targetRow - 1, targetCol);

    // flip up block
    flipBlock(board, targetRow + 1, targetCol);

    // flip down block
    flipBlock(board, targetRow, targetCol - 1);

    // flip up block
    flipBlock(board, targetRow, targetCol + 1);
  } // method




  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the game.
   *
   * @param args
   *   Command-line arguments.
   */
  public static void main(String[] args) throws IOException {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));

    int chance = 3;

    // Set up the board
    Matrix<Integer> board = defaultBoard(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    Matrix<Integer> prev = board.clone();

    // Run the game
    String[] commands = new String[] {"FLIPONE", "FLIPALL", "UNDO", "DONE"};
    boolean done = false;

    while (!done) {
      Matrix.print(pen, board, true);
      String command = IOUtils.readCommand(pen, eyes, "Action: ", commands);
      switch (command.toUpperCase()) {
        case "FLIPALL":
          if (chance <= 0) {
            pen.println("Sorry, you've used up your Flip All chances.");
            break;
          } // if

          if (checkAllZero(board)) {
            pen.println("It's cheating. Don't do this");
            break;
          } // if

          prev = board.clone();
          flipAll(board);
          chance--;
          pen.println("FLIPALL remaining: " + chance);
          break;

        case "FLIPONE":
          int rowToRemove =
              IOUtils.readInt(pen, eyes, "Row: ", 0, board.height() - 1);
          int colToRemove =
              IOUtils.readInt(pen, eyes, "Column: ", 0, board.width() - 1);
          prev = board.clone();
          process(board, rowToRemove, colToRemove);
          break;


        case "DONE":
          done = true;
          break;


        case "UNDO":
          if (board.equals(prev)) {
            pen.println("Sorry: Nothing to Undo.");
          } else {
            board = prev.clone();
          } // if/else
          break;

        default:
          pen.printf("Unexpected command: '%s'. Please try again.\n", command);
          break;
      } // switch

      if (checkSuccess(board)) {
        pen.println("Game Success!");
        break;
      } // if

    } // while

    // Print final results
    Matrix.print(pen, board, true);

    // And we're done
    pen.close();
  } // main(String[])
} // class
