
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * test class for MarbleSolitaireTextViewTest.
 */
public class TriangleSolitaireTextViewTest {
  private TriangleSolitaireModel cons1;

  private TriangleSolitaireTextView view1;
  private TriangleSolitaireTextView view5;
  private StringBuffer s;
  private CorruptAppendable corruptApp;

  /**
   * initializes the models.
   */
  @Before
  public void init() {
    cons1 = new TriangleSolitaireModel();
    view1 = new TriangleSolitaireTextView(cons1);
    s = new StringBuffer();
    view5 = new TriangleSolitaireTextView(cons1, s);
    corruptApp = new CorruptAppendable();
  }

  //tests toString
  @Test
  public void testToString() {
    String s1 = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";
    String s2 = "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O _\n" +
            "O O O O O";
    String s3 = "      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O";
    String s4 = "      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O _\n" +
            " O O O O O O\n" +
            "O O O O O O O";
    String s5 = "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O";
    TriangleSolitaireModel cons2 = new TriangleSolitaireModel(3, 3);
    TriangleSolitaireModel cons3 = new TriangleSolitaireModel(7);
    TriangleSolitaireModel cons4 = new TriangleSolitaireModel(7, 4, 4);
    TriangleSolitaireTextView view2 = new TriangleSolitaireTextView(cons2);
    TriangleSolitaireTextView view3 = new TriangleSolitaireTextView(cons3);
    TriangleSolitaireTextView view4 = new TriangleSolitaireTextView(cons4);
    assertEquals(s1, view1.toString());
    assertEquals(s2, view2.toString());
    assertEquals(s3, view3.toString());
    assertEquals(s4, view4.toString());
    cons1.move(2, 0, 0, 0);
    assertEquals(s5, view1.toString());
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
    String s1 = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";
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