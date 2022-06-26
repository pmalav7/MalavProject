package milestone;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This interface specifies the operation of space.A space is characterized by room and items.
 */

public interface SpaceInterface {

  /**
   * This method returns a list of all the rooms objects.
   *
   * @return a list of room objects.
   */

  List<RoomImpl> getRooms();

  /**
   * This method returns a list of all items object.
   *
   * @return a list of item objects.
   */

  List<Items> getItems();

  /**
   * This method returns the room name of all rooms present in world.
   *
   * @return an array containing all room names.
   */

  String[] getAllRooms();

  /**
   * This method finds a list of all neighbouring rooms of a given room.
   *
   * @param roomName refers to the room about which the neighbours are requested.
   * @return a list of string which has all neighbouring room names.
   */

  List<String> getNeighbours(String roomName);

  /**
   * This method finds a list of all the items present in a given room.
   *
   * @param roomName refers to the room about which the items are requested.
   * @return a list of string which has all items of the room.
   */

  List<String> itemsInRoom(String roomName);

  /**
   * This method finds the number of items present in the room.
   *
   * @param roomName refers to the room for which count of item is required.
   * @return an integer giving information about number of item in room.
   */

  int numberOfItemsInRoom(String roomName);

  /**
   * This method gives complete information about the room including its neighbours, player and
   * items present in the room. It also gives information about presence of target and pet present.
   *
   * @param roomName refers to name of the about which details are required.
   * @param hp       refers to object of HumanPlayer class.
   * @param t        refers to object of TargetImpl class.
   * @param p        refers to object of PetImpl class.
   * @return a string which gives detailed information about the requested room.
   */

  String getRoomDetails(String roomName, HumanPlayer hp, TargetImpl t, PetImpl p);

  /**
   * This method associates every room with its respective items.
   *
   * @return a hashmap which has room name as key and item names as values.
   */

  Map<String, List<String>> clonedRoomItem();

  /**
   * This method calculates the DFS traversal for the room based on their index location.
   *
   * @param start refers to index location of the room from where DFS traversal is required.
   * @return a list of string of index positions in which rooms will be traversed.
   */

  List<String> dfs(int start);
}
