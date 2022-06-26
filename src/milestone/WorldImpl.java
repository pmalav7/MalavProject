package milestone;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javax.imageio.ImageIO;
import view.FileListener;
import view.HomePageNew;
import view.WorldViewRef;

/**
 * WorldImpl class acts as bridge between our SpaceImpl class and Driver class as our driver class
 * can only interact with our WorldImpl class. This class performs major function of decoupling the
 * model from the text file. It also has all the important methods of SpaceImpl and
 * TargetImpl class.
 */

public class WorldImpl implements World, WorldPlayerInterface, FileListener {


  private SpaceImpl spaceImpl;
  private TargetImpl target;
  private PetImpl pet;
  private List<RoomImpl> roomsL;
  private List<Items> itemsL;
  private List<Player> playersL;
  private List<String> playerNameList;
  private List<String> coordinates;
  private int turnCounter;
  private List<ComputerPlayer> computerPlayerList;
  private List<HumanPlayer> humanPlayerList;
  private List<Items> itemsArrayList;
  private List<RoomImpl> rooms;
  private boolean isGameRunning;
  private HumanPlayer humanPlayer;
  private int turn;
  private int counter;
  private HomePageNew homePageNew;
  private Readable filereader;
  private int countPlayer;

  /**
   * The constructor of WorldImpl takes in a string of file location as an input. It then uses this
   * file to create our board , rooms , items and target objects.
   *
   * @param filereader  This string contains the location of the file in the local computer.
   * @param turnCounter provides the total number of turns permitted to the model.
   */

  public WorldImpl(Readable filereader, int turnCounter) {
    this.turnCounter = turnCounter;
    coordinates = new ArrayList<>();
    rooms = new ArrayList<>();
    itemsArrayList = new ArrayList<>();
    isGameRunning = true;
    countPlayer = 0;
    if (filereader == null) {
      throw new IllegalArgumentException("File name cannot be null");
    }
    if (turnCounter <= 0) {
      throw new IllegalStateException("turn counter can't be 0 or negative.");
    }


    Scanner sc = new Scanner(filereader);
    while (sc.hasNext()) {
      //Read information about the world
      String line = sc.nextLine();
      String[] words = line.split("\\s+");
      int xcoordinate = Integer.parseInt(words[0]);
      int ycoordinate = Integer.parseInt(words[1]);
      StringBuffer sb = new StringBuffer();
      for (int i = 2; i < words.length; i++) {
        sb.append(words[i]).append(" ");
      }
      String str = sb.toString();
      Board b = new Board(xcoordinate, ycoordinate, str);
      coordinates.add(Integer.toString(xcoordinate));
      coordinates.add(Integer.toString(ycoordinate));
      coordinates.add(str);

      //Read Information about the Target
      String line1 = sc.nextLine();
      String[] words1 = line1.split("\\s+");
      int health = Integer.parseInt(words1[0]);
      StringBuffer sb1 = new StringBuffer();
      for (int i = 1; i < words1.length; i++) {
        sb1.append(words1[i]).append(" ");
      }
      String nameOfTarget = sb1.toString();

      target = new TargetImpl(nameOfTarget, health);

      //Read information about target's pet
      String line6 = sc.nextLine();
      String[] words6 = line6.split("\\s+");
      String pname = words6[0];
      pet = new PetImpl(pname, 0);

      //Read information about number of spaces
      String line2 = sc.nextLine();
      int numberOfSpaces = Integer.parseInt(line2);

      //Read information about the spaces
      int count = 0;

      for (int i = 0; i < numberOfSpaces; i++) {
        String line3 = sc.nextLine();
        String[] words2 = line3.trim().split("\\s+");
        int leftCoordinate = words2[0].equals("") ? 0 : Integer.parseInt(words2[0]);
        int topCoordinate = Integer.parseInt(words2[1]);
        int rightCoordinate = Integer.parseInt(words2[2]);
        int bottomCoordinate = Integer.parseInt(words2[3]);

        StringBuffer sb2 = new StringBuffer();
        for (int j = 4; j < words2.length; j++) {
          sb2.append(words2[j]).append(" ");
        }
        String nameOfSpace = sb2.toString();
        //Adding all the room objects into rooms arraylist
        rooms.add(new RoomImpl(leftCoordinate, topCoordinate,
                rightCoordinate, bottomCoordinate, nameOfSpace.trim(),
                count));
        sb2.delete(0, sb2.length());
        Arrays.fill(words2, null);
        count++;

        coordinates.add(Integer.toString(leftCoordinate));
        coordinates.add(Integer.toString(topCoordinate));
        coordinates.add(Integer.toString(rightCoordinate));
        coordinates.add(Integer.toString(bottomCoordinate));
        coordinates.add(nameOfSpace.trim());
      }


      //Read Information about number of items available
      String line4 = sc.nextLine();
      int numberOfItems = Integer.parseInt(line4);

      //Read information about all the items

      for (int i = 0; i < numberOfItems; i++) {
        String line5 = sc.nextLine();

        String[] words3 = line5.trim().split("\\s+");
        int index = Integer.parseInt(words3[0]);
        int damage = Integer.parseInt(words3[1]);
        StringBuffer sb3 = new StringBuffer();
        for (int j = 2; j < words3.length; j++) {
          sb3.append(words3[j]).append(" ");
        }
        String nameOfItem = sb3.toString();
        //Adding all items object into itemsArrayList
        itemsArrayList.add(new Items(index, damage, nameOfItem.trim()));
        sb3.delete(0, sb3.length());
        Arrays.fill(words3, null);
      }
    }
    roomsL = new ArrayList<>(rooms);
    itemsL = new ArrayList<>(itemsArrayList);
    spaceImpl = new SpaceImpl(rooms, itemsArrayList);
    sc.close();
    playerNameList = new ArrayList<>();
    sc.close();
  }


  /**
   * This class is initialized by one parameter.
   * @param homePageNew refers to object of WorldViewRef.
   */

  public WorldImpl(WorldViewRef homePageNew) {
    if (homePageNew == null) {
      throw new IllegalStateException("WorldViewRef object can't be null");
    }
    this.homePageNew = (HomePageNew) homePageNew;
    homePageNew.register(this);
  }

  // This method gives the count of items in rooms by calling a method from
  // spaceImpl class
  @Override
  public int countOfItemsInRoom(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("Room name cannot be null");
    }
    return spaceImpl.numberOfItemsInRoom(roomName);
  }

  // This method is a getter for the arraylist of all Items object
  @Override
  public List<Items> getItemsL() {
    List<Items> i = new ArrayList<>(itemsL);
    return i;
  }

  //This method is a getter for the arraylist of all Rooms object
  @Override
  public List<RoomImpl> getRoomsL() {
    List<RoomImpl> r = new ArrayList<>(roomsL);
    return r;
  }

  // This method gives the list of all rooms by calling a method from
  // spaceImpl class
  @Override
  public String[] getRoomsInWorld() {
    return spaceImpl.getAllRooms();
  }

  // This method gives the list of all rooms by calling a method from
  //  spaceImpl class
  @Override
  public List<String> getNeighbors(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("Rooom Name cannot be null");
    }
    return spaceImpl.getNeighbours(roomName);
  }

  // This method gives the list of all items present in a specific room by calling a method from
  //  spaceImpl class
  @Override
  public List<String> getItemsInRoom(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("Room name cannot be null");
    }
    return spaceImpl.itemsInRoom(roomName);
  }

  // This method gives the list of all items present in a specific room  and its neighbours
  // by calling a method from spaceImpl class
  @Override
  public String spaceInfo(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("Room name cannot be null");
    }
    return spaceImpl.getRoomDetails(roomName, this.humanPlayer, this.target, this.pet);
  }

  // This method moves the location of the target to the next index location by calling
  // a method from TargetImpl class
  @Override
  public void moveTarget() {
    target.moveTarget(spaceImpl);
  }

  // This method gets the index location of the target calling
  // a method from TargetImpl class
  @Override
  public int getLocationOfTarget() {
    return target.getLocation();
  }

  // This method gives the room name in which target is present by calling
  // a method from TargetImpl class
  @Override
  public String whereIsTarget() {
    return target.whereIsTarget(spaceImpl);
  }

  @Override
  public void draw() {
    int xcoor = 0;
    int ycoor = 0;
    xcoor = Integer.parseInt(coordinates.get(0));
    ycoor = Integer.parseInt(coordinates.get(1));

    BufferedImage bufferedImage = new BufferedImage(ycoor * 30,
            xcoor * 30, BufferedImage.TYPE_BYTE_BINARY);
    Graphics2D g2d = bufferedImage.createGraphics();
    for (int i = 3; i < coordinates.size(); i += 5) {
      int leftCoor = Integer.parseInt(coordinates.get(i));
      int topCoor = Integer.parseInt(coordinates.get(i + 1));
      int rightCoor = Integer.parseInt(coordinates.get(i + 2));
      int bottomCoor = Integer.parseInt(coordinates.get(i + 3));
      String rname = coordinates.get(i + 4);
      g2d.drawRect(topCoor * 20, leftCoor * 20,
              (bottomCoor - topCoor + 1) * 20,
              (rightCoor - leftCoor + 1) * 20);
      g2d.drawString(rname, (topCoor) * 20, (leftCoor + 1) * 20);
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 12));
      File file1 = new File("res/MyWorld.png");
      try {
        ImageIO.write(bufferedImage, "png", file1);
      } catch (IOException e) {
        throw new IllegalArgumentException("There was a problem with drawing the game");
      }
    }
  }

  @Override
  public String playerTurn() {
    String value;
    if (playersL == null) {
      value = "Please create player by using create command\n";
    } else {
      value = String.format("It's turn for player %s\n", playersL.get(turn).getName());
    }
    return value;
  }

  @Override
  public void createPlayer(String playerName, String space, int capacity, boolean isHuman) {
    if (playerName == null || space == null || playerName.isEmpty()) {
      throw new IllegalStateException("Player name or space object is null");
    }
    if (capacity < 0) {
      throw new IllegalStateException("Capacity cannot be less than 0");
    }
    if (playersL == null) {
      playersL = new ArrayList<>(3);
      computerPlayerList = new ArrayList<>(3);
      humanPlayerList = new ArrayList<>(3);
      playerNameList = new ArrayList<>(3);
    }
    RandomInterface r = new RandomClass();
    String[] allRooms = spaceImpl.getAllRooms();

    if (playerNameList.contains(playerName)) {
      throw new IllegalStateException("Player name cannot be repeated");
    }

    if (!Arrays.asList(allRooms).contains(space)) {
      throw new IllegalStateException("This space is not available");
    }

    if (countPlayer == 10) {
      throw new IllegalArgumentException("Maximum player capacity reached");
    }

    if (isHuman) {
      countPlayer++;
      playersL.add(new HumanPlayer(playerName, space, capacity, true));
      humanPlayerList.add(new HumanPlayer(playerName, space, capacity, true));
    } else {
      countPlayer++;
      playersL.add(new ComputerPlayer(playerName, space, capacity, false, r));
      computerPlayerList.add(new ComputerPlayer(playerName, space, capacity, false, r));
    }
    playerNameList.add(playerName);

  }


  @Override
  public String move(String roomName) {
    if (roomName == null) {
      throw new IllegalStateException("Room name cannot be null");
    }
    String moves = "";
    moves = compPlayer();
    if (counter < turnCounter) {
      if (playersL.get(turn).getType()) {
        if (roomName == null) {
          throw new IllegalStateException("Room name cannot be null");
        } else {
          moves = playersL.get(turn).moveToNeighbourSpace(roomName, spaceImpl);
          turn = (turn + 1) % playersL.size();
          counter++;
        }
      }
    } else {
      moves = "You have ran out of moves";
    }
    moveTarget();
    pet.movePetDfsNew(spaceImpl);
    return moves;
  }

  @Override
  public String pickItem(String itemName) {
    if (itemName == null) {
      throw new IllegalStateException("Item name cannot be null");
    }
    compPlayer();
    String pickUp = "";
    if (playersL == null) {
      pickUp = "Please create player";
    } else if (counter < turnCounter) {
      if (playersL.get(turn).getType()) {
        pickUp = playersL.get(turn).pickUpItem(itemName, spaceImpl);
        turn = (turn + 1) % playersL.size();
        counter++;
      }
    } else {
      pickUp = "You have ran out of moves";
    }
    moveTarget();
    pet.movePetDfsNew(spaceImpl);
    return pickUp;
  }

  @Override
  public String lookAround() {
    String look = "";
    if (playersL == null) {
      look = "Please create player";
    } else if (counter < turnCounter) {
      if (playersL.get(turn).getType()) {
        look = playersL.get(turn).lookAround(spaceImpl, target, pet);
        turn = (turn + 1) % playersL.size();
        counter++;
      }
    } else {
      look = "You have ran out of moves";
    }
    moveTarget();
    pet.movePetDfsNew(spaceImpl);
    return look;
  }

  @Override
  public String attack(String weaponName) {
    if (weaponName == null) {
      throw new IllegalStateException("Weapon name cannot be null");
    }
    String attack = "";
    if (playersL == null) {
      attack = "Please create player";
    } else if (counter < turnCounter) {
      if (playersL.get(turn).getType()) {
        attack = playersL.get(turn).attackTarget(weaponName, target, spaceImpl);
        turn = (turn + 1) % playersL.size();
        counter++;
      }
    } else {
      attack = "You have ran out of moves";
    }
    /*if (!isGameRunning()){
      throw new IllegalStateException("Target character died");
    }*/
    moveTarget();
    pet.movePetDfsNew(spaceImpl);
    return attack;
  }

  @Override
  public String describePlayer(String playerName) {
    if (playerName == null) {
      throw new IllegalStateException("Player name cannot be null");
    }
    for (int i = 0; i < playersL.size(); i++) {
      if (playersL.get(i).getName().equals(playerName)) {
        return playersL.get(i).playerDescription(playerName);
      }
    }
    return humanPlayer.playerDescription(playerName);
  }

  @Override
  public String showNearbySpace() {
    return playersL.get(turn).showNearbySpace(spaceImpl, pet);
  }

  @Override
  public String showNearbyItem() {
    return playersL.get(turn).showNearbyItems(spaceImpl);
  }

  @Override
  public boolean isGameRunning() {
    if (!target.isAlive()) {
      isGameRunning = false;
    }

    return isGameRunning;
  }

  @Override
  public String compPlayer() {
    StringBuilder movement = new StringBuilder();
    if (playersL == null) {
      playersL = new ArrayList<>();
      computerPlayerList = new ArrayList<>();
      humanPlayerList = new ArrayList<>();
      playerNameList = new ArrayList<>();
      movement.append("Please create player");
    } else if (counter < turnCounter && humanPlayerList.size() > 0 && target.isAlive()) {
      if (!playersL.get(turn).getType()) {
        for (int i = 0; i < computerPlayerList.size(); i++) {
          if (Objects.equals(playersL.get(turn).getName(), computerPlayerList.get(i).getName())) {
            movement.append(computerPlayerList.get(i)
                    .computerPlayer(spaceImpl, target, pet))
                .append("It is turn of player ")
                .append(playersL.get((turn + 1) % playersL.size())
                    .getName());
            if (movement.toString().contains("killed")) {
              throw new IllegalStateException("Target dead!");
            }
            turn = (turn + 1) % playersL.size();
            counter++;
            moveTarget();
            pet.movePetDfsNew(spaceImpl);
          }
        }
      } else {
        movement.append("It is turn of player ").append(playersL.get(turn).getName());
      }
    } else if (!target.isAlive()) {
      throw new IllegalStateException("Target Died");
    } else if (counter == turnCounter || !target.isAlive()) {
      movement.append("You have ran out of moves");
      isGameRunning = false;
      throw new IllegalArgumentException("Out of moves");
    }
    return String.format("%s", movement);
  }

  @Override
  public String itemPossessed() {
    return playersL.get(turn).itemsPossessed();
  }

  @Override
  public String petMovement(String roomName) {
    if (roomName == null) {
      throw new IllegalStateException("Room name cannot be null");
    }
    pet.movePet(roomName, spaceImpl);
    turn = (turn + 1) % playersL.size();
    counter++;
    //pet.movePetDfsNew(spaceImpl);
    moveTarget();
    return "Pet movement successfull";
  }

  @Override
  public String clue() {
    if (playersL != null) {
      if (playersL.get(turn).getSpace().equals(target.whereIsTarget(spaceImpl))) {
        String roomName = playersL.get(turn).getSpace();
        StringBuilder sb = new StringBuilder();
        sb.append("\nPlayer ").append(playersL.get(turn).getName())
                .append(" is in same space with Target\n").append(this.spaceInfo(roomName))
                .append("\n");
        return sb.toString();
      } else if (target.whereIsTarget(spaceImpl).equals(pet.whereIsPet(spaceImpl))) {
        String roomName = playersL.get(turn).getSpace();
        StringBuilder sb = new StringBuilder();
        sb.append("\nTarget is not visible\n").append(this.spaceInfo(roomName)).append("\n");
        return sb.toString();

      } else if (playersL.get(turn).showNearbySpace(spaceImpl, pet).contains(
              target.whereIsTarget(spaceImpl))) {
        String roomName = playersL.get((turn + 1) % playersL.size()).getSpace();
        StringBuilder sb = new StringBuilder();
        sb.append("\nTarget is in neighboring space: ").append(target.whereIsTarget(spaceImpl))
                .append(this.spaceInfo(roomName)).append("\n");
        return sb.toString();
      } else {
        String roomName = playersL.get(turn).getSpace();
        StringBuilder sb = new StringBuilder();
        sb.append("\nTarget is not in neighboring space as it is at: ").append(
                        target.whereIsTarget(spaceImpl))
                .append(this.spaceInfo(roomName)).append("\n");
        return sb.toString();
      }
    } else {
      return String.format("Target information will only be visible after player is created\n");
    }
  }

  @Override
  public List<String> getCoordinates() {
    return coordinates;
  }

  @Override
  public List<String> itemsPossessedList() {
    return playersL.get(turn).itemPossessedList();
  }

  @Override
  public List<String> neighbours() {
    return playersL.get(turn).showNearByNeighbours(spaceImpl);
  }

  @Override
  public void moveCoordinates(int row, int column) {
    int counter = 0;
    List<String> neighbours = playersL.get(turn).showNearByNeighbours(spaceImpl);
    for (RoomImpl room : spaceImpl.getRooms()) {
      for (int i = 0; i < neighbours.size(); i++) {
        if (Objects.equals(neighbours.get(i), room.getRoomName())) {
          if ((room.getTopcoordinate() * 20 <= row && row <= room.getBottomcoordinate() * 21)
                  && (room.getLeftcoordinate() * 20 <= column
              && column <= room.getRightcoordinate() * 21)) {
            counter++;
            move(room.getRoomName());
          }
        }
      }
    }
    if (counter == 0) {
      throw new IllegalStateException("Not a valid neighbour");
    }
  }

  @Override
  public List<String> items() {
    return playersL.get(turn).showItemsNearby(spaceImpl);
  }

  @Override
  public String[] allRooms() {
    return spaceImpl.getAllRooms();
  }

  @Override
  public String petLocation() {
    return pet.whereIsPet(spaceImpl);
  }


  @Override
  public void update(String file) {
    if (file == null) {
      throw new IllegalStateException("File name cannot be nulll");
    }
    try {
      Readable reader = new FileReader(file);
      this.filereader = reader;
      String[] args = new String[2];
      args[0] = file;
      args[1] = String.valueOf(2);
      DriverControllerViewNew.main(args);
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Error in reading file");
    }

  }

  @Override
  public String whoseTurn() {
    return playersL.get(turn).getName();
  }

  @Override
  public void restart() {
    counter = 0;
    this.playersL.clear();
    this.computerPlayerList.clear();
    this.humanPlayerList.clear();
    this.playerNameList.clear();
    SpaceImpl.playerRoom.clear();
    SpaceImpl.playerItem.clear();
    countPlayer = 0;
  }

}
