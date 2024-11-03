package edu.grinnell.csc207.util;

import java.io.PrintWriter;

public class TileFlip {

  /**
   * The default width.
   */
  static final int DEFAULT_WIDTH = 10;

  /**
   * The default number of rows.
   */
  static final int DEFAULT_HEIGHT = 10;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The board of tiles to play Tileflip on.
   */
  Tiles tileBoard;

  /**
   * The number correlating with the pattern of how the tileBoard is mixed up.
   */
  int idNumber;

  /**
   * The number of times the tileBoard is mixed up.
   */
  int generationNumber;


  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Intializes TileFlip game with default sized board.
   *
   * @param serialNumber the specific pattern of the TileFlip game.
   * @param randomGenerationLimit the number of times the board is mixed up.
   */
  public TileFlip(int serialNumber, int randomGenerationLimit) {
    tileBoard = new Tiles(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    createRandomStart(serialNumber, randomGenerationLimit);
  } // TileFlip()

  /**
   * Intializes TileFlip game.
   *
   * @param width width of tileBoard
   * @param height height of tileBoard
   * @param serialNumber the specific pattern of the TileFlip game.
   * @param randomGenerationLimit the number of times the board is mixed up.
   */
  public TileFlip(int width, int height, int serialNumber, int randomGenerationLimit) {
    tileBoard = new Tiles(width, height);
    createRandomStart(serialNumber, randomGenerationLimit);
  } // TileFlip(int, int)

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Checks if the player has won the tile flip game,
   * by having every tile as O.
   *
   * @param board The board to check.
   * @return returns true if all tiles are O.
   */
  public boolean win() {
    Tiles successBoard = new Tiles(this.tileBoard.width(), this.tileBoard.height());
    return tileBoard.equals(successBoard);
  } // method

  /**
   * Flips the tile located at row and col and the four surrounding tiles
   * in contact with the tile located at row and col.
   *
   * @param row the row of the center tile to be flipped.
   * @param col the column of the center tile to be flipped.
   */
  public void flipTilesAction(int row, int col) {
    this.tileBoard.flipTile(row, col);
    this.tileBoard.flipTile(row + 1, col);
    this.tileBoard.flipTile(row, col + 1);
    this.tileBoard.flipTile(row - 1, col);
    this.tileBoard.flipTile(row, col - 1);
  } // flipTilesAction(int, int)

  /**
   * Creates a solvable TileFlip with a random pattern.
   * 
   * @param serialNumber The id number that generates a specific tile board.
   * @param randomGenerationLimit The amount of times to keep mixing up the board.
   */
  public void createRandomStart(int serialNumber, int randomGenerationLimit) {
    for (int i = 0; i < randomGenerationLimit; i++) {
      this.flipTilesAction((serialNumber * i) % this.tileBoard.height(), (serialNumber * i) % this.tileBoard.width());
    } // for
  } // createRandomStart

  /**
   * Prints idNumber and generationNumber
   *
   * @param pen Used for printing.
   */
  public void giveIDandGenerationNums(PrintWriter pen) {
    pen.println("ID: " + idNumber + "          " + "GENERATION: " + generationNumber);
  } // giveIDandGenerationNums(PrintWriter)

  /**
   * Prints the tileBoard and the id and generation numbers.
   *
   * @param pen Used for printing.
   */
  public void printTileBoard(PrintWriter pen) {
    giveIDandGenerationNums(pen);
    this.tileBoard.print(pen);
  } // printTileBoard(pen)
}
