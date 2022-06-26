package milestone;

/**
 * This interface represents the room of the world. A room can have players , items , pet and target
 * at any given time.
 */
public interface RoomInterface {

  /**
   * This methods gives the name of a specific room.
   *
   * @return a string the name of room.
   */

  String getRoomName();

  /**
   * This method gives the index position of a room.
   *
   * @return an integer giving index of the room.
   */
  int getIndex();

  /**
   * This method gives the left boundary of the room.
   *
   * @return an integer that gives the left coordinate of a specific room.
   */
  int getLeftcoordinate();

  /**
   * This method gives the top boundary of the room.
   *
   * @return an integer that gives the top coordinate of a specific room.
   */
  int getTopcoordinate();

  /**
   * This method gives the bottom boundary of the room.
   *
   * @return an integer that gives the bottom coordinate of a specific room.
   */

  int getBottomcoordinate();

  /**
   * This method gives the right boundary of the room.
   *
   * @return an integer that gives the right coordinate of a specific room.
   */

  int getRightcoordinate();

}

