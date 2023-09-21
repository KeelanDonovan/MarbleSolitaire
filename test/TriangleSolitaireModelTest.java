import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the TriangleSolitaireModelTest Class.
 */
public class TriangleSolitaireModelTest {

  TriangleSolitaireModel cons1;
  TriangleSolitaireModel cons2;
  TriangleSolitaireModel cons3;
  TriangleSolitaireModel cons4;
  TriangleSolitaireModel cons5;
  TriangleSolitaireModel cons6;
  TriangleSolitaireTextView view1;
  TriangleSolitaireTextView view2;
  TriangleSolitaireTextView view3;
  TriangleSolitaireTextView view4;
  TriangleSolitaireTextView view5;
  TriangleSolitaireTextView view6;

  /**
   * initializes the models.
   */
  @Before
  public void init() {
    cons1 = new TriangleSolitaireModel();
    cons2 = new TriangleSolitaireModel(4, 4);
    cons3 = new TriangleSolitaireModel(9);
    cons4 = new TriangleSolitaireModel(7, 4, 4);
    cons5 = new TriangleSolitaireModel(7, 4, 0);
    cons6 = new TriangleSolitaireModel(7, 4, 2);
    view1 = new TriangleSolitaireTextView(cons1);
    view2 = new TriangleSolitaireTextView(cons2);
    view3 = new TriangleSolitaireTextView(cons3);
    view4 = new TriangleSolitaireTextView(cons4);
    view5 = new TriangleSolitaireTextView(cons5);
    view5 = new TriangleSolitaireTextView(cons6);
  }

  //tests the first constructor
  @Test
  public void testTriangleSolitaireModelConstructorOne() {
    String s = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(s, view1.toString());
  }

  //tests the second constructor
  @Test
  public void testTriangleSolitaireModelConstructorTwo() {
    String s = "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O _";
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
    new EuropeanSolitaireModel(0, 1);
  }

  //Tests third constructor for correctness
  @Test
  public void testEuropeanSolitaireModelConstructorThree() {
    String s = "        _\n" +
            "       O O\n" +
            "      O O O\n" +
            "     O O O O\n" +
            "    O O O O O\n" +
            "   O O O O O O\n" +
            "  O O O O O O O\n" +
            " O O O O O O O O\n" +
            "O O O O O O O O O";
    assertEquals(s, view3.toString());
  }

  //Tests third constructor exception with 0
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorThreeEOne() {
    new EuropeanSolitaireModel(0);
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
    String s = "      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O _\n" +
            " O O O O O O\n" +
            "O O O O O O O";
    assertEquals(s, view4.toString());
  }

  //Tests fourth constructor exception with a invalid empty position
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEOne() {
    new EuropeanSolitaireModel(5, 0, 1);
  }

  //Tests fourth constructor exception with a out of bounds empty position
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourETwo() {
    new EuropeanSolitaireModel(5, -6, 6);
  }

  //Tests fourth constructor exception with 0
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEThree() {
    new EuropeanSolitaireModel(0, 6, 6);
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

  //Tests move for an invalid move where the from position is empty
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEOne() {
    cons1.move(0, 0, 2, 0);
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
    cons4.move(3, 3, 5, 5);
  }

  //Tests move for an invalid move where the move is over 2 spaces apart
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveESeven() {
    cons1.move(2, 3, 5, 3);
  }

  //Tests move for an invalid move
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEEight() {
    cons6.move(6, 0, 4, 2);
  }


  //Tests for a valid move down
  @Test
  public void testMoveDown() {
    assertEquals(cons5.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons5.getSlotAt(3, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons5.getSlotAt(4, 0), MarbleSolitaireModelState.SlotState.Empty);
    cons5.move(2, 0, 4, 0);
    assertEquals(cons5.getSlotAt(4, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons5.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons5.getSlotAt(3, 0), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move up
  @Test
  public void testMoveUp() {
    assertEquals(cons5.getSlotAt(6, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons5.getSlotAt(5, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons5.getSlotAt(4, 0), MarbleSolitaireModelState.SlotState.Empty);
    cons5.move(6, 0, 4, 0);
    assertEquals(cons5.getSlotAt(4, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons5.getSlotAt(5, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons5.getSlotAt(6, 0), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move left
  @Test
  public void testMoveLeft() {
    assertEquals(cons6.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    cons6.move(4, 4, 4, 2);
    assertEquals(cons6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons6.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move right
  @Test
  public void testMoveRight() {
    assertEquals(cons6.getSlotAt(4, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(4, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    cons6.move(4, 0, 4, 2);
    assertEquals(cons6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(4, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons6.getSlotAt(4, 0), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid diagonally up
  @Test
  public void testMoveDiagUp() {
    assertEquals(cons6.getSlotAt(6, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    cons6.move(6, 4, 4, 2);
    assertEquals(cons6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons6.getSlotAt(6, 4), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid diagonally down
  @Test
  public void testMoveDiagDown() {
    assertEquals(cons6.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    cons6.move(2, 0, 4, 2);
    assertEquals(cons6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(cons6.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(cons6.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests GameOver
  @Test
  public void testGameOver() {
    assertEquals(cons1.isGameOver(), false);
    cons1.move(2, 0, 0, 0);
    cons1.move(4, 0, 2, 0);
    cons1.move(4, 2, 4, 0);
    cons1.move(4, 4, 4, 2);
    cons1.move(3, 2, 3, 0);
    cons1.move(3, 0, 1, 0);
    cons1.move(2, 2, 2, 0);
    cons1.move(0, 0, 2, 2);
    cons1.move(2, 0, 0, 0);
    assertEquals(cons1.isGameOver(), false);
    cons1.move(2, 2, 4, 4);
    assertEquals(cons1.isGameOver(), true);
  }

  //Tests getboardSize for a default board
  @Test
  public void testGetBoardSizeOne() {
    assertEquals(5, cons1.getBoardSize());
  }

  //Tests board size for a 9 size board
  @Test
  public void testGetBoardSizeTwo() {
    assertEquals(9, cons3.getBoardSize());
  }

  //Tests getSlotAt
  @Test
  public void testGetSlotAt() {
    int boardSize = cons1.getBoardSize();
    int sCol = 0;
    int sRow = 0;

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        if (x == sCol && y == sRow) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, cons1.getSlotAt(sCol, sRow));
        } else {
          if (x <= y) {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble, cons1.getSlotAt(y, x));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, cons1.getSlotAt(y, x));
          }
        }
      }
    }
  }

  //Tests getScore
  @Test
  public void testGetScore() {
    assertEquals(cons1.getScore(), 14);
    cons1.move(2, 0, 0, 0);
    assertEquals(cons1.getScore(), 13);
    cons1.move(4, 0, 2, 0);
    cons1.move(4, 2, 4, 0);
    cons1.move(4, 4, 4, 2);
    cons1.move(3, 2, 3, 0);
    cons1.move(3, 0, 1, 0);
    cons1.move(2, 2, 2, 0);
    cons1.move(0, 0, 2, 2);
    cons1.move(2, 0, 0, 0);
    cons1.move(2, 2, 4, 4);
    assertEquals(cons1.getScore(), 4);
  }
}