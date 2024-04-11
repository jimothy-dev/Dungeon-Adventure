package View;

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
    frame.setBounds(70, 70, WIDTH, HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
  }

  public void addPanel(JPanel panel){
    this.panel = panel;
  }

  public void addKeyListener(KeyListener listener) {
    this.panel.addKeyListener(listener);
  }

  public void createWindow() {
    this.frame.add(panel);
    this.frame.setVisible(true);
  }
}
