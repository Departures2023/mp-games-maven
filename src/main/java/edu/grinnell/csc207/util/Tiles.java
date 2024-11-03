package edu.grinnell.csc207.util;

import java.io.PrintWriter;

public class Tiles {
  
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * A matrix containing "tiles" that can be flipped, i.e.
   * either store X or O.
   * Used to record tiles.
   */
  Matrix<String> tileBoard;

  /**
   * Width of tileBoard.
   */
  int width;

  /**
   * Height of tile Board.
   */
  int height;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+


  /**
   * Sets up a Board of Tiles.
   *
   * @param widthT Width of the Tile Board
   * @param heightT height of the Tile Board
   */
  public Tiles(int widthT, int heightT) {
    this.width = widthT;
    this.height = heightT;

    tileBoard = new MatrixV0<>(width, height, "O");
  } // Tiles(int, int)


  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Returns the state of the tile located at width and height.
   *
   * @param width width of the tile to look at.
   * @param height height of the tile to look at.
   */
  public String tileState(int width, int height) {
    return tileBoard.get(width, height);
  } // tileState(int, int)

  /**
   * Returns the width of the tile board.
   *
   * @return the width.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Returns the height of the tile board.
   *
   * @return the height.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Flips a tile.
   *
   * @param board the board containing the tiles to flip.
   * @param row the row of the tile to be flipped.
   * @param col the column of the tile to be flipped.
   */
  public void flipTile(int row, int col) {
    if (row >= 0 && row < this.height() && col >= 0 && col < this.width()) {
      if (this.tileBoard.get(row, col).equals("O")) {
        this.tileBoard.set(row, col, "X");
      } else {
        this.tileBoard.set(row, col, "O");
      } // if/else
    } // if
  } // flipTile(int, int)

  /**
   * Returns a boolean indicating if the Tiles, board and this.tileBoard, are equal.
   *
   * @param board the board to compare tileBoard to.
   * @return a boolean indicating equality of tiles.
   */
  public boolean checkEqual(Tiles board){
    return this.tileBoard.equals(board.tileBoard);
  } // checkEqual(Tiles)

  /**
   * Prints the tileBoard.
   *
   * @param pen Used for printing.
   */
  public void print(PrintWriter pen) {
    Matrix.print(pen, this.tileBoard);
  } // print(PrintWriter)

} // class Tiles
