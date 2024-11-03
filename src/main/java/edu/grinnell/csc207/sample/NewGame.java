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
public class NewGame {

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
    TileFlipIO Game = new TileFlipIO();
    Game.getDifficultyLevel(eyes, pen);
    Game.setupGame(args);


    while (!(Game.isGameFinished())) {
      Game.printGame(pen);
      Game.playerAction(pen, eyes);
    }

    // And we're done
    pen.close();
  } // main(String[])
} // class
