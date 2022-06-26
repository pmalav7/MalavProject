import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import milestone.Items;
import milestone.PetImpl;
import milestone.RoomImpl;
import milestone.SpaceImpl;
import milestone.WorldImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the methods of PetImpl class.
 */


public class PetImplTest {

  private List<RoomImpl> rooms;
  private List<Items> items;
  private StringReader sr;
  private SpaceImpl sp;
  private PetImpl pet;


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
    sp = new SpaceImpl(rooms, items);
    pet = new PetImpl("Fortune", 0);
  }

  @Test(expected = IllegalStateException.class)
  public void petImplConstructor() {
    PetImpl pi = new PetImpl("Kitty", -1);
  }

  @Test
  public void getLocation() {
    PetImpl pi = new PetImpl("Tommy", 0);
    assertEquals(0, pi.getLocation());
    pi.movePet("Dining Hall", sp);
    assertEquals(3, pi.getLocation());
  }

  @Test
  public void whereIsPetNew() {
    assertEquals(0, pet.getLocation());
  }

  @Test
  public void movePet() {
    PetImpl pi = new PetImpl("Tommy", 0);
    pi.movePet("Dining Hall", sp);
    assertEquals("Dining Hall", pi.whereIsPet(sp));
  }

  @Test(expected = IllegalStateException.class)
  public void inValidRoomMovePet() {
    PetImpl pi = new PetImpl("Tommy", 0);
    pi.movePet("Bathroom", sp);
  }

  @Test
  public void notNeighbourMovePet() {
    PetImpl pi = new PetImpl("Tommy", 0);
    pi.movePet("Lancaster Room", sp);
    assertEquals("Lancaster Room", pi.whereIsPet(sp));
  }

  @Test
  public void whereIsPet() {
    PetImpl pi = new PetImpl("Tommy", 5);
    assertEquals("Foyer", pi.whereIsPet(sp));
  }

  @Test(expected = IllegalStateException.class)
  public void movingPetToSameLocation() {
    PetImpl pi = new PetImpl("Tommy", 5);
    assertEquals("Foyer", pi.whereIsPet(sp));
    pi.movePet("Foyer", sp);
  }

  @Test
  public void movingPetDfs() {
    PetImpl pi = new PetImpl("Tommy", 0);
    pi.movePetDfsNew(sp);
    assertEquals("Billiard Room", pi.whereIsPet(sp));
    pi.movePetDfsNew(sp);
    assertEquals("Dining Hall", pi.whereIsPet(sp));
    pi.movePetDfsNew(sp);
  }

  @Test
  public void movingPetDfsLastPosition() {
    PetImpl pi = new PetImpl("Tommy", 0);
    for (int i = 0; i < 27; i++) {
      pi.movePetDfsNew(sp);
    }
    assertEquals("Armory", pi.whereIsPet(sp));
    pi.movePetDfsNew(sp);
  }

  @Test
  public void testDfsMovementUpdatingDfsAfterManualMovementByPlayer() {
    PetImpl pi = new PetImpl("Tommy", 0);
    pi.movePetDfsNew(sp);
    pi.movePet("Foyer", sp);
    pi.movePetDfsNew(sp);
    assertEquals("Drawing Room", pi.whereIsPet(sp));
    pi.movePetDfsNew(sp);
    assertEquals("Armory", pi.whereIsPet(sp));
    pi.movePetDfsNew(sp);
    assertEquals("Billiard Room", pi.whereIsPet(sp));
  }

  @Test
  public void testDfsIfReachesTheEnd() {
    PetImpl pi = new PetImpl("Tommy", 0);
    pi.movePet("Foyer", sp);
    for (int i = 0; i < 28; i++) {
      pi.movePetDfsNew(sp);
    }
    assertEquals("Armory", pi.whereIsPet(sp));
  }

  @Test
  public void testDfsMovement() {
    PetImpl pi = new PetImpl("Tommy", 0);
    pi.movePetDfsNew(sp);
    assertEquals("Billiard Room", pi.whereIsPet(sp));
    pi.movePetDfsNew(sp);
    assertEquals("Dining Hall", pi.whereIsPet(sp));
  }

  @Test
  public void testEquals() {
    PetImpl ri = new PetImpl("Malav", 12);
    assertTrue(ri.equals(ri)); //Reflexivity
    PetImpl ri1 = new PetImpl("Malav", 12);
    assertTrue(ri.equals(ri1)); //Symmetry
    PetImpl ri2 = ri1;
    assertTrue(ri2.equals(ri)); //Transitivity
    PetImpl ri3 = new PetImpl("Shivam", 24);
    assertFalse(ri3.equals(ri1));
  }

  @Test
  public void testHashCode() {
    PetImpl sr = new PetImpl("Malav", 2);
    assertTrue(sr.hashCode() == sr.hashCode()); //Reflexivity
    PetImpl sr1 = new PetImpl("Malav", 2);
    assertTrue(sr.hashCode() == sr1.hashCode()); //Symmetry
    PetImpl sr2 = sr1;
    assertTrue(sr2.hashCode() == sr.hashCode()); //Transitivity
  }

  @Test
  public void newDfsLocation() {
    PetImpl pi = new PetImpl("Tommmy", 0);
    pi.movePet("Piazza", sp);
    pi.movePetDfsNew(sp);
    assertEquals("Foyer", pi.whereIsPet(sp));
    pi.movePetDfsNew(sp);
    assertEquals("Drawing Room", pi.whereIsPet(sp));
  }

}