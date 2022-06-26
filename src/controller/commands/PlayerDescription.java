package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;

/**
 * This class represents a command that gives a description of a player.
 */
public class PlayerDescription implements WorldCommandController {
  private String playerName;

  /**
   * The class constructor initializes the player name string.
   */
  public PlayerDescription() {
    this.playerName = "malav";
  }


  @Override
  public String execute(WorldPlayerInterface w1) {
    String details = "";
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    details = w1.describePlayer(this.playerName);
    return details;
  }

  @Override
  public void setInput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("string can't be null or empty");
    }
    this.playerName = s;
  }
}
