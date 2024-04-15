package View;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowManager {
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  private JFrame frame;
  private JPanel panel;

  public WindowManager() {
    frame = new JFrame("FILL TITLE HERE");
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
}
