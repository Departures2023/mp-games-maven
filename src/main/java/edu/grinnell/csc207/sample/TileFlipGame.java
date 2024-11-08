package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.TileFlipIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * Runs a Flip tile game.
 *
 * @author Kevin Tang
 * @author Richard Lin
 */
public class TileFlipGame {

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the game.
   *
   * @param args
   *   Command-line arguments.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));

    // Set up the board
    TileFlipIO game = new TileFlipIO();
    game.getDifficultyLevel(pen, eyes);
    game.setupGame(args);


    while (!(game.isGameFinished())) {
      game.printGame(pen);
      game.playerAction(pen, eyes);
    } // while

    // And we're done
    pen.close();
  } // main(String[])
} // class
