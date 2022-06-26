import static org.junit.Assert.assertEquals;

import controller.SwingController;
import milestone.WorldPlayerInterface;
import org.junit.Test;
import view.HomePageNew;
import view.WorldViewRef;

/**
 * This class tests the mock model.
 */

public class SwingMockModelTest {


  @Test
  public void testLookAround() {
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    swc.showDisplay("L");
    //swc.validateInput("","288,213");
    assertEquals("Look Around called", log.toString());
    //assertEquals("Test",out.toString());
    //assertEquals("Set echo output called",log1.toString());
  }

  @Test
  public void computerPlayer() {
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    swc.showDisplay("CP");
    assertEquals("Computer Player move checked", log.toString());
    //assertEquals("Set Top Label called",log1.toString());
  }

  @Test
  public void showItemAvailable() {
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    swc.showDisplay("I");
    assertEquals("Enquired for item available for pickup", log.toString());
  }

  @Test
  public void itemPickup() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    swc.validateInput("I", "Revolver");
    assertEquals("Input: Revolver\n", log.toString());
  }


  @Test
  public void testMovePlayer() {
    Appendable out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    swc.validateInput("MOVE", "288,213");
    assertEquals("Row = 288 Column = 213", log.toString());
  }

  @Test
  public void addPlayer() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    //log1.setLength(0);
    swc.validateInput("PLAYER", "Malav,0,Armory,true");
    assertEquals("Input: Malav\n"
            +
            "Armory\n"
            +
            "0\n"
            +
            "true\n", log.toString());
  }

  @Test
  public void resetGame() {
    StringBuilder log = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    swc.showDisplay("RESTART");
    assertEquals("Restart method invoked", log.toString());
  }

  @Test
  public void testPlayerDescription() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    swc.validateInput("PD", "Malav");
    assertEquals("Describe: Malav\n", log.toString());
    //assertEquals("Set echo output called",log1.toString());
  }

  @Test
  public void testAttack() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    log1.setLength(0);
    swc.showDisplay("A");
    assertEquals("Item possessed list requested", log.toString());
    //assertEquals("Show Display called",log1.toString());
    log.setLength(0);
    //log1.setLength(0);
    swc.validateInput("A", "Revolver");
    assertEquals("Input: Revolver\n", log.toString());
    //assertEquals("Set echo output called",log1.toString());
  }

  @Test
  public void clueCommand() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    //log1.setLength(0);
    swc.showDisplay("C");
    assertEquals("Clue requested", log.toString());
    /*assertEquals("Set features invoked\n"
            + "Set echo output calledSet echo output called",log1.toString());*/
  }

  @Test
  public void movePet() {
    StringBuilder log = new StringBuilder();
    StringBuilder log1 = new StringBuilder();
    WorldPlayerInterface model = new MockWorld(log, "Test");
    SwingController swc = new SwingController(model);
    WorldViewRef wv = new HomePageNew("Kill", model);
    swc.setView(wv);
    log1.setLength(0);
    swc.showDisplay("P");
    assertEquals("List of rooms requested", log.toString());
    //assertEquals("Show Display called",log.toString());
    log.setLength(0);
    //log1.setLength(0);
    swc.validateInput("P", "Armory");
    assertEquals("Input: Armory\n", log.toString());
    //assertEquals("Set echo output called",log1.toString());
  }
}

