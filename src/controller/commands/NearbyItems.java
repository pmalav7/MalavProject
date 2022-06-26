package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;

/**
 * This class represents commands that provide information about
 * the items available to pick by the player.
 */
public class NearbyItems implements WorldCommandController {
  private String details;

  /**
   * The class constructor initializes the details string.
   */
  public NearbyItems() {

    details = "details";
  }


  @Override
  public String execute(WorldPlayerInterface w1) {
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    details = w1.items().toString();
    details = details.replaceAll("[\\[\\](){}]", "");
    return details;
  }

  @Override
  public void setInput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("string can't be null or empty");
    }
    this.details = "details";
  }
}