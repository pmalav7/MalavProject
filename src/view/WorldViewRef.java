package view;

import controller.Features;
import java.util.List;
import milestone.WorldImpl;

/**
 * This interface methods used by the view.
 */


public interface WorldViewRef {

  /**
   * This method displays a string on the frame.
   * @param s represents a string to be displayed.
   */

  void setEchoOutput(String s);

  /**
   * Clear the text field. Note that a more general "setInputString" would work
   * for this purpose but would be incorrect. This is because the text field is
   * not set programmatically in general but input by the user.
   */

  void clearInputString();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();


  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f);

  /**
   * This method refreshes the screen.
   */
  void refresh();

  /**
   * This method is used by the listener.
   * @param w1 refers to object of WorldImpl.
   */

  void register(WorldImpl w1);

  /**
   * This method is used for handling the exceptions.
   * @param s refers to message that will be displayed.
   */

  void handleException(String s);

  /**
   * This method is used in cases where multiple option will be given to user.
   * @param s refers to list of string which will be options.
   */

  void showDisplay(List<String> s);

  /**
   * This method is used to set the top label.
   * @param s refers to the string which will be displayed on top.
   */

  void setTopLabel(String s);

  /**
   * This method sets the visibility of the frame.
   */

  void makeVisible();

}
