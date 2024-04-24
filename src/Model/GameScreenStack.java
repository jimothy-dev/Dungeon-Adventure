package Model;

import Controller.MusicManager;

import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Stack;

public class GameScreenStack {
  private Stack<GameScreen> screens;
  private AudioManager musicManager;
  private AudioManager soundManager;


  public GameScreenStack() {
    this.screens = new Stack<>();
    this.musicManager = new AudioManager();
    this.soundManager = new AudioManager();
  }

  public void addState(GameScreen state) {
    state.setAudioManagers(musicManager, soundManager);
    this.screens.add(state);

  }

  public void backToPreviousState() {
    this.screens.pop();
  }

  public void clearStack() {
    this.screens.clear();
  }

  public void loop() {
    try {
      this.screens.peek().loop();
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  public void render(Graphics graphics) {
    try {
      this.screens.peek().render(graphics);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  public void keyPressed(int keyCode) {
    try {
      this.screens.peek().keyPressed(keyCode);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }

  public void keyReleased(int keyCode) {
    try {
      this.screens.peek().keyReleased(keyCode);
    } catch(EmptyStackException e) {
      System.err.println("[GameStateManager]: GameState stack is empty!");
      System.exit(-1);
    }
  }
}
