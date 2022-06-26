package controller.commands;

import controller.WorldCommandController;
import java.util.Arrays;
import java.util.Objects;
import milestone.WorldPlayerInterface;


/**
 * This class represents the command to show items possessed by player.
 */

public class AllRooms implements WorldCommandController {

  private String details;

  /**
   * The class constructor initializes the details string.
   */

  public AllRooms() {
    this.details = "details";
  }


  @Override
  public String execute(WorldPlayerInterface w1) {
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    details = Arrays.toString(w1.allRooms());
    details = details.replaceAll("[\\[\\](){}]", "");
    return details;
  }

  @Override
  public void setInput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("string can't be null or empty");
    }
    this.details = Objects.requireNonNullElse(s, "details");
  }
}
