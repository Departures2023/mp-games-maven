package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Implements a Tile Flip Board Game with IO components.
 *
 * @author Kevin Tang
 * @author Richard Lin
 */
public class TileFlipIO {

  /**
   * TileFlip Game, tracking tiles pieces.
   */
  TileFlip game;

  /**
   * int tracking the difficultly level of the game.
   */
  int difficultyLevel;

  /**
   * Boolean tracking if the game is finished.
   */
  boolean isGameFinished;

  /**
   * Constructor for TileFlipIO.
   */
  public TileFlipIO() {
    this.isGameFinished = false;
  } // TileFlipIO

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Sets up the dimensions of the Game and initiates the Game Board.
   *
   * @param args Command-line Arguments.
   */
  public void setupGame(String[] args) {
    if ((args.length == 2)) {
      if (checkStringOnlyInt(args[0]) && checkStringOnlyInt(args[1])
          && args[0].length() != 0 && args[1].length() != 0) {
        this.game = new TileFlip(Integer.parseInt(args[0]),
            Integer.parseInt(args[1]), this.difficultyLevel);
        return;
      } // if
    } // if

    this.game = new TileFlip(this.difficultyLevel);
  } // setupGame(String[])

  /**
   * Prints the current Game.
   *
   * @param pen Used for printing.
   */
  public void printGame(PrintWriter pen) {
    game.printTileBoard(pen);
  } // printGame(PrintWriter)

  /**
   * Prompts for a player action and does it.
   *
   * @param pen Used for printing.
   * @param eyes Used for reading.
   * @throws IOException An Exception.
   */
  public void playerAction(PrintWriter pen, BufferedReader eyes) throws IOException {
    if (this.game.win()) {
      this.isGameFinished = true;
      pen.println("Game Success!");
      return;
    } // if

    String[] commands = {"FLIP", "DONE"};
    pen.println("Actions are {\"FLIP\", \"DONE\"}");
    String command = IOUtils.readCommand(pen, eyes, "Action: ", commands);
    switch (command.toUpperCase()) {

      case "FLIP":
        int tileRow =
            IOUtils.readInt(pen, eyes, "Row: ", 0, this.game.giveTileBoard().height());
        int tileCol =
            IOUtils.readInt(pen, eyes, "Column: ", 0, this.game.giveTileBoard().width());
        this.game.flipTilesAction(tileRow, tileCol);
        break;


      case "DONE":
        this.isGameFinished = true;
        break;

      default:
        pen.printf("Unexpected command: '%s'. Please try again.\n", command);
        break;
    } // switch
  } // playerAction(PrintWRiter, BufferedReader)

  /**
   * Returns a boolean value indicating if the game is finished.
   *
   * @return a boolean.
   */
  public boolean isGameFinished() {
    return this.isGameFinished;
  } // isGameFinished

  /**
   * Prompts for Difficulty levek from the player.
   *
   * @param pen Used for printing.
   * @param eyes Used for reading.
   * @throws IOException an Exception.
   */
  public void getDifficultyLevel(PrintWriter pen, BufferedReader eyes) throws IOException {

    pen.println("What is the Difficulty Level?");

    getDifficultyLevelHelper(eyes, pen);

  } // getDifficultyLevel(PrintWriter, BufferedReader)

  // +----------------+------------------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Gets a response from the player that is of the correct form (only numbers) and
   * prompts the player again if the response is incorrect.
   *
   * @param eyes Used for reading.
   * @param pen Used for printing.
   * @throws IOException An exception.
   */
  public void getDifficultyLevelHelper(BufferedReader eyes, PrintWriter pen) throws IOException {
    while (true) {

      pen.print("Difficulty Level: ");

      pen.flush();

      String response = eyes.readLine();
      if (checkStringOnlyInt(response) && response.length() != 0) {
        // cannot be 0 or negative number
        if (response.length() > 7) {
          pen.println("Invalid Input. Input cannot be longer than 7 characters. Please try again.");
        } else if (Integer.parseInt(response) != 0) {

          this.difficultyLevel = Integer.parseInt(response);
          return;
        } else {
          pen.println("Invalid Input. Input cannot be 0. Please try again.");
        } // if/else-if/else
      } else {
        pen.println("Invalid Input. Input must only be numbers. Please try again.");
      } // if/else
    } // while
  } // getDifficultyLevelHelper(BufferedReader, int, PrintWriter)

  /**
   * @param str input string
   * @return true/false
   */
  public boolean checkStringOnlyInt(String str) {
    for (int i = 0; i < str.length(); i++) {
      if ((str.charAt(i) < '0') || (str.charAt(i) > '9')) {
        return false;
      } // if
    } // for
    return true;
  } // checkStringOnlyInt(String)
} // tileflipIO
