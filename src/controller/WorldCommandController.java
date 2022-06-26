package controller;

import milestone.WorldPlayerInterface;

/**
 * This interface provides operation of controller that would manage all the commands given to
 * controller. It can be asked to execute the command using the method specified below.
 */
public interface WorldCommandController {

  /**
   * This method is used for execution of the commands.
   * @param w1 refers to the object of WorldPlayerInterface.
   * @return a string which shows the success message.
   */
  String execute(WorldPlayerInterface w1);

  /**
   * This method is used to pass inputs from controller to model.
   * @param s refers to inputs received from view.
   */
  void setInput(String s);

}
