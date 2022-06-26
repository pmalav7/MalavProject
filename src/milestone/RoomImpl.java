package milestone;

import java.util.Objects;

/**
 * Rooms are crucial part of this game because they will provide for locations for the target to
 * move as serve as a location for an item. A room can have multiple items associated with them.
 * This class creates the room object and implements all the associated methods.
 * <p>
 * This class represents the room in the world and implement the associated methods.
 */

public class RoomImpl implements RoomInterface {

  private Board bd;
  private final int leftcoordinate;
  private final int topcoordinate;
  private final int rightcoordinate;
  private final int bottomcoordinate;
  private final String name;
  private final int index;

  /**
   * A room constructor takes in 6 parameters which are necessary to define a space .
   * The coordinates of the room cannot be negative as well as they cannot exceed the coordinates
   * of the board.
   *
   * @param leftcoordinate   This coordinate represents the beginning row of the room
   * @param topcoordinate    This coordinate represents the beginning column of the room
   * @param rightcoordinate  This coordinate represents the ending row of the room
   * @param bottomcoordinate This coordinate represents the ending column of the room
   * @param name             There is a name associated with every room
   * @param index            Each room has its unique index which helps in mapping items to
   *                         the rooms i.e items and rooms having same index reside together.
   */

  public RoomImpl(int leftcoordinate, int topcoordinate, int rightcoordinate,
                  int bottomcoordinate, String name, int index) throws IllegalArgumentException {
    if (bd != null) {
      if (bd.getXcoordinate() < leftcoordinate
              || bd.getXcoordinate() < rightcoordinate
              || bd.getYcoordinate() < topcoordinate
              || bd.getYcoordinate() < bottomcoordinate) {
        throw new IllegalArgumentException("Coordinates are bigger than the board");
      }
    }

    if (leftcoordinate < 0 || rightcoordinate < 0 || topcoordinate < 0 || bottomcoordinate < 0) {
      throw new IllegalArgumentException("Coordinates can't be negative");
    }

    if (leftcoordinate > rightcoordinate || topcoordinate > bottomcoordinate) {
      throw new IllegalArgumentException("There are wrong coordinates");
    }
    this.leftcoordinate = leftcoordinate;
    this.topcoordinate = topcoordinate;
    this.bottomcoordinate = bottomcoordinate;
    this.rightcoordinate = rightcoordinate;
    this.name = name;
    this.index = index;
  }

  //This method returns the name of the room
  @Override
  public String getRoomName() {
    return name;
  }

  // This method returns the index(position) of a room
  @Override
  public int getIndex() {
    return index;
  }

  // This method gives the top row coordinates of a room
  @Override
  public int getLeftcoordinate() {
    return leftcoordinate;
  }

  //This method gives the beginning column coordinates of a room
  @Override
  public int getTopcoordinate() {
    return topcoordinate;
  }

  //This method gives the ending column coordinates of a room
  @Override
  public int getBottomcoordinate() {
    return bottomcoordinate;
  }

  //This method gives the ending row coordinates of a room
  @Override
  public int getRightcoordinate() {
    return rightcoordinate;
  }

  //This method gives basic information about a room
  @Override
  public String toString() {
    return String.format("The room named %s, has these coordinates %d %d %d %d and has index of %d",
            getRoomName(), getLeftcoordinate(), getTopcoordinate(), getRightcoordinate(),
            getBottomcoordinate(), getIndex());
  }

  //This method overrides the equals method
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RoomImpl)) {
      return false;
    }
    RoomImpl that = (RoomImpl) o;
    return this.getLeftcoordinate() == that.getLeftcoordinate()
            && this.getTopcoordinate() == that.getTopcoordinate()
            && this.getRightcoordinate() == that.getRightcoordinate()
            && this.getBottomcoordinate() == that.getBottomcoordinate()
            && Objects.equals(this.getRoomName(), that.getRoomName())
            && this.getIndex() == that.getIndex();
  }

  //As we have already override the equals method,
  // we also need to override the hashcode
  @Override
  public int hashCode() {
    int result = Objects.hash(leftcoordinate, topcoordinate, rightcoordinate,
            bottomcoordinate, name, index);
    return result;
  }


}
