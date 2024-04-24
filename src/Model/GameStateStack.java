package Model;

import Controller.MusicManager;

import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Stack;

public class GameStateStack {
  private Stack<GameState> states;
  private MusicManager musicManager;


  public GameStateStack() {
    this.states = new Stack<>();
    this.musicManager = new MusicManager();
  }
  public void addState(GameState state) {
    state.setSoundManager(musicManager);
    this.states.add(state);

  }

  public void backToPreviousState() {
    this.states.pop();
  }

  public void clearStack() {
    this.states.clear();
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
