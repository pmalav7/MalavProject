package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;

/**
 * This class represents the command used to move the pet.
 */
public class MovePet implements WorldCommandController {

  private String roomName;

  /**
   * The class constructor initializes the roomName string.
   */
  public MovePet() {
    this.roomName = "roomname";
  }


  @Override
  public String execute(WorldPlayerInterface w1) {
    String details = "";
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    details = w1.petMovement(this.roomName.trim());
    return details;
  }

  @Override
  public void setInput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("string can't be null or empty");
    }
    this.roomName = s;
  }
}
