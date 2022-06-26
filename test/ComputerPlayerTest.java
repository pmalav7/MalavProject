import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
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
 * This class provides testing of computer player . It makes use of RandomMock class while creating
 * computer player object to imitate the random behaviour of the computer player.
 */


public class ComputerPlayerTest {

  private List<RoomImpl> rooms;
  private List<Items> items;
  private StringReader sr;
  private SpaceImpl space;

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
    WorldImpl w1 = new WorldImpl(sr, 5);
    rooms = new ArrayList<>(w1.getRoomsL());
    items = new ArrayList<>(w1.getItemsL());
    //space = new SpaceImpl(rooms, items);
  }

  //Computer player looking around
  @Test
  public void computerPlayerMove() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    t.moveTarget(space);
    t.moveTarget(space);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface r = new RandomMock(0);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3, false, r);
    assertEquals("Player Malav has moved to Billiard Room and this "
            + "space has [Billiard Cue]\n", cp.computerPlayer(space, t, pet));
  }

  @Test
  public void computerPlayerPickItem() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    t.moveTarget(space);
    t.moveTarget(space);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface random = new RandomMock(1, 0);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3,
            false, random);
    assertEquals("Player Malav has picked up the item Revolver\n",
            cp.computerPlayer(space, t, pet));
  }

  @Test
  public void computerPlayerLook() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    RandomInterface random = new RandomMock(2);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    t.moveTarget(space);
    t.moveTarget(space);
    PetImpl pet = new PetImpl("kitty", 0);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3,
            false, random);
    assertEquals("Player Malav is at space: Armory and following spaces are "
            + "visible from this space: [Billiard Room, Dining Hall, Drawing Room] "
            + "+ player that are around: [] and items that are around: "
            + "[Drawing Room=[Letter Opener], Armory=[Revolver], "
            + "Billiard Room=[Billiard Cue]]\n", cp.computerPlayer(space, t, pet));
  }

  @Test
  public void computerPlayerNoItemsToPickThenMove() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface random = new RandomMock(1, 0);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Dining Hall", 3,
            false, random);
    assertEquals("Player Malav has moved to Armory and this space has [Revolver]\n",
            cp.computerPlayer(space, t, pet));
  }

  @Test
  public void computerPlayerNoItemsToPickThenMoveSecondType() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface random = new RandomMock(1, 1);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Dining Hall", 3,
            false, random);
    assertEquals("Player Malav has moved to Billiard Room and this space has "
            + "[Billiard Cue]\n", cp.computerPlayer(space, t, pet));
  }

  @Test
  public void computerPlayerItemPossesed() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    t.moveTarget(space);
    t.moveTarget(space);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface random = new RandomMock(1, 0);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3, false, random);
    assertEquals("Player Malav has picked up the item Revolver\n",
            cp.computerPlayer(space, t, pet));
    assertEquals("Player Malav has following items: [Revolver]", cp.itemsPossessed());
  }

  @Test
  public void computerPlayerDescription() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface random = new RandomMock(0, 1);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3,
            false, random);
    assertEquals("Player Malav has no items and is at space: Armory",
            cp.playerDescription("Malav"));
  }

  //As the item is already picked up , computer player could not find any item as
  // a result of which it moves to next place by simulation
  @Test
  public void computerPlayerTryingToPickAlreadyPickedItem() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface random = new RandomMock(1, 0);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3,
            false, random);
    HumanPlayer hp = new HumanPlayer("MrX", "Armory", 2, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Player Malav has moved to Billiard Room and this space has "
            + "[Billiard Cue]\n", cp.computerPlayer(space, t, pet));
  }

  @Test
  public void computerPlayerLookWithHumanLook() {
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    PetImpl pet = new PetImpl("kitty", 10);
    RandomInterface random = new RandomMock(2);
    SpaceImpl space = new SpaceImpl(rooms, items);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3,
            false, random);
    HumanPlayer hp = new HumanPlayer("Daksh", "Armory", 2, true);
    assertEquals("Player Malav is at space: Armory and following spaces are visible from"
                    + " this space: [Billiard Room, Dining Hall, Drawing Room] "
                    +
                    "+ player that are around: "
                    +
                    "[Daksh=Armory, Doctor Lucky=Armory] and items that are around: "
                    + "[Drawing Room=[Letter Opener], Armory=[Revolver], "
                    +
                    "Billiard Room=[Billiard Cue]]\n",
            cp.computerPlayer(space, t, pet));
  }

  @Test
  public void computerPlayerLookWithTarget() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    PetImpl pet = new PetImpl("kitty", 10);
    RandomInterface random = new RandomMock(2);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3,
            false, random);
    HumanPlayer hp = new HumanPlayer("Daksh", "Armory", 2, true);
    assertEquals("Player Malav is at space: Armory and following spaces are visible "
                    + "from this space: [Billiard Room, Dining Hall, Drawing Room] "
                    + "+ player that are around: [Daksh=Armory, Doctor Lucky=Armory] "
                    + "and items that are around: [Drawing Room=[Letter Opener], "
                    +
                    "Armory=[Revolver], "
                    + "Billiard Room=[Billiard Cue]]\n",
            cp.computerPlayer(space, t, pet));
  }

  @Test
  public void attackTargetNoWeapon() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl target = new TargetImpl("Target", 50);
    RandomInterface random = new RandomMock(2);
    PetImpl p = new PetImpl("Fortune", 0);
    ComputerPlayer hp = new ComputerPlayer("Malav", "Armory", 3, false, random);
    assertEquals("Attack successful! The health of target is reduced by 1. "
            +
            "Remaining health of target: 49\n", hp.computerPlayer(space, target, p));
    assertEquals(49, target.getHealth());
  }

  @Test
  public void attackTargetAfterItemPicking() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 7);
    t.moveTarget(space);
    t.moveTarget(space);
    t.moveTarget(space);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface random = new RandomMock(1, 0, 0);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3, false, random);
    assertEquals("Player Malav has picked up the item Revolver\n",
            cp.computerPlayer(space, t, pet));
    assertEquals("Player Malav has moved to Dining Hall and this space has []\n",
            cp.computerPlayer(space, t, pet));
    assertEquals("Attack Successful! The health of target is reduced by 3. "
            +
            "Remaining health of target: 4\n", cp.computerPlayer(space, t, pet));
  }

  @Test
  public void killingTargetAfterItemPicking() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Doctor Lucky", 3);
    t.moveTarget(space);
    t.moveTarget(space);
    t.moveTarget(space);
    PetImpl pet = new PetImpl("kitty", 0);
    RandomInterface random = new RandomMock(1, 0, 0);
    ComputerPlayer cp = new ComputerPlayer("Malav", "Armory", 3, false, random);
    assertEquals("Player Malav has picked up the item Revolver\n",
            cp.computerPlayer(space, t, pet));
    assertEquals("Player Malav has moved to Dining Hall and this space has []\n",
            cp.computerPlayer(space, t, pet));
    assertEquals("Attack Successful! You have successfully killed the target\n",
            cp.computerPlayer(space, t, pet));
  }

  @Test
  public void killingTargetByPoke() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl target = new TargetImpl("Target", 1);
    RandomInterface random = new RandomMock(2);
    PetImpl p = new PetImpl("Fortune", 0);
    ComputerPlayer hp = new ComputerPlayer("Malav", "Armory", 3, false, random);
    assertEquals("Attack Successful!You have successfully killed the target\n",
            hp.computerPlayer(space, target, p));
    assertEquals(0, target.getHealth());
  }


  @Test
  public void attackTargetNotWorkingIfTargetNotAround() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl target = new TargetImpl("Target", 50);
    target.moveTarget(space);
    PetImpl p = new PetImpl("Fortune", 0);
    RandomInterface random = new RandomMock(2);
    ComputerPlayer hp = new ComputerPlayer("Malav", "Armory", 3, false, random);
    assertEquals("Player Malav is at space: Armory and following spaces are visible "
                    + "from this space: [Billiard Room, Dining Hall, Drawing Room] + "
                    + "player that are around: [Target=Billiard Room] and items that are around: "
                    + "[Drawing Room=[Letter Opener], Armory=[Revolver], "
                    + "Billiard Room=[Billiard Cue]]\n",
            hp.computerPlayer(space, target, p));
    assertEquals(50, target.getHealth());
  }


  @Test
  public void computerPlayerAlwaysAttackWhenTargetIsAround() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl target = new TargetImpl("Target", 50);
    RandomInterface random = new RandomMock(1, 0);
    PetImpl p = new PetImpl("Fortune", 0);
    ComputerPlayer hp = new ComputerPlayer("Malav", "Armory", 3, false, random);
    assertEquals("Attack successful! The health of target is reduced by 1. "
            +
            "Remaining health of target: 49\n", hp.computerPlayer(space, target, p));
    assertEquals(49, target.getHealth());
  }

  @Test
  public void computerPlayerUsingItemHavingMaxDamage() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl target = new TargetImpl("Target", 50);
    target.moveTarget(space);
    target.moveTarget(space);
    RandomInterface random = new RandomMock(1, 0, 1, 0, 1, 0, 1, 1, 1, 0);
    PetImpl p = new PetImpl("Fortune", 0);
    ComputerPlayer hp = new ComputerPlayer("Malav", "Armory", 3, false, random);
    assertEquals("Player Malav has picked up the item Revolver\n",
            hp.computerPlayer(space, target, p));
    assertEquals("Player Malav has moved to Billiard Room and "
            +
            "this space has [Billiard Cue]\n", hp.computerPlayer(space, target, p));
    assertEquals("Player Malav has picked up the item Billiard Cue\n",
            hp.computerPlayer(space, target, p));
    target.moveTarget(space);
    assertEquals("Player Malav has moved to Dining Hall and "
            +
            "this space has []\n", hp.computerPlayer(space, target, p));
    assertEquals("Attack Successful! The health of target is"
            +
            " reduced by 3. Remaining health of target: 47\n", hp.computerPlayer(space, target, p));
  }

  @Test
  public void testEquals() {
    RandomInterface random = new RandomMock(2);
    ComputerPlayer ri = new ComputerPlayer("Malav", "Armory", 3,
            false, random);
    assertTrue(ri.equals(ri)); //Reflexivity
    ComputerPlayer ri1 = new ComputerPlayer("Malav", "Armory", 3,
            false, random);
    assertTrue(ri.equals(ri1)); //Symmetry
    ComputerPlayer ri2 = ri1;
    assertTrue(ri2.equals(ri)); //Transitivity
    ComputerPlayer ri3 = new ComputerPlayer("Malav", "Kitchen",
            3, false, random);
    assertFalse(ri3.equals(ri1));
  }

  @Test
  public void testHashCode() {
    RandomInterface random = new RandomMock(2);
    ComputerPlayer sr = new ComputerPlayer("Malav", "Kitchen", 3,
            false, random);
    assertTrue(sr.hashCode() == sr.hashCode()); //Reflexivity
    ComputerPlayer sr1 = new ComputerPlayer("Malav", "Kitchen", 3,
            false, random);
    assertTrue(sr.hashCode() == sr1.hashCode()); //Symmetry
    ComputerPlayer sr2 = sr1;
    assertTrue(sr2.hashCode() == sr.hashCode()); //Transitivity
  }

}