package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;

/**
 * This class represents a command to pick up an item by a player.
 */
public class PickUpItem implements WorldCommandController {
  private String itemName;

  /**
   * The class constructor initializes the details string.
   */
  public PickUpItem() {

    this.itemName = "item";
  }

  @Override
  public String execute(WorldPlayerInterface w1) {
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    w1.pickItem(this.itemName);
    return "Item pickup successful";
  }

  @Override
  public void setInput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("string can't be null or empty");
    }
    this.itemName = s;
  }
}