package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * class which represents a MarbleSolitaireTextView, which uses a MarbleSolitaireModelState
 * as a refernce to display a view, and uses an appendable to display an output.
 * This class extends the abstract solitaire text view class which implements
 * the MarbleSolitaireView interface
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructor for MarbleSolitaireTextView that takes in a state and an Appendable.
   *
   * @param state the MarbleSolitaireModelState that the view class gets information from
   * @param a The appendable that the view will append onto
   * @throws IllegalArgumentException if the state or appendable inputted are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable a)
          throws IllegalArgumentException {
    super(state, a);
  }


  /**
   * Constructor for MarbleSolitaireTextView that takes in a state and sets appendable to
   * System.out.
   * @param state the MarbleSolitaireModelState that the view gets information from
   * @throws IllegalArgumentException if the inputted state is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state) throws IllegalArgumentException {
    super(state);
  }

  /**
   * returns the board as a string representation.
   *
   * @return returns a string of the board
   */
  @Override
  public String toString() {
    int size = state.getBoardSize();
    int armSize = (size + 2) / 3;
    StringBuilder b = new StringBuilder("");
    String s = "";
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        if (state.getSlotAt(y, x).equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          b.append(" ");
        } else {
          if (state.getSlotAt(y, x).equals(MarbleSolitaireModelState.SlotState.Marble)) {
            b.append("O");
          } else {
            b.append("_");
          }
          if (x == (size - 1)) {
            b.append("\n");
          }
          if (((0 <= y && y <= (armSize - 2)) || ((size - armSize + 1) <= y && y <= (size - 1)))
                  && state.getSlotAt(y, x + 1)
                  .equals(MarbleSolitaireModelState.SlotState.Invalid)) {
            if (y != (size - 1)) {
              b.append("\n");
            }
            break;
          }
        }
        if (x != (size - 1)) {
          b.append(" ");
        }
      }
    }
    s = b.toString();
    return s;
  }
}
