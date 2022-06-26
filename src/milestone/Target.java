package milestone;


/**
 * This interface specifies the operation of a target player. The target player is the main
 * character of the game who's death ends the game.
 */

public interface Target {

  /**
   * This method checks whether target is alive or not.
   *
   * @return true if target is alive and false if its dead.
   */
  boolean isAlive();

  /**
   * This method finds the index location of the target.
   *
   * @return an integer representing index location of the room.
   */

  int getLocation();

  /**
   * This method is used to move the target around the world by one index location on each turn.
   *
   * @param spaceImpl refers to object of SpaceImpl class.
   */

  void moveTarget(SpaceImpl spaceImpl);

  /**
   * This method find out the room name in which target is present.
   *
   * @param spaceImpl refers to the object of SpaceImpl class.
   * @return the room of world in which target is present.
   */

  String whereIsTarget(SpaceImpl spaceImpl);

  /**
   * This method gives the name of the target.
   *
   * @return a string which represents name of target.
   */
  String getName();

  /**
   * This method gives the current health of the target.
   *
   * @return an integer which represents health of target.
   */

  int getHealth();

  /**
   * This method updates the health of target after every attack.
   */

  void updateHealth(int health);
}
