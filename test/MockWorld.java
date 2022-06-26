import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import milestone.WorldPlayerInterface;

/**
 * This class is used to mock the behaviour of model which would assist in testing whether calls
 * are methods or not.
 */

public class MockWorld implements WorldPlayerInterface {
  private StringBuilder log;
  private final String uniqueCode;

  /**
   * A mock constructor is characterized with string builder and string.
   * @param log would log the input that is given to a specific method.
   * @param uniqueCode would be used in return to check whether the method recieved a call.
   */

  public MockWorld(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void createPlayer(String playerName, String space, int capacity, boolean isHuman) {
    log.append("Input: " + playerName + "\n" + space + "\n" + capacity + "\n" + isHuman + "\n");
  }

  @Override
  public String move(String roomName) {
    System.out.println("Here");
    log.append("Input: " + roomName + "\n");
    return uniqueCode;
  }

  @Override
  public String pickItem(String itemName) {
    log.append("Input: " + itemName + "\n");
    return uniqueCode;
  }

  @Override
  public String lookAround() {
    log.append("Look Around called");
    System.out.println(uniqueCode);
    return uniqueCode;
  }

  @Override
  public String spaceInfo(String roomName) {
    log.append("Input: " + roomName + "\n");
    return uniqueCode;
  }

  @Override
  public String describePlayer(String playerName) {
    log.append("Describe: " + playerName + "\n");
    return uniqueCode;
  }

  @Override
  public String playerTurn() {
    return "";
  }

  @Override
  public String showNearbySpace() {
    return null;
  }

  @Override
  public String showNearbyItem() {
    return null;
  }

  @Override
  public void draw() {
  }

  @Override
  public String compPlayer() {
    log.append("Computer Player move checked");
    return "";
  }

  @Override
  public boolean isGameRunning() {
    return true;
  }

  @Override
  public String attack(String weaponName) {
    log.append("Input: " + weaponName + "\n");
    return uniqueCode;
  }

  @Override
  public String itemPossessed() {
    log.append("Item possessed list requested");
    return "";
  }

  @Override
  public String petMovement(String roomName) {
    log.append("Input: " + roomName + "\n");
    return uniqueCode;
  }

  @Override
  public String clue() {
    log.append("Clue requested");
    return "";
  }

  @Override
  public List<String> getCoordinates() {
    return null;
  }

  @Override
  public List<String> itemsPossessedList() {
    String str = uniqueCode;
    String[] strSplit = str.split("");
    ArrayList<String> strList = new ArrayList<String>(
            Arrays.asList(strSplit));
    log.append("Item possessed list requested");
    return strList;
  }

  @Override
  public List<String> items() {
    String str = uniqueCode;
    String[] strSplit = str.split("");
    ArrayList<String> strList = new ArrayList<String>(
            Arrays.asList(strSplit));
    log.append("Enquired for item available for pickup");
    return strList;
  }

  @Override
  public List<String> neighbours() {
    return null;
  }

  @Override
  public void moveCoordinates(int row, int column) {
    log.append("Row = " + row + " " + "Column = " + column);
  }

  @Override
  public String[] allRooms() {
    log.append("List of rooms requested");
    return new String[0];
  }

  @Override
  public String whereIsTarget() {
    return null;
  }

  @Override
  public String petLocation() {
    return null;
  }

  @Override
  public String whoseTurn() {
    return null;
  }

  @Override
  public void restart() {
    log.append("Restart method invoked");
  }
}
