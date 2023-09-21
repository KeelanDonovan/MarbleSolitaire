import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the EuropeanSolitaireModelTest Class.
 */
public class EuropeanSolitaireModelTest {

  EuropeanSolitaireModel cons1;
  EuropeanSolitaireModel cons2;
  EuropeanSolitaireModel cons3;
  EuropeanSolitaireModel cons4;
  MarbleSolitaireTextView view1;
  MarbleSolitaireTextView view2;
  MarbleSolitaireTextView view3;
  MarbleSolitaireTextView view4;

  /**
   * initializes the models.
   */
  @Before
  public void init() {
    cons1 = new EuropeanSolitaireModel();
    cons2 = new EuropeanSolitaireModel(0, 4);
    cons3 = new EuropeanSolitaireModel(5);
    cons4 = new EuropeanSolitaireModel(5, 4, 4);
    view1 = new MarbleSolitaireTextView(cons1);
    view2 = new MarbleSolitaireTextView(cons2);
    view3 = new MarbleSolitaireTextView(cons3);
    view4 = new MarbleSolitaireTextView(cons4);
  }

  //tests the first constructor
  @Test
  public void testEuropeanSolitaireModelConstructorOne() {
    String s = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(s, view1.toString());
  }

  //tests the second constructor
  @Test
  public void testEuropeanSolitaireModelConstructorTwo() {
    String s = "    O O _\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(s, view2.toString());
  }

  //tests for placing empty out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorTwoEOne() {
    new EuropeanSolitaireModel(-4, 3);
  }

  //Tests for placing empty on invalid
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorTwoETwo() {
    new EuropeanSolitaireModel(0, 0);
  }

  //Tests third constructor for correctness
  @Test
  public void testEuropeanSolitaireModelConstructorThree() {
    String s = "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O";
    assertEquals(s, view3.toString());
  }

  //Tests third constructor exception with a positive even number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorThreeEOne() {
    new EuropeanSolitaireModel(4);
  }

  //Tests third constructor exception with a negative even number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorThreeETwo() {
    new EuropeanSolitaireModel(-4);
  }

  //Tests third constructor exception with a negative odd number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorThreeEThree() {
    new EuropeanSolitaireModel(-5);
  }

  //Tests fourth constructor for correctness
  @Test
  public void testEuropeanSolitaireModelConstructorFour() {
    String s = "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O _ O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O";
    assertEquals(s, view4.toString());
  }

  //Tests fourth constructor exception with a invalid empty position
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEOne() {
    new EuropeanSolitaireModel(5, 0, 0);
  }

  //Tests fourth constructor exception with a out of bounds empty position
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourETwo() {
    new EuropeanSolitaireModel(5, -6, 6);
  }

  //Tests fourth constructor exception with a positive even number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEThree() {
    new EuropeanSolitaireModel(4, 6, 6);
  }

  //Tests fourth constructor exception with a negative even number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEFour() {
    new EuropeanSolitaireModel(-4, 6, 6);
  }

  //Tests fourth constructor exception with a negative odd number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEFive() {
    new EuropeanSolitaireModel(-5, 6, 6);
  }

  //Tests move for an invalid move where the to position is empty
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEOne() {
    cons1.move(3, 3, 1, 3);
  }

  //Tests move for an invalid move where the to position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveETwo() {
    cons1.move(0, 0, 0, 2);
  }

  //Tests move for an invalid move where the from position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEThird() {
    cons1.move(0, 2, 0, 0);
  }

  //Tests move for an invalid move where the from position is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEFourth() {
    cons1.move(0, 2, -2, 2);
  }

  //Tests move for an invalid move where the to and from positions have marbles
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEFive() {
    cons1.move(2, 2, 2, 4);
  }

  //Tests move for an invalid move where the in between position is empty
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveESix() {
    cons1.move(2, 3, 4, 3);
  }

  //Tests move for an invalid move where the move is over 2 spaces apart
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveESeven() {
    cons1.move(2, 3, 5, 3);
  }

  //Tests move for an invalid move where the move is not orthogonal
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEEight() {
    cons1.move(2, 3, 3, 4);
  }

  //Tests for a valid move down
  @Test
  public void testMoveDown() {
    assertEquals(cons1.getSlotAt(1, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    cons1.move(1, 3, 3, 3);
    assertEquals(cons1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(1, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons1.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move up
  @Test
  public void testMoveUp() {
    assertEquals(cons1.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    cons1.move(5, 3, 3, 3);
    assertEquals(cons1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons1.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move left
  @Test
  public void testMoveLeft() {
    assertEquals(cons1.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    cons1.move(3, 5, 3, 3);
    assertEquals(cons1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons1.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move right
  @Test
  public void testMoveRight() {
    assertEquals(cons1.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    cons1.move(3, 1, 3, 3);
    assertEquals(cons1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons1.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests GameOver
  @Test
  public void testGameOver() {
    assertEquals(cons1.isGameOver(), false);
    cons1.move(1, 3, 3, 3);
    cons1.move(2, 5, 2, 3);
    cons1.move(4, 5, 2, 5);
    cons1.move(3, 3, 3, 5);
    cons1.move(3, 6, 3, 4);
    cons1.move(2, 6, 2, 4);
    cons1.move(3, 1, 3, 3);
    cons1.move(3, 3, 3, 5);
    cons1.move(5, 3, 3, 3);
    cons1.move(4, 1, 4, 3);
    cons1.move(3, 3, 5, 3);
    cons1.move(6, 3, 4, 3);
    cons1.move(4, 3, 4, 5);
    cons1.move(4, 6, 4, 4);
    cons1.move(6, 2, 4, 2);
    cons1.move(5, 4, 3, 4);
    cons1.move(3, 4, 3, 6);
    cons1.move(1, 4, 3, 4);
    cons1.move(1, 2, 3, 2);
    cons1.move(3, 2, 5, 2);
    cons1.move(2, 0, 2, 2);
    cons1.move(2, 2, 2, 4);
    cons1.move(2, 4, 4, 4);
    cons1.move(4, 0, 2, 0);
    assertEquals(cons1.isGameOver(), false);
    cons1.move(5, 1, 5, 3);
    assertEquals(cons1.isGameOver(), true);
  }

  //Tests getboardSize for a default board
  @Test
  public void testGetBoardSizeOne() {
    assertEquals(7, cons1.getBoardSize());
  }

  //Tests board size for a 5 x 5 board
  @Test
  public void testGetBoardSizeTwo() {
    assertEquals(13, cons3.getBoardSize());
  }

  //Tests getSlotAt
  @Test
  public void testGetSlotAt() {
    int armSize = 3;
    int boardSize = 7;
    int sCol = 3;
    int sRow = 3;
    int stepper = 0;

    for (int y = 0; y < boardSize - 2; y = y + 1) {
      for (int x = 0; x < boardSize; x = x + 1) {
        if (x == sCol && y == sRow) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, cons1.getSlotAt(sCol, sRow));
        } else {
          if (y < armSize - 1 && x < armSize - 1 - stepper) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, cons1.getSlotAt(x, y));
          } else if (y < (armSize - 1) && x > (boardSize - armSize) + stepper) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, cons1.getSlotAt(x, y));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble, cons1.getSlotAt(x, y));
          }
        }
      }
      stepper++;
    }

    stepper = 1;
    for (int y = boardSize - 2; y < boardSize; y = y + 1) {
      for (int x = 0; x < boardSize; x = x + 1) {
        if (y > (boardSize - armSize) && x < (armSize - 1) - stepper) {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, cons1.getSlotAt(x, y));
        } else if (y > (boardSize - armSize) && x > (boardSize - armSize) + stepper) {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, cons1.getSlotAt(x, y));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, cons1.getSlotAt(x, y));
        }
      }
      stepper--;
    }
  }

  //Tests getScore
  @Test
  public void testGetScore() {
    assertEquals(cons1.getScore(), 36);
    cons1.move(1, 3, 3, 3);
    assertEquals(cons1.getScore(), 35);
    cons1.move(2, 5, 2, 3);
    cons1.move(4, 5, 2, 5);
    cons1.move(3, 3, 3, 5);
    cons1.move(3, 6, 3, 4);
    cons1.move(2, 6, 2, 4);
    cons1.move(3, 1, 3, 3);
    cons1.move(3, 3, 3, 5);
    cons1.move(5, 3, 3, 3);
    cons1.move(4, 1, 4, 3);
    cons1.move(3, 3, 5, 3);
    cons1.move(6, 3, 4, 3);
    assertEquals(cons1.getScore(), 24);
    cons1.move(4, 3, 4, 5);
    cons1.move(4, 6, 4, 4);
    cons1.move(6, 2, 4, 2);
    cons1.move(5, 4, 3, 4);
    cons1.move(3, 4, 3, 6);
    cons1.move(1, 4, 3, 4);
    cons1.move(1, 2, 3, 2);
    cons1.move(3, 2, 5, 2);
    cons1.move(2, 0, 2, 2);
    cons1.move(2, 2, 2, 4);
    cons1.move(2, 4, 4, 4);
    cons1.move(4, 0, 2, 0);
    assertEquals(cons1.getScore(), 12);
  }
}