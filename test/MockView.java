import controller.Features;
import java.util.List;
import milestone.WorldImpl;
import view.WorldViewRef;


/**
 * this creates mock view.
 */
public class MockView implements WorldViewRef {
  private StringBuilder log;
  private final String uniqueCode;

  /**
   * this class is initialized.
   * @param log string
   * @param uniqueCode unique code of method.
   */
  public MockView(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void setEchoOutput(String s) {
    log.append("Set echo output called");
  }

  @Override
  public void clearInputString() {
    log.append("Clear input string called");
  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void setFeatures(Features f) {

    log.append("Set features invoked\n");
  }

  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void register(WorldImpl w1) {

  }

  @Override
  public void handleException(String s) {

  }

  @Override
  public void showDisplay(List<String> s) {
    log.append("Show Display called");
  }

  @Override
  public void setTopLabel(String s) {
    log.append("Set Top Label called");
  }
}
