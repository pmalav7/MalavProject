import static org.junit.Assert.assertEquals;

import milestone.Board;
import org.junit.Test;

/**
 * This board test class tests all the methods of the Board class.
 * It will also check when a wrong input is given to the board parameter.
 */

public class BoardTest {

  @Test(expected = IllegalArgumentException.class)
  public void xcoordinateNegative() {
    Board b = new Board(-10, 30, "House");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ycoordinateNegative() {
    Board b = new Board(10, -30, "House");
  }

  @Test(expected = IllegalArgumentException.class)
  public void bothCoordinatesNegative() {
    Board b = new Board(-10, -20, "House");
  }

  @Test(expected = IllegalArgumentException.class)
  public void xcoordinateZero() {
    Board b = new Board(0, 20, "House");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ycoordinateZero() {
    Board b = new Board(20, 0, "House");
  }

  @org.junit.Test
  public void getXcoordinate() {
    Board b = new Board(10, 20, "House");
    assertEquals(10, b.getXcoordinate());
  }

  @org.junit.Test
  public void getYcoordinate() {
    Board b = new Board(10, 20, "House");
    assertEquals(20, b.getYcoordinate());
  }

  @org.junit.Test
  public void getName() {
    Board b = new Board(10, 20, "House");
    assertEquals("House", b.getName());
  }

}