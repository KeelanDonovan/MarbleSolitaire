package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * class which represents a European Solitaire Model, which differs
 * from the original model as it is an octagon shape.
 */
public class EuropeanSolitaireModel extends EnglishSolitaireModel {

  /**
   * Default constructor for EuropeanSolitaireModel which sets the armSize to 3, and the empty in
   * the center.
   */
  public EuropeanSolitaireModel() {
    super();
    int center = ((3 * (this.armSize - 1)) / 2);
    makeOctogon(center, center);
  }

  /**
   * Constructor which takes in an armSize, and sets the empty to the middle.
   *
   * @param armSize the armSize to be taken in.
   * @throws IllegalArgumentException throws an Exception if the armSize is not a positive odd
   *                                  number.
   */
  public EuropeanSolitaireModel(int armSize) throws IllegalArgumentException {
    super(armSize);
    int center = ((3 * (this.armSize - 1)) / 2);
    makeOctogon(center, center);
  }

  /**
   * Constructor which takes in an empty position, and sets the armSize to 3.
   *
   * @param row the row of the empty position
   * @param col the column of the empty position
   * @throws IllegalArgumentException throws an exception if the empty position is invalid.
   */
  public EuropeanSolitaireModel(int row, int col) throws IllegalArgumentException {
    super();
    makeOctogon(col, row);
  }

  /**
   * Constructor which takes in an armSize and the empty position.
   *
   * @param armSize the armSize to be taken in
   * @param row     the row of the empty position
   * @param col     the column of the empty position
   * @throws IllegalArgumentException throws an exception if the armSize or empty pos are invalid
   */
  public EuropeanSolitaireModel(int armSize, int row, int col) throws IllegalArgumentException {
    super(armSize);
    makeOctogon(col, row);
  }

  //turns the standard '+' english solitaire board into an octagon
  private void makeOctogon(int sCol, int sRow) throws IllegalArgumentException {
    int stepper = 0;
    int rightEdge = (armSize * 2) - 1;
    int center = ((3 * (this.armSize - 1)) / 2);
    board[center][center] = SlotState.Marble;

    for (int y = 1; y < armSize - 1; y++) {
      for (int x = armSize - stepper - 2; x < rightEdge + stepper + 1; x++) {
        board[x][y] = SlotState.Marble;
      }
      stepper++;
    }

    for (int y = (boardSize - armSize) + 1; y < boardSize - 1; y++) {
      for (int x = armSize - stepper - 1; x < rightEdge + stepper; x++) {
        board[x][y] = SlotState.Marble;
      }
      stepper--;
    }
    if (sRow >= boardSize || sCol >= boardSize || sRow < 0 || sCol < 0
            || board[sCol][sRow] == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid position for empty");
    }
    board[sCol][sRow] = SlotState.Empty;
    int n = armSize - 2;
    score += (n * (n + 1)) * 2;
  }
}
