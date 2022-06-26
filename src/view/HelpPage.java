package view;

import controller.Features;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import milestone.WorldImpl;


/**
 * This class represents the help page which consists of help information.
 */
public class HelpPage extends JFrame implements WorldViewRef {

  private JPanel jPanel1;
  private JLabel lblwelcome;
  private JLabel rule1;
  private StringBuilder sb;

  /**
   * The constructor of the class initializes the frame with all the required components.
   */
  public HelpPage(String windowTitle) {
    super(windowTitle);
    if (windowTitle == null) {
      throw new IllegalStateException("windowTitle can't be null");
    }
    this.setSize(500, 400);
    setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new GridLayout(0, 1));
    initComponents();
  }

  private void initComponents() {
    jPanel1 = new JPanel();
    lblwelcome = new JLabel();
    rule1 = new JLabel();
    jPanel1.setSize(500, 400);
    jPanel1.setBackground(new Color(161, 57, 65));
    lblwelcome.setFont(new Font("Segoe UI", 1, 24));
    lblwelcome.setForeground(new Color(243, 219, 116));
    lblwelcome.setText("<html>Welcome to Help Page<br></html>");
    sb = new StringBuilder("<html>Press M to move<br>Press P for Pet movement");
    sb.append("<br>Press L for look around<br>Press I for Item pickup");
    sb.append("<br>Press A for Attack<br>Press C for clue");
    sb.append("<br>Click on player for Player Info");
    sb.append("<br>Player will be invisible if it is with pet in the space</html>");
    rule1.setText(sb.toString());
    lblwelcome.setAlignmentY(0.0F);
    jPanel1.add(lblwelcome);
    jPanel1.add(rule1);
    add(jPanel1);
    this.setVisible(true);
  }

  @Override
  public void setEchoOutput(String s) {
    if (s == null) {
      sb.append(s);
    }
  }

  @Override
  public void clearInputString() {
    sb = new StringBuilder();
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalStateException("features can't be null");
    }
    sb = new StringBuilder();
  }


  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void register(WorldImpl w1) {
    if (w1 == null) {
      throw new IllegalStateException("features can't be null");
    }
    sb = new StringBuilder();
  }

  @Override
  public void handleException(String s) {
    if (s == null) {
      throw new IllegalStateException("features can't be null");
    }
    sb = new StringBuilder();
  }

  @Override
  public void showDisplay(List<String> s) {
    if (s == null) {
      throw new IllegalStateException("features can't be null");
    }
    sb = new StringBuilder();
  }

  @Override
  public void setTopLabel(String s) {
    if (s == null) {
      throw new IllegalStateException("features can't be null");
    }
    sb = new StringBuilder();
  }
}


