package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * class which represents a MarbleSolitaireTextView, which uses a MarbleSolitaireModelState
 * as a refernce to display a view, and uses an appendable to display an output.
 * This class differs in that the toString is suited to handle Triangle Solitaire models.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructor for TriangleSolitaireTextView that takes in a state and an Appendable.
   *
   * @param state the MarbleSolitaireModelState that the view class gets information from
   * @param a     The appendable that the view will append onto
   * @throws IllegalArgumentException if the state or appendable inputted are null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state, Appendable a)
          throws IllegalArgumentException {
    super(state, a);
  }


  /**
   * Constructor for TriangleSolitaireTextView that takes in a state and sets appendable to
   * System.out.
   *
   * @param state the MarbleSolitaireModelState that the view gets information from
   * @throws IllegalArgumentException if the inputted state is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state)
          throws IllegalArgumentException {
    super(state);
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder("");
    int dimension = state.getBoardSize();
    int stepper = 0;
    for (int y = 0; y < dimension; y++) {
      for (int z = dimension - 1 - stepper; z > 0; z--) {
        b.append(" ");
      }
      for (int x = 0; x < dimension; x++) {
        if (state.getSlotAt(y, x).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          b.append("O");
        }
        if (state.getSlotAt(y, x).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          b.append("_");
        }
        if ((x == dimension - 1 && y == dimension - 1)
                || state.getSlotAt(y, x + 1).equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          if (y != dimension - 1) {
            b.append("\n");
          }
          break;
        }
        b.append(" ");
      }
      stepper++;
    }
    return b.toString();
  }
}
