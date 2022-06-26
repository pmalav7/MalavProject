package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;

/**
 * This class represents the reset command used to restart the game.
 */
public class Reset implements WorldCommandController {
  private String details;

  /**
   * The class constructor initializes the details string.
   */
  public Reset() {
    this.details = "details";
  }

  @Override
  public String execute(WorldPlayerInterface w1) {
    if (w1 == null) {
      throw new IllegalStateException("WorldplayerInterface object cannot be null");
    }
    details = "Successfully restarted";
    try {
      w1.restart();
    } catch (IllegalStateException ise) {
      details = "Problem in restart";
    }
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
