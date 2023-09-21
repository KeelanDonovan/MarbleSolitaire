
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * test class for MarbleSolitaireTextViewTest.
 */
public class MarbleSolitaireTextViewTest {
  private EnglishSolitaireModel cons1;

  private MarbleSolitaireTextView view1;
  private MarbleSolitaireTextView view5;
  private StringBuffer s;
  private CorruptAppendable corruptApp;

  /**
   * initializes the models.
   */
  @Before
  public void init() {
    cons1 = new EnglishSolitaireModel();
    view1 = new MarbleSolitaireTextView(cons1);
    s = new StringBuffer();
    view5 = new MarbleSolitaireTextView(cons1, s);
    corruptApp = new CorruptAppendable();
  }

  //tests toString
  @Test
  public void testToString() {
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O";
    String s2 = "    O O _\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O";
    String s3 = "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O";
    String s4 = "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O _ O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O";
    String s5 = "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O";
    EnglishSolitaireModel cons2 = new EnglishSolitaireModel(0, 4);
    EnglishSolitaireModel cons3 = new EnglishSolitaireModel(5);
    EnglishSolitaireModel cons4 = new EnglishSolitaireModel(5, 4, 4);
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(cons2);
    MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(cons3);
    MarbleSolitaireTextView view4 = new MarbleSolitaireTextView(cons4);
    assertEquals(s1, view1.toString());
    assertEquals(s2, view2.toString());
    assertEquals(s3, view3.toString());
    assertEquals(s4, view4.toString());
    cons1.move(1, 3, 3, 3);
    assertEquals(s5, view1.toString());
  }

  //Tests toString with European Model
  @Test
  public void testToStringEuropean() {
    EuropeanSolitaireModel cons2 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(cons2);
    String s = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(s, view2.toString());
  }

  //Tests move for a null state in constructor
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorViewOne() {
    new MarbleSolitaireTextView(null);
  }

  //Tests move for a null state in constructor with null state
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorViewTwo() {
    new MarbleSolitaireTextView(null, s);
  }

  //Tests move for a null state in constructor with null state
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorViewThree() {
    new MarbleSolitaireTextView(cons1, null);
  }

  //Tests move for a null state in constructor with both null
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionConstructorViewFour() {
    new MarbleSolitaireTextView(null, null);
  }

  //Tests renderBoard for Correctness
  @Test
  public void testRenderBoard() {
    String s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O";
    assertEquals(s.toString(), "");
    try {
      view5.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(s1, s.toString());
  }

  //tests renderBoard for IOException
  @Test
  public void testExceptionRenderBoard() throws IOException {
    MarbleSolitaireTextView view6 = new MarbleSolitaireTextView(cons1, corruptApp);
    CorruptAppendable corruptApp = new CorruptAppendable();
    try {
      view6.renderBoard();
      fail();
    } catch (IOException e) {
      assertEquals("render was not possible", e.getMessage());
    }
  }

  //Tests renderBoard for Correctness
  @Test
  public void testRenderMessageOne() {
    String s1 = "Hello";
    assertEquals(s.toString(), "");
    try {
      view5.renderMessage(s1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(s.toString(), s1);
  }

  //Tests renderBoard for Correctness
  @Test
  public void testRenderMessageTwo() {
    String s1 = "Hellosdfsjflsjflakjsfs fslfjslfjsfsfjs" +
            "sflksjfsjfjs fsjlfsf";
    assertEquals(s.toString(), "");
    try {
      view5.renderMessage(s1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(s.toString(), s1);
  }

  //tests renderMessage for IOException
  @Test
  public void testExceptionRenderMessage() throws IOException {
    MarbleSolitaireTextView view6 = new MarbleSolitaireTextView(cons1, corruptApp);
    CorruptAppendable corruptApp = new CorruptAppendable();
    try {
      view6.renderMessage("Hello");
      fail();
    } catch (IOException e) {
      assertEquals("render was not possible", e.getMessage());
    }
  }
}