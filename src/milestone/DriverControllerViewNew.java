package milestone;

import controller.SwingController;
import java.io.FileReader;
import java.io.IOException;
import view.HomePageNew;
import view.WorldViewRef;

/**
 * Driver class would initialize the model as well as the controller.
 */

public class DriverControllerViewNew {

  /**
   * The method take input from the user using the  command line arguments.
   * @param args provides the file and number of turns to the model.
   */
  public static void main(String[] args) {
    String file = "res/mansion.txt";
    //int turnsCounter = Integer.parseInt(args[1]);
    int turnsCounter = 5;
    //String file = args[0];
    //int turnsCounter = Integer.parseInt(args[1]);
    try {
      Readable reader = new FileReader(file);
      WorldImpl model = new WorldImpl(reader, turnsCounter);
      WorldViewRef vp = new HomePageNew("Kill Dr. Lucky", model);
      SwingController controller1 = new SwingController(model);
      WorldImpl w1 = new WorldImpl(vp);
      controller1.setView(vp);
    } catch (IOException e) {
      throw new IllegalStateException("Error in starting the controller");
    }
  }


}
