import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import milestone.HumanPlayer;
import milestone.Items;
import milestone.RoomImpl;
import milestone.SpaceImpl;
import milestone.World;
import milestone.WorldImpl;
import milestone.WorldPlayerInterface;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests important method that are unique to world class.
 */

public class WorldImplTest {
  private List<RoomImpl> rooms;
  private List<Items> items;
  private HumanPlayer humanPlayer;
  private StringReader sr;
  private WorldPlayerInterface w1;
  private WorldImpl w2;

  /**
   * Initializing the world model using string reader for testing purpose.
   */

  @Before
  public void setup() {
    sr = new StringReader("36 30 Doctor Lucky's Mansion\n"
            + "50 Doctor Lucky\n"
            + "Fortune the Cat\n"
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
    w1 = new WorldImpl(sr, 5);
    rooms = new ArrayList<>(w2.getRoomsL());
    items = new ArrayList<>(w2.getItemsL());
  }

  @Test(expected = IllegalStateException.class)
  public void createPlayerWithSimilarName() {
    w1.compPlayer();
    w1.createPlayer("Malav", "Armory", 3, true);
    w1.createPlayer("Malav", "Kitchen", 3, true);
  }

  @Test(expected = IllegalStateException.class)
  public void createHumanPlayerAndComputerPlayerWithSimilarName() {
    w1.compPlayer();
    w1.createPlayer("Malav", "Armory", 3, true);
    w1.createPlayer("Malav", "Kitchen", 3, false);
  }

  @Test
  public void showNearbyItems() {
    w1.compPlayer();
    w1.createPlayer("Malav", "Armory", 3, true);
    assertEquals("Available option/s to pick[Revolver]", w1.showNearbyItem());
  }


  @Test(expected = IllegalStateException.class)
  public void createPlayerWithUnavailableSpace() {
    w1.compPlayer();
    w1.createPlayer("Malav", "XYZ", 3, true);
  }

  @Test
  public void spaceInfo() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    w1.createPlayer("Malav", "XYZ", 3, true);
    assertEquals("", w1.spaceInfo("Armory"));
  }

}