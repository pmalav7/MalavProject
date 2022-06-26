package controller;

import controller.commands.AllRooms;
import controller.commands.AttackTarget;
import controller.commands.ClueCommand;
import controller.commands.ComputerPlayerMovements;
import controller.commands.CreatePlayer;
import controller.commands.ItemPossessed;
import controller.commands.LookAround;
import controller.commands.MovePet;
import controller.commands.MoveToNeighbour;
import controller.commands.NearbyItems;
import controller.commands.PickUpItem;
import controller.commands.PlayerDescription;
import controller.commands.Reset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import milestone.WorldPlayerInterface;
import view.WorldViewRef;

/**
 * This class represents the controller.
 */
public class SwingController implements Features {

  private WorldPlayerInterface w1;
  private WorldViewRef wv;
  private Map<String, WorldCommandController> commandControllerMap;

  /**
   * The controller constructor is initialized by 1 parameter.
   * @param w1 refers to the object of WorldPlayerInterface.
   */
  public SwingController(WorldPlayerInterface w1) {
    if (w1 == null) {
      throw new IllegalStateException("WorldPlayerInterface w1 can't be null");
    }
    this.w1 = w1;
    this.commandControllerMap = new HashMap<>();
    //the object of WorldViewRef is deliberately set to null.
    //It would be set using one of the setter methods.
    wv = null;
    createMap();
  }


  private void createMap() {
    commandControllerMap.put("I", new PickUpItem());
    commandControllerMap.put("A", new AttackTarget());
    commandControllerMap.put("P", new MovePet());
    commandControllerMap.put("L", new LookAround());
    commandControllerMap.put("C", new ClueCommand());
    commandControllerMap.put("CP", new ComputerPlayerMovements());
    commandControllerMap.put("NearbyItems", new NearbyItems());
    commandControllerMap.put("PossessedItems", new ItemPossessed());
    commandControllerMap.put("PLAYER", new CreatePlayer());
    commandControllerMap.put("AllRooms", new AllRooms());
    commandControllerMap.put("MOVE", new MoveToNeighbour());
    commandControllerMap.put("PD", new PlayerDescription());
    commandControllerMap.put("RESTART", new Reset());
  }

  /**
   * This method is used for setting the view.
   * @param wv refers to the object of WorldViewRef.
   */

  public void setView(WorldViewRef wv) {
    if (wv == null) {
      throw new IllegalStateException("WorldViewRef object can't be null");
    }
    this.wv = wv;
    wv.setFeatures(this);

  }

  @Override
  public void validateInput(String input, String commandInput) {
    if (input == null || commandInput == null) {
      throw new IllegalStateException("Input or command input cannot be null");
    }

    if (Objects.equals(input, "I")) {
      WorldCommandController pickUpItem = commandControllerMap.get(input);
      pickUpItem.setInput(commandInput.trim());
      try {
        wv.setEchoOutput(commandControllerMap.get(input).execute(w1).trim());
      } catch (IllegalArgumentException iae) {
        wv.handleException("Maximum capacity reached");
      }
    }
    if (Objects.equals(input, "P")) {
      WorldCommandController movePet = commandControllerMap.get(input);
      movePet.setInput(commandInput);
      try {
        wv.setEchoOutput(commandControllerMap.get(input).execute(w1).trim());
      } catch (IllegalStateException ise) {
        wv.handleException("Pet is already at same place");
      }
    }
    if (Objects.equals(input, "A")) {
      WorldCommandController attack = commandControllerMap.get(input);
      attack.setInput(commandInput.trim());
      try {
        wv.setEchoOutput(commandControllerMap.get(input).execute(w1).trim());
      } catch (IllegalStateException ise) {
        wv.handleException("Target character died");
      }
    }
    if ("PLAYER".equals(input)) {
      WorldCommandController player = commandControllerMap.get(input);
      player.setInput(commandInput);
      try {
        wv.setEchoOutput(commandControllerMap.get(input).execute(w1).trim());
      } catch (IllegalStateException ise) {
        wv.handleException("Player Name might be null or repeated");
      } catch (IllegalArgumentException iae) {
        wv.handleException("Max player capacity reached");
      }
    }
    if ("MOVE".equals(input)) {
      WorldCommandController move = commandControllerMap.get(input);
      move.setInput(commandInput);
      try {
        wv.setEchoOutput(commandControllerMap.get(input).execute(w1).trim());
      } catch (IllegalStateException ise) {
        wv.handleException("Invalid Neighbours");
      }
    }
    if ("PD".equals(input)) {
      WorldCommandController playerDescription = commandControllerMap.get(input);
      playerDescription.setInput(commandInput);
      wv.setEchoOutput(commandControllerMap.get(input).execute(w1).trim());
    }


  }

  @Override
  public void showDisplay(String display) {
    if (display == null || display.isEmpty()) {
      throw new IllegalStateException("Cannot pass null string in show display");
    }
    if (Objects.equals(display, "I")) {
      List<String> nearbyList = new ArrayList<String>(Arrays.asList(
              commandControllerMap.get("NearbyItems").execute(w1).split(",")));

      wv.showDisplay(nearbyList);

    }
    if (Objects.equals(display, "P")) {
      List<String> allRooms = new ArrayList<String>(Arrays.asList(
              commandControllerMap.get("AllRooms").execute(w1).split(",")));

      wv.showDisplay(allRooms);

    }
    if (Objects.equals(display, "C")) {
      wv.setEchoOutput(commandControllerMap.get("C").execute(w1).trim());
    }
    if (Objects.equals(display, "A")) {

      List<String> possessedList = new ArrayList<String>(Arrays.asList(
              commandControllerMap.get("PossessedItems").execute(w1).split(",")));

      wv.showDisplay(possessedList);

    }
    if (Objects.equals(display, "L")) {
      wv.setEchoOutput(commandControllerMap.get("L").execute(w1).trim());

    }

    if (Objects.equals(display, "CP")) {
      try {
        wv.setTopLabel(commandControllerMap.get("CP").execute(w1));
        wv.refresh();
      } catch (IllegalArgumentException iae) {
        wv.handleException("Out of moves !! Target lives another day");

      } catch (IllegalStateException ise) {
        wv.handleException("Target dead");
      }

    }
    if ("RESTART".equals(display)) {
      wv.setEchoOutput(commandControllerMap.get("RESTART").execute(w1));
    }
  }
}
