package view;

import controller.Features;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileSystemView;
import milestone.ReadOnlyModel;
import milestone.WorldImpl;

/**
 * This class represents the home page which appears first.
 */
public class HomePageNew extends JFrame implements WorldViewRef, FileTransmitter {

  private JButton btnstart;
  private JPanel jPanel1;
  private JLabel lbl1stcontributor;
  private JLabel lbl2ndcontributor;
  private JLabel lbl3rdcontributor;
  private JLabel lblwelcome;
  private JLabel fileName;
  private JButton saveButton;
  private JButton openButton;
  private HelpPage panel1;

  private EntryPage panel;
  private JMenu menu;
  private JMenuBar menuBar;
  private JMenuItem i1;
  private JMenuItem i2;
  private JMenuItem i3;

  private ArrayList<WorldImpl> observer;
  private ReadOnlyModel m;

  /**
   * The constructor of the class initializes the frame with all the required components.
   */
  public HomePageNew(String windowTitle, ReadOnlyModel m) {
    super(windowTitle);
    if (windowTitle == null || windowTitle.isEmpty()) {
      throw new IllegalStateException("windowtitle empty");
    }
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(null);
    this.m = m;
    initComponents();
    observer = new ArrayList<>();
  }

  private void initComponents() {

    jPanel1 = new JPanel();
    lblwelcome = new JLabel();
    lbl1stcontributor = new JLabel();
    lbl2ndcontributor = new JLabel();
    lbl3rdcontributor = new JLabel();
    btnstart = new JButton();
    menu = new JMenu();
    menuBar = new JMenuBar();
    saveButton = new JButton("save");
    openButton = new JButton("open");
    fileName = new JLabel("No File Selected");
    clearHomePage();


    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setBackground(new Color(161, 57, 65));

    lblwelcome.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
    lblwelcome.setForeground(new Color(243, 219, 116));
    lblwelcome.setText("Welcome");
    lblwelcome.setAlignmentY(0.0F);
    lblwelcome.setHorizontalAlignment(JLabel.CENTER);

    lbl1stcontributor.setText("MALAV");
    lbl1stcontributor.setForeground(new Color(240, 225, 185));

    lbl2ndcontributor.setText("PROF");
    lbl2ndcontributor.setForeground(new Color(240, 225, 185));

    lbl3rdcontributor.setText("ANEESH");
    lbl3rdcontributor.setForeground(new Color(240, 225, 185));

    btnstart.setBackground(new Color(252, 246, 245));
    btnstart.setForeground(new Color(161, 57, 65));
    btnstart.setFont(new Font("Segoe UI", 1, 12));
    btnstart.setText("Start");
    menu = new JMenu("Menu");
    i1 = new JMenuItem("Load new game");
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

    saveButton.setBackground(new Color(252, 246, 245));
    saveButton.setVisible(true);
    jPanel1.add(saveButton);
    openButton.setVisible(true);
    jPanel1.add(openButton);


    GroupLayout groupLayout = new GroupLayout(jPanel1);
    jPanel1.setLayout(groupLayout);
    groupLayout.setAutoCreateGaps(true);
    groupLayout.setAutoCreateContainerGaps(true);

    groupLayout.setHorizontalGroup(// mods by aneesh
            groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblwelcome)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl1stcontributor, GroupLayout.PREFERRED_SIZE,
                                    61, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl3rdcontributor, GroupLayout.PREFERRED_SIZE,
                                    61, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl2ndcontributor, GroupLayout.PREFERRED_SIZE,
                                    61, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnstart)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(saveButton)
                    .addComponent(openButton)
    );
    groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap(100, Short.MAX_VALUE)
                            .addComponent(lblwelcome)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                    150, Short.MAX_VALUE)
                            .addGroup(groupLayout.createParallelGroup(
                                            GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl1stcontributor)
                                    .addComponent(lbl3rdcontributor)
                                    .addComponent(lbl2ndcontributor))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                    150, Short.MAX_VALUE)
                            .addComponent(btnstart)
                            .addComponent(saveButton)
                            .addComponent(openButton)
                            .addContainerGap(100, Short.MAX_VALUE))
    );

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    saveButton.setVisible(false);
    openButton.setVisible(false);
    fileName.setVisible(false);
    pack();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height;
    int width = screenSize.width;
    this.setSize(width / 2, height / 2);

    this.setLocationRelativeTo(null);

    this.setVisible(true);
  }


  @Override
  public void setEchoOutput(String s) {
    if (s == null) {
      throw new IllegalStateException("Thrown from homepage");
    }
    if (panel != null) {
      panel.setEchoOutput(s);
    }

  }

  @Override
  public void clearInputString() {
    panel.clearInputString();
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


  @Override
  public void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalStateException("features cannot be null");
    }
    i1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("Action event cannot be null");
        }
        openButton.setVisible(true);
        fileName.setVisible(true);
        refresh();
      }
    });
    i2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("Action event cannot be null");
        }
        panel1 = new HelpPage("Help Page");

      }
    });
    i3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("Action event cannot be null");
        }
        dispose();

      }
    });
    btnstart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("Action event cannot be null");
        }
        if (e.getSource() == btnstart || e.getSource() == i1) {

          clearHomePage();

          WorldPanelNew wpn = new WorldPanelNew(f, m);
          panel = new EntryPage(wpn, m);
          panel.setVisible(true);
          panel.setFeatures(f);
        }
      }
    });
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("Action event cannot be null");
        }
        String com = e.getActionCommand();
        if (Objects.equals(com, "save")) {
          JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
          int r = j.showSaveDialog(null);
          if (r == JFileChooser.APPROVE_OPTION) {
            fileName.setText(j.getSelectedFile().getAbsolutePath());
          } else {
            fileName.setText("The user cancelled the operation");
          }
        } else {
          JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
          int r = j.showOpenDialog(null);
          if (r == JFileChooser.APPROVE_OPTION) {
            fileName.setText(j.getSelectedFile().getAbsolutePath());
          } else {
            fileName.setText("The user cancelled the operation");
          }
        }
      }
    });
    openButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e == null) {
          throw new IllegalStateException("Action event cannot be null");
        }
        String com = e.getActionCommand();
        if (Objects.equals(com, "save")) {
          JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
          int r = j.showSaveDialog(null);
          if (r == JFileChooser.APPROVE_OPTION) {
            fileName.setText(j.getSelectedFile().getAbsolutePath());

            notifyObserver();
          } else {
            fileName.setText("The user cancelled the operation");
          }
        } else {
          JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
          int r = j.showOpenDialog(null);
          if (r == JFileChooser.APPROVE_OPTION) {
            fileName.setText(j.getSelectedFile().getAbsolutePath());

            notifyObserver();
          } else {
            fileName.setText("The user cancelled the operation");
          }
        }
      }
    });

  }

  @Override
  public void refresh() {
    this.repaint();
    if (panel != null) {
      panel.refresh();
    }
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  private void clearHomePage() {
    this.dispose();
    this.revalidate();
  }

  @Override
  public void register(WorldImpl obs) {
    if (obs == null) {
      throw new IllegalStateException("world object cannot be null");
    }
    observer.add(obs);
  }

  @Override
  public void handleException(String s) {
    if (s == null) {
      throw new IllegalStateException("String cannot be null");
    }
    if (panel != null) {
      panel.handleException(s);
    }

  }

  @Override
  public void showDisplay(List<String> s) {
    if (s == null || s.isEmpty()) {
      throw new IllegalStateException("List of string is empty or null");
    }
    if (panel != null) {
      panel.showDisplay(s);
    }
  }

  @Override
  public void setTopLabel(String s) {
    if (panel != null) {
      panel.setTopLabel(s);
    }
  }

  @Override
  public void notifyObserver() {
    if (!fileName.getText().contains("cancelled")) {
      for (WorldImpl obs : observer) {
        obs.update(fileName.getText());
      }
    }
  }
}

