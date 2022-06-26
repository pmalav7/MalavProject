package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;

/**
 * This class represents the command used to move to a neighbouring room.
 */
public class MoveToNeighbour implements WorldCommandController {
  private int row;
  private int column;

  /**
   * The constructor initializes the value of row and column to 0.
   */
  public MoveToNeighbour() {
    this.row = 0;
    this.column = 0;
  }

  @Override
  public String execute(WorldPlayerInterface w1) {
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    w1.moveCoordinates(this.row, this.column);
    return "Player movement successful";
  }

  @Override
  public void setInput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("string can't be null or empty");
    }
    String[] array = s.split(",");
    this.row = Integer.parseInt(array[0]);
    this.column = Integer.parseInt(array[1]);
  }
}
