import Controller.Engine;
import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      Engine.init();
      Engine.start();
    });
  }
}
