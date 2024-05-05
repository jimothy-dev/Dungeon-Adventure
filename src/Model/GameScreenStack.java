package Model;

import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class is the stack object that holds each window during game play.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class GameScreenStack {

/**
 * screens field is the stack that holds all game screens in the game
 */
  private final Stack<GameScreen> screens;

/**
 * GameScreenStack constructor makes a game screen stack and
 * initializes the fields
 */
  public GameScreenStack() {
    this.screens = new Stack<>();
  }

/**
 * addState method puts a game screen on the stack
 *
 * @param state is the game screen to be added
 */
  public void addScreen(GameScreen screen) {
      this.screens.add(screen);
  }

  /**
   * backToPreviousState method pops off the top screen of the stack
   */
  public void backToPreviousState() {
    this.screens.pop();
  }

  /**
   * clearStack method empties the stack
   */
  public void clearStack() {
    this.screens.clear();
  }

  /**
   * loop method repeatedly looks at the screen at the top of the stack
   */
  public void loop() {
    try {
      this.screens.peek().loop();
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  /**
   * render method displays the graphics of the top screen
   *
   * @param graphics is the graphics from the screen at the top of the stack
   */
  public void render(Graphics graphics) {
    try {
      this.screens.peek().render(graphics);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  /**
   * keyPressed method looks for key press events
   * in the screen at the top of the stack.
   *
   * @param keyCode is the code for the key pressed
   */
  public void keyPressed(int keyCode) {
    try {
      this.screens.peek().keyPressed(keyCode);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  /**
   * keyReleased method looks for key released
   * events in the screen at the top of the stack
   *
   * @param keyCode is the code for the key released
   */
  public void keyReleased(int keyCode) {
    try {
      this.screens.peek().keyReleased(keyCode);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }
}
