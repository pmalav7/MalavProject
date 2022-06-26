package controller;

/**
 * This interface specifies methods used to communicate between controller and view.
 */
public interface Features {

  /**
   * This method is used for passing information from controller to view.
   * @param display refers to messages from controller.
   */
  void showDisplay(String display);

  /**
   * This method is used to pass information from view to controller.
   * @param input refers to inputs given by user.
   * @param commandInput refers to task that is requested by user.
   */
  void validateInput(String input, String commandInput);
}
