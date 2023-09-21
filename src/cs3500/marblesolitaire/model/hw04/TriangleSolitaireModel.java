package cs3500.marblesolitaire.model.hw04;

/**
 * class which represents a Triangle Solitaire Model, which differs
 * from the original model as it is a triangle shape.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * default constructor which sets the boardSize to 5 and the empty at the position
   * 0,0.
   */
  public TriangleSolitaireModel() {
    super(5);
    makeTriangle(0, 0);
  }

  /**
   * default constructor which takes in the boardSize and sets the empty at 0,0.
   *
   * @param boardSize the boardSize to be taken in
   * @throws IllegalArgumentException throws an exception if the boardSize is not positive.
   */
  public TriangleSolitaireModel(int boardSize) throws IllegalArgumentException {
    super(boardSize);
    if (boardSize < 1) {
      throw new IllegalArgumentException("Not a positive dimension");
    }
    makeTriangle(0, 0);
  }

  /** Constructor which takes in an empty position, sets the boardSize to 5.
   * @param row the row of the empty position
   * @param col the column of the empty position
   * @throws IllegalArgumentException throws an exception if the empty position is invalid.
   */
  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {
    super(5);
    makeTriangle(col, row);
  }

  /**
   * Constructor which takes in an armSize and the empty position.
   * @param boardSize the boardSize to be taken in
   * @param row the row of the empty position
   * @param col the column of the empty position
   * @throws IllegalArgumentException throws an exception if the boardSize or empty pos are invalid
   */
  public TriangleSolitaireModel(int boardSize, int row, int col) throws IllegalArgumentException {
    super(boardSize);
    if (boardSize < 1) {
      throw new IllegalArgumentException("Not a positive dimension");
    }
    makeTriangle(col, row);
  }

  //makes the triangle board
  private void makeTriangle(int sCol, int sRow) throws IllegalArgumentException {
    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        if (x <= y) {
          board[x][y] = SlotState.Marble;
          score++;
        } else {
          board[x][y] = SlotState.Invalid;
        }
      }
    }
    if (sRow >= boardSize || sCol >= boardSize || sRow < 0 || sCol < 0
            || board[sCol][sRow] == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid position for empty");
    }
    board[sCol][sRow] = SlotState.Empty;
  }

  //Returns true if given valid move, else false
  @Override
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow >= boardSize || fromCol >= boardSize || toRow >= boardSize
            || toCol >= boardSize || fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      return false;
    }
    if (board[fromCol][fromRow] == SlotState.Marble) {
      if (board[toCol][toRow] == SlotState.Empty) {
        if ((Math.abs(toCol - fromCol) == 2 && toRow == fromRow)
                || (Math.abs(toRow - fromRow) == 2 && toCol == fromCol)
                || ((toRow - fromRow) == 2 && (toCol - fromCol) == 2)
                || ((toRow - fromRow) == -2 && (toCol - fromCol) == -2)) {
          if (board[(toCol + fromCol) / 2][(toRow + fromRow) / 2] == SlotState.Marble) {
            return true;
          }
        }
      }
    }
    return false;
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
                || validMove(x, y, x, y + 2) || validMove(x, y, x, y - 2)
                || validMove(x, y, x + 2, y + 2) || validMove(x, y, x - 2, y - 2)) {
          return false;
        }
      }
    }
    return true;
  }
}
