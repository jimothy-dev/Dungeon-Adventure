package Model;

import java.awt.Graphics;

public abstract class GameState {
  protected GameStateStack gameStateManager;
  protected  GameState(GameStateStack manager) {
    this.gameStateManager = manager;
    this.init();
  }

  protected abstract void init();
  protected abstract void loop();
  protected abstract void render(Graphics graphics);
  protected abstract void keyPressed(int keyCode);
  protected abstract void keyReleased(int keyCode);
}
