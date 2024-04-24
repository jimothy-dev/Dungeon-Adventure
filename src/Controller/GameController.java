package Controller;

import Model.GameStateStack;
import View.MainMenu;
import View.WindowManager;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameController {
  //This class currently handles both the game loop and window management, which could be separated
  private static GameStateStack gameStateStack;
  private static WindowManager windowManager;
  private static Timer timer;
  private static MusicManager musicManager;
  private static SoundEffectsManager soundEffectsManager;

  public static void init() {
    gameStateStack  = new GameStateStack();
    windowManager = new WindowManager();
    timer = new Timer(20, new MainGameLoop());
    musicManager = new MusicManager();
    soundEffectsManager = new SoundEffectsManager();
    musicManager.loadAllAudio(new File("src/Assets/BackgroundMusic"));
    soundEffectsManager.loadAllAudio(new File("src/Assets/SoundEffects"));
  }

  public static void start() {
    gameStateStack.addState(new MainMenu(gameStateStack, musicManager, soundEffectsManager));
    windowManager.addPanel(new GameScreen());
    windowManager.addKeyListener(new Keyboard());
    windowManager.createWindow();
    timer.start();
  }

  private static class MainGameLoop implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent theEvent){
      gameStateStack.loop();
    }
  }

  private static class GameScreen extends JPanel {
    @Override
    protected void paintComponent(Graphics theGraphics) {
      super.paintComponent(theGraphics);
      gameStateStack.render(theGraphics);
      repaint();
    }
  }

  private static class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent key) {
    }

    @Override
    public void keyPressed(KeyEvent key) {
      gameStateStack.keyPressed(key.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent key) {
      gameStateStack.keyReleased(key.getKeyCode());
    }
  }
}
