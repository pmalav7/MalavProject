package milestone;

/**
 * This interface represents the items of that would be used by the player to attack the target.
 */

public interface ItemsInterface {

  /**
   * This method gives information about damage caused by an item.
   *
   * @return damage of a particular item.
   */
  int getDamage();

  /**
   * This method gets the name of an item.
   *
   * @return the name of the item.
   */

  String getName();

  /**
   * This methods find the index location of an item.
   *
   * @return the integer representing the location.
   */
  int getLocation();
}
