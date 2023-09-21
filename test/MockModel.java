import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * A mock class for the solitaire model used for testing purposes,
 * which uses a string builder to append method inputs to show method inputs.
 */
public class MockModel implements MarbleSolitaireModel {

  final StringBuilder log;

  /**
   * constructor for the Mock Model.
   *
   * @param log log to store inputs.
   */
  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    log.append(fromRow).append(" ").append(fromCol)
            .append(" ").append(toRow).append(" ").append(toCol).append(" ");
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}