package milestone;

/**
 * This interface represents the pet of the target player which will be
 * initialized at same location with target.
 */

public interface Pet {

  /**
   * This method is used for movement of the pet on the board.
   * @param roomName refers to room where we intend to move the pet
   * @param spaceImpl refers to the object of SpaceImpl class
   */
  void movePet(String roomName, SpaceImpl spaceImpl);

  /**
   * This method is used to get the room name in which the pet is present.
   * @param space refers to the object of SpaceImpl class
   * @return a string which gives the room name in which pet is present
   */
  String whereIsPet(SpaceImpl space);

  /**
   * This method returns the name of the pet.
   * @return a string which denotes the pet name.
   */
  String getPetName();

  /**
   * This method returns the index location at which pet is present.
   * @return an integer representing a specific room.
   */
  int getLocation();

  /**
   * This method is used to automatically move the pet in DFS manner over the board.
   * @param space refers to object of SpaceImpl class.
   */

  void movePetDfsNew(SpaceImpl space);
}
