package milestone;

/**
 * This class creates the board and implements all its associated operations.
 */

public class Board implements BoardInterface {

  private final int xcoordinate;
  private final int ycoordinate;
  private final String name;

  /**
   * The board constructor is characterized by 3 parameters.
   *
   * @param xcoordinate This coordinate determines the total number of rows available for the
   *                    development of the rooms.
   * @param ycoordinate This coordinate determines the total number of columns available for the
   *                    development of the rooms.
   * @param name        Every game has a name . This name parameter specifies the name of our game
   */

  public Board(int xcoordinate, int ycoordinate, String name) throws IllegalArgumentException {

    if (xcoordinate <= 0) {
      throw new IllegalArgumentException("X_coordinate cannot be zero");
    }
    if (ycoordinate <= 0) {
      throw new IllegalArgumentException("Y_coordinate cannot be zero");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    this.xcoordinate = xcoordinate;
    this.ycoordinate = ycoordinate;
    this.name = name;
  }

  //This method returns Y coordinate which would determine the number of columns available
  @Override
  public int getYcoordinate() {
    return ycoordinate;
  }

  //This method returns X coordinate which would determine the number of rows available
  @Override
  public int getXcoordinate() {
    return xcoordinate;
  }

  //This method returns the name
  @Override
  public String getName() {
    return name;
  }
}
