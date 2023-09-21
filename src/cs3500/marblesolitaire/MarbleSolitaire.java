package cs3500.marblesolitaire;

import java.io.InputStreamReader;
import java.util.Scanner;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Class responsible for interacting with the user to create and start playing a solitaire
 * game.
 */
public final class MarbleSolitaire {

  /**
   * The main method to be run to start and play the solitaire game, the main method will exit
   * if given an invalid argument to start the game.
   *
   * @param args the array of strings that will be used to create a solitaire game.
   */
  public static void main(String[] args) throws IllegalArgumentException {
    StringBuilder b = new StringBuilder();
    for (String s : args) {
      b.append(s);
      b.append(" ");
    }
    Scanner sc = new Scanner(b.toString());
    Appendable out = System.out;

    int boardSize = 0;
    boolean holeGiven = false;
    boolean sizeGiven = false;
    int sCol = 0;
    int sRow = 0;
    int size = args.length;



    if (size != 1 && size != 4 && size != 3 && size != 6) {
      System.out.println("invalid argument length");
      System.exit(1);
    }

    String type = sc.next();

    while (sc.hasNext() && (!sizeGiven || !holeGiven)) {
      String next = sc.next();
      if (next.equals("-size") && !sizeGiven) {
        try {
          int bTemp = Integer.parseInt(sc.next());
          boardSize = bTemp;
          sizeGiven = true;
        } catch (Exception e) {
          System.out.println("size not right");
          System.exit(1);
        }
      }
      if (!sc.hasNext()) {
        break;
      }
      if (next.equals("-hole") && !holeGiven) {
        try {
          sRow = Integer.parseInt(sc.next());
          sCol = Integer.parseInt(sc.next());
          holeGiven = true;
        } catch (Exception e) {
          System.out.println("hole not correct");
          System.exit(1);
        }
      }
    }

    MarbleSolitaireView view;
    MarbleSolitaireControllerImpl controller;
    Readable in = new InputStreamReader(System.in);
    MarbleSolitaireFactory f = new MarbleSolitaireFactory();
    MarbleSolitaireModel model = null;

    if (holeGiven && sizeGiven) {
      model = f.getModel(type, boardSize, sRow, sCol);
    } else if (holeGiven) {
      model = f.getModel(type, sRow, sCol);
    } else if (sizeGiven) {
      model = f.getModel(type, boardSize);
    } else if (!holeGiven && !sizeGiven) {
      model = f.getModel(type);
    }

    view = f.getView(type, model, out);
    controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
  }
}

