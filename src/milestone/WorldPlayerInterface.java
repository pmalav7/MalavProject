package milestone;

import java.util.List;

/**
 * This interface specifies the operation of player in the world.
 * It can be asked to create a human player and computer player using one of the command below.
 * Another command would assist in checking whether game is over or not. It can also check whether
 * its turn of computer player or not and take action accordingly.
 */

public interface WorldPlayerInterface extends ReadOnlyModel {

  /**
   * This method is used to create the players for playing the game.
   * @param playerName refers to name of desired player.
   * @param space refers to name of room where they will be initialized.
   * @param capacity refers to capacity of carrying the items.
   * @param isHuman refers to type of player it will be viz Human Player or Computer Player.
   */


  void createPlayer(String playerName, String space, int capacity, boolean isHuman);

  /**
   * This method is used for moving to the neighbouring room.
   * @param roomName refers to the name of desired room where the player wants to move.
   * @return the status of the method call.
   */

  String move(String roomName);

  /**
   * This method is used for picking up items available in same room as player is .
   * @param itemName refers to name of the items desired for picking up.
   * @return the status of method call.
   */

  String pickItem(String itemName);

  /**
   * This method gives the player an overview of which players , target and items that are present
   * in the current room or the neighbouring rooms.
   * @return a string which provides the description .
   */

  String lookAround();

  /**
   * This method gives complete information about the room including its neighbours, player and
   * items present in the room. It also gives information about presence of target and pet present.
   *
   * @param roomName refers to name of the about which details are required.
   * @return a string which gives detailed information about the requested room.
   */

  String spaceInfo(String roomName);

  /**
   * This method gives the current location of the player along with items possessed by that player.
   * @param playerName refers to name of player about which information is required.
   * @return a string which provides the player description.
   */


  String describePlayer(String playerName);

  /**
   * This method manages the turn among the players while giving turns in order of players
   * addition in the gameplay.
   * @return the name of player whose turn it is.
   */

  String playerTurn();

  /**
   * This method gives names of spaces that are available to move for the player.
   * @return the names of neighbouring spaces that don't have a pet.
   */


  String showNearbySpace();

  /**
   * This method gives names of items that are available to pick by the player.
   * @return the names of items that are present in the same room with the player.
   */

  String showNearbyItem();

  /**
   * This method creates a graphical representation of the world.
   */

  void draw();

  /**
   * This method is called when it is the turn of computer player.
   * @return the output of any random action that is performed by the computer player.
   */

  String compPlayer();

  //boolean isGameRunning();

  /**
   * This method is used for attacking the target character using the weapon possessed by the
   * player.
   * @param weaponName refers to name of weapon that will be used.
   * @return a string which provides the attack status.
   */

  String attack(String weaponName);

  /**
   * This method helps player in knowing which items are in his possession.
   * @return a string giving information on item possessed by the player.
   */


  String itemPossessed();

  /**
   * This method moves the pet ot the desired location.
   * @param roomName refers the room name where the pet will be moved.
   * @return a string displaying the execution status of the method.
   */

  String petMovement(String roomName);

  /**
   * This method provides clues to the player having a turn i.e give information about the target
   * and information about the space in which player is present.
   * @return the string containing target and space information.
   */

  String clue();

  /**
   * This method provides list of items possessed.
   * @return list of string which represents all items in possession.
   */

  List<String> itemsPossessedList();

  /**
   * This method return list of items lying around in same room.
   * @return list of string which represents items lying around.
   */

  List<String> items();

  /**
   * This method return list of neighbours available to move.
   * @return list of string which represents neighbours.
   */

  List<String> neighbours();

  /**
   * This method is used to converting mouse clicks to move.
   * @param row represents the X-coordinate.
   * @param column represents the Y-coordinate.
   */

  void moveCoordinates(int row, int column);

  /**
   * This method is used for getting a list of all the rooms.
   * @return an array of all the rooms available to move.
   */

  String[] allRooms();

  /**
   * This method is used for restarting the game with new specification.
   */

  void restart();
}
