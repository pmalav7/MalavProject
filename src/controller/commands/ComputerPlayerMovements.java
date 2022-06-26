package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;

/**
 * This class represents the command used to perform random action by the computer player.
 */
public class ComputerPlayerMovements implements WorldCommandController {

  private String details;

  /**
   * The class constructor initializes the details string.
   */
  public ComputerPlayerMovements() {
    this.details = "details";
  }

  @Override
  public String execute(WorldPlayerInterface w1) {
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    details = w1.compPlayer();
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
