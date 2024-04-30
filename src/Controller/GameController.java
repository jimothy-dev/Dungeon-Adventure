package Controller;

import Model.GameScreenStack;
import View.MainMenu;
import View.FrameManager;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameController {
  private static GameScreenStack gameScreenStack;
  private static FrameManager frameManager;
  private static Timer timer;

  public static void init() {
    gameScreenStack = new GameScreenStack();
    frameManager = new FrameManager();
    timer = new Timer(20, new MainGameLoop());
  }

  public static void start() {
    gameScreenStack.addScreen(new MainMenu(gameScreenStack));
    frameManager.addPanel(new GameScreen());
    frameManager.addKeyListener(new Keyboard());
    frameManager.createWindow();
    timer.start();
  }

  private static class MainGameLoop implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent theEvent){
      gameScreenStack.loop();
    }
  }

  private static class GameScreen extends JPanel {
    @Override
    protected void paintComponent(Graphics theGraphics) {
      super.paintComponent(theGraphics);
      gameScreenStack.render(theGraphics);
      repaint();
    }
  }

  private static class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent key) {
    }

    @Override
    public void keyPressed(KeyEvent key) {
      gameScreenStack.keyPressed(key.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent key) {
      gameScreenStack.keyReleased(key.getKeyCode());
    }
  }
}
