package milestone;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class creates the human player and implements all its associated methods which can be
 * considered as a turn.
 */

public class HumanPlayer implements Player {

  private String playerName;
  private String roomName;
  private int capacity;
  private boolean isHuman;
  private List<String> it;
  private HashMap<String, String> playerInNeighbouringSpace;

  /**
   * The human player is characterized by four parameters.
   *
   * @param name     - name of the player.
   * @param roomName -  name of the room name in which the player is initialized.
   * @param capacity - capacity of a player to carry a item
   * @param isHuman  - determines whether it is a human player or computer player based on true
   *                 and false respectively
   */

  public HumanPlayer(String name, String roomName, int capacity, boolean isHuman) {
    if (capacity < 0) {
      throw new IllegalStateException("Capacity cannot be negative");
    }
    if (name == null) {
      throw new IllegalStateException("Name of player cannot be null");
    }
    if (roomName == null) {
      throw new IllegalStateException("Room name cannot be null");
    }
    this.playerName = name;
    this.roomName = roomName;
    this.capacity = capacity;
    this.isHuman = isHuman;
    it = new ArrayList<>();
    playerInNeighbouringSpace = new HashMap<>();
    combinePlayerRoom(name, roomName);
    it.add("poke");
    SpaceImpl.playerItem.put(getName(), it);
  }

  //Add every new player along with its room to a hashmap as key value pair
  private void combinePlayerRoom(String playerName, String roomName) {
    if (playerName == null || roomName == null) {
      throw new IllegalStateException("String can't be null");
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
    List<String> itemsInRoom = space.itemsInRoom(this.roomName);
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
      throw new IllegalStateException("Space object or Target or pet object object is null");
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
      sb.append("Player ").append(getName()).append(" is at space: ").append(getSpace())
              .append(" and following spaces are visible from this space: ").append(neighbours)
              .append(". Player that are around: ").append(listOfEntry)
              .append(" and items that are around: ").append(listOfEntry2);
      return sb.toString();
    }

  }


  @Override
  public String attackTarget(String weaponName, TargetImpl t, SpaceImpl space) {
    if (weaponName == null || t == null || space == null) {
      throw new IllegalStateException("One of the parameter passed is null");
    }
    List<String> weapons = new ArrayList<>();
    String attackStatus = null;
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
        if (("poke").equals(weaponName)) {
          int health = t.getHealth();
          if (t.getHealth() == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Attack Successful!Player ").append(this.playerName)
                    .append(" have successfully killed the target");
            attackStatus = sb.toString();
            health--;
            t.updateHealth(health);

          } else {
            health--;
            t.updateHealth(health);
            StringBuilder sb = new StringBuilder();
            sb.append("Attack Successful! The health of target is reduced by 1. ")
                    .append("Remaining health of target: ").append(t.getHealth());
            attackStatus = sb.toString();
          }
        } else if (!SpaceImpl.playerItem.get(playerName).isEmpty() && SpaceImpl.playerItem
                .containsKey(playerName)) {
          weapons = SpaceImpl.playerItem.get(playerName);
          int max;
          String weaponToBeUsed = null;
          List<Items> allWeapons = space.getItems();
          if (weapons.contains(weaponName)) {
            this.capacity++;
            for (int j = 0; j < allWeapons.size(); j++) {
              if (Objects.equals(allWeapons.get(j).getName(), weaponName)) {
                max = allWeapons.get(j).getDamage();
                weaponToBeUsed = allWeapons.get(j).getName();
                if (t.getHealth() <= max) {
                  StringBuilder sb = new StringBuilder();
                  sb.append("Attack Successful!Player ").append(this.playerName)
                          .append(" have successfully killed the target");
                  attackStatus = sb.toString();
                  t.updateHealth(0);
                  break;
                } else {
                  t.updateHealth(t.getHealth() - max);
                  StringBuilder sb = new StringBuilder();
                  sb.append("Attack Successful! The health of target is reduced by ").append(max)
                          .append("Remaining health of target: ").append(t.getHealth());
                  attackStatus = sb.toString();
                  break;
                }
              }
            }
          } else {
            throw new IllegalArgumentException("Please enter a valid weapon name");
          }

          for (Map.Entry<String, List<String>> entry : SpaceImpl.playerItem.entrySet()) {
            entry.getValue().remove(weaponName);
          }
          allWeapons.remove(weaponToBeUsed);
        }
      } else {
        if (!("poke").equals(weaponName)) {
          for (Map.Entry<String, List<String>> entry : SpaceImpl.playerItem.entrySet()) {
            entry.getValue().remove(weaponName);
            this.capacity++;
          }
        }
        attackStatus = "Attack Failed!!";
      }
    } else {
      attackStatus = "Attack Failed!! Target is not in the same space";
      if (!("poke").equals(weaponName)) {
        for (Map.Entry<String, List<String>> entry : SpaceImpl.playerItem.entrySet()) {
          entry.getValue().remove(weaponName);
          this.capacity++;
        }
      }
    }
    return attackStatus;
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

  @Override
  public String itemsPossessed() {
    String items = null;
    if (playerName == null) {
      throw new IllegalStateException("Player name cannot be null");
    }
    if (!SpaceImpl.playerItem.isEmpty() && SpaceImpl.playerItem.containsKey(this.playerName)) {
      items = SpaceImpl.playerItem.get(this.playerName).toString();
    }
    if (items != null) {
      return String.format("Player %s has following items: %s", getName(), items);
    } else {
      return String.format("Player %s has no items available", getName());
    }

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
    List<String> neighbours = space.getNeighbours(this.roomName);
    if (neighbours.contains(pet.whereIsPet(space))) {
      neighbours.remove(pet.whereIsPet(space));
    }
    return "Available option/s to move" + neighbours.toString();
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

  //This method returns the name of the player
  @Override
  public String getName() {
    return playerName;
  }

  //This method returns the space in which player exist
  @Override
  public String getSpace() {
    return this.roomName;
  }

  //This method returns the capacity of a specific player
  @Override
  public int getCapacity() {
    return capacity;
  }

  //This method returns the type of player
  @Override
  public boolean getType() {
    return isHuman;
  }

  //This method overrides the equals method
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HumanPlayer)) {
      return false;
    }
    HumanPlayer that = (HumanPlayer) o;
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


}

