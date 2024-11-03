package edu.grinnell.csc207.util;

public class Tiles {
  
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
   * Flips a tile.
   *
   * @param board the board contiaining the tiles to flip.
   * @param row the row of the tile to be flipped.
   * @param col the column of the tile to be flipped.
   */
  static void flipTile(Matrix<String> board, int row, int col) {
    if (row >= 0 && row < board.height() && col >= 0 && col < board.width()) {
      if (board.get(row, col).equals("O")) {
        board.set(row, col, "X");
      } else {
        board.set(row, col, "O");
      } // if/else
    } // if
  } // flipTile(Matrix<String>, int, int)
} // class Tiles
