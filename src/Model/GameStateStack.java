package Model;

import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Stack;

public class GameStateStack {
  private Stack<GameState> states;

  public GameStateStack() {
    this.states = new Stack<>();
  }
  public void addState(GameState state) {
    this.states.add(state);
  }

  public void backToPreviousState() {
    this.states.pop();
  }

  public void clearStack() {
    this.states.clear();
  }

  public void init() {
    this.states.peek().init();
  }

  public void loop() {
    try {
      this.states.peek().loop();
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  public void render(Graphics graphics) {
    try {
      this.states.peek().render(graphics);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  public void keyPressed(int keyCode) {
    try {
      this.states.peek().keyPressed(keyCode);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  public void keyReleased(int keyCode) {
    try {
      this.states.peek().keyReleased(keyCode);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }
}
