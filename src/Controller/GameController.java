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

/**
 * GameController class handles game looping and window management
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class GameController {

  /**
   * myGameScreenStack field is the stack for all game screens
   */
  private static GameScreenStack myGameScreenStack;

  /**
   * myFrameManager field is the frame manager for all game screens
   */
  private static FrameManager myFrameManager;

  /**
   * myTimer field is for all game timing purposes
   */
  private static Timer myTimer;

  /**
   * init method initializes the fields
   */
  public static void init() {
    myGameScreenStack = new GameScreenStack();
    myFrameManager = new FrameManager();
    myTimer = new Timer(20, new MainGameLoop());
  }

  /**
   * start method starts the game, and initializes game play state
   */
  public static void start() {
    myGameScreenStack.addScreen(new MainMenu(myGameScreenStack));
    myFrameManager.addPanel(new GameScreen());
    myFrameManager.addKeyListener(new Keyboard());
    myFrameManager.createWindow();
    myTimer.start();
    myGameScreenStack.addScreen(new MainMenu(myGameScreenStack));
    myFrameManager.addPanel(new GameScreen());
    myFrameManager.addKeyListener(new Keyboard());
    myFrameManager.createWindow();
    myTimer.start();
  }

  /**
   * nested MainGameLoop class provides looping action listening
   */
  private static class MainGameLoop implements ActionListener {

    /**
     * actionPerformed method kicks off the loop action
     * in the game screen stack
     *
     * @param theEvent is the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent theEvent){
      myGameScreenStack.loop();
    }
  }

  /**
   * nested GameScreen class provides painting of game screens
   */
  private static class GameScreen extends JPanel {

    /**
     * paintComponents method displays on monitor the current
     * game stack rendering graphics
     *
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics theGraphics) {
      super.paintComponent(theGraphics);
      myGameScreenStack.render(theGraphics);
      repaint();
    }
  }

  /**
   * nested Keyboard class provides game key listeneing event handling
   */
  private static class Keyboard implements KeyListener {

    /**
     * keyTyped method handles key typed events from the game screen stack
     *
     * @param key the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent key) {
    }

    /**
     * keyPressed method handles key pressed events from the game screen stack
     *
     * @param key the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent key) {
      myGameScreenStack.keyPressed(key.getKeyCode());
    }

    /**
     * keyReleased method handles key released events from the game screen stack
     *
     * @param key the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent key) {
      myGameScreenStack.keyReleased(key.getKeyCode());
    }
  }
}
