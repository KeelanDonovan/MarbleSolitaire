package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class for Solitaire Models, which reduces code duplication across
 * the different models.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  protected int boardSize;
  protected SlotState[][] board;
  protected int score;

  /**
   * construcor for AbstractSolitaireModel which takes in a boardSize and initializes
   * boardSize, the board dimensions, and the score.
   *
   * @param boardSize the baordSize to be taken in
   * @throws IllegalArgumentException throws an argument exception if boardSize is not positive
   */
  public AbstractSolitaireModel(int boardSize) throws IllegalArgumentException {
    if (boardSize <= 0) {
      throw new IllegalArgumentException("Invalid boardSize");
    }
    this.boardSize = boardSize;
    this.board = new SlotState[boardSize][boardSize];
    this.score = -1;
  }

  /**
   * makes a move on the board.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if a move is invalid, i.e., if
   *                                  the to position is not empty, there is not a marble inbetween
   *                                  the to and from positions, if the move is not orthogonal and 2
   *                                  away, if the from position does not have a marble, and if any
   *                                  of the coordinates is out of bounds for the board.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (validMove(fromRow, fromCol, toRow, toCol)) {
      board[fromCol][fromRow] = SlotState.Empty;
      board[(toCol + fromCol) / 2][(toRow + fromRow) / 2] = SlotState.Empty;
      board[toCol][toRow] = SlotState.Marble;
      score--;
    } else {
      throw new IllegalArgumentException("Invalid move");
    }
  }

  //abstract method for validMove, which is used by all solitaire models.
  protected abstract boolean validMove(int fromRow, int fromCol, int toRow, int toCol);

  /**
   * returns the board size of the board.
   *
   * @return returns boardSize
   */
  @Override
  public int getBoardSize() {
    return boardSize;
  }

  /**
   * returns the slot at the position.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return returns the slot at the position
   * @throws IllegalArgumentException throws an exception if given an invalid position
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row >= boardSize || col >= boardSize || col < 0 || row < 0) {
      throw new IllegalArgumentException("Invalid position");
    }
    return board[col][row];
  }

  /**
   * returns the score of the game.
   *
   * @return returns the score
   */
  @Override
  public int getScore() {
    return score;
  }

  //Abstract method for isGameOver
  public abstract boolean isGameOver();
}