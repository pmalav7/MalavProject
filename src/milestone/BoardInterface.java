package milestone;

/**
 * This interface specifies the board of the game on which game will be played.
 */


public interface BoardInterface {

  /**
   * This method finds the y-coordinate of the board.
   *
   * @return the y-coordinate of the board.
   */

  int getYcoordinate();

  /**
   * This method find the x-coordinate of the room.
   *
   * @return the x-coordinate of the board.
   */

  int getXcoordinate();

  /**
   * This method gives the name of the board.
   *
   * @return the name of the game.
   */

  String getName();
}
