import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
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
 * This class tests the methods implemented in human player class.
 */

public class HumanPlayerTest {

  private List<RoomImpl> rooms;
  private List<Items> items;
  private SpaceImpl space;
  private StringReader sr;
  private TargetImpl target;

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
    SpaceImpl space = new SpaceImpl(rooms, items);

  }

  @Test(expected = IllegalStateException.class)
  public void negativeCapacity() {
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", -2, true);
  }


  @Test(expected = IllegalStateException.class)
  public void moveToNonNeighbourSpace() {
    //SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.moveToNeighbourSpace("Foyer", space);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Drawing Room", 4, true);
    hp1.moveToNeighbourSpace("Foyer", space);
  }

  @Test(expected = IllegalStateException.class)
  public void moveToNonExistingSpace() {
    //SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.moveToNeighbourSpace("Kitchen", space);
  }

  @Test
  public void moveToNeighbour() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    assertEquals("Player Malav has moved to Armory and this space has [Revolver]",
            hp.moveToNeighbourSpace("Armory", space));
  }

  @Test
  public void pickUpItem() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Kitchen", 3, true);
    System.out.println(space.itemsInRoom(hp.getSpace()));
    hp.pickUpItem("Sharp Knife", space);
    System.out.println(space.itemsInRoom(hp.getSpace()));
    List<String> it = new ArrayList<>();
    it.add("Crepe Pan");
    HashMap<String, List<String>> hm = new HashMap<>();
    hm.put("Kitchen", it);
    assertEquals(hm.get("Kitchen"), space.itemsInRoom(hp.getSpace()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void pickUpItemLimitExceeded() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Kitchen", 1, true);
    hp.pickUpItem("Sharp Knife", space);
    hp.pickUpItem("Crepe Pan", space);
  }

  @Test(expected = IllegalStateException.class)
  public void pickUpNonExistingItem() {
    //SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Kitchen", 1, true);
    hp.pickUpItem("Gun", space);
  }

  //Another player present in neighbouring space
  @Test
  public void lookAroundPlayerInNeighbouringSpace() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Wine Cellar", 3, true);
    TargetImpl t = new TargetImpl("Target", 6);
    PetImpl p = new PetImpl("Fortune", 0);
    HumanPlayer hp2 = new HumanPlayer("Abhi", "Nursery", 4, true);
    assertEquals("Player Malav is at space: Dining Hall and following "
                    + "spaces are visible from this space: [Billiard Room, Drawing Room, "
                    + "Kitchen, Parlor, Tennessee Room, Trophy Room, Wine Cellar] + "
                    + "player that are around: [Daksh=Wine Cellar] and items that are around: "
                    + "[Trophy Room=[Duck Decoy, Monkey Hand], Kitchen=[Crepe Pan, Sharp Knife], "
                    + "Drawing Room=[Letter Opener], Billiard Room=[Billiard Cue], "
                    + "Wine Cellar=[Rat Poison, Piece of Rope]]",
            hp.lookAround(space, t, p));
  }

  @Test
  public void lookAroundPlayerInSameSpace() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Dining Hall", 3, true);
    PetImpl p = new PetImpl("Fortune", 10);
    assertEquals("Player Malav is at space: Dining Hall and following spaces are visible"
            + " from this space: [Armory, Billiard Room, Drawing Room, Kitchen, Parlor,"
            + " Tennessee Room, Trophy Room, Wine Cellar] + player that are around: "
            + "[Target=Armory, Daksh=Dining Hall] and items that are around: "
            + "[Trophy Room=[Duck Decoy, Monkey Hand], Kitchen=[Crepe Pan, Sharp Knife], "
            + "Drawing Room=[Letter Opener], Armory=[Revolver], Billiard Room=[Billiard Cue], "
            + "Wine Cellar=[Rat Poison, Piece of Rope]]", hp.lookAround(space, t, p));
  }

  //Player has no items
  @Test
  public void playerDescription() {
    //SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    assertEquals("Player Malav has no items and is at space: Dining Hall",
            hp.playerDescription("Malav"));
  }

  //Player has picked up an item
  @Test
  public void playerDescriptionWithItems() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Player Malav has following items: [Revolver] and is at space: Armory",
            hp.playerDescription("Malav"));
  }

  //Player is moving and has picked up items from 2 locations
  @Test
  public void playerDescriptionWithItemsWithMovement() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Player Malav has following items: [Revolver] and is at space: Armory",
            hp.playerDescription("Malav"));
    hp.moveToNeighbourSpace("Drawing Room", space);
    hp.pickUpItem("Letter Opener", space);
    assertEquals("Player Malav has following items: [Revolver, Letter Opener] and "
            +
            "is at space: Drawing Room", hp.playerDescription("Malav"));
  }

  //Another player trying to pickup already taken item
  @Test(expected = IllegalStateException.class)
  public void anotherPlayerPickingAlreadyPickedItem() {
    //SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp1 = new HumanPlayer("MrX", "Armory", 2, true);
    hp.pickUpItem("Revolver", space);
    hp1.pickUpItem("Revolver", space);
  }


  @Test(expected = IllegalStateException.class)
  public void pickingUpItemThatIsNotPresentInTheRoom() {
    //SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    hp.pickUpItem("Sharp Knife", space);
  }

  @Test
  public void itemsPossesed() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Kitchen", 3, true);

    hp.pickUpItem("Sharp Knife", space);
    assertEquals("Player Malav has following items: [Sharp Knife]", hp.itemsPossessed());
  }

  @Test
  public void nearbySpace() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    PetImpl pet = new PetImpl("Kitty", 20);
    HumanPlayer hp = new HumanPlayer("Malav", "Kitchen", 3, true);
    assertEquals("Available option/s to move[Dining Hall, Parlor, Wine Cellar]",
            hp.showNearbySpace(space, pet));
    hp.moveToNeighbourSpace("Wine Cellar", space);
    assertEquals("Wine Cellar", hp.getSpace());
    assertEquals("Available option/s to move[Dining Hall, Drawing Room, Kitchen]",
            hp.showNearbySpace(space, pet));
  }

  @Test
  public void nearbySpacePetPresence() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    PetImpl pet = new PetImpl("Kitty", 1);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    assertEquals("Available option/s to move[Dining Hall, Drawing Room]",
            hp.showNearbySpace(space, pet));
  }

  @Test
  public void nearbyItems() {
    //WorldImpl w1 = new WorldImpl("res/mansion.txt", 5);
    SpaceImpl space = new SpaceImpl(rooms, items);
    HumanPlayer hp = new HumanPlayer("Malav", "Kitchen", 3, true);
    assertEquals("Available option/s to pick[Crepe Pan, Sharp Knife]", hp.showNearbyItems(space));
    hp.moveToNeighbourSpace("Wine Cellar", space);
    assertEquals("Available option/s to pick[Rat Poison, Piece of Rope]",
            hp.showNearbyItems(space));
  }

  @Test
  public void humanComputerPlayerLookAround() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    RandomInterface random = new RandomMock(2);
    ComputerPlayer cp = new ComputerPlayer("Daksh", "Dining Hall", 3, false, random);
    PetImpl p = new PetImpl("Fortune", 0);
    assertEquals("Player Malav is at space: Armory and following spaces are "
                    + "visible from this space: [Billiard Room, Dining Hall, Drawing Room] + "
                    + "player that are around: [Target=Armory, Daksh=Dining Hall] and items "
                    + "that are around: [Drawing Room=[Letter Opener], Armory=[Revolver], "
                    + "Billiard Room=[Billiard Cue]]. Pet Fortune is also in same room.",
            hp.lookAround(space, t, p));
  }

  @Test
  public void getName() {
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    assertEquals("Malav", hp.getName());
  }

  @Test
  public void getSpace() {
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    assertEquals("Armory", hp.getSpace());
  }

  @Test
  public void getCapacity() {
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    assertEquals(3, hp.getCapacity());
  }

  @Test
  public void testEquals() {
    HumanPlayer ri = new HumanPlayer("Malav", "Armory", 3, true);
    assertTrue(ri.equals(ri)); //Reflexivity
    HumanPlayer ri1 = new HumanPlayer("Malav", "Armory", 3, true);
    assertTrue(ri.equals(ri1)); //Symmetry
    HumanPlayer ri2 = ri1;
    assertTrue(ri2.equals(ri)); //Transitivity
    HumanPlayer ri3 = new HumanPlayer("Malav", "Kitchen", 3, true);
    assertFalse(ri3.equals(ri1));
  }

  @Test
  public void testHashCode() {
    HumanPlayer sr = new HumanPlayer("Malav", "Kitchen", 3, true);
    assertTrue(sr.hashCode() == sr.hashCode()); //Reflexivity
    HumanPlayer sr1 = new HumanPlayer("Malav", "Kitchen", 3, true);
    assertTrue(sr.hashCode() == sr1.hashCode()); //Symmetry
    HumanPlayer sr2 = sr1;
    assertTrue(sr2.hashCode() == sr.hashCode()); //Transitivity
  }

  @Test
  public void lookAroundPlayerInSameSpaceWithPetAndPlayerInSameNeighbour() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Armory", 3, true);
    PetImpl p = new PetImpl("Fortune", 0);
    assertEquals("Player Malav is at space: Dining Hall and "
            + "following spaces are visible from this space: [Billiard Room, Drawing Room, "
            + "Kitchen, Parlor, Tennessee Room, Trophy Room, Wine Cellar] + "
            + "player that are around: [] and items that are around: "
            + "[Trophy Room=[Duck Decoy, Monkey Hand], Kitchen=[Crepe Pan, Sharp Knife], "
            + "Drawing Room=[Letter Opener], Billiard Room=[Billiard Cue],"
            + " Wine Cellar=[Rat Poison, Piece of Rope]]", hp.lookAround(space, t, p));
  }

  @Test
  public void lookAroundPlayerInSameSpaceWithPetAndPlayerInDifferentNeighbour() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Dining Hall", 3, true);
    PetImpl p = new PetImpl("Fortune", 0);
    assertEquals("Player Malav is at space: Dining Hall and following spaces are "
            + "visible from this space: [Billiard Room, Drawing Room, Kitchen, Parlor, "
            + "Tennessee Room, Trophy Room, Wine Cellar] + player that are around: "
            + "[Daksh=Dining Hall] and items that are around: "
            + "[Trophy Room=[Duck Decoy, Monkey Hand], Kitchen=[Crepe Pan, Sharp Knife], "
            + "Drawing Room=[Letter Opener], Billiard Room=[Billiard Cue], "
            + "Wine Cellar=[Rat Poison, Piece of Rope]]", hp.lookAround(space, t, p));
  }

  @Test
  public void lookAroundPlayerPetInSameSpace() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Armory", 3, true);
    PetImpl p = new PetImpl("Fortune", 0);
    assertEquals("Player Malav is at space: Armory and following spaces are visible "
            +
            "from this space: [Billiard Room, Dining Hall, Drawing Room]. Player that are around:"
            +
            " [Target=Armory,Daksh=Armory] and items that are around: "
            +
            "[Drawing Room=[Letter Opener], "
            +
            "Armory=[Revolver], Billiard Room=[Billiard Cue]]. Pet Fortune is also in same room."
            +
            "", hp.lookAround(space, t, p));
  }

  @Test
  public void lookAroundPlayerInSameSpaceWithPetAndPlayerInSameNeighbourAndTargetWithPlayer() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Dining Hall", 3, true);
    PetImpl p = new PetImpl("Fortune", 0);
    p.movePet("Dining Hall", space);
    assertEquals("Player Malav is at space: Armory and following spaces are visible "
            + "from this space: [Billiard Room, Drawing Room] + player that are around: "
            + "[Target=Armory] and items that are around: [Drawing Room=[Letter Opener], "
            + "Armory=[Revolver], Billiard Room=[Billiard Cue]]", hp.lookAround(space, t, p));
  }

  @Test
  public void lookAroundPlayerInSameSpaceWithPetPlayerInSameNeighbourTargetWithPlayerItemPickup() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Dining Hall", 3, true);
    PetImpl p = new PetImpl("Fortune", 0);
    p.movePet("Dining Hall", space);
    assertEquals("Player Malav is at space: Armory and following spaces are visible "
            + "from this space: [Billiard Room, Drawing Room] + player that are around: "
            + "[Target=Armory] and items that are around: [Drawing Room=[Letter Opener], "
            + "Billiard Room=[Billiard Cue]]", hp.lookAround(space, t, p));
  }

  @Test
  public void lookPlayerInSameSpaceWithPetPlayerInSameNeighbourTargetWithOtherPlayerItemPickup() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Drawing Room", 3, true);
    hp1.pickUpItem("Letter Opener", space);
    PetImpl p = new PetImpl("Fortune", 0);
    p.movePet("Dining Hall", space);
    assertEquals("Player Malav is at space: Armory and following spaces are visible "
            + "from this space: [Billiard Room, Drawing Room] + player that are around: "
            + "[Target=Armory, Daksh=Drawing Room] and items that are around: "
            + "[Armory=[Revolver], Billiard Room=[Billiard Cue]]", hp.lookAround(space, t, p));
  }

  @Test
  public void lookAroundPlayerInDifferentSpaceWithPet() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl t = new TargetImpl("Target", 6);
    HumanPlayer hp = new HumanPlayer("Malav", "Drawing Room", 3, true);
    PetImpl p = new PetImpl("Fortune", 0);
    assertEquals("Player Malav is at space: Drawing Room and following spaces are visible "
                    + "from this space: [Dining Hall, Foyer, Wine Cellar] + "
                    +
                    "player that are around: "
                    + "[] and items that are around: [Drawing Room=[Letter Opener], "
                    + "Wine Cellar=[Rat Poison, Piece of Rope]]",
            hp.lookAround(space, t, p));
  }

  @Test
  public void attackTargetNoWeapon() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    assertEquals("Attack successful! The health of target is reduced by 1. "
            +
            "Remaining health of target: 49", hp.attackTarget("poke", target, space));
    assertEquals(49, target.getHealth());
  }

  @Test
  public void attackTargetOnlyOneWeapon() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Attack Successful! The health of target is reduced by 3. "
                    +
                    "Remaining health of target: 47",
            hp.attackTarget("Revolver", target, space));
    assertEquals(47, target.getHealth());
  }

  @Test
  public void attackTargetPokeInspiteOfOtherWeapons() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Attack Successful! The health of target is reduced by 1. "
                    +
                    "Remaining health of target: 49",
            hp.attackTarget("poke", target, space));
    assertEquals(49, target.getHealth());
  }

  @Test
  public void attackTargetMultipleItemsAvailable() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    hp.moveToNeighbourSpace("Billiard Room", space);
    hp.pickUpItem("Billiard Cue", space);
    target.moveTarget(space);
    assertEquals("Attack Successful! The health of target is reduced by 2. "
                    +
                    "Remaining health of target: 48",
            hp.attackTarget("Billiard Cue", target, space));
    assertEquals(48, target.getHealth());
  }

  @Test
  public void attackTargetFailingTargetDifferentRoom() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    target.moveTarget(space);
    assertEquals("Target is not in the same space", hp.attackTarget("poke", target, space));
  }

  @Test
  public void attackTargetFailingOtherPlayerSameRoomNoWeapon() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp2 = new HumanPlayer("Shivam", "Armory", 3, true);
    assertEquals("Attack Failed!!", hp.attackTarget("poke", target, space));
  }

  @Test
  public void attackTargetFailingOtherPlayerNeighbourRoomNoWeapon() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp2 = new HumanPlayer("Shivam", "Billiard Room", 3, true);
    assertEquals("Attack Failed!!", hp.attackTarget("poke", target, space));
  }

  @Test
  public void attackTargetFailingOtherPlayerSameRoomOneWeapon() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    HumanPlayer hp2 = new HumanPlayer("Shivam", "Armory", 3, true);
    assertEquals("Attack Failed!!", hp.attackTarget("Revolver", target, space));
  }

  @Test
  public void attackTargetFailingOtherPlayerNeighbourRoomOneWeapon() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    HumanPlayer hp2 = new HumanPlayer("Shivam", "Billiard Room", 3, true);
    assertEquals("Attack Failed!!", hp.attackTarget("Revolver", target, space));
  }

  @Test
  public void attackTargetMultipleItemsTestItemIsDiscarded() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 50);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    hp.moveToNeighbourSpace("Billiard Room", space);
    hp.pickUpItem("Billiard Cue", space);
    target.moveTarget(space);
    assertEquals("Attack Successful! The health of target is reduced by 3."
                    +
                    " Remaining health of target: 47",
            hp.attackTarget("Revolver", target, space));
    assertEquals(47, target.getHealth());
    assertEquals("Player Malav has following items: [Billiard Cue]", hp.itemsPossessed());
  }

  @Test
  public void attackTargetNoWeaponTargetDies() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 1);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    assertEquals("Attack Successful!Player Malav has successfully killed the target",
            hp.attackTarget("poke", target, space));
  }

  @Test
  public void attackTargetOneWeaponMoreThanLifeAvailableTargetDies() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    TargetImpl target = new TargetImpl("Target", 2);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Attack Successful! Player Malav has successfully killed the target",
            hp.attackTarget("Revolver", target, space));
  }

  @Test
  public void attackTargetMultipleWeaponMoreThanLifeAvailableTargetDies() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 2);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    hp.moveToNeighbourSpace("Billiard Room", space);
    hp.pickUpItem("Billiard Cue", space);
    target.moveTarget(space);
    assertEquals("Attack Successful! Player Malav has successfully killed the target",
            hp.attackTarget("Revolver", target, space));
  }

  @Test
  public void attackTargetMultipleWeaponEqualLifeAvailableTargetDies() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void attackTargetWrongWeaponName() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 2);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Attack Successful! You have successfully killed the target",
            hp.attackTarget("Revolveri", target, space));
  }

  @Test
  public void attackFailedItemThrownAway() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp2 = new HumanPlayer("Shivam", "Billiard Room", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Attack Failed!!", hp.attackTarget("Revolver", target, space));
    assertEquals("Player Malav has following items: []", hp.itemsPossessed());
  }

  @Test(expected = IllegalStateException.class)
  public void attackFailedItemThrownAwayNew() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp2 = new HumanPlayer("Shivam", "Billiard Room", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Attack Failed!!", hp.attackTarget("Revolver", target, space));
    assertEquals("Player Malav has following items: []", hp.itemsPossessed());
    assertEquals("", hp.pickUpItem("Revolver", space));
  }


  @Test
  public void attackFailedTargetInDifferentSpaceWeaponThrown() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    hp.moveToNeighbourSpace("Billiard Room", space);
    assertEquals("Attack Failed!! Target is not in the same space",
            hp.attackTarget("Revolver", target, space));
    assertEquals("Player Malav has following items: []", hp.itemsPossessed());
  }

  @Test
  public void attackFailedAnotherPersonInNeighbouringRoomWithPet() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    PetImpl pi = new PetImpl("Kitty", 1);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    HumanPlayer hp1 = new HumanPlayer("Daksh", "Billiard Room", 2, true);
    assertEquals("Attack Failed!!", hp.attackTarget("Revolver", target, space));
  }

  @Test
  public void spaceUnavailableToMoveBecauseOfPet() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    PetImpl pi = new PetImpl("Kitty", 1);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    assertEquals("Available option/s to move[Dining Hall, Drawing Room]",
            hp.showNearbySpace(space, pi));
  }

  @Test
  public void attackFailedAnotherPlayerInSameRoom() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    HumanPlayer hp = new HumanPlayer("Malav", "Armory", 3, true);
    HumanPlayer hp2 = new HumanPlayer("Shivam", "Armory", 3, true);
    hp.pickUpItem("Revolver", space);
    assertEquals("Attack Failed!!", hp.attackTarget("Revolver", target, space));
    assertEquals("Player Malav has following items: []", hp.itemsPossessed());
  }

  @Test
  public void testLookAround() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    PetImpl pi = new PetImpl("Kitty", 0);
    assertEquals("Player Malav is at space: Dining Hall and following spaces are "
                    +
                    "visible from this space: [Billiard Room, Drawing Room, Kitchen, Parlor, "
                    +
                    "Tennessee Room, Trophy Room, Wine Cellar]. Player that are around: [] "
                    +
                    "and items that are around: [Trophy Room=[Duck Decoy, Monkey Hand], "
                    +
                    "Kitchen=[Crepe Pan, Sharp Knife], Drawing Room=[Letter Opener], "
                    +
                    "Billiard Room=[Billiard Cue], Wine Cellar=[Rat Poison, Piece of Rope]]",
            hp.lookAround(space, target, pi));
  }

  @Test
  public void lookAroundTargetInNeighbour() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    HumanPlayer hp = new HumanPlayer("Malav", "Dining Hall", 3, true);
    PetImpl pi = new PetImpl("Kitty", 6);
    assertEquals("Player Malav is at space: Dining Hall and following spaces are visible "
                    +
                    "from this space: [Armory, Billiard Room, Drawing Room, Kitchen, Parlor, "
                    +
                    "Tennessee "
                    +
                    "Room, Trophy Room, Wine Cellar]. Player that are around: [Target=Armory] "
                    +
                    "and items "
                    +
                    "that are around: [Trophy Room=[Duck Decoy, Monkey Hand], "
                    +
                    "Kitchen=[Crepe Pan, Sharp"
                    +
                    " Knife], Drawing Room=[Letter Opener], Armory=[Revolver], "
                    +
                    "Billiard Room=[Billiard Cue], Wine Cellar=[Rat Poison, Piece of Rope]]",
            hp.lookAround(space, target, pi));
  }

  @Test
  public void lookAroundTargetNotAround() {
    SpaceImpl space = new SpaceImpl(rooms, items);
    target = new TargetImpl("Target", 5);
    HumanPlayer hp = new HumanPlayer("Malav", "Foyer", 3, true);
    PetImpl pi = new PetImpl("Kitty", 10);
    assertEquals("Player Malav is at space: Foyer and following spaces are visible "
                    +
                    "from this space: [Drawing Room, Piazza]. Player that are around: [] and items "
                    +
                    "that are around: [Piazza=[Civil War Cannon], Drawing Room=[Letter Opener]]",
            hp.lookAround(space, target, pi));
  }


}