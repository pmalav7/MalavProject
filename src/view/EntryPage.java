package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import milestone.ReadOnlyModel;
import milestone.WorldImpl;

/**
 * This class is used for adding players to the game.
 */

public class EntryPage extends JFrame implements WorldViewRef {


  private JButton addPlayerButton;
  private JButton startGameButton;
  private JPanel rightSidePanel;
  private HelpPage panel3;
  private WorldPanelNew leftSidePanel;
  private JPanel mainPage;

  private JLabel lblcapacity1;
  private JLabel lblname;
  private JTextField numCapacity;
  private JRadioButton rbComputer;
  private JRadioButton rbHuman;
  private JTextField txtName;
  private boolean isHuman;
  private ButtonGroup group;
  private GamePageRef panel;
  private JButton roomList;
  private JComboBox jc;

  private JLabel playerInfo;
  private WorldPanelNew worldPanelNew;
  private JButton capacityButton;
  private String[] capacity;
  private JMenu menu;
  private JMenuBar menuBar;
  private JMenuItem i1;
  private JMenuItem i2;
  private JMenuItem i3;
  private HomePageNew panel1;
  private boolean capacityPressed;
  private boolean locationPressed;
  private JLabel status;
  private String selected;
  private ReadOnlyModel m;
  private int counter;
  private JPanel msgBox;

  /**
   * The constructor of the class initializes the frame with all the required components.
   */

  public EntryPage() {

    initComponents();

  }

  /**
   * This class is initialized by 2 parameters.
   *
   * @param worldPanelNew refers to object of WorldPanelNew.
   * @param m             refers to object of ReadOnlyModel.
   */

  public EntryPage(WorldPanelNew worldPanelNew, ReadOnlyModel m) {
    if (worldPanelNew == null || m ==  null) {
      throw new IllegalStateException("worldpanel can't be null");
    }
    this.worldPanelNew = worldPanelNew;
    this.m = m;

    initComponents();
  }



  private void initComponents() {
    this.setLayout(new BorderLayout());

    rightSidePanel = new JPanel();
    mainPage = new JPanel();
    addPlayerButton = new JButton();
    addPlayerButton.setEnabled(false);
    startGameButton = new JButton();
    txtName = new JTextField();
    numCapacity = new JTextField();
    capacityPressed = false;
    locationPressed = false;

    rbHuman = new JRadioButton();
    rbComputer = new JRadioButton();

    lblname = new JLabel();
    lblcapacity1 = new JLabel();
    group = new ButtonGroup();
    this.add(rightSidePanel);
    roomList = new JButton("Location");
    isHuman = true;
    jc = new JComboBox<>();
    playerInfo = new JLabel();
    capacityButton = new JButton("Capacity");
    capacity = new String[1];
    menu = new JMenu();
    menuBar = new JMenuBar();
    status = new JLabel();
    msgBox = new JPanel();

    menu = new JMenu("Menu");
    i1 = new JMenuItem("Start new game");
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
    selected = "room";
    counter++;


    rightSidePanel.setBackground(new Color(161, 57, 65));
    rightSidePanel.setMaximumSize(new Dimension(153, 631));
    rightSidePanel.setMinimumSize(new Dimension(146, 599));
    rightSidePanel.setPreferredSize(new Dimension(170, 631));
    rightSidePanel.setLayout(new FlowLayout());

    lblname.setForeground(new Color(0, 0, 0));
    lblname.setText("Name");
    lblname.setBorder(new EmptyBorder(0, 0, 0, 0));
    rightSidePanel.add(lblname);
    rightSidePanel.validate();
    txtName.getAccessibleContext().setAccessibleName("Name");


    txtName.setColumns(10);
    txtName.setSize(20, 20);
    rightSidePanel.add(txtName);
    rightSidePanel.validate();


    rbHuman.setText("Human Player");
    rbHuman.setForeground(new Color(0, 0, 0));

    group.add(rbHuman);
    rightSidePanel.add(rbHuman);
    rbHuman.setSelected(true);
    rbHuman.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }
        isHuman = true;
      }
    });

    rbComputer.setText("Computer Player");
    rbComputer.setForeground(new Color(0, 0, 0));
    rightSidePanel.add(rbComputer);
    group.add(rbComputer);
    rightSidePanel.validate();
    rbComputer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }
        isHuman = false;
      }
    });

    capacityButton.setForeground(new Color(161, 57, 65));
    rightSidePanel.add(capacityButton);
    rightSidePanel.revalidate();

    roomList.setForeground(new Color(161, 57, 65));
    rightSidePanel.add(roomList);
    rightSidePanel.validate();

    addPlayerButton.setForeground(new Color(161, 57, 65));
    addPlayerButton.setText("Add Player");

    addPlayerButton.setActionCommand("Add Player");
    addPlayerButton.setSize(20, 20);
    addPlayerButton.setVisible(true);

    rightSidePanel.add(addPlayerButton);


    rightSidePanel.validate();


    startGameButton.setForeground(new Color(161, 57, 65));
    startGameButton.setText("Play");

    startGameButton.setSize(20, 20);
    rightSidePanel.add(startGameButton);
    rightSidePanel.validate();

    startGameButton.setEnabled(false);


    playerInfo.setVisible(true);
    rightSidePanel.add(playerInfo);
    rightSidePanel.validate();



    this.add(rightSidePanel, BorderLayout.EAST);
    this.add(new JScrollPane(mainPage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
    this.repaint();

    pack();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height;
    int width = screenSize.width;
    this.setSize(width / 2, height / 2);
    this.setMinimumSize(new Dimension(700, 500));


    this.setLocationRelativeTo(null);

    this.setVisible(true);


  }


  @Override
  public void setEchoOutput(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("String can't be null or empty");
    }
    if (panel != null) {
      panel.setEchoOutput(s);
    }

  }

  @Override
  public void clearInputString() {
    txtName.setText("");
    numCapacity.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


  @Override
  public void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalStateException("Features can't be null");
    }
    draw(f);

    final String[] room = new String[1];

    i1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }

        dispose();
        panel1 = new HomePageNew("Kill Dr. Lucky", m);
        panel1.setVisible(true);
        panel1.setFeatures(f);
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

    txtName.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }
        txtName.setText(txtName.getText());
      }
    });

    capacityButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }

        capacityPressed = true;
        if (capacityPressed && locationPressed) {
          addPlayerButton.setEnabled(true);
        }
        List<String> strings = new ArrayList<>();
        strings.add("0");
        strings.add("1");
        strings.add("2");
        showDisplay(strings);
        if (!Objects.equals(selected, "")) {
          capacity[0] = selected;
        }

      }
    });

    rbComputer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }
        isHuman = false;
      }
    });
    rbHuman.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }
        isHuman = true;
      }
    });
    roomList.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }

        f.showDisplay("P");
        locationPressed = true;
        if (capacityPressed && locationPressed) {
          addPlayerButton.setEnabled(true);
        }
        if (!Objects.equals(selected, "")) {
          room[0] = selected;
        }
      }
    });

    addPlayerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(txtName.getText());
        sb.append(",");
        sb.append(capacity[0]);
        sb.append(",");
        sb.append(room[0]);
        sb.append(",");
        sb.append(isHuman);
        String playerDetails = sb.toString();

        /*String playerDetails = txtName.getText() + "," + capacity[0]
                + "," + room[0] + "," + isHuman;*/
        f.validateInput("PLAYER", playerDetails);

        draw(f);
        playerInfo.setVisible(false);
        startGameButton.setEnabled(true);
        capacityPressed = false;
        locationPressed = false;
        addPlayerButton.setEnabled(false);
        clearInputString();
        status.setVisible(true);
      }
    });



    startGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("action event can't be null");
        }
        clearEntryPage();
        WorldPanelNew wpn = new WorldPanelNew(f, m);
        panel = new GamePageRef(wpn, m);
        panel.setVisible(true);
        panel.setFeatures(f);
        counter++;
      }
    });


  }

  @Override
  public void refresh() {
    Component[] componentList = rightSidePanel.getComponents();
    for (Component c : componentList) {
      if (c instanceof JComboBox) {
        rightSidePanel.remove(c);
      }
    }
    playerInfo.setVisible(false);
    rightSidePanel.revalidate();
    rightSidePanel.repaint();
    this.revalidate();
    this.repaint();
    if (panel != null) {
      panel.refresh();
    }

  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void register(WorldImpl w1) {

  }

  @Override
  public void handleException(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("String can't be null");
    }
    status.setSize(20, 50);
    status.setText(s);
    msgBox.add(status);
    msgBox.setBackground(new Color(243, 219, 116));


    this.add(msgBox, BorderLayout.SOUTH);

    int delay = 10000; //milliseconds
    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (evt == null) {
          throw new IllegalStateException("action event can't be null");
        }
        status.setVisible(false);
        msgBox.setVisible(false);

      }
    };
    new Timer(delay, taskPerformer).start();

    if (panel != null) {
      panel.handleException(s);
    }



  }

  @Override
  public void showDisplay(List<String> s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("List of string can't be null or empty");
    }

    if (counter == 1) {
      String[] items = s.toArray(new String[0]);
      Object selectedObject = JOptionPane.showInputDialog(null,
              "Your option", "Selection", JOptionPane.DEFAULT_OPTION,
              null, items, items[0]);
      if (selectedObject != null) {
        selected = selectedObject.toString();
      } else {
        selected = "";

      }
    } else {
      if (panel != null) {
        panel.showDisplay(s);
      }
    }
  }

  @Override
  public void setTopLabel(String s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("Set top label string cannot be null");
    }
    if (panel != null) {
      panel.setTopLabel(s);
    }
  }



  private void draw(Features f) {
    if (f == null) {
      throw new IllegalStateException("Features can't be null");
    }
    mainPage.removeAll();

    playerInfo.setVisible(false);

    leftSidePanel = new WorldPanelNew(f, m);

    leftSidePanel.setBackground(new Color(240, 225, 185));
    leftSidePanel.setForeground(new Color(161, 57, 65));
    leftSidePanel.setPreferredSize(new Dimension(1000, 1000));

    mainPage.add(leftSidePanel);

  }

  private void clearEntryPage() {
    this.dispose();
    this.revalidate();
  }


}
