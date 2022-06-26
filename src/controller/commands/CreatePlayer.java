package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;

/**
 * This class represents the command used to create player from controller.
 */
public class CreatePlayer implements WorldCommandController {

  private String playerName;
  private String roomName;
  private int capacity;
  private boolean isHuman;

  /**
   * The constructor of the class initializes player name, capacity,room name and human type.
   */
  public CreatePlayer() {
    this.playerName = "playername";
    this.capacity = 0;
    this.roomName = "roomname";
    this.isHuman = true;
  }

  @Override
  public String execute(WorldPlayerInterface w1) {
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    w1.createPlayer(this.playerName, this.roomName, this.capacity, this.isHuman);
    return "Player created successfully";
  }

  @Override
  public void setInput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("string can't be null or empty");
    }
    String[] array = s.split(",");
    this.playerName = array[0].trim();
    this.capacity = Integer.parseInt(array[1]);
    this.roomName = array[2].trim();
    this.isHuman = Boolean.parseBoolean(array[3]);
  }
}
