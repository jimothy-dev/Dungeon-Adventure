package Controller;

import Model.GameStateStack;
import View.MainMenu;
import View.WindowManager;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameController {
  private static GameStateStack gameStateStack;
  private static WindowManager windowManager;
  private static Timer timer;

  public static void init() {
    gameStateStack  = new GameStateStack();
    windowManager = new WindowManager();
    timer = new Timer(20, new MainGameLoop());
  }

  public static void start() {
    gameStateStack.addState(new MainMenu(gameStateStack));
    windowManager.addPanel(new GameScreen());
    windowManager.addKeyListener(new Keyboard());
    windowManager.createWindow();
    timer.start();
  }

  private static class MainGameLoop implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent arg){
      gameStateStack.loop();
    }
  }

  private static class GameScreen extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      gameStateStack.render(g);
      repaint();
    }
  }

  private static class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent key) {
      gameStateStack.keyPressed(key.getKeyCode());
    }
    @Override
    public void keyPressed(KeyEvent key) {
      gameStateStack.keyPressed(key.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent key) {
      gameStateStack.keyPressed(key.getKeyCode());
    }
  }
}
