package milestone;

import java.util.List;

/**
 * This interface represents the players of the game . There are two types of player viz.
 * Human Player and Computer Player.
 */

public interface Player {

  /**
   * This method is used for moving to the neighbouring room.
   *
   * @param roomName refers to the name of desired room where the player wants to move.
   * @param space    refers to object of the SpaceImpl class.
   * @return the status of the method call.
   */

  String moveToNeighbourSpace(String roomName, SpaceImpl space);

  /**
   * This method is used for picking up items available in same room as player is .
   *
   * @param itemName refers to name of the items desired for picking up.
   * @param space    refers to object of SpaceImpl class
   * @return the status of method call.
   */

  String pickUpItem(String itemName, SpaceImpl space);

  /**
   * This method gives the player an overview of which players , target and items that are present
   * in the current room or the neighbouring rooms.
   *
   * @param space refers to object of SpaceImpl class.
   * @param t     refers to object of TargetImpl class.
   * @param p     refers to object of PetImpl class.
   * @return a string which provides the description .
   */

  String lookAround(SpaceImpl space, TargetImpl t, PetImpl p);

  /**
   * This method gives the current location of the player along with items possessed by that player.
   *
   * @param playerName refers to name of player about which information is required.
   * @return a string which provides the player description.
   */

  String playerDescription(String playerName);

  /**
   * This method helps player in knowing which items are in his possession.
   *
   * @return a string giving information on item possessed by the player.
   */

  String itemsPossessed();

  /**
   * This method gives the name of the player.
   *
   * @return the name of the player.
   */

  String getName();

  /**
   * This method provides the name of the room in which player is present.
   *
   * @return the name of the room.
   */

  String getSpace();

  /**
   * This method gives information about the type of player.
   *
   * @return true if it's a human player else returns false in case of computer player.
   */

  boolean getType();

  /**
   * This method gives information about maximum item a player can possess.
   *
   * @return a number which is the maximum carrying capacity of the player.
   */

  int getCapacity();

  /**
   * This method provides list of items possessed.
   * @return list of string which represents all items in possession.
   */
  List<String> itemPossessedList();

  /**
   * This method gives names of spaces that are available to move for the player.
   *
   * @param space refers to object of SpaceImpl class.
   * @param pet   refers to object of PetImpl class.
   * @return the names of neighbouring spaces that don't have a pet.
   */

  String showNearbySpace(SpaceImpl space, PetImpl pet);

  /**
   * This method gives names of items that are available to pick by the player.
   *
   * @param space refers to object of SpaceImpl class.
   * @return the names of items that are present in the same room with the player.
   */

  String showNearbyItems(SpaceImpl space);

  /**
   * This method is used for attacking the target character using the weapon possessed by the
   * player.
   *
   * @param weaponName refers to name of weapon that will be used.
   * @param t          refers to object of TargetImpl class.
   * @param space      refers to object of SpaceImpl class.
   * @return a string which provides the attack status.
   */

  String attackTarget(String weaponName, TargetImpl t, SpaceImpl space);

  /**
   * This method return list of neighbours available to move.
   * @param space refers to object of spaceImpl.
   * @return list of string which represents neighbours.
   */
  List<String> showNearByNeighbours(SpaceImpl space);

  /**
   * This method return list of items lying around in same room.
   * @param space refers to object of spaceImpl.
   * @return list of string which represents items lying around.
   */
  List<String> showItemsNearby(SpaceImpl space);
}
