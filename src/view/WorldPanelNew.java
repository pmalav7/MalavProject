package view;

import controller.Features;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import milestone.ReadOnlyModel;
import milestone.SpaceImpl;

/**
 * This class represents the map where player movement takes place.
 */

public class WorldPanelNew extends JPanel {

  private final Features w1;
  private final Map<Shape, String> shapes;
  private JDialog playerInfo;
  private ArrayList<EntryPage> observers;
  private ArrayList<GamePageRef> observers1;
  private JLabel info;
  private ReadOnlyModel readOnlyModel;

  /**
   * This class is initialized by 2 parameters.
   *
   * @param m refers to object of Feature interface.
   * @param r refers to object of ReadOnlyModel.
   */


  public WorldPanelNew(Features m, ReadOnlyModel r) {
    this.w1 = m;
    shapes = new HashMap<>();
    observers = new ArrayList<>();
    info = new JLabel();
    observers1 = new ArrayList<>();
    this.readOnlyModel = r;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    java.util.List<String> coordinates;
    coordinates = readOnlyModel.getCoordinates();
    int xcoor = 0;
    int ycoor = 0;
    xcoor = Integer.parseInt(coordinates.get(0));
    ycoor = Integer.parseInt(coordinates.get(1));

    for (int i = 3; i < coordinates.size(); i += 5) {
      int leftCoor = Integer.parseInt(coordinates.get(i));
      int topCoor = Integer.parseInt(coordinates.get(i + 1));
      int rightCoor = Integer.parseInt(coordinates.get(i + 2));
      int bottomCoor = Integer.parseInt(coordinates.get(i + 3));
      String rname = coordinates.get(i + 4);
      g2d.setColor(new Color(161, 57, 65));
      g2d.drawRect(topCoor * 20, leftCoor * 20,
              (bottomCoor - topCoor + 1) * 20,
              (rightCoor - leftCoor + 1) * 20);
      if (SpaceImpl.playerRoom != null) {
        Map<String, String> pr;
        pr = SpaceImpl.playerRoom;
        List<String> values = new ArrayList<>(pr.values());
        for (int j = 0; j < values.size(); j++) {
          if (Objects.equals(values.get(j), rname)) {
            int count = 0;
            for (Map.Entry<String, String> entry : pr.entrySet()) {
              if (entry.getValue().equals(rname)) {
                count++;
                g2d.setColor(new Color(0, 0, 200));
                /*if (!rname.equals(w1.petLocation())) {*/
                if (!rname.equals(readOnlyModel.petLocation())) {
                  if (count == 1) {
                    g2d.drawString(entry.getKey(), (topCoor) * 20 + 30,
                            (leftCoor + 1) * 20 + 30);
                    Ellipse2D.Double circle = new Ellipse2D.Double(topCoor * 20 + 30,
                            leftCoor * 20 + 30, 10, 10);
                    shapes.put(new Ellipse2D.Double(topCoor * 20 + 30,
                            leftCoor * 20 + 30, 10, 10), entry.getKey());
                    g2d.setColor(new Color(0, 0, 100));
                    g2d.fill(circle);
                    //New code being added here
                    addMouseListener(new MouseListener() {
                      @Override
                      public void mouseClicked(MouseEvent e) {
                        for (Map.Entry<Shape, String> set : shapes.entrySet()) {
                          if (set.getKey().contains(e.getX(), e.getY())) {
                            //System.out.println("I m here");
                            //add(new JOptionPane(w1.playerInfo(set.getValue())));
                            //repaint();
                            //display(w1.playerInfo(set.getValue()));
                            //playerInfo.setText(w1.playerInfo(set.getValue()));
                            JLabel jl = new JLabel();
                            playerInfo = new JDialog();
                            //System.out.println(w1.playerInfo(set.getValue()));

                            //Commented by Malav 8:30 pm
                            /*jl.setText(w1.playerInfo(set.getValue()));
                            playerInfo.add(jl);
                            info.setText(w1.playerInfo(set.getValue()));
                            notifyObserver();*/

                            System.out.println(set.getValue());
                            w1.validateInput("PD", set.getValue());
                            w1.showDisplay("PD");
                            /*notifyObserver1();*/
                            //add(playerInfo);
                            //System.out.println(w1.playerInfo(set.getValue()));
                          }
                        }

                      }

                      @Override
                      public void mousePressed(MouseEvent e) {

                      }

                      @Override
                      public void mouseReleased(MouseEvent e) {

                      }

                      @Override
                      public void mouseEntered(MouseEvent e) {

                      }

                      @Override
                      public void mouseExited(MouseEvent e) {

                      }
                    });

                  } else {
                    g2d.drawString(entry.getKey(), (topCoor) * 20 + 30 + 10 * count,
                            (leftCoor + 1) * 20 + 30 + 10 * count);
                    Ellipse2D.Double circle = new Ellipse2D.Double(topCoor * 20 + 30 + 10 * count,
                            leftCoor * 20 + 30 + 10 * count, 10, 10);
                    shapes.put(new Ellipse2D.Double(topCoor * 20 + 30, leftCoor * 20 + 30,
                            10, 10), entry.getKey());
                    g2d.setColor(new Color(0, 0, 100));
                    g2d.fill(circle);
                    addMouseListener(new MouseListener() {
                      @Override
                      public void mouseClicked(MouseEvent e) {
                        //if (shapes.size() != 1) {
                        for (Map.Entry<Shape, String> set : shapes.entrySet()) {
                          if (set.getKey().contains(e.getX(), e.getY())) {
                            //display(w1.playerInfo(set.getValue()));
                            //playerInfo.setText(w1.playerInfo(set.getValue()));
                            JLabel jl = new JLabel();
                            playerInfo = new JDialog();

                            //Commented by Malav
                            /*jl.setText(w1.playerInfo(set.getValue()));
                            playerInfo.add(jl);
                            //add(playerInfo);
                            info.setText(w1.playerInfo(set.getValue()));
                            notifyObserver();*/

                            w1.validateInput("PD", set.getValue());
                            w1.showDisplay("PD");



                            /*notifyObserver1();*/
                            //System.out.println(w1.playerInfo(set.getValue()));
                            g2d.setColor(new Color(161, 57, 65));
                          }
                        }
                        //}

                      }

                      @Override
                      public void mousePressed(MouseEvent e) {

                      }

                      @Override
                      public void mouseReleased(MouseEvent e) {

                      }

                      @Override
                      public void mouseEntered(MouseEvent e) {

                      }

                      @Override
                      public void mouseExited(MouseEvent e) {

                      }
                    });
                  }
                }
                /*if (rname.equals(w1.petLocation())) {*/
                /*if (rname.equals(readOnlyModel.petLocation())) {
                  for (Map.Entry<String, String> entryNew : pr.entrySet()) {
                    if (readOnlyModel.whoseTurn() == entryNew.getKey()) {
                      g2d.drawString(entryNew.getKey(), (topCoor) * 20 + 30 + 20,
                              (leftCoor + 1) * 20 + 30);
                      Ellipse2D.Double circle = new Ellipse2D.Double(topCoor * 20 + 30 + 15,
                              leftCoor * 20 + 30, 10, 10);
                      g2d.setColor(new Color(0, 0, 100));
                      g2d.fill(circle);
                    }
                  }
                }*/
              }
            }
            //g2d.drawString();

            //g2d.drawOval(topCoor*20,leftCoor*20,5,5);

          }
        }

      }
      g2d.setColor(new Color(161, 57, 65));
      /*if (w1.isRunning()) {*/
      if (readOnlyModel.isGameRunning()) {
        /*if (Objects.equals(w1.whereIsTarget(), rname)) {*/
        if (Objects.equals(readOnlyModel.whereIsTarget(), rname)) {
          g2d.setColor(new Color(237, 37, 78));
          g2d.drawString("Target", (topCoor) * 20 + 20, (leftCoor + 1) * 20 + 20);
          Ellipse2D.Double circle = new Ellipse2D.Double(topCoor * 20 + 20,
                  leftCoor * 20 + 20, 12, 12);
          //g2d.setColor(Color.MAGENTA);
          g2d.fill(circle);
        }
      }

      g2d.setColor(new Color(1, 25, 54));
      g2d.drawString(rname, (topCoor) * 20, (leftCoor + 1) * 20);
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 12));
      /*File file1 = new File("res/MyWorld.png");
      try {
        ImageIO.write(bufferedImage, "png", file1);
      } catch (IOException e) {
        throw new IllegalArgumentException("There was a problem with drawing the game");
      }*/
    }

    /*if (!w1.isGameRunning()) {
      g2d.drawString("Game Over!", 100, 100);
    } else {
      g2d.drawString("It is turn of", 100, 100);
    }*/
  }


}
