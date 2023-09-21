package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.IOException;
import java.util.Scanner;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;

  /**
   * Constructor for MarbleSolitaireControllerImpl.
   *
   * @param model the game model
   * @param view  the view of the model
   * @param input the game input
   * @throws IllegalArgumentException if either the view, model, input are empty.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Model, View, or Input is null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * Plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if any rendering of a board or message fails.
   */
  @Override
  public void playGame() throws IllegalStateException {Scanner sc = new Scanner(input);
    while (!model.isGameOver()) {
      int[] moves; //array of the move coordinates
      moves = new int[4];
      String temp;

      renderTurn();
      for (int i = 0; i < 4; i++) {
        if (!sc.hasNext()) {
          throw new IllegalStateException("No more inputs");
        }
        temp = sc.next();
        while (!validInput(temp)) {
          renderM("Invalid input, try again." + "\n");
          if (!sc.hasNext()) {
            throw new IllegalStateException("No more inputs");
          }
          temp = sc.next();
        }
        if (equalsQ(temp)) {
          quitGame();
          //QUITS THE METHOD FUNCTION USING RETURN
          return;
        }
        moves[i] = Integer.parseInt(temp) - 1;
      }

      try {
        model.move(moves[0], moves[1], moves[2], moves[3]);
      } catch (IllegalArgumentException e) {
        renderM("Invalid move, try again." + "\n");
      }
    }
    renderM("Game over!" + "\n");
    renderTurn();
  }
  //helper method for rendering messages and getting rid of duplicate try-catches
  private void renderM(String s) {
    try {
      view.renderMessage(s);
    } catch (IOException ex) {
      throw new IllegalStateException("Render failed");
    }
  }

  //checks if a string is an in bounds number for the board
  private boolean validInput(String s) {
    return equalsQ(s) || equalsPos(s);
  }

  //transmits quit game
  private void quitGame() {
    try {
      view.renderMessage("Game quit!" + "\n");
      view.renderMessage("State of game when quit:" + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission failed");
    }
    renderTurn();
  }

  //checks if a string is equal to a positive number
  private boolean equalsPos(String s) {
    try {
      Integer.parseInt(s);
    } catch (Exception e) {
      return false;
    }
    return Integer.parseInt(s) > 0;
  }

  //checks if a string is equal to q
  private boolean equalsQ(String s) {
    return s.equals("q") || s.equals("Q");
  }

  //renders the current turn of the board
  private void renderTurn() {
    try {
      view.renderBoard();
      view.renderMessage("\n" + "Score: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Render failed");
    }
  }
}
