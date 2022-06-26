import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import milestone.RoomImpl;
import milestone.WorldImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * This class will check all different possibilities of various coordinates and different methods
 * implemented in room class.
 */
public class RoomImplTest {

  @Test
  public void validRoom() {
    RoomImpl r = new RoomImpl(30, 40, 50, 80, "House", 1);
    assertEquals("The room named House, has these coordinates 30 40 50 80 and "
            + "has index of 1", r.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void leftCoordinateNegative() {
    RoomImpl r = new RoomImpl(-10, 4, 6, 7, "House", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void rightCoordinateNegative() {
    RoomImpl r = new RoomImpl(10, 4, -6, 7, "House", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void topCoordinateNegative() {
    RoomImpl r = new RoomImpl(10, -4, 15, 7, "House", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void bottomCoordinateNegative() {
    RoomImpl r = new RoomImpl(10, 4, 15, -7, "House", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void leftCoordinateGreaterThanRightCoordinate() {
    RoomImpl r = new RoomImpl(10, 4, 8, 7, "House", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void topCoordinateGreaterThanBottomCoordinate() {
    RoomImpl r = new RoomImpl(4, 10, 8, 7, "House", 0);
  }

  //These tests give null pointer exception
  @Test(expected = IllegalArgumentException.class)
  public void leftCoordinateGreaterThanBoardXcoordinate() {
    RoomImpl r = new RoomImpl(37, 10, 8, 7, "House", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void rightCoordinateGreaterThanBoardXcoordinate() {
    RoomImpl r = new RoomImpl(30, 10, 38, 7, "House", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void topCoordinateGreaterThanBoardYcoordinate() {
    RoomImpl r = new RoomImpl(30, 10, 38, 7, "House", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void bottomCoordinateGreaterThanBoardYcoordinate() {
    RoomImpl r = new RoomImpl(30, 10, 38, 7, "House", 0);
  }

  @Test
  public void getRoomName() {
    RoomImpl r = new RoomImpl(30, 40, 50, 80, "House", 1);
    assertEquals("House", r.getRoomName());
  }

  @Test
  public void getIndex() {
    RoomImpl r = new RoomImpl(30, 40, 50, 80, "House", 1);
    assertEquals(1, r.getIndex());
  }

  @Test
  public void getLeft_coordinate() {
    RoomImpl r = new RoomImpl(30, 40, 50, 80, "House", 1);
    assertEquals(30, r.getLeftcoordinate());
  }

  @Test
  public void getTop_coordinate() {
    RoomImpl r = new RoomImpl(30, 40, 50, 80, "House", 1);
    assertEquals(40, r.getTopcoordinate());
  }

  @Test
  public void getBottom_coordinate() {
    RoomImpl r = new RoomImpl(30, 40, 50, 80, "House", 1);
    assertEquals(80, r.getBottomcoordinate());
  }

  @Test
  public void getRight_coordinate() {
    RoomImpl r = new RoomImpl(30, 40, 50, 80, "House", 1);
    assertEquals(50, r.getRightcoordinate());
  }


  @Test
  public void testEquals() {
    RoomImpl ri = new RoomImpl(10, 12, 12, 14, "Garden", 1);
    assertTrue(ri.equals(ri)); //Reflexivity
    RoomImpl ri1 = new RoomImpl(10, 12, 12, 14, "Garden", 1);
    assertTrue(ri.equals(ri1)); //Symmetry
    RoomImpl ri2 = ri1;
    assertTrue(ri2.equals(ri)); //Transitivity
    RoomImpl ri3 = new RoomImpl(11, 13, 12, 20, "Kitchen", 2);
    assertFalse(ri3.equals(ri1));
  }

  @Test
  public void testHashCode() {
    RoomImpl sr = new RoomImpl(10, 13, 12, 15, "House", 2);
    assertTrue(sr.hashCode() == sr.hashCode()); //Reflexivity
    RoomImpl sr1 = new RoomImpl(10, 13, 12, 15, "House", 2);
    assertTrue(sr.hashCode() == sr1.hashCode()); //Symmetry
    RoomImpl sr2 = sr1;
    assertTrue(sr2.hashCode() == sr.hashCode()); //Transitivity
  }
}