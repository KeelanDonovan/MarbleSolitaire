package cs3500.marblesolitaire;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * MarbleSolitaireFactory class to help with creating the models and views for the main method.
 */
public class MarbleSolitaireFactory {

  //Default constructors
  protected MarbleSolitaireModel getModel(String shapeType) throws IllegalArgumentException {
    if (shapeType.equalsIgnoreCase("english")) {
      return new EnglishSolitaireModel();

    } else if (shapeType.equalsIgnoreCase("European")) {
      return new EuropeanSolitaireModel();

    } else if (shapeType.equalsIgnoreCase("triangle")) {
      return new TriangleSolitaireModel();
    }
    throw new IllegalArgumentException("Invalid inputs");
  }

  //Size only constructors
  protected MarbleSolitaireModel getModel(String shapeType, int size)
          throws IllegalArgumentException {
    if (shapeType.equalsIgnoreCase("english")) {
      return new EnglishSolitaireModel(size);

    } else if (shapeType.equalsIgnoreCase("European")) {
      return new EuropeanSolitaireModel(size);

    } else if (shapeType.equalsIgnoreCase("triangle")) {
      return new TriangleSolitaireModel(size);
    }
    throw new IllegalArgumentException("Invalid inputs");
  }


  //empty position only constructors
  protected MarbleSolitaireModel getModel(String shapeType, int row, int col)
          throws IllegalArgumentException {
    if (shapeType.equalsIgnoreCase("english")) {
      return new EnglishSolitaireModel(row, col);

    } else if (shapeType.equalsIgnoreCase("European")) {
      return new EuropeanSolitaireModel(row, col);

    } else if (shapeType.equalsIgnoreCase("triangle")) {
      return new TriangleSolitaireModel(row, col);
    }
    throw new IllegalArgumentException("Invalid inputs");
  }

  //size and empty position constructors
  protected MarbleSolitaireModel getModel(String shapeType, int size, int row, int col)
          throws IllegalArgumentException {
    if (shapeType.equalsIgnoreCase("english")) {
      return new EnglishSolitaireModel(size, row, col);

    } else if (shapeType.equalsIgnoreCase("European")) {
      return new EuropeanSolitaireModel(size, row, col);

    } else if (shapeType.equalsIgnoreCase("triangle")) {
      return new TriangleSolitaireModel(size, row, col);
    }
    throw new IllegalArgumentException("Invalid inputs");
  }

  //returns the correct view depending on the given type
  protected MarbleSolitaireView getView(String type, MarbleSolitaireModel model, Appendable out) {
    if (type.equals("english") || type.equals("european")) {
      return new MarbleSolitaireTextView(model, out);
    } else if (type.equalsIgnoreCase("triangle")) {
      return new TriangleSolitaireTextView(model, out);
    }
    throw new IllegalArgumentException("Invalid inputs");
  }
}
