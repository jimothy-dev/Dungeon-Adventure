package View;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameManager {
  public static final int WIDTH = 800; //Should these be private and have getters?
  public static final int HEIGHT = 600;
  private final JFrame frame;
  private JPanel panel;

  public FrameManager() {
    frame = new JFrame("Dungeon Adventure");
    frame.setBounds(70, 70, 0, 0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
  }

  public void addPanel(JPanel panel){
    this.panel = panel;
    this.panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.panel.setFocusable(true);
    this.panel.requestFocusInWindow();
  }

  public void addKeyListener(KeyListener listener) {
        try {
      this.panel.addKeyListener(listener);
    } catch(NullPointerException e) {
      System.err.println("[WindowManager]: Error! Tried to add KeyListener before JPanel");
      System.exit(-1);
    }
  }

  public void createWindow() {
    this.frame.setContentPane(panel);
    this.frame.pack();
    this.frame.setVisible(true);
  }

  public static int getHeight() {
    return HEIGHT;
  }

  public static int getWidth() {
    return WIDTH;
  }
}
