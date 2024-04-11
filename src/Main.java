import Controller.Game;
import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      Game.init();
      Game.start();
    });
  }
}
