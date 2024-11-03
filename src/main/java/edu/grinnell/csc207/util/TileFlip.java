package edu.grinnell.csc207.util;

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


  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Intializes TileFlip game with default sized board.
   */
  public TileFlip() {
    tileBoard = new Tiles(DEFAULT_WIDTH, DEFAULT_HEIGHT);
  } // TileFlip()

  /**
   * Intializes TileFlip game.
   *
   * @param width width of tileBoard
   * @param height height of tileBoard
   */
  public TileFlip(int width, int height) {
    tileBoard = new Tiles(width, height);
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




}
