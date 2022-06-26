import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import milestone.ComputerPlayer;
import milestone.HumanPlayer;
import milestone.Items;
import milestone.PetImpl;
import milestone.RandomInterface;
import milestone.RoomImpl;
import milestone.SpaceImpl;
import milestone.TargetImpl;
import milestone.WorldImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * In this class we test all the important methods related to our space (that comprises of
 * rooms and items).
 */
public class SpaceImplTest {

  private List<RoomImpl> rooms;
  private List<Items> items;
  private HumanPlayer humanPlayer;
  private StringReader sr;

  /**
   * In order to get space we should first initialize our world which would gives list of rooms
   * object and list of items object. These lists are taken as parameters to create object
   * of space.
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
    WorldImpl w1 = new WorldImpl(sr, 5);
    rooms = new ArrayList<>(w1.getRoomsL());
    items = new ArrayList<>(w1.getItemsL());
    SpaceImpl space = new SpaceImpl(rooms, items);
  }

  @Test
  public void getRoomDetailsWithTwoHumanPlayer() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    HumanPlayer humanPlayer = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp = new HumanPlayer("Daksh", "Billiard Room", 2, true);
    hp.moveToNeighbourSpace("Armory", s);
    TargetImpl t = new TargetImpl("Boss", 10);
    PetImpl p = new PetImpl("Fortune", 10);
    t.moveTarget(s);
    assertEquals(" In room named Armory , there is/are [Revolver] present and "
                    + "its neighbour is/are [Billiard Room, Dining Hall, Drawing Room] . "
                    + "The players present are [Malav, Daksh] .",
            s.getRoomDetails("Armory", humanPlayer, t, p));
  }

  @Test
  public void getRoomDetailsWithItemPresentPicked() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    HumanPlayer humanPlayer = new HumanPlayer("Malav", "Armory", 3, true);
    TargetImpl t = new TargetImpl("Boss", 10);
    PetImpl p = new PetImpl("Fortune", 10);
    assertEquals(" In room named Armory , there is/are [Revolver] present and "
                    + "its neighbour is/are [Billiard Room, Dining Hall, Drawing Room] . "
                    + "The players present are [Malav] . Target Boss is also here",
            s.getRoomDetails("Armory", humanPlayer, t, p));
    humanPlayer.pickUpItem("Revolver", s);
    assertEquals(" In room named Armory , there is/are [] present and "
                    + "its neighbour is/are [Billiard Room, Dining Hall, Drawing Room] . "
                    + "The players present are [Malav] . Target Boss is also here",
            s.getRoomDetails("Armory", humanPlayer, t, p));
  }

  @Test
  public void getRoomDetailsWithOneTypeOfEach() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    HumanPlayer humanPlayer = new HumanPlayer("Malav", "Armory", 3, true);
    TargetImpl t = new TargetImpl("Boss", 10);
    RandomInterface r = new RandomMock();
    ComputerPlayer hp = new ComputerPlayer("Daksh", "Billiard Room", 2, true, r);
    hp.moveToNeighbourSpace("Armory", s);
    PetImpl p = new PetImpl("Fortune", 0);
    assertEquals(" In room named Armory , there is/are [Revolver] present and "
            + "its neighbour is/are [Billiard Room, Dining Hall, Drawing Room] . "
            + "The players present are [Malav, Daksh] . Target Boss is also here along "
            + "with Pet Fortune is also here", s.getRoomDetails("Armory", humanPlayer, t, p));
  }

  @Test
  public void getRoomDetailsWithOneHumanPlayerOneComputerPlayer() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    HumanPlayer humanPlayer = new HumanPlayer("Malav", "Armory", 3, true);
    TargetImpl t = new TargetImpl("Boss", 10);
    t.moveTarget(s);
    PetImpl p = new PetImpl("Fortune", 10);
    RandomInterface r = new RandomMock();
    ComputerPlayer hp = new ComputerPlayer("Daksh", "Armory", 2, true, r);
    assertEquals(" In room named Armory , there is/are [Revolver] present and "
                    + "its neighbour is/are [Billiard Room, Dining Hall, Drawing Room] . "
                    + "The players present are [Malav, Daksh] .",
            s.getRoomDetails("Armory", humanPlayer, t, p));
  }


  @Test
  public void getAllRooms() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    String allRooms = Arrays.toString(s.getAllRooms());
    assertEquals("[Armory, Billiard Room, Carriage House, Dining Hall, "
            + "Drawing Room, Foyer, Green House, Hedge Maze, Kitchen, Lancaster Room, "
            + "Library, Lilac Room, Master Suite, Nursery, Parlor, Piazza, Servants' Quarters, "
            + "Tennessee Room, Trophy Room, Wine Cellar, Winter Garden]", allRooms);
  }


  @Test
  public void getRoomDetailsItemPickUp() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    HumanPlayer humanPlayer = new HumanPlayer("Malav", "Kitchen", 3, true);
    TargetImpl t = new TargetImpl("Boss", 10);
    PetImpl p = new PetImpl("Fortune", 10);
    assertEquals(" In room named Kitchen , there is/are [Crepe Pan, Sharp Knife] present and "
                    + "its neighbour is/are [Dining Hall, Parlor, Wine Cellar] . "
                    + "The players present are [Malav] .",
            s.getRoomDetails("Kitchen", humanPlayer, t, p));
    humanPlayer.pickUpItem("Crepe Pan", s);
    assertEquals(" In room named Kitchen , there is/are [Sharp Knife] present and "
                    + "its neighbour is/are [Dining Hall, Parlor, Wine Cellar] . "
                    + "The players present are [Malav] .",
            s.getRoomDetails("Kitchen", humanPlayer, t, p));
  }


  @Test
  public void getNeighbours() {
    ArrayList<String> ar = new ArrayList<>();
    ar.add("Lancaster Room");
    ar.add("Lilac Room");
    ar.add("Parlor");
    SpaceImpl s = new SpaceImpl(rooms, items);
    assertEquals(ar, s.getNeighbours("Servants' Quarters"));
  }

  @Test
  public void getOneNeighbour() {
    ArrayList<String> ar = new ArrayList<>();
    ar.add("Drawing Room");
    ar.add("Piazza");
    SpaceImpl s = new SpaceImpl(rooms, items);
    assertEquals(ar, s.getNeighbours("Foyer"));
  }


  @Test
  public void itemsInRoom() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    ArrayList<String> ar = new ArrayList<>();
    ar.add("Crepe Pan");
    ar.add("Sharp Knife");
    assertEquals(ar, s.itemsInRoom("Kitchen"));
  }


  @Test(expected = IllegalArgumentException.class)
  public void checkOverlap() {
    StringReader sr1 = new StringReader("36 30 Doctor Lucky's Mansion\n"
            + "50 Doctor Lucky\n"
            + "21\n"
            + "22 19 23 26 Armory\n"
            + "16 21 21 28 Billiard Room\n"
            + "22 19 23 16 Carriage House\n"
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
    WorldImpl w1 = new WorldImpl(sr1, 5);
    rooms = new ArrayList<>(w1.getRoomsL());
    items = new ArrayList<>(w1.getItemsL());
    SpaceImpl s = new SpaceImpl(rooms, items);
  }

  @Test
  public void checkOverlapNoOverlap() {
    WorldImpl w1 = new WorldImpl(sr, 5);
    rooms = new ArrayList<>(w1.getRoomsL());
    items = new ArrayList<>(w1.getItemsL());
    SpaceImpl s = new SpaceImpl(rooms, items);
  }

  @Test
  public void testDfs() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    assertEquals("[Armory, Billiard Room, Dining Hall, Drawing Room, Foyer, Piazza, "
                    +
                    "Hedge Maze, Green House, Hedge Maze, Piazza, Winter Garden, "
                    +
                    "Carriage House, Winter Garden, Piazza, Foyer, Drawing Room, "
                    +
                    "Wine Cellar, Kitchen, Parlor, Servants' Quarters, Lancaster Room, "
                    +
                    "Lilac Room, Master Suite, Library, Nursery, Library, Trophy Room, "
                    +
                    "Tennessee Room]",
            s.dfs(0).toString());
  }

  @Test
  public void testDfsDifferentIndex() {
    SpaceImpl s = new SpaceImpl(rooms, items);
    assertEquals("[Drawing Room, Armory, Billiard Room, Dining Hall, Kitchen, Parlor, "
                    +
            "Servants' Quarters, Lancaster Room, Lilac Room, Master Suite, Library, Nursery, "
                    +
            "Library, Trophy Room, Tennessee Room, Trophy Room, Library, Master Suite, "
                    +
            "Lilac Room, Lancaster Room, Servants' Quarters, Parlor, Kitchen, Wine Cellar, "
                    +
            "Kitchen, Dining Hall, Billiard Room, Armory, Drawing Room, Foyer, Piazza, "
                    +
            "Hedge Maze, Green House, Hedge Maze, Piazza, Winter Garden, Carriage House]",
            s.dfs(4).toString());
  }

}

