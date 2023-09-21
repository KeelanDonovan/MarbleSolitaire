package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract Solitaire text view class which implements the marble solitaire view
 * interface, which is used to remove code duplication from the triangle and marble
 * solitaire text view classes.
 */
abstract class AbstractSolitaireTextView implements MarbleSolitaireView {

  protected final MarbleSolitaireModelState state;
  protected final Appendable a;

  /**
   * Constructor for AbstractSolitaireTextView that takes in a state and an Appendable.
   *
   * @param state the MarbleSolitaireModelState that the view class gets information from
   * @param a     The appendable that the view will append onto
   * @throws IllegalArgumentException if the state or appendable inputted are null
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState state, Appendable a)
          throws IllegalArgumentException {
    if (state == null || a == null) {
      throw new IllegalArgumentException("State or Appendable is null");
    }
    this.state = state;
    this.a = a;
  }


  /**
   * Constructor for AbstractSolitaireTextView that takes in a state and sets appendable to
   * System.out.
   *
   * @param state the MarbleSolitaireModelState that the view gets information from
   * @throws IllegalArgumentException if the inputted state is null
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState state)
          throws IllegalArgumentException {
    if (state == null) {
      throw new IllegalArgumentException("State is null");
    }
    this.state = state;
    this.a = System.out;
  }

  /**
   * renders the current state of the board by calling toString on this.
   *
   * @throws IOException if append onto the appendable was not possible
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      a.append(this.toString());
    } catch (Exception e) {
      throw new IOException("render was not possible");
    }
  }

  /**
   * Transmits a messsage to the appendable.
   *
   * @param message the message to be transmitted
   * @throws IOException if it was not possible to append onto the readable
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      a.append(message);
    } catch (Exception e) {
      throw new IOException("render was not possible");
    }

  }
}
