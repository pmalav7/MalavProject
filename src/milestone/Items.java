package milestone;

import java.util.Objects;

/**
 * Items class represents all those items which will be used during our gameplay in order to attack
 * a target.
 */
public class Items implements ItemsInterface {

  private final int location;
  private final int damage;
  private final String name;

  /**
   * Items constructor take in 3 important parameters required to create an item object.
   *
   * @param location This location parameter determines in which room as specific item is present.
   *                 It is not necessary for all items to have a unique location index.
   * @param damage   This parameter shows the capacity of a specific item to
   *                 decrease the health of the target.
   *                 The health of the target would be reduced to the magnitude
   *                 of this damage parameter.
   * @param name     This parameter takes in the name of the item.
   */
  public Items(int location, int damage, String name) throws IllegalArgumentException {

    if (location < 0) {
      throw new IllegalArgumentException("The index of item cannot be zero");
    }
    if (damage <= 0) {
      throw new IllegalArgumentException("Damage of item cannot be negative");
    }
    if (name == null) {
      throw new IllegalArgumentException("Name of item cannot be null");
    }
    this.location = location;
    this.damage = damage;
    this.name = name;
  }

  //This method returns the damage of a specific item
  @Override
  public int getDamage() {
    return damage;
  }

  //This method return the name of an item
  @Override
  public String getName() {
    return name;
  }

  //This method returns the location of  an item which helps in mapping items to the room
  @Override
  public int getLocation() {
    return location;
  }

  //This method overrides the equals method
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Items)) {
      return false;
    }
    Items that = (Items) o;
    return this.getLocation() == that.getLocation()
            && this.getName() == that.getName()
            && this.getDamage() == that.getDamage();
  }

  //As we have already override the equals method,
  // we also need to override the hashcode
  @Override
  public int hashCode() {
    return Objects.hash(location, damage, name);
  }
}
