package Model;

import java.awt.Graphics;

public abstract class GameState {
  protected GameStateStack gameStateManager;
  protected  GameState(GameStateStack manager) {
    this.gameStateManager = manager;
  }

  protected abstract void loop();
  protected abstract void render(Graphics graphics);
  protected abstract void keyPressed(int keyCode);
  protected abstract void keyReleased(int keyCode);
}
