package milestone;

import java.util.List;
import java.util.Objects;


/**
 * This class implements the TargetImpl interface and all its associated methods which involves
 * various actions performed by target character.
 */

public class TargetImpl implements Target {

  private final String targetName;
  private int health;
  private int location;

  /**
   * The target constructor takes 2 parameters.
   *
   * @param targetName This parameter gives a name to our target character
   * @param health     This parameter assigns health to our target character. For this milestone ,
   *                   the health field is final as we are not attacking the target.
   */

  public TargetImpl(String targetName, int health) throws IllegalArgumentException {
    if (targetName == null) {
      throw new IllegalArgumentException("Target name cannot be null");
    }
    if (health < 0) {
      throw new IllegalArgumentException("Health cannot be negative");
    }
    if (health == 0) {
      throw new IllegalArgumentException("Health cannot be zero");
    }
    this.targetName = targetName;
    this.health = health;
    location = 0;
  }

  // This method checks whether the target is alive or not.
  // It makes this decision on the basis of the health of the target
  @Override
  public boolean isAlive() {
    return health != 0;
  }

  //This method returns the current location of the Target
  @Override
  public int getLocation() {
    return location;
  }

  //This method moves the target. It also checks whether the target is at the last available room
  // and if so it prints the message and doesn't increment the counter
  @Override
  public void moveTarget(SpaceImpl spaceImpl) {
    if (spaceImpl == null) {
      throw new IllegalStateException("Space is null");
    }
    List<RoomImpl> rooms = spaceImpl.getRooms();
    int size = rooms.size();
    if (location == size) {
      location = 0;
    } else {
      location++;
    }
  }

  //This method returns the name of the Target
  @Override
  public String getName() {
    return targetName;
  }

  //This method return the health of the target
  @Override
  public int getHealth() {
    return health;
  }

  //This method provides the description of the target,its location and its health
  @Override
  public String toString() {
    return String.format("Target %s is present at index location %d and is at %d health",
            targetName, location, health);
  }

  //This method gives the current room name in which the target is present
  @Override
  public String whereIsTarget(SpaceImpl spaceImpl) {
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

  @Override
  public void updateHealth(int health) {
    if (health < 0) {
      throw new IllegalStateException("Health cannot be negative");
    }
    this.health = health;
  }

  //This method overrides the equals method
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TargetImpl)) {
      return false;
    }
    TargetImpl that = (TargetImpl) o;
    return this.getName() == that.getName()
            && this.getHealth() == that.getHealth();
  }

  //As we have already override the equals method,
  // we also need to override the hashcode
  @Override
  public int hashCode() {
    int result = Objects.hash(targetName, health);
    return result;
  }
}

