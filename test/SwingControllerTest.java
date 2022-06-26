import static org.junit.Assert.assertEquals;

import controller.SwingController;
import milestone.WorldPlayerInterface;
import org.junit.Test;
import view.WorldViewRef;

/**
 * This class uses mock model and mock view to check the command flow in MVC.
 */

public class SwingControllerTest {

  @Test
  public void testMovePlayer() {
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    swc.validateInput("MOVE", "288,213");
    assertEquals("Set features invoked\nRow = 288 Column = 213Set echo output called",
            log.toString());
  }

  @Test
  public void testLookAround() {
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    swc.showDisplay("L");
    //swc.validateInput("","288,213");
    assertEquals("Set features invoked\n"
            + "Look Around calledSet echo output called", log.toString());
  }

  @Test
  public void computerPlayer() {
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    swc.showDisplay("CP");
    assertEquals("Set features invoked\n"
            +
            "Computer Player move checkedSet Top Label called", log.toString());
    //assertEquals("Set Top Label called",log1.toString());
  }

  @Test
  public void showItemAvailable() {
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    swc.showDisplay("I");
    assertEquals("Set features invoked\n"
            + "Enquired for item available for pickupShow Display called", log.toString());
    //assertEquals("Show Display called",log.toString());
  }

  @Test
  public void itemPickup() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    swc.validateInput("I", "Revolver");
    assertEquals("Set features invoked\n"
            + "Input: Revolver\n"
            + "Set echo output called", log.toString());
    //assertEquals("Set echo output called",log.toString());
  }

  @Test
  public void setFeatures() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log1, "Test");
    swc.setView(vp);
    assertEquals("Set features invoked", log1.toString());
  }

  @Test
  public void movePet() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    log1.setLength(0);
    swc.showDisplay("P");
    assertEquals("Set features invoked\n"
            +
            "List of rooms requestedShow Display called", log.toString());
    //assertEquals("Show Display called",log.toString());
    log.setLength(0);
    //log1.setLength(0);
    swc.validateInput("P", "Armory");
    assertEquals("Input: Armory\n"
            + "Set echo output called", log.toString());
    //assertEquals("Set echo output called",log1.toString());
  }

  @Test
  public void clueCommand() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    //log1.setLength(0);
    swc.showDisplay("C");
    assertEquals("Set features invoked\n"
            + "Clue requestedSet echo output called", log.toString());
    /*assertEquals("Set features invoked\n"
            + "Set echo output calledSet echo output called",log1.toString());*/
  }

  @Test
  public void testAttack() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    log1.setLength(0);
    swc.showDisplay("A");
    assertEquals("Set features invoked\n"
            +
            "Item possessed list requestedShow Display called", log.toString());
    //assertEquals("Show Display called",log1.toString());
    log.setLength(0);
    //log1.setLength(0);
    swc.validateInput("A", "Revolver");
    assertEquals("Input: Revolver\nSet echo output called", log.toString());
    //assertEquals("Set echo output called",log1.toString());
  }

  @Test
  public void testPlayerDescription() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    //log1.setLength(0);
    swc.validateInput("PD", "Malav");
    assertEquals("Set features invoked\n"
            + "Describe: Malav\n"
            + "Set echo output called", log.toString());
    //assertEquals("Set echo output called",log1.toString());
  }

  @Test
  public void resetGame() {
    StringBuilder log = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    swc.showDisplay("RESTART");
    assertEquals("Set features invoked\n"
            + "Restart method invokedSet echo output called", log.toString());
  }

  @Test
  public void addPlayer() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef vp = new MockView(log, "Test");
    swc.setView(vp);
    //log1.setLength(0);
    swc.validateInput("PLAYER", "Malav,0,Armory,true");
    assertEquals("Set features invoked\n"
            + "Input: Malav\n"
            + "Armory\n"
            + "0\n"
            + "true\n"
            + "Set echo output calledClear input string called", log.toString());
  }
}
