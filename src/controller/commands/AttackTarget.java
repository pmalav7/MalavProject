package controller.commands;

import controller.WorldCommandController;
import milestone.WorldPlayerInterface;


/**
 * This class represents the command used to perform an attack on target.
 */
public class AttackTarget implements WorldCommandController {

  private String weaponName;

  /**
   * The class constructor initializes the weapon name string.
   */
  public AttackTarget() {

    this.weaponName = "weaponName";
  }

  @Override
  public String execute(WorldPlayerInterface w1) {
    String details = "";
    if (w1 == null) {
      throw new IllegalStateException("Object of World Player Interface cannot be null");
    }
    details = w1.attack(this.weaponName);
    return details;
  }

  @Override
  public void setInput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("Input string cannot be null");
    }
    this.weaponName = s;
  }
}

