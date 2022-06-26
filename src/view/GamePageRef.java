package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import milestone.ReadOnlyModel;
import milestone.WorldImpl;
import milestone.WorldPlayerInterface;

/**
 * This class represents the game page where all the action will take place.
 */
public class GamePageRef extends JFrame implements WorldViewRef {
  private static final long serialVersionUID = -2179965453492637485L;

  private JLabel moveButton;
  private JLabel movePetButton;
  private JLabel lookAroundButton;
  private JLabel clueButton;
  private JLabel attackButton;
  private JLabel pickItemButton;
  private JPanel mainPage;
  private JPanel rightPanel;
  private HelpPage panel3;
  private WorldPanelNew leftPanel;
  private JLabel lblname;

  private JRadioButton rbComputer;
  private JRadioButton rbHuman;
  private JTextField txtName;
  private JTextField txtRoomName;
  private String name;
  private int capacity;
  private String roomName;
  private WorldPlayerInterface model;
  private boolean isHuman = true;
  private Features swc;
  private JRadioButton[] available;
  private JLabel infoDisplay;
  private WorldPlayerInterface w1;
  private Graphics g;

  private JTextArea getInfoDisplay;
  private JPanel leftTop;

  private int prev;
  private int count;
  private JTextArea status;
  private JPanel msgBox;
  private JMenu menu;
  private JMenuBar menuBar;
  private JMenuItem i1;
  private JMenuItem i2;
  private JMenuItem i3;
  private EntryPage panel1;
  private WorldPanelNew worldPanelNew;
  private JLabel playerInfo;
  private String help;
  private String selected;
  private ReadOnlyModel m;


  /**
   * The constructor of the class initializes the frame with all the required components.
   */

  public GamePageRef() {
    initComponents();
    this.setFocusable(true);
    setLocation(200, 100);
  }

  /**
   * This class is initialized by 2 parameters.
   * @param worldPanelNew refers to object of WorldPanelNew.
   * @param m refers to object of ReadOnlyModel.
   */

  public GamePageRef(WorldPanelNew worldPanelNew, ReadOnlyModel m) {
    if (worldPanelNew == null || m == null) {
      throw new IllegalStateException("WorldPane object cannot be null");
    }
    this.worldPanelNew = worldPanelNew;
    this.setFocusable(true);
    initComponents();
    this.m = m;
  }


  private void initComponents() {
    this.setLayout(new BorderLayout());
    mainPage = new JPanel();
    available = new JRadioButton[20];
    leftTop = new JPanel();
    rightPanel = new JPanel();
    moveButton = new JLabel();
    movePetButton = new JLabel();
    lookAroundButton = new JLabel();
    clueButton = new JLabel();
    attackButton = new JLabel();
    pickItemButton = new JLabel();
    txtName = new JTextField();

    txtRoomName = new JTextField();
    rbHuman = new JRadioButton();
    rbComputer = new JRadioButton();
    lblname = new JLabel();

    infoDisplay = new JLabel();
    getInfoDisplay = new JTextArea();
    status = new JTextArea();
    msgBox = new JPanel();
    menu = new JMenu();
    menuBar = new JMenuBar();
    playerInfo = new JLabel();


    menu = new JMenu("Menu");
    i1 = new JMenuItem("Restart game");
    i2 = new JMenuItem("Help");
    i3 = new JMenuItem("Quit");
    menu.add(i1);
    menu.add(i2);
    menu.add(i3);
    menuBar.add(menu);
    menu.setVisible(true);
    menuBar.setVisible(true);
    menuBar.setBackground(new Color(240, 225, 185));
    setJMenuBar(menuBar);


    rightPanel.setBackground(new Color(161, 57, 65));
    rightPanel.setMaximumSize(new Dimension(160, 631));
    rightPanel.setMinimumSize(new Dimension(160, 599));
    rightPanel.setPreferredSize(new Dimension(170, 631));
    rightPanel.setLayout(new GridLayout(9, 1, 0, 7));

    clueButton.setForeground(new Color(240, 225, 185));
    clueButton.setBorder(new EmptyBorder(0, 14, 0, 0));
    clueButton.setText("Press C for target clue");

    clueButton.setSize(20, 20);
    rightPanel.add(clueButton);
    rightPanel.validate();

    moveButton.setForeground(new Color(240, 225, 185));
    moveButton.setBorder(new EmptyBorder(0, 14, 0, 0));
    moveButton.setText("Press M to move player");

    moveButton.setSize(20, 20);
    rightPanel.add(moveButton);
    rightPanel.validate();

    lblname.setForeground(Color.WHITE);
    lblname.setText("Room Name:");
    lblname.setVisible(false);

    txtName.getAccessibleContext().setAccessibleName("Name");
    txtName.setColumns(10);
    txtName.setSize(20, 20);
    txtName.setVisible(false);



    movePetButton.setForeground(new Color(240, 225, 185));
    movePetButton.setBorder(new EmptyBorder(0, 14, 0, 0));
    movePetButton.setText("Press P to move pet");

    rightPanel.add(movePetButton);
    rightPanel.validate();

    lookAroundButton.setForeground(new Color(240, 225, 185));
    lookAroundButton.setBorder(new EmptyBorder(0, 14, 0, 0));
    lookAroundButton.setText("Press L to look around");

    lookAroundButton.setSize(20, 20);
    rightPanel.add(lookAroundButton);
    rightPanel.validate();

    attackButton.setForeground(new Color(240, 225, 185));
    attackButton.setBorder(new EmptyBorder(0, 14, 0, 0));
    attackButton.setText("Press A to attack target");

    attackButton.setSize(20, 20);
    rightPanel.add(attackButton);
    rightPanel.validate();

    pickItemButton.setForeground(new Color(240, 225, 185));
    pickItemButton.setBorder(new EmptyBorder(0, 14, 0, 0));
    pickItemButton.setText("Press I to pick item");

    pickItemButton.setSize(20, 20);
    rightPanel.add(pickItemButton);
    rightPanel.validate();

    playerInfo.setVisible(true);
    rightPanel.add(playerInfo);
    rightPanel.validate();


    this.add(rightPanel, BorderLayout.EAST);
    this.add(new JScrollPane(mainPage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);


    pack();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height;
    int width = screenSize.width;
    this.setSize(width / 2, height / 2);

    this.setMinimumSize(new Dimension(700, 500));

    this.setLocationRelativeTo(null);

  }




  @Override
  public void setTopLabel(String s) {
    getInfoDisplay.setText(s);
  }

  @Override
  public void setEchoOutput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("String cannot be empty");
    }

    status.setText(s);
    dialog(s);

  }

  @Override
  public void clearInputString() {
    status.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
  }


  @Override
  public void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalStateException("Feature cannot be null");
    }
    draw(f);
    f.showDisplay("C"); //clue is persistent

    i1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }

        dispose();

        panel1 = new EntryPage(worldPanelNew, m);
        panel1.setVisible(true);
        panel1.setFeatures(f);
        f.showDisplay("RESTART");

        refresh();
      }
    });
    i2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }
        panel3 = new HelpPage("Help Page");
      }
    });

    i3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }
        dispose();
      }
    });

    f.showDisplay("CP");

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
        if (e == null) {
          throw new IllegalStateException("key event can't be null");
        }
        if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {

          refresh();

          prev = count;
          count++;

          draw(f);

          playerInfo.setVisible(false);
        }
        if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
          refresh();
          draw(f);



          f.showDisplay("I");

          if (!Objects.equals(selected, "")) {
            f.validateInput("I", selected);
          }
          f.showDisplay("CP");
          draw(f);
          playerInfo.setVisible(false);
        }
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {

          refresh();
          draw(f);

          f.showDisplay("A");
          if (!Objects.equals(selected, "")) {
            f.validateInput("A", selected);
          }

          draw(f);

          f.showDisplay("CP");

          playerInfo.setVisible(false);

        }
        if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
          refresh();
          draw(f);

          f.showDisplay("P");
          if (!Objects.equals(selected, "")) {
            f.validateInput("P", selected);
          }
          draw(f);
          f.showDisplay("CP");

          playerInfo.setVisible(false);
        }
        if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
          refresh();
          draw(f);

          f.showDisplay("L");

          draw(f);

          f.showDisplay("CP");

          playerInfo.setVisible(false);
        }
        if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
          refresh();
          draw(f);

          f.showDisplay("C");

          playerInfo.setVisible(false);

        }
      }
    });


  }

  @Override
  public void refresh() {
    Component[] componentList = rightPanel.getComponents();
    for (Component c : componentList) {
      if ((c instanceof JRadioButton) || (c instanceof JComboBox) || (c instanceof JTextField)) {
        rightPanel.remove(c);
      }
    }
    rightPanel.revalidate();
    rightPanel.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void register(WorldImpl w1) {
    if (w1 == null) {
      throw new IllegalStateException("WorldImpl object can't be null");
    }
  }

  @Override
  public void handleException(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("String cannot be empty or null");
    }
    JDialog d = new JDialog(this, "Error");
    JLabel l = new JLabel(s);
    JPanel p = new JPanel();
    if (s.contains("Pet")) {

      dialog(s);

    } else {
      JButton jb = new JButton("Close");
      jb.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (e == null) {
            throw new IllegalStateException("action event can't be null");
          }
          if (s.contains("dead") || s.contains("moves")) {
            dispose();
          } else {
            d.dispose();
          }
        }
      });
      jb.setVisible(true);
      p.add(l);
      p.add(jb);
      d.add(p);
      d.setSize(150, 100);
      d.setVisible(true);
    }
  }

  @Override
  public void showDisplay(List<String> s) {
    if (s.isEmpty()) {
      handleException("No items for pickup");
    }
    String[] items = s.toArray(new String[0]);
    Object selectedObject = JOptionPane.showInputDialog(null,
            "Your option", "Selection", JOptionPane.DEFAULT_OPTION,
            null, items, items[0]);
    if (selectedObject != null) {
      selected = selectedObject.toString();
    } else {
      selected = "";
    }
  }


  private void draw(Features f) {
    if (f == null) {
      throw new IllegalStateException("features cannot be null");
    }
    mainPage.removeAll();
    leftPanel = new WorldPanelNew(f, m);


    leftPanel.add(leftTop);
    if (count == prev + 1) {
      leftPanel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          if (e == null) {
            throw new IllegalStateException("mouse event can't be null");
          }
          int r = e.getX();
          int c = e.getY();
          String coordinates = String.valueOf(r) + "," + String.valueOf(c);
          f.validateInput("MOVE", coordinates);
          prev++;
          int i = 1;
          int j = 0;
          while (j < i) {
            draw(f);
            f.showDisplay("CP");
            j++;
          }
        }
      });
    }

    leftPanel.setBackground(new Color(240, 225, 185));
    leftPanel.setForeground(new Color(161, 57, 65));
    leftPanel.setPreferredSize(new Dimension(500, 631));

    getInfoDisplay.setSize(100, 50);
    getInfoDisplay.setBackground(new Color(80, 25, 30));
    getInfoDisplay.setForeground(new Color(243, 219, 116));
    getInfoDisplay.setColumns(this.getWidth() / 15);
    getInfoDisplay.setLineWrap(true);
    getInfoDisplay.setWrapStyleWord(true);

    getInfoDisplay.setVisible(true);
    leftTop.setBackground(new Color(80, 25, 30));
    leftTop.setForeground(new Color(243, 219, 116));
    leftTop.setSize(500, 30);
    leftTop.setVisible(true);
    leftTop.add(getInfoDisplay);


    this.add(leftTop, BorderLayout.NORTH);

    leftPanel.validate();
    leftPanel.setPreferredSize(new Dimension(1000, 1000));

    mainPage.add(leftPanel);
  }


  private void dialog(String stat) {
    if (stat == null) {
      throw new IllegalStateException("Stat ");
    }

    if (stat == null || stat.isEmpty()) {
      throw new IllegalStateException("String cannot be empty or null");
    }
    status.setSize(20, 50);
    status.setText(stat);

    status.setBackground(new Color(243, 219, 116));
    status.setForeground(new Color(161, 57, 65));
    status.setColumns(this.getWidth() / 15);

    status.setLineWrap(true);
    status.setWrapStyleWord(true);

    JScrollPane scrollPane = new JScrollPane(status, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    msgBox.add(status);
    msgBox.setBackground(new Color(243, 219, 116));


    msgBox.setPreferredSize(new Dimension(50, 70));
    this.add(msgBox, BorderLayout.SOUTH);
    this.revalidate();

  }
}

