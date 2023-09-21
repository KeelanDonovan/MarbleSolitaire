//Keelan Donovan, Benjamin Katoaka
package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * class which represents an English Solitaire Model, which differs
 * from the original model as it is a '+' shape.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  protected int armSize;

  /**
   * default constructor which sets armSize to 3, and the empty to the middle.
   */
  public EnglishSolitaireModel() {
    super(7);
    initialize(3);
    board[armSize][armSize] = MarbleSolitaireModelState.SlotState.Empty;
  }

  /**
   * constructor that takes in an empty position, and sets the armSize to 3.
   *
   * @param sRow row of empty space
   * @param sCol column of empty space
   * @throws IllegalArgumentException throws an exception if empty position is not valid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(7);
    initialize(3);
    if (sRow >= boardSize || sCol >= boardSize || sRow < 0 || sCol < 0
            || board[sCol][sRow] == MarbleSolitaireModelState.SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      board[sCol][sRow] = MarbleSolitaireModelState.SlotState.Empty;
    }
  }

  /**
   * constructor which takes in an armSize, and sets empty to middle.
   *
   * @param a armSize
   * @throws IllegalArgumentException throws an exception if the armSize is not a pos even num
   */
  public EnglishSolitaireModel(int a) throws IllegalArgumentException {
    super((a * 3) - 2);
    if (a <= 0 || (a % 2) == 0) {
      throw new IllegalArgumentException("Arm thickness is not a positive odd number");
    }
    initialize(a);
    int center = ((3 * (a - 1)) / 2);
    board[center][center] = MarbleSolitaireModelState.SlotState.Empty;
  }

  /**
   * constructor that takes in an empty position and armSize.
   *
   * @param a    armSize
   * @param sRow empty row
   * @param sCol empty column
   * @throws IllegalArgumentException if armSize is not valid or empty position is invalid
   */
  public EnglishSolitaireModel(int a, int sRow, int sCol) throws IllegalArgumentException {
    super((a * 3) - 2);
    if (a <= 0 || (a % 2) == 0) {
      throw new IllegalArgumentException("Arm thickness is not a positive odd number");
    }
    initialize(a);
    if (sRow >= boardSize || sCol >= boardSize || sRow < 0 || sCol < 0
            || board[sCol][sRow] == MarbleSolitaireModelState.SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      board[sCol][sRow] = MarbleSolitaireModelState.SlotState.Empty;
    }
  }

  /**
   * initializes the armSize and boardSize, and calls makeBoard,
   * to get rid of duplicate code.
   *
   * @param armSize the armSize taken in through the four constructors
   */
  private void initialize(int armSize) {
    this.armSize = armSize;
    makeBoard();
  }

  /**
   * makes the board according to the armsize and places an empty slot.`
   * in the specified coordinates.
   */
  private void makeBoard() {
    for (int y = 0; y < boardSize; y = y + 1) {
      for (int x = 0; x < boardSize; x = x + 1) {
        if (y < (armSize - 1) && x < (armSize - 1)) {
          board[x][y] = MarbleSolitaireModelState.SlotState.Invalid;
        } else if (y < (armSize - 1) && x > (boardSize - armSize)) {
          board[x][y] = MarbleSolitaireModelState.SlotState.Invalid;
        } else if (y > (boardSize - armSize) && x < (armSize - 1)) {
          board[x][y] = MarbleSolitaireModelState.SlotState.Invalid;
        } else if (y > (boardSize - armSize) && x > (boardSize - armSize)) {
          board[x][y] = MarbleSolitaireModelState.SlotState.Invalid;
        } else {
          board[x][y] = MarbleSolitaireModelState.SlotState.Marble;
          score++;
        }
      }
    }
  }

  /**
   * returns if the game is over.
   *
   * @return a boolean if the game is over
   */
  @Override
  public boolean isGameOver() {
    for (int y = 0; y < boardSize; y = y + 1) {
      for (int x = 0; x < boardSize; x = x + 1) {
        if (validMove(x, y, x + 2, y) || validMove(x, y, x - 2, y)
                || validMove(x, y, x, y + 2) || validMove(x, y, x, y - 2)) {
          return false;
        }
      }
    }
    return true;
  }

  //Returns true if given valid move, else false
  @Override
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow >= boardSize || fromCol >= boardSize || toRow >= boardSize
            || toCol >= boardSize || fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      return false;
    }
    if (board[fromCol][fromRow] == MarbleSolitaireModelState.SlotState.Marble) {
      if (board[toCol][toRow] == MarbleSolitaireModelState.SlotState.Empty) {
        if ((Math.abs(toCol - fromCol) == 2 && toRow == fromRow)
                || (Math.abs(toRow - fromRow) == 2 && toCol == fromCol)) {
          if (board[(toCol + fromCol) / 2][(toRow + fromRow) / 2] == MarbleSolitaireModelState.SlotState.Marble) {
            return true;
          }
        }
      }
    }
    return false;
  }
}