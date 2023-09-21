//Keelan Donovan, Benjamin Katoaka

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.*;

public class EnglishSolitaireModelTest {

  EnglishSolitaireModel model1;
  EnglishSolitaireModel model2;
  EnglishSolitaireModel model3;
  EnglishSolitaireModel model4;

  /**
   * initializes the models
   */
  @Before
  public void init() {
    model1 = new EnglishSolitaireModel();
    model2 = new EnglishSolitaireModel(0, 4);
    model3 = new EnglishSolitaireModel(5);
    model4 = new EnglishSolitaireModel(5, 4, 4);
  }

  //tests the first constructor EnglishSolitaireModel()
  @Test
  public void testEnglishSolitaireModelConstructorOne() {
    int armSize = 3;
    int boardSize = 7;
    int sCol = 3;
    int sRow = 3;

    for (int y = 0; y < boardSize; y++) {
      for (int x = 0; x < boardSize; x++) {
        if (x == sCol && y == sRow) {
          Assert.assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(sCol, sRow));
        } else {
          if (y < armSize - 1 && x < armSize - 1) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(x, y));
          } else if (y < (armSize - 1) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x < (armSize - 1)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(x, y));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(x, y));
          }
        }
      }
    }
  }

  //tests the second constructor
  @Test
  public void testEnglishSolitaireModelConstructorTwo() {
    int armSize = 3;
    int boardSize = 7;
    int sCol = 0;
    int sRow = 4;
    for (int y = 0; y < boardSize; y = y + 1) {
      for (int x = 0; x < boardSize; x = x + 1) {
        if (x == sCol && y == sRow) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(sCol, sRow));
        } else {
          if (y < armSize - 1 && x < armSize - 1) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(x, y));
          } else if (y < (armSize - 1) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x < (armSize - 1)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(x, y));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(x, y));
          }
        }
      }
    }
  }

  //tests for placing empty out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorTwoEOne() {
    new EnglishSolitaireModel(-4, 3);
  }

  //Tests for placing empty on invalid
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorTwoETwo() {
    new EnglishSolitaireModel(0, 0);
  }


  //Tests third constructor for correctness
  @Test
  public void testEnglishSolitaireModelConstructorThree() {
    int armSize = 5;
    int boardSize = 13;
    int sCol = 6;
    int sRow = 6;
    for (int y = 0; y < boardSize; y = y + 1) {
      for (int x = 0; x < boardSize; x = x + 1) {
        if (x == sCol && y == sRow) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(sCol, sRow));
        } else {
          if (y < armSize - 1 && x < armSize - 1) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(x, y));
          } else if (y < (armSize - 1) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x < (armSize - 1)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(x, y));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(x, y));
          }
        }
      }
    }
  }

  //Tests third constructor exception with a positive even number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorThreeEOne() {
    new EnglishSolitaireModel(4);
  }

  //Tests third constructor exception with a negative even number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorThreeETwo() {
    new EnglishSolitaireModel(-4);
  }

  //Tests third constructor exception with a negative odd number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorThreeEThree() {
    new EnglishSolitaireModel(-5);
  }

  //Tests fourth constructor for correctness
  @Test
  public void testEnglishSolitaireModelConstructorFour() {
    int armSize = 5;
    int boardSize = 13;
    int sCol = 4;
    int sRow = 4;
    for (int y = 0; y < boardSize; y = y + 1) {
      for (int x = 0; x < boardSize; x = x + 1) {
        if (x == sCol && y == sRow) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, model4.getSlotAt(sCol, sRow));
        } else {
          if (y < armSize - 1 && x < armSize - 1) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model4.getSlotAt(x, y));
          } else if (y < (armSize - 1) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model4.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x < (armSize - 1)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model4.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model4.getSlotAt(x, y));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble, model4.getSlotAt(x, y));
          }
        }
      }
    }
  }

  //Tests fourth constructor exception with a invalid empty position
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEOne() {
    new EnglishSolitaireModel(5, 0, 0);
  }

  //Tests fourth constructor exception with a out of bounds empty position
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourETwo() {
    new EnglishSolitaireModel(5, -6, 6);
  }

  //Tests fourth constructor exception with a positive even number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEThree() {
    new EnglishSolitaireModel(4, 6, 6);
  }

  //Tests fourth constructor exception with a negative even number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEFour() {
    new EnglishSolitaireModel(-4, 6, 6);
  }

  //Tests fourth constructor exception with a negative odd number
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorFourEFive() {
    new EnglishSolitaireModel(-5, 6, 6);
  }

  //Tests move for an invalid move where the to position is empty
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEOne() {
    model1.move(3, 3, 1, 3);
  }

  //Tests move for an invalid move where the to position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveETwo() {
    model1.move(0, 0, 0, 2);
  }

  //Tests move for an invalid move where the from position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEThree() {
    model1.move(0, 2, 0, 0);
  }

  //Tests move for an invalid move where the from position is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEFour() {
    model1.move(0, 2, -2, 2);
  }

  //Tests move for an invalid move where the to and from positions have marbles
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEFive() {
    model1.move(2, 2, 2, 4);
  }

  //Tests move for an invalid move where the in between position is empty
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveESix() {
    model1.move(2, 3, 4, 3);
  }

  //Tests move for an invalid move where the move is over 2 spaces apart
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveESeven() {
    model1.move(2, 3, 5, 3);
  }

  //Tests move for an invalid move where the move is not orthogonal
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionMoveEEight() {
    model1.move(2, 3, 3, 4);
  }

  //Tests for a valid move down
  @Test
  public void testMoveDown() {
    assertEquals(model1.getSlotAt(1, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    model1.move(1, 3, 3, 3);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(1, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model1.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move up
  @Test
  public void testMoveUp() {
    assertEquals(model1.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    model1.move(5, 3, 3, 3);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model1.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move left
  @Test
  public void testMoveLeft() {
    assertEquals(model1.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    model1.move(3, 5, 3, 3);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model1.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);
  }

  //Tests for a valid move right
  @Test
  public void testMoveRight() {
    assertEquals(model1.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    model1.move(3, 1, 3, 3);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model1.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);
  }

  //tests isGameOver() method
  @Test
  public void isGameOver() {
    assertEquals(model1.isGameOver(), false);
    model1.move(1, 3, 3, 3);
    model1.move(2, 5, 2, 3);
    model1.move(4, 5, 2, 5);
    model1.move(3, 3, 3, 5);
    model1.move(3, 6, 3, 4);
    model1.move(2, 6, 2, 4);
    model1.move(3, 1, 3, 3);
    model1.move(3, 3, 3, 5);
    model1.move(5, 3, 3, 3);
    model1.move(4, 1, 4, 3);
    model1.move(3, 3, 5, 3);
    model1.move(6, 3, 4, 3);
    model1.move(4, 3, 4, 5);
    model1.move(4, 6, 4, 4);
    model1.move(6, 2, 4, 2);
    model1.move(5, 4, 3, 4);
    model1.move(3, 4, 3, 6);
    model1.move(1, 4, 3, 4);
    model1.move(1, 2, 3, 2);
    model1.move(3, 2, 5, 2);
    model1.move(2, 0, 2, 2);
    model1.move(2, 2, 2, 4);
    model1.move(2, 4, 4, 4);
    assertEquals(model1.isGameOver(), false);
    model1.move(4, 0, 2, 0);
    assertEquals(model1.isGameOver(), true);
  }

  //tests getBoardSize() method
  @Test
  public void getBoardSize() {
    assertEquals(7, model1.getBoardSize());
    assertEquals(13, model3.getBoardSize());
  }

  //tests getSlotAt() method
  @Test
  public void getSlotAt() {
    int armSize = 3;
    int boardSize = 7;
    int sCol = 3;
    int sRow = 3;

    for (int y = 0; y < boardSize; y = y + 1) {
      for (int x = 0; x < boardSize; x = x + 1) {
        if (x == sCol && y == sRow) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(sCol, sRow));
        } else {
          if (y < armSize - 1 && x < armSize - 1) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(x, y));
          } else if (y < (armSize - 1) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x < (armSize - 1)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(x, y));
          } else if (y > (boardSize - armSize) && x > (boardSize - armSize)) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(x, y));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(x, y));
          }
        }
      }
    }
  }

  //tests getScore() method
  @Test
  public void getScore() {
    assertEquals(model1.getScore(), 32);
    model1.move(1, 3, 3, 3);
    assertEquals(model1.getScore(), 31);
    model1.move(2, 5, 2, 3);
    model1.move(4, 5, 2, 5);
    model1.move(3, 3, 3, 5);
    model1.move(3, 6, 3, 4);
    model1.move(2, 6, 2, 4);
    model1.move(3, 1, 3, 3);
    model1.move(3, 3, 3, 5);
    model1.move(5, 3, 3, 3);
    model1.move(4, 1, 4, 3);
    model1.move(3, 3, 5, 3);
    model1.move(6, 3, 4, 3);
    assertEquals(model1.getScore(), 20);
    model1.move(4, 3, 4, 5);
    model1.move(4, 6, 4, 4);
    model1.move(6, 2, 4, 2);
    model1.move(5, 4, 3, 4);
    model1.move(3, 4, 3, 6);
    model1.move(1, 4, 3, 4);
    model1.move(1, 2, 3, 2);
    model1.move(3, 2, 5, 2);
    model1.move(2, 0, 2, 2);
    model1.move(2, 2, 2, 4);
    model1.move(2, 4, 4, 4);
    model1.move(4, 0, 2, 0);
    assertEquals(model1.getScore(), 8);
  }
}