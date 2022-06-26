import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import milestone.Items;
import milestone.RoomImpl;
import milestone.SpaceImpl;
import milestone.Target;
import milestone.TargetImpl;
import milestone.WorldImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the functionality of the target.
 */


public class TargetTest {
  private List<RoomImpl> rooms;
  private List<Items> items;
  private SpaceImpl space;
  private StringReader sr;

  /**
   * As one of the methods takes object of the space as input we need to initialise test
   * just for the testing purpose.
   *
   */

  @Before
  public void setup() {

    sr = new StringReader("36 30 Doctor Lucky's Mansion\n"
            + "50 Doctor Lucky\n"
            + "21\n"
            + "22 19 23 26 Armory\n"
            + "16 21 21 28 Billiard Room\n"
            + "28  0 35  5 Carriage House\n"
            + "12 11 21 20 Dining Hall\n"
            + "22 13 25 18 Drawing Room\n"
            + "26 13 27 18 Foyer\n"
            + "28 26 35 29 Green House\n"
            + "30 20 35 25 Hedge Maze\n"
            + "16  3 21 10 Kitchen\n"
            + " 0  3  5  8 Lancaster Room\n"
            + " 4 23  9 28 Library\n"
            + " 2  9  7 14 Lilac Room\n"
            + " 2 15  7 22 Master Suite\n"
            + " 0 23  3 28 Nursery\n"
            + "10  5 15 10 Parlor\n"
            + "28 12 35 19 Piazza\n"
            + " 6  3  9  8 Servants' Quarters\n"
            + " 8 11 11 20 Tennessee Room\n"
            + "10 21 15 26 Trophy Room\n"
            + "22  5 23 12 Wine Cellar\n"
            + "30  6 35 11 Winter Garden\n"
            + "20\n"
            + "8 3 Crepe Pan\n"
            + "4 2 Letter Opener\n"
            + "12 2 Shoe Horn\n"
            + "8 3 Sharp Knife\n"
            + "0 3 Revolver\n"
            + "15 3 Civil War Cannon\n"
            + "2 4 Chain Saw\n"
            + "16 2 Broom Stick\n"
            + "1 2 Billiard Cue\n"
            + "19 2 Rat Poison\n"
            + "6 2 Trowel\n"
            + "2 4 Big Red Hammer\n"
            + "6 2 Pinking Shears\n"
            + "18 3 Duck Decoy\n"
            + "13 2 Bad Cream\n"
            + "18 2 Monkey Hand\n"
            + "11 2 Tight Hat\n"
            + "19 2 Piece of Rope\n"
            + "9 3 Silken Cord\n"
            + "7 2 Loud Noise");
    WorldImpl w1 = new WorldImpl(sr, 5);
    rooms = new ArrayList<>(w1.getRoomsL());
    items = new ArrayList<>(w1.getItemsL());

  }

  @Test
  public void isAlive() {
    Target t = new TargetImpl("Malav", 12);
    assertTrue(t.isAlive());
  }

  @Test
  public void getLocation() {
    Target t = new TargetImpl("Dr. Malav", 56);
    assertEquals(0, t.getLocation());
  }

  @Test
  public void moveTarget() {
    Target t = new TargetImpl("Dr. Malav", 56);
    assertEquals(0, t.getLocation());
    t.moveTarget(space);
    t.moveTarget(space);
    assertEquals(2, t.getLocation());
  }

  @Test
  public void testToString() {

  }

  @Test
  public void whereIsTarget() {
    TargetImpl t = new TargetImpl("Dr.Lucky", 10);
    assertEquals("Armory", t.whereIsTarget(space));
    t.moveTarget(space);
    t.moveTarget(space);
    assertEquals("Carriage House", t.whereIsTarget(space));
  }

  @Test
  public void testEquals() {
    TargetImpl ri = new TargetImpl("Malav", 12);
    assertTrue(ri.equals(ri)); //Reflexivity
    TargetImpl ri1 = new TargetImpl("Malav", 12);
    assertTrue(ri.equals(ri1)); //Symmetry
    TargetImpl ri2 = ri1;
    assertTrue(ri2.equals(ri)); //Transitivity
    TargetImpl ri3 = new TargetImpl("Shivam", 24);
    assertFalse(ri3.equals(ri1));
  }

  @Test
  public void testHashCode() {
    TargetImpl sr = new TargetImpl("Malav", 2);
    assertTrue(sr.hashCode() == sr.hashCode()); //Reflexivity
    TargetImpl sr1 = new TargetImpl("Malav", 2);
    assertTrue(sr.hashCode() == sr1.hashCode()); //Symmetry
    TargetImpl sr2 = sr1;
    assertTrue(sr2.hashCode() == sr.hashCode()); //Transitivity
  }
}