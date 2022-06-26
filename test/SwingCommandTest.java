import static org.junit.Assert.assertEquals;

import controller.SwingController;
import controller.WorldCommandController;
import controller.commands.AllRooms;
import controller.commands.AttackTarget;
import controller.commands.ClueCommand;
import controller.commands.ComputerPlayerMovements;
import controller.commands.CreatePlayer;
import controller.commands.ItemPossessed;
import controller.commands.LookAround;
import controller.commands.MovePet;
import controller.commands.MoveToNeighbour;
import controller.commands.NearbyItems;
import controller.commands.PickUpItem;
import controller.commands.PlayerDescription;
import controller.commands.Reset;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import milestone.WorldImpl;
import org.junit.Before;
import org.junit.Test;
import view.HomePageNew;
import view.WorldViewRef;

/**
 * This class tests the commands.
 */

public class SwingCommandTest {

  private StringReader sr;
  private WorldImpl w1;
  private Map<String, WorldCommandController> commandControllerMap;

  /**
   * Initializing the world model using string reader for testing purpose.
   */

  @Before
  public void setup() {
    commandControllerMap = new HashMap<>();
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
    commandControllerMap.put("I", new PickUpItem());
    commandControllerMap.put("A", new AttackTarget());
    commandControllerMap.put("P", new MovePet());
    commandControllerMap.put("L", new LookAround());
    commandControllerMap.put("C", new ClueCommand());
    commandControllerMap.put("CP", new ComputerPlayerMovements());
    commandControllerMap.put("NearbyItems", new NearbyItems());
    commandControllerMap.put("PossessedItems", new ItemPossessed());
    commandControllerMap.put("PLAYER", new CreatePlayer());
    commandControllerMap.put("AllRooms", new AllRooms());
    commandControllerMap.put("MOVE", new MoveToNeighbour());
    commandControllerMap.put("PD", new PlayerDescription());
    commandControllerMap.put("RESTART", new Reset());
  }

  @Test
  public void createPlayer() {
    SwingController swc = new SwingController(w1);
    WorldCommandController player = commandControllerMap.get("PLAYER");
    player.setInput("Malav,2,Armory,true");
    assertEquals("Player created successfully",
            commandControllerMap.get("PLAYER").execute(w1).trim());
  }

  @Test
  public void lookAroundTargetNotVisible() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    swc.showDisplay("L");
    assertEquals("Player Malav is at space: Armory and following spaces are visible "
                    + "from this space: [Dining Hall, Drawing Room]. Player that are around: [] "
                    + "and items that are around: [Drawing Room=[Letter Opener],"
                    + " Armory=[Revolver]]", commandControllerMap.get("L").execute(w1).trim());
  }

  @Test
  public void itemPickup() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    WorldCommandController player = commandControllerMap.get("I");
    player.setInput("Revolver");
    /*swc.validateInput("I","Revolver");*/
    assertEquals("Item pickup successful", commandControllerMap.get("I").execute(w1));
  }

  @Test
  public void movePet() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    WorldCommandController pet = commandControllerMap.get("P");
    pet.setInput("Foyer");
    assertEquals("Pet movement successfull", commandControllerMap.get("P").execute(w1));
  }

  @Test
  public void clue() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    swc.showDisplay("C");
    assertEquals("Player Malav is in same space with Target\n"
            +
            " In room named Armory there is/are [Revolver]present and its neighbour "
            +
            "is/are [Billiard Room, Dining Hall, Drawing Room] . The players present "
            +
            "are [Malav]Target Doctor Lucky  is also here along with Pet Fortune "
            +
            "is also here\n", commandControllerMap.get("C").execute(w1));

  }

  @Test
  public void computerPlayerMovement() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    assertEquals("It is turn of player Malav", commandControllerMap.get("CP").execute(w1));
  }

  @Test
  public void nearbyItems() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    assertEquals("Revolver", commandControllerMap.get("NearbyItems").execute(w1));
  }

  @Test
  public void itemsPossessed() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    swc.validateInput("I", "Revolver");
    assertEquals("poke, Revolver", commandControllerMap.get("PossessedItems").execute(w1));
  }

  @Test
  public void allRooms() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    assertEquals("Armory, Billiard Room, Carriage House, Dining Hall, Drawing Room, "
            + "Foyer, Green House, Hedge Maze, Kitchen, Lancaster Room, Library, Lilac Room, "
            + "Master Suite, Nursery, Parlor, Piazza, Servants' Quarters, Tennessee Room, Trophy "
            + "Room, Wine Cellar, Winter Garden", commandControllerMap.get("AllRooms").execute(w1));
  }

  @Test
  public void move() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Dining Hall,true");
    WorldCommandController pet = commandControllerMap.get("MOVE");
    pet.setInput("288,213");
    assertEquals("If player doesn't move to desired room,please re-click",
            commandControllerMap.get("MOVE").execute(w1));
  }

  @Test
  public void attackTarget() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    WorldCommandController pet = commandControllerMap.get("A");
    pet.setInput("poke");
    assertEquals("Attack Successful! The health of target is reduced by "
            + "1. Remaining health of target: 49", commandControllerMap.get("A").execute(w1));
  }

  @Test
  public void attackTargetTargetNotAround() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Dining Hall,true");
    WorldCommandController pet = commandControllerMap.get("A");
    pet.setInput("poke");
    assertEquals("Attack Failed!! Target is not in the same space",
            commandControllerMap.get("A").execute(w1));
  }

  @Test
  public void attackTargetPlayerAround() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    swc.validateInput("PLAYER", "Aneesh,2,Armory,true");
    WorldCommandController attack = commandControllerMap.get("A");
    attack.setInput("poke");
    assertEquals("Attack Failed!!",
            commandControllerMap.get("A").execute(w1));
  }

  @Test
  public void attackPlayerInNeighbour() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    swc.validateInput("PLAYER", "Aneesh,2,Dining Hall,true");
    WorldCommandController attack = commandControllerMap.get("A");
    attack.setInput("poke");
    assertEquals("Attack Failed!!",
            commandControllerMap.get("A").execute(w1));
  }

  @Test
  public void lookAroundTarget() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    assertEquals("Player Malav is at space: Armory and following spaces are visible "
                    + "from this space: [Billiard Room, Dining Hall, Drawing Room]. Player that "
                    + "are around: [Doctor Lucky =Armory] and items that are around: "
                    + "[Drawing Room=[Letter Opener], Armory=[Revolver], Billiard"
                    + " Room=[Billiard Cue]]. Pet Fortune is also in same room.",
            commandControllerMap.get("L").execute(w1).trim());
  }

  @Test
  public void lookAroundPlayerAround() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    swc.validateInput("PLAYER", "Aneesh,2,Armory,true");
    assertEquals("Player Malav is at space: Armory and following spaces are "
                    + "visible from this space: Billiard Room, Dining Hall, Drawing Room."
                    + " Player that are around: Doctor Lucky =Armory and items that are around: "
                    + "Drawing Room=Letter Opener, Armory=Revolver, "
                    + "Billiard Room=Billiard Cue. Pet Fortune is also in same room.",
            commandControllerMap.get("L").execute(w1).trim());
  }

  @Test(expected = IllegalStateException.class)
  public void moveToInvalidNeighbour() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Dining Hall,true");
    WorldCommandController playerMove = commandControllerMap.get("MOVE");
    playerMove.setInput("500,500");
    assertEquals("If player doesn't move to desired room,please re-click",
            commandControllerMap.get("MOVE").execute(w1));
  }

  @Test(expected = IllegalStateException.class)
  public void movePetToSameLocation() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Dining Hall,true");
    WorldCommandController pet = commandControllerMap.get("P");
    pet.setInput("Armory");
    commandControllerMap.get("P").execute(w1);
  }

  @Test(expected = IllegalStateException.class)
  public void nullPlayerName() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    WorldCommandController pet = commandControllerMap.get("PLAYER");
    pet.setInput(",2,Dining Hall,true");
    commandControllerMap.get("PLAYER").execute(w1);
  }

  @Test
  public void killTarget() {
    StringReader sr1 = new StringReader("36 30 Doctor Lucky's Mansion\n"
            + "1 Doctor Lucky\n"
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
    WorldImpl w1 = new WorldImpl(sr1, 5);
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    WorldCommandController pet = commandControllerMap.get("A");
    pet.setInput("poke");
    assertEquals("Attack Successful!Player Malav have successfully killed the target",
            commandControllerMap.get("A").execute(w1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void maxTurnReached() {
    StringReader sr1 = new StringReader("36 30 Doctor Lucky's Mansion\n"
            + "10 Doctor Lucky\n"
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
    WorldImpl w1 = new WorldImpl(sr1, 2);
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,2,Armory,true");
    WorldCommandController pet = commandControllerMap.get("A");
    pet.setInput("poke");
    swc.showDisplay("L");
    swc.showDisplay("CP");
  }

  @Test(expected = IllegalArgumentException.class)
  public void maximumPlayerTesting() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "M,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M1,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M2,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M3,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M4,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M5,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M6,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M7,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M8,2,Dining Hall,true");
    swc.validateInput("PLAYER", "M9,2,Dining Hall,true");
    WorldCommandController pet = commandControllerMap.get("PLAYER");
    pet.setInput("M10,2,Dining Hall,true");
    assertEquals("Maximum player capacity reached", commandControllerMap.get("PLAYER").execute(w1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void maximumCapacityReached() {
    SwingController swc = new SwingController(w1);
    WorldViewRef wv = new HomePageNew("Kill", w1);
    swc.setView(wv);
    swc.validateInput("PLAYER", "Malav,0,Armory,true");
    WorldCommandController player = commandControllerMap.get("I");
    player.setInput("Revolver");
    assertEquals("Item carrying capacity is already at maximum",
            commandControllerMap.get("I").execute(w1).trim());
  }






}