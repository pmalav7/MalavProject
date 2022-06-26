package milestone;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface specifies the operation of the world. The world initializes the players,
 * target character,pet , room and items .
 */
public interface World {
  /**
   * This method finds the number of items present in the room.
   *
   * @param roomName refers to the room for which count of item is required.
   * @return an integer giving information about number of item in room.
   */
  int countOfItemsInRoom(String roomName);

  /**
   * This method returns a list of all items object.
   *
   * @return a list of item objects.
   */

  List<Items> getItemsL();

  /**
   * This method returns a list of all the rooms objects.
   *
   * @return a list of room objects.
   */

  List<RoomImpl> getRoomsL();

  /**
   * This method returns the room name of all rooms present in world.
   *
   * @return an array containing all room names.
   */

  String[] getRoomsInWorld();

  /**
   * This method finds a list of all neighbouring rooms of a given room.
   *
   * @param roomName refers to the room about which the neighbours are requested.
   * @return a list of string which has all neighbouring room names.
   */

  List<String> getNeighbors(String roomName);

  /**
   * This method finds a list of all the items present in a given room.
   *
   * @param roomName refers to the room about which the items are requested.
   * @return a list of string which has all items of the room.
   */

  List<String> getItemsInRoom(String roomName);

  /**
   * This method gives complete information about the room including its neighbours, player and
   * items present in the room. It also gives information about presence of target and pet present.
   *
   * @param roomName refers to name of the about which details are required.
   */

  String spaceInfo(String roomName);

  /**
   * This method is used to move the target around the world by one index location on each turn.
   */

  void moveTarget();

  /**
   * This method finds the index location of the target.
   *
   * @return an integer representing index location of the room.
   */

  int getLocationOfTarget();

  /**
   * This method finds out the room name in which target is present.
   *
   * @return the room of world in which target is present.
   */

  String whereIsTarget();

}
