import Controller.GameController;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GameController.init();
      GameController.start();
    });
  }
}
