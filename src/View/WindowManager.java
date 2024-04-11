package View;

import javax.swing.JFrame;

public class WindowManager {
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  private static JFrame frame;

  public WindowManager() {
    frame = new JFrame("FILL TITLE HERE");
    frame.setBounds(70, 70, WIDTH, HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
  }

  public static void createWindow() {

    frame.setVisible(true);
  }
}
