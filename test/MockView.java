import java.io.IOException;

import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * A mock class for the solitaire view model used for testing purposes,
 * which uses a string builder to append method inputs to show method inputs.
 */
public class MockView implements MarbleSolitaireView {

  final StringBuilder log;

  /**
   * constructor for the Mock View.
   *
   * @param log log to store inputs.
   */
  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void renderBoard() throws IOException {
    return;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append(message);
  }
}

