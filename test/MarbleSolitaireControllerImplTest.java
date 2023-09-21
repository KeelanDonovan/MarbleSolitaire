import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * test class for MarbleSolitaireControllerImpl.
 */
public class MarbleSolitaireControllerImplTest {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private MarbleSolitaireModel modelEur;
  private MarbleSolitaireView viewEur;
  private MarbleSolitaireModel modelTri;
  private MarbleSolitaireView viewTri;
  private Readable input;
  private Appendable output;
  private StringBuilder viewLog;
  private StringBuilder modelLog;
  private MockView mView;
  private MockModel mModel;
  private static String fullGame;
  private static String fullGameEur;
  private static String fullGameTri;

  /**
   * initializes the models.
   */
  @Before
  public void init() {
    model = new EnglishSolitaireModel();
    input = new StringReader("");
    output = new StringBuilder();
    view = new MarbleSolitaireTextView(model, output);
    viewLog = new StringBuilder();
    modelLog = new StringBuilder();
    mView = new MockView(viewLog);
    mModel = new MockModel(modelLog);
    fullGame = "2 4 4 4 3 6 3 4 5 6 3 6 4 4 4 6 4 7 4 5 3 7 3 5 4 2 4 4 4 4 4 6 6 4 4 4 5" +
            " 2 5 4 4 4 6 4 7 4 5 4 5 4 5 6 5 7 5 5 7 3 5 3 6 5 4 5 4 5 4 7 2 5 4 5 2 3 4" +
            " 3 4 3 6 3 3 1 3 3 3 3 3 5 3 5 5 5 5 1 3 1";
    fullGameEur = fullGame += " 6 2 6 4";
    fullGameTri = "3 1 1 1 5 1 3 1 5 3 5 1 5 5 5 3 4 3 4 1 4 1 2 1 " +
            "3 3 3 1 1 1 3 3 3 1 1 1 3 3 5 5";
    modelEur = new EuropeanSolitaireModel();
    viewEur = new MarbleSolitaireTextView(modelEur, output);
    modelTri = new TriangleSolitaireModel();
    viewTri = new TriangleSolitaireTextView(modelTri, output);
  }

  //Tests for controller constructor exception for null model
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorOne() {
    new MarbleSolitaireControllerImpl(null, view, input);
  }

  //Tests for controller constructor exception for null view
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorTwo() {
    new MarbleSolitaireControllerImpl(model, null, input);
  }

  //Tests for controller constructor exception for null input
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorThree() {
    new MarbleSolitaireControllerImpl(model, view, null);
  }

  //Tests controller constructor for correctness after quiting immediately, lowercase Q
  @Test
  public void testConstructorQuitLowerCase() {
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller constructor for correctness after quiting immediately, lowercase Q for Euro
  @Test
  public void testConstructorQuitLowerCaseEur() {
    String s1 = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";
    Readable temp = new StringReader("q");
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelEur, viewEur, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller constructor for correctness after quiting immediately, lowercase Q for Tri
  @Test
  public void testConstructorQuitLowerCaseTri() {
    String s1 = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";
    Readable temp = new StringReader("q");
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelTri, viewTri, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller constructor for correctness after quiting immediately, uppercase Q
  @Test
  public void testConstructorQuitUpperCase() {
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("Q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller constructor for correctness when making a valid move then quiting
  @Test
  public void testConstructorMoveThenQuit() {
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n";
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller constructor for correctness when making a valid move then quiting for Euro
  @Test
  public void testConstructorMoveThenQuitEur() {
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 35\n";
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelEur, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller constructor for correctness when making a valid move then quiting for Tri
  @Test
  public void testConstructorMoveThenQuitTri() {
    String s1 = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move, try again.\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelTri, viewTri, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests if view's renderMessage is getting the correct input with MockView, quitting first
  @Test
  public void testInputViewRenderMessageOne() {
    Readable temp = new StringReader("q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 32\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting the correct input with MockView, quitting first,
  //for european
  @Test
  public void testInputViewRenderMessageOneEur() {
    Readable temp = new StringReader("q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(modelEur, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 36\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting the correct input with MockView, quitting first,
  //for Triangle
  @Test
  public void testInputViewRenderMessageOneTri() {
    Readable temp = new StringReader("q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(modelTri, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 14\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting the correct input with MockView, making a move then
  //quiting
  @Test
  public void testInputViewRenderMessageTwo() {
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 31\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting the correct input with MockView, making a move then
  //quiting, for european
  @Test
  public void testInputViewRenderMessageTwoEuropean() {
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(modelEur, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 36\n" +
            "\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 35\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting the correct input with MockView, making a move then
  //quiting, for triangle
  @Test
  public void testInputViewRenderMessageTwoTriangle() {
    Readable temp = new StringReader("3 1 1 1 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(modelTri, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 14\n" +
            "\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 13\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting the correct input with MockView, making a move then
  //quiting, with new lines instead of spaces
  @Test
  public void testInputViewRenderMessageTwoNewLines() {
    Readable temp = new StringReader("2\n4\n4\n4\nq");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 31\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting the correct input with MockView, making a move then
  //quiting, with some spaces and some new lines
  @Test
  public void testInputViewRenderMessageTwoMixed() {
    Readable temp = new StringReader("2 4\n4\n4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 31\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting correct input from an invalid move
  @Test
  public void testInputViewRenderMessageThree() {
    Readable temp = new StringReader("-2 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "Invalid input, try again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 32\n", viewLog.toString());

  }

  //Tests if view's renderMessage is getting correct input from a valid move and invalid inputs in
  //in the middle
  @Test
  public void testInputViewRenderMessageFour() {
    Readable temp = new StringReader("e e e 2 r r r 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 31\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting correct input from a valid move and invalid inputs
  //before, for european
  @Test
  public void testInputViewRenderMessageFourEur() {
    Readable temp = new StringReader("e e e r t d 2 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(modelEur, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 36\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 35\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting correct input from a valid move and invalid inputs
  //before, for triangle
  @Test
  public void testInputViewRenderMessageFourTri() {
    Readable temp = new StringReader("e e e r t d 3 1 1 1 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(modelTri, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 14\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 13\n", viewLog.toString());
  }

  //Tests if view's renderMessage is getting correct input from an invalid move then a valid move
  @Test
  public void testInputViewRenderMessageFive() {
    Readable temp = new StringReader("2 2 2 2 2 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "Invalid move, try again.\n" +
            "\n" +
            "Score: 32\n" +
            "\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 31\n", viewLog.toString());

  }

  //Tests if view's renderMessage is getting correct input for 2 valid moves then invalid moves
  @Test
  public void testInputViewRenderMessageSix() {
    Readable temp = new StringReader("2 4 4 4 3 6 3 4 5 6 3 6" +
            "4 4 4 6 4 7 4 5 3 7 3 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "\n" +
            "Score: 31\n" +
            "\n" +
            "Score: 30\n" +
            "Invalid move, try again.\n" +
            "\n" +
            "Score: 30\n" +
            "Invalid move, try again.\n" +
            "\n" +
            "Score: 30\n" +
            "Invalid move, try again.\n" +
            "\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "\n" +
            "Score: 30\n", viewLog.toString());

  }

  //Tests if view's renderMessage is getting correct input for a full game
  @Test
  public void testInputViewRenderMessageSeven() {
    Readable temp = new StringReader(fullGame);
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(model, mView, temp);
    c.playGame();
    assertEquals("\n" +
            "Score: 32\n" +
            "\n" +
            "Score: 31\n" +
            "\n" +
            "Score: 30\n" +
            "\n" +
            "Score: 29\n" +
            "\n" +
            "Score: 28\n" +
            "\n" +
            "Score: 27\n" +
            "\n" +
            "Score: 26\n" +
            "\n" +
            "Score: 25\n" +
            "\n" +
            "Score: 24\n" +
            "\n" +
            "Score: 23\n" +
            "\n" +
            "Score: 22\n" +
            "\n" +
            "Score: 21\n" +
            "\n" +
            "Score: 20\n" +
            "\n" +
            "Score: 19\n" +
            "\n" +
            "Score: 18\n" +
            "\n" +
            "Score: 17\n" +
            "\n" +
            "Score: 16\n" +
            "\n" +
            "Score: 15\n" +
            "\n" +
            "Score: 14\n" +
            "\n" +
            "Score: 13\n" +
            "\n" +
            "Score: 12\n" +
            "\n" +
            "Score: 11\n" +
            "\n" +
            "Score: 10\n" +
            "\n" +
            "Score: 9\n" +
            "Game over!\n" +
            "\n" +
            "Score: 8\n", viewLog.toString());

  }

  //Tests if the model's move is getting correct input for one valid move
  @Test
  public void testInputModelMoveOne() {
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for one valid move with new lines
  @Test
  public void testInputModelMoveOneNewLines() {
    Readable temp = new StringReader("2\n4\n4\n4\nq");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for one valid move with new lines and spaces
  @Test
  public void testInputModelMoveOneMixed() {
    Readable temp = new StringReader("2\n4 4\n4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for three valid moves
  @Test
  public void testInputModelMoveTwo() {
    Readable temp = new StringReader("2 4 4 4 3 6 3 4 5 6 3 6 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 2 5 2 3 4 5 2 5 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for a valid move,
  //then an invalid move, then a valid move
  @Test
  public void testInputModelMoveThree() {
    Readable temp = new StringReader("2 4 4 4 4 6 3 4 3 6 3 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 3 5 2 3 2 5 2 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for a valid move,
  //then a random character, then a valid move
  @Test
  public void testInputModelMoveFour() {
    Readable temp = new StringReader("2 4 4 4 e 4 6 3 4 3 6 3 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 3 5 2 3 2 5 2 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for a valid move,
  //then many random characters, then a valid move
  @Test
  public void testInputModelMoveFive() {
    Readable temp = new StringReader("2 4 4 4 e d e r : 4 6 : l 3 4 3 6 3 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 3 5 2 3 2 5 2 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for a valid move,
  //negative numbers in the middle, then a valid move
  @Test
  public void testInputModelMoveSix() {
    Readable temp = new StringReader("2 4 4 4 -4 -5 -6 -7 4 6 3 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 3 5 2 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for a valid move,
  //negative numbers in the middle and a quit, then a valid move
  @Test
  public void testInputModelMoveSeven() {
    Readable temp = new StringReader("2 4 4 4 -4 q -6 -7 4 6 3 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for an invalid diagonal move
  @Test
  public void testInputModelMoveEight() {
    Readable temp = new StringReader("2 2 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 1 3 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for an invalid orthogonal move
  @Test
  public void testInputModelMoveNine() {
    Readable temp = new StringReader("1 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("0 3 3 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for an invalid out of bounds move
  @Test
  public void testInputModelMoveTen() {
    Readable temp = new StringReader("100001 4 4 4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("100000 3 3 3 ", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for invalid negative positions
  @Test
  public void testInputModelMoveEleven() {
    Readable temp = new StringReader("-100001 -4 -4 -4 q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for invalid random letters (not q)
  @Test
  public void testInputModelMoveTwelve() {
    Readable temp = new StringReader("e r t x z } > q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("", modelLog.toString());
  }

  //Tests if the model's move is getting correct input for valid move with invalids
  // at the begining and end
  @Test
  public void testInputModelMoveThirteen() {
    Readable temp = new StringReader("e 2 4 4 4 > q");
    MarbleSolitaireControllerImpl c = new MarbleSolitaireControllerImpl(mModel, view, temp);
    c.playGame();
    assertEquals("1 3 3 3 ", modelLog.toString());
  }

  //Tests controller for a valid move but without quiting, tests if controller runs out of inputs
  @Test
  public void testExceptionControllerOne() {
    Readable temp = new StringReader("4 2 4 4");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    try {
      controller.playGame();
      fail();
    } catch (IllegalStateException e) {
      assertEquals("No more inputs", e.getMessage());
    }
  }

  //Tests controller for a IOException with a corrupted appendable
  @Test
  public void testExceptionControllerTwo() throws IOException {
    CorruptAppendable temp = new CorruptAppendable();
    Readable tempInput = new StringReader("4 2 4 4 q");
    MarbleSolitaireTextView corruptView = new MarbleSolitaireTextView(model, temp);
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model, corruptView, tempInput);
    try {
      controller.playGame();
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Render failed", e.getMessage());
    }
  }

  //Tests controller for invalid inputs without quiting, tests if controller runs out of inputs
  @Test
  public void testExceptionControllerThree() {
    Readable temp = new StringReader("4 e e e");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    try {
      controller.playGame();
      fail();
    } catch (IllegalStateException e) {
      assertEquals("No more inputs", e.getMessage());
    }
  }

  //Tests controller for a IOException with a corrupted readable
  @Test
  public void testExceptionControllerFour() throws IOException {
    CorruptAppendable temp = new CorruptAppendable();
    CorruptReadable corruptInput = new CorruptReadable();
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model, view, corruptInput);
    try {
      controller.playGame();
      fail();
    } catch (IllegalStateException e) {
      assertEquals("No more inputs", e.getMessage());
    }
  }

  //Tests controller for a valid move left
  @Test
  public void testControllerLeft() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n";
    Readable temp = new StringReader("4 2 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for a valid move left with an extra fifth character
  @Test
  public void testControllerLeftFive() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n";
    Readable temp = new StringReader("4 2 4 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for a valid move right
  @Test
  public void testControllerRight() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n";
    Readable temp = new StringReader("4 6 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for a valid move up
  @Test
  public void testControllerUp() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n";
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for a valid move down
  @Test
  public void testControllerDown() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n";
    Readable temp = new StringReader("6 4 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for an invalid diagonal move
  @Test
  public void testControllerDiagonal() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move, try again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("6 6 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for quit on the first input
  @Test
  public void testControllerQuitOne() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("q 4 4 4");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for quit on the second input
  @Test
  public void testControllerQuitTwo() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("2 q 4 4");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for quit on the third input
  @Test
  public void testControllerQuitTHree() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("2 4 q 4");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for quit on the fourth input
  @Test
  public void testControllerQuitFour() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("2 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for quit after an invalid input
  @Test
  public void testControllerQuitFive() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("e 2 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for quit after three invalid input
  @Test
  public void testControllerQuitSix() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    Readable temp = new StringReader("e 2 4 -9 L 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for a move after three invalid inputs
  @Test
  public void testControllerQuitSeven() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n";
    Readable temp = new StringReader("e 2 4 -9 L 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
  }

  //Tests controller for a full game of only valid inputs
  @Test
  public void testControllerGameOverOne() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O _ _ O O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O O O _ O _ O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O _ _ O O _ O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ O O _ O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ _ O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 19\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ _\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 18\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ O\n" +
            "    _ _ O\n" +
            "Score: 17\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ O O _\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 16\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 15\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 14\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O O _ O _ _ _\n" +
            "O _ O _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 13\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O O _ O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 11\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 10\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 9\n" +
            "Game over!\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n";
    Readable temp = new StringReader(fullGame);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, model.isGameOver());
  }

  //Tests controller for a full game of only valid inputs for European
  @Test
  public void testControllerGameOverOneEur() {
    assertEquals(false, modelEur.isGameOver());
    String s1 = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 33\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O _ _ O O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O O O _ O _ O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O _ _ O O _ O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ O O _ O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ _ O O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ _\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ O _ O _ _\n" +
            "  O _ _ O O\n" +
            "    _ _ O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ O O _\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 19\n" +
            "    O O O\n" +
            "  O O _ _ O\n" +
            "O O O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 18\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O O _ O _ _ _\n" +
            "O _ O _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 17\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O O _ O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 16\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 15\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 14\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 13\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "Game over!\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "  _ _ O _ O\n" +
            "    _ _ O\n" +
            "Score: 11\n";
    Readable temp = new StringReader(fullGameEur);
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelEur, viewEur, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, modelEur.isGameOver());
  }

  //Tests controller for a full game of only valid inputs for Triangle
  @Test
  public void testControllerGameOverOneTri() {
    assertEquals(false, modelTri.isGameOver());
    String s1 = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "_ O O O O\n" +
            "Score: 12\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O _ _ O O\n" +
            "Score: 11\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O _ O _ _\n" +
            "Score: 10\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 9\n" +
            "    O\n" +
            "   O O\n" +
            "  _ O O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 8\n" +
            "    O\n" +
            "   O O\n" +
            "  O _ _\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 7\n" +
            "    _\n" +
            "   O _\n" +
            "  O _ O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 6\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 5\n" +
            "Game over!\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O _ O _ O\n" +
            "Score: 4\n";
    Readable temp = new StringReader(fullGameTri);
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelTri, viewTri, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, modelTri.isGameOver());
  }

  //Tests controller for a full game an invalid move then all valid moves for European
  @Test
  public void testControllerGameOverTwo() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move, try again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O _ _ O O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O O O _ O _ O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O _ _ O O _ O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ O O _ O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ _ O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 19\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ _\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 18\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ O\n" +
            "    _ _ O\n" +
            "Score: 17\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ O O _\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 16\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 15\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 14\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O O _ O _ _ _\n" +
            "O _ O _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 13\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O O _ O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 11\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 10\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 9\n" +
            "Game over!\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n";
    Readable temp = new StringReader("1 4 4 4 " + fullGame);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, model.isGameOver());
  }

  //Tests controller for a full game an invalid move then all valid moves for European
  @Test
  public void testControllerGameOverTwoEur() {
    assertEquals(false, modelEur.isGameOver());
    String s1 = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move, try again.\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 33\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O _ _ O O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O O O _ O _ O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O _ _ O O _ O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ O O _ O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ _ O O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ _\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ O _ O _ _\n" +
            "  O _ _ O O\n" +
            "    _ _ O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ O O _\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 19\n" +
            "    O O O\n" +
            "  O O _ _ O\n" +
            "O O O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 18\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O O _ O _ _ _\n" +
            "O _ O _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 17\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O O _ O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 16\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 15\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 14\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 13\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "Game over!\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "  _ _ O _ O\n" +
            "    _ _ O\n" +
            "Score: 11\n";
    Readable temp = new StringReader("1 4 4 4 " + fullGameEur);
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelEur, viewEur, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, modelEur.isGameOver());
  }

  //Tests controller for a full game an invalid move then all valid moves for Triangle
  @Test
  public void testControllerGameOverTwoTri() {
    assertEquals(false, modelTri.isGameOver());
    String s1 = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move, try again.\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "_ O O O O\n" +
            "Score: 12\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O _ _ O O\n" +
            "Score: 11\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O _ O _ _\n" +
            "Score: 10\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 9\n" +
            "    O\n" +
            "   O O\n" +
            "  _ O O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 8\n" +
            "    O\n" +
            "   O O\n" +
            "  O _ _\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 7\n" +
            "    _\n" +
            "   O _\n" +
            "  O _ O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 6\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 5\n" +
            "Game over!\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O _ O _ O\n" +
            "Score: 4\n";
    Readable temp = new StringReader("1 4 4 4 " + fullGameTri);
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelTri, viewTri, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, modelTri.isGameOver());
  }

  //Tests controller for a full game with invalid inputs then all valid moves
  @Test
  public void testControllerGameOverThree() {
    assertEquals(false, model.isGameOver());
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O _ _ O O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O _ O O\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O O _ _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O O O _ O _ O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O _ _ O O _ O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ O O _ O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ _ O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 19\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ _\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 18\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ O\n" +
            "    _ _ O\n" +
            "Score: 17\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ O O _\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 16\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 15\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 14\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O O _ O _ _ _\n" +
            "O _ O _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 13\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O O _ O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 11\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 10\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 9\n" +
            "Game over!\n" +
            "    O O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n";
    Readable temp = new StringReader("e r -4 -492384398749 " + fullGame);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, model.isGameOver());
  }

  //Tests controller for a full game with invalid inputs then all valid moves for European
  @Test
  public void testControllerGameOverThreeEur() {
    assertEquals(false, modelEur.isGameOver());
    String s1 = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 33\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O _ _ O O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O O _ _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O O O _ O _ O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ O _ O _\n" +
            "O _ _ O O _ O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ O O _ O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ _ O O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ O _ _\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ O _ O _ _\n" +
            "  O _ _ O O\n" +
            "    _ _ O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ O O _\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 19\n" +
            "    O O O\n" +
            "  O O _ _ O\n" +
            "O O O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 18\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O O _ O _ _ _\n" +
            "O _ O _ O _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 17\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O O _ O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 16\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ O O _ _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 15\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 14\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 13\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "  O O _ _ O\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "Game over!\n" +
            "    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ O _ _\n" +
            "  _ _ O _ O\n" +
            "    _ _ O\n" +
            "Score: 11\n";
    Readable temp = new StringReader("e r -4 -492384398749 " + fullGameEur);
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelEur, viewEur, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, modelEur.isGameOver());
  }

  //Tests controller for a full game with invalid inputs then all valid moves for Triangle
  @Test
  public void testControllerGameOverThreeTri() {
    assertEquals(false, modelTri.isGameOver());
    String s1 = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "Invalid input, try again.\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "_ O O O O\n" +
            "Score: 12\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O _ _ O O\n" +
            "Score: 11\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O _ O _ _\n" +
            "Score: 10\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 9\n" +
            "    O\n" +
            "   O O\n" +
            "  _ O O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 8\n" +
            "    O\n" +
            "   O O\n" +
            "  O _ _\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 7\n" +
            "    _\n" +
            "   O _\n" +
            "  O _ O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 6\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ O\n" +
            "O _ O _ _\n" +
            "Score: 5\n" +
            "Game over!\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O _ O _ O\n" +
            "Score: 4\n";
    Readable temp = new StringReader("e r -4 -492384398749 " + fullGameTri);
    MarbleSolitaireControllerImpl controller =
            new MarbleSolitaireControllerImpl(modelTri, viewTri, temp);
    controller.playGame();
    assertEquals(s1, output.toString());
    assertEquals(true, modelTri.isGameOver());
  }
}