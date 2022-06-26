package milestone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class implements the PetImpl interface which represents the pet of target character.
 */

public class PetImpl implements Pet {

  private final String petName;
  private int location;
  private SpaceImpl spaceImpl;
  private int counter;
  private int marker;
  private List<String> dfsMoves;

  /**
   * The PetImpl constructor takes 2 parameter for initialization.
   *
   * @param petName  refers to the name of the pet.
   * @param location refers to the index location where the pet is present.
   */

  public PetImpl(String petName, int location) {
    if (petName == null) {
      throw new IllegalStateException("Pet name cannot be null");
    }
    if (location < 0) {
      throw new IllegalStateException("Location cannot be negative");
    }
    this.petName = petName;
    this.location = location;
    counter = 0;
    marker = 1;
    dfsMoves = new ArrayList<>();
  }

  @Override
  public String getPetName() {
    return petName;
  }

  @Override
  public int getLocation() {
    return this.location;
  }

  @Override
  public void movePet(String roomName, SpaceImpl spaceImpl) {
    if (roomName == null || spaceImpl == null) {
      throw new IllegalStateException("You have entered null parameters");
    }
    if (whereIsPet(spaceImpl).equals(roomName)) {
      throw new IllegalStateException("Pet is already at that position."
              + "Please enter a different room name");
    }
    String[] roomNames = spaceImpl.getAllRooms();
    if (!Arrays.asList(roomNames).contains(roomName)) {
      throw new IllegalStateException("Please enter a valid room name");
    }
    List<RoomImpl> rooms = spaceImpl.getRooms();
    for (int i = 0; i < rooms.size(); i++) {
      if (Objects.equals(roomName, rooms.get(i).getRoomName())) {
        this.location = i;
      }
    }
    counter = 1;
  }

  @Override
  public String whereIsPet(SpaceImpl spaceImpl) {
    if (spaceImpl == null) {
      throw new IllegalStateException("Space object is null");
    }
    String str;
    StringBuilder s = new StringBuilder();
    int i = getLocation();
    List<RoomImpl> rooms = spaceImpl.getRooms();
    for (RoomImpl room : rooms) {
      if (i == room.getIndex()) {
        s.delete(0, s.length());
        s.append(room.getRoomName());
      }
    }
    str = s.toString();
    return str.trim();
  }

  private List<String> runDfs(int location, SpaceImpl space) {
    if (location < 0 || location > space.getRooms().size() || space == null) {
      throw new IllegalStateException("There is problem in parameters of runDFS()");
    }
    List<String> dfs = space.dfs(location);
    return dfs;
  }

  @Override
  public void movePetDfsNew(SpaceImpl space) {
    if (space == null) {
      throw new IllegalStateException("Space object cannot be null");
    }
    if (counter == 0) {
      dfsMoves = runDfs(0, space);
    }
    if (counter == 1) {
      dfsMoves = runDfs(this.location, space);
      counter++;
      marker = 1;
    }
    if (marker == dfsMoves.size()) {
      counter = 0;
      marker = 1;
      dfsMoves = runDfs(0, space);
    }
    String roomName = dfsMoves.get(marker);
    marker++;
    List<RoomImpl> rooms = space.getRooms();
    for (int i = 0; i < rooms.size(); i++) {
      if (Objects.equals(roomName, rooms.get(i).getRoomName())) {
        this.location = rooms.get(i).getIndex();
        break;
      }
    }

  }

  //This method overrides the equals method
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PetImpl)) {
      return false;
    }
    PetImpl that = (PetImpl) o;
    return Objects.equals(this.getPetName(), that.getPetName())
            && this.getLocation() == that.getLocation();
  }

  //As we have already override the equals method,
  // we also need to override the hashcode
  @Override
  public int hashCode() {
    int result = Objects.hash(petName, location);
    return result;
  }

}
