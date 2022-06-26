package milestone;

import java.util.List;

/**
 * This interface represents a read-only model which provides some information to the view.
 */

public interface ReadOnlyModel {

  /**
   * This method returns a list which consist of multiple room coordinates used for drawing.
   * @return a list of coordinates.
   */

  List<String> getCoordinates();

  /**
   * This method checks whether game is running or not.
   * @return a boolean which represents game status.
   */

  boolean isGameRunning();

  /**
   * This method is used to locating the target.
   * @return a string which represents the room in which target is present.
   */

  String whereIsTarget();

  /**
   * This method gives information on pet location.
   * @return a string which represents the room in which target is present.
   */

  String petLocation();

  /**
   * This method gives name of player who has current turn.
   * @return a name of player.
   */

  String whoseTurn();

}
