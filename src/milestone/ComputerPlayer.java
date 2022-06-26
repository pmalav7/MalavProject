package milestone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class creates the computer player which performs random actions on its turn.
 */

public class ComputerPlayer implements Player {

  private final RandomInterface randomInterface;
  private final String playerName;
  private String roomName;
  private int capacity;
  private final boolean isHuman;
  private final List<String> it;
  private List<String> playerNameList;
  private Map<String, String> playerInNeighbouringSpace;

  /**
   * Computer player is characterized by name , room name , capacity, isHuman and random interface.
   *
   * @param name            gives a name to the computer player.
   * @param roomName        provides the location where the player is initialized.
   * @param capacity        provides the limit up to which a player can carry items.
   * @param isHuman         provides information about a player type - Human or Computer
   * @param randomInterface provides a randomizer to the computer player.
   */

  public ComputerPlayer(String name, String roomName, int capacity,
                        boolean isHuman, RandomInterface randomInterface) {

    if (capacity < 0) {
      throw new IllegalStateException("Capacity cannot be negative");
    }
    if (name == null) {
      throw new IllegalStateException("Name of player cannot be null");
    }
    if (roomName == null) {
      throw new IllegalStateException("Room name cannot be null");
    }
    if (randomInterface == null) {
      throw new IllegalStateException("Random interface cannot be null");
    }
    this.playerName = name;
    this.randomInterface = randomInterface;
    this.roomName = roomName;
    this.capacity = capacity + 1;
    this.isHuman = isHuman;
    it = new ArrayList<>();
    playerNameList = new ArrayList<>();
    playerInNeighbouringSpace = new HashMap<>();
    combinePlayerRoom(name, roomName);

    it.add("poke");
    SpaceImpl.playerItem.put(getName(), it);
  }

  //Add every new player along with its room to a hashmap as key value pair
  private void combinePlayerRoom(String playerName, String roomName) {
    if (roomName == null) {
      throw new IllegalStateException("Roomname cannot be null");
    }
    if (playerName == null) {
      throw new IllegalStateException("Player name cannot be null");
    }
    SpaceImpl.playerRoom.put(this.playerName, this.roomName);
  }


  //Moves the player to neighbouring space
  @Override
  public String moveToNeighbourSpace(String roomName, SpaceImpl space) {
    if (roomName == null || space == null) {
      throw new IllegalStateException("Room name cannot be null");
    }
    if (Objects.equals(this.roomName, roomName)) {
      throw new IllegalStateException("You have entered same room name");
    } else if (!Arrays.asList(space.getAllRooms()).contains(roomName)) {
      throw new IllegalStateException("This room does not exist");
    } else {
      List<String> neighbours = space.getNeighbours(this.roomName);
      if (!neighbours.contains(roomName)) {
        throw new IllegalStateException("A neighbour was not entered");
      }
      for (int i = 0; i < neighbours.size(); i++) {
        if (neighbours.get(i).equals(roomName)) {
          this.roomName = roomName;
          break;
        }
      }
    }
    SpaceImpl.playerRoom.remove(this.playerName);
    SpaceImpl.playerRoom.put(this.playerName, roomName);
    return String.format("Player %s has moved to %s and this space has %s",
            this.playerName, roomName, space.itemsInRoom(roomName));
  }

  // This method is used for picking up any item available in the given space
  @Override
  public String pickUpItem(String itemName, SpaceImpl space) {
    if (itemName == null || space == null) {
      throw new IllegalStateException("Itemname cannot be null");
    }
    if (this.capacity == 0) {
      throw new IllegalArgumentException("Item carrying capacity is already at maximum");
    }

    List<String> itemsInRoom = space.itemsInRoom(this.getSpace());
    if (itemsInRoom == null) {
      throw new IllegalStateException("There is nothing to pick from this room");
    }
    if (!itemsInRoom.contains(itemName)) {
      throw new IllegalStateException("Item not available");
    } else {
      for (int i = 0; i < itemsInRoom.size(); i++) {
        if (Objects.equals(itemsInRoom.get(i), itemName)) {
          it.add(itemName);
          SpaceImpl.playerItem.put(getName(), it);
          this.capacity = this.capacity - 1;
          space.clonedRoomItem().get(this.getSpace()).remove(itemName);
          break;
        }
      }
    }
    return String.format("Player %s has picked up the item %s", getName(), itemName);
  }

  //This method is used for looking around by a specific space
  @Override
  public String lookAround(SpaceImpl space, TargetImpl t, PetImpl p) {
    if (space == null || t == null || p == null) {
      throw new IllegalStateException("Space object or Target object is null");
    }
    String petPos = p.whereIsPet(space);
    String targetPos = t.whereIsTarget(space);
    playerInNeighbouringSpace = new HashMap<>();
    HashMap<String, List<String>> itemsInNeighbouringSpace = new HashMap<>();
    List<String> itemsInSameSpace = space.clonedRoomItem().get(this.roomName);
    if (itemsInSameSpace.size() != 0) {
      itemsInNeighbouringSpace.put(this.roomName, itemsInSameSpace);
    }
    List<String> neighbours = space.getNeighbours(this.getSpace());
    //New line
    neighbours.remove(petPos);
    for (int i = 0; i < neighbours.size(); i++) {
      List<String> items = space.clonedRoomItem().get(neighbours.get(i));
      if (!items.isEmpty()) {
        itemsInNeighbouringSpace.put(neighbours.get(i), items);
      }
    }
    Set<Map.Entry<String, List<String>>> entrySet2 = itemsInNeighbouringSpace.entrySet();
    List<Map.Entry<String, List<String>>> listOfEntry2
            = new ArrayList<>(entrySet2);
    if (Objects.equals(targetPos, this.getSpace())) {
      playerInNeighbouringSpace.put(t.getName(), targetPos);
    }
    //Original Lines
    if (neighbours.contains(targetPos)) {
      playerInNeighbouringSpace.put(t.getName(), targetPos);
    }
    for (Map.Entry<String, String> entry : SpaceImpl.playerRoom.entrySet()) {
      if (entry.getValue().equals(this.getSpace()) && !petPos.equals(entry.getValue())) {
        playerInNeighbouringSpace.put(entry.getKey(), entry.getValue());
      }
    }
    for (String key : SpaceImpl.playerRoom.keySet()) {
      for (int j = 0; j < neighbours.size(); j++) {
        if (Objects.equals(SpaceImpl.playerRoom.get(key), neighbours.get(j))) {
          if (!Objects.equals(key, this.getName())) {
            playerInNeighbouringSpace.put(key, neighbours.get(j));
          }
        }
      }
    }
    playerInNeighbouringSpace.remove(this.getName());
    Set<Map.Entry<String, String>> entrySet = playerInNeighbouringSpace.entrySet();
    List<Map.Entry<String, String>> listOfEntry
            = new ArrayList<>(entrySet);

    if (Objects.equals(this.getSpace(), p.whereIsPet(space))) {
      StringBuilder sb = new StringBuilder();
      sb.append("Player ").append(getName()).append(" is at space: ").append(getSpace())
              .append(" and following spaces are visible from this space: ").append(neighbours)
              .append(". Player that are around: ").append(listOfEntry)
              .append(" and items that are around: ").append(listOfEntry2)
              .append(". Pet ").append(p.getPetName()).append(" is also in same room.");
      return sb.toString();
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append("Player ").append(getName()).append("is at space: ").append(getSpace())
              .append("and following spaces are visible from this space: ").append(neighbours)
              .append("Player that are around: ").append(listOfEntry)
              .append("and items that are around: ").append(listOfEntry2);
      return sb.toString();
    }

  }

  //This method gives description of the player
  @Override
  public String playerDescription(String playerName) {
    if (playerName == null) {
      throw new IllegalStateException("Player name cannot be null");
    }
    String value = null;
    String spaceInfo = SpaceImpl.playerRoom.get(playerName);
    if (SpaceImpl.playerItem.containsKey(playerName)) {
      String items = SpaceImpl.playerItem.get(playerName).toString();
      value = String.format("Player %s has following items: %s and is at space: %s",
              playerName, items, spaceInfo);
    } else {
      value = String.format("Player %s has no items and is at space: %s", playerName, spaceInfo);
    }
    return value;
  }

  //This method returns information about items that are in possession of a player
  @Override
  public String itemsPossessed() {
    if (playerName == null) {
      throw new IllegalStateException("Player name cannot be null");
    }
    String items = SpaceImpl.playerItem.get(this.getName()).toString();
    return String.format("Player %s has following items: %s", getName(), items);
  }

  //This method returns the name of the player
  @Override
  public String getName() {
    return playerName;
  }

  //This method returs the space in which player exist
  @Override
  public String getSpace() {
    return roomName;
  }

  //This method returns the capacity of a specific player
  @Override
  public int getCapacity() {
    return capacity;
  }

  @Override
  public List<String> itemPossessedList() {
    List<String> items = SpaceImpl.playerItem.get(this.playerName);
    return items;
  }

  //Gives neighbouring space
  @Override
  public String showNearbySpace(SpaceImpl space, PetImpl pet) {
    if (space == null || pet == null) {
      throw new IllegalStateException("space object/pet object cannot be null");
    }
    return "Available option/s to move" + space.getNeighbours(this.roomName).toString();
  }

  //Gives information about nearby items in same room
  @Override
  public String showNearbyItems(SpaceImpl space) {
    if (space == null) {
      throw new IllegalStateException("space object cannot be null");
    }
    List<String> items = space.clonedRoomItem().get(this.roomName);
    if (items.isEmpty()) {
      throw new IllegalStateException();
    } else {
      return "Available option/s to pick" + items;
    }
  }


  @Override
  public String attackTarget(String weaponName, TargetImpl t, SpaceImpl space) {
    if (t == null || space == null || weaponName == null) {
      throw new IllegalStateException("Target object/space object/weaponName is null is null");
    }
    String attackStatus = null;
    String weaponToBeUsed = null;
    if (Objects.equals(this.getSpace(), t.whereIsTarget(space))) {

      HashMap<String, String> playersNearby = new HashMap<>();
      List<String> neighbours = space.getNeighbours(this.getSpace());
      for (Map.Entry<String, String> entry : SpaceImpl.playerRoom.entrySet()) {
        if (entry.getValue().equals(this.getSpace())) {
          playersNearby.put(entry.getKey(), entry.getValue());
        }
      }
      for (String key : SpaceImpl.playerRoom.keySet()) {
        for (int j = 0; j < neighbours.size(); j++) {
          if (Objects.equals(SpaceImpl.playerRoom.get(key), neighbours.get(j))) {
            if (!Objects.equals(key, this.getName())) {
              playersNearby.put(key, neighbours.get(j));
            }
          }
        }
      }
      playersNearby.remove(this.getName());
      if (playersNearby.isEmpty()) {
       /* if (SpaceImpl.playerItem.get(playerName) == null) {
          int health = t.getHealth();
          if (t.getHealth() == 1) {
            t.updateHealth(0);
            StringBuilder sb = new StringBuilder();
            sb.append("Attack Successful!Player ").append(this.playerName)
                    .append("have successfully killed the target");
            attackStatus = sb.toString();
            health--;

          } else {
            health--;
            t.updateHealth(health);
            StringBuilder sb = new StringBuilder();
            sb.append("Attack successful! The health of target is reduced by 1. ")
                    .append("Remaining health of target: ").append(t.getHealth());
            attackStatus = sb.toString();
          }
        } else */
          if (!SpaceImpl.playerItem.get(playerName).isEmpty()) {
          List<String> weapons = SpaceImpl.playerItem.get(playerName);
          List<Items> allWeapons = space.getItems();
          int count = 0;
          int max = 0;
          for (int i = 0; i < weapons.size(); i++) {
            for (int j = 0; j < allWeapons.size(); j++) {
              if (Objects.equals(weapons.get(i), allWeapons.get(j).getName())) {
                if (allWeapons.get(j).getDamage() > max) {
                  max = allWeapons.get(j).getDamage();
                  weaponToBeUsed = allWeapons.get(j).getName();
                  count ++;
                }
              }
            }
          }
          if (count == 0) {
            max = 1;
          }
          if (t.getHealth() <= max) {
            StringBuilder sb = new StringBuilder();
            sb.append("Attack Successful!Player ").append(this.playerName)
                    .append("have successfully killed the target");
            attackStatus = sb.toString();
            t.updateHealth(0);
          } else {
            t.updateHealth(t.getHealth() - max);
            StringBuilder sb = new StringBuilder();
            sb.append("Attack successful! The health of target is reduced by ").append(max)
                    .append("Remaining health of target: ").append(t.getHealth());
            attackStatus = sb.toString();
          }
          for (Map.Entry<String, List<String>> entry : SpaceImpl.playerItem.entrySet()) {
            entry.getValue().remove(weaponToBeUsed);
          }
          allWeapons.remove(weaponToBeUsed);
        }
      } else {
        attackStatus = "Attack Failed!! Target is not in the same space";
        for (Map.Entry<String, List<String>> entry : SpaceImpl.playerItem.entrySet()) {
          entry.getValue().remove(weaponToBeUsed);
        }
      }
    }
    return attackStatus;
  }

  @Override
  public List<String> showNearByNeighbours(SpaceImpl space) {
    if (space == null) {
      throw new IllegalStateException("Space object cannot be null");
    }
    return space.getNeighbours(this.roomName);
  }

  @Override
  public List<String> showItemsNearby(SpaceImpl space) {
    if (space == null) {
      throw new IllegalStateException("space object cannot be null");
    }
    List<String> items = space.clonedRoomItem().get(this.roomName);
    return items;
  }

  //This method returns the type of player
  @Override
  public boolean getType() {
    return isHuman;
  }


  /**
   * This method is specific to computer player as it helps in randomizing the acts of computer
   * player.
   *
   * @param space provides the space object used in various tasks performed by the the computer
   *              player.
   * @return string output of the action performed viz move, pick and look around. It moves to next
   *              place if not item is available.
   */

  // the acts of computer player
  public String computerPlayer(SpaceImpl space, TargetImpl target, PetImpl pet) {
    if (space == null) {
      throw new IllegalStateException("Space object cannot be null");
    }
    if (target == null) {
      throw new IllegalStateException("Target object cannot be null");
    }
    if (pet == null) {
      throw new IllegalStateException("Pet object cannot be null");
    }
    String value;
    HashMap<String, String> playersNearby = new HashMap<>();
    List<String> neighbours = space.getNeighbours(this.getSpace());
    for (Map.Entry<String, String> entry : SpaceImpl.playerRoom.entrySet()) {
      if (entry.getValue().equals(this.getSpace())) {
        playersNearby.put(entry.getKey(), entry.getValue());
      }
    }
    for (String key : SpaceImpl.playerRoom.keySet()) {
      for (int j = 0; j < neighbours.size(); j++) {
        if (Objects.equals(SpaceImpl.playerRoom.get(key), neighbours.get(j))) {
          if (!Objects.equals(key, this.getName())) {
            playersNearby.put(key, neighbours.get(j));
          }
        }
      }
    }
    playersNearby.remove(this.getName());
    if (Objects.equals(this.getSpace(), target.whereIsTarget(space)) && playersNearby.isEmpty()) {
      value = attackTarget("attack", target, space);
    } else {
      int turn = randomInterface.getRandom(3);
      if (turn == 0) {
        List<String> neighbours1 = space.getNeighbours(getSpace());
        int selectNeighbour = randomInterface.getRandom(neighbours1.size());
        value = moveToNeighbourSpace(neighbours.get(selectNeighbour), space);
      } else if (turn == 1) {
        List<String> itemsInRoom = space.itemsInRoom(getSpace());
        if (itemsInRoom.isEmpty()) {
          List<String> neighbours1 = space.getNeighbours(getSpace());
          int selectNeighbour = randomInterface.getRandom(neighbours1.size());
          value = moveToNeighbourSpace(neighbours.get(selectNeighbour), space);
        } else {
          int selectItem = randomInterface.getRandom(itemsInRoom.size());
          value = pickUpItem(itemsInRoom.get(selectItem), space);
        }
      } else {
        value = lookAround(space, target, pet);
      }
    }
    value = value.replaceAll("[\\[\\](){}]", "");
    return value + "\n";
  }

  //This method overrides the equals method
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ComputerPlayer)) {
      return false;
    }
    ComputerPlayer that = (ComputerPlayer) o;
    return this.getName() == that.getName()
            && this.getSpace() == that.getSpace()
            && this.getCapacity() == that.getCapacity()
            && this.getType() == this.getType();
  }

  //As we have already override the equals method,
  // we also need to override the hashcode
  @Override
  public int hashCode() {
    int result = Objects.hash(playerName, roomName, capacity, isHuman);
    return result;
  }

}



