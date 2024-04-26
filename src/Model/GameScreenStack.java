package Model;

import Controller.AudioManager;

import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Stack;

public class GameScreenStack {
  private final Stack<GameScreen> screens;
  private final AudioManager musicManager;
  private final AudioManager soundManager;


  public GameScreenStack() {
    this.screens = new Stack<>();
    this.musicManager = new AudioManager();
    this.soundManager = new AudioManager();
  }

  public void addScreen(GameScreen screen) {
      screen.setAudioManagers(musicManager, soundManager);
      this.screens.add(screen);
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
