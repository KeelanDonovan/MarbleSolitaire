package cs3500.marblesolitaire.controller;

public interface MarbleSolitaireController {
  /**
   * Plays a new game of MarbleSolitaire
   * @throws IllegalArgumentException if the controller is unable to read input of transmit output
   */
  void playGame() throws IllegalStateException;
}
