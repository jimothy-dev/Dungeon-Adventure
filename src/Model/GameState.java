package Model;

import Controller.MusicManager;
import Controller.SoundEffectsManager;

import java.awt.Graphics;

public abstract class GameState {
  protected GameStateStack gameStateManager;
  protected MusicManager musicManager;
  protected SoundEffectsManager soundEffectsManager;

  protected  GameState(GameStateStack manager) {
    this.gameStateManager = manager;
  }

  public void setSoundManager(MusicManager musicManager) {
    this.musicManager = musicManager;
  }

  protected abstract void loop();
  protected abstract void render(Graphics graphics);
  protected abstract void keyPressed(int keyCode);
  protected abstract void keyReleased(int keyCode);

  protected abstract void playBackgroundMusic();
  protected abstract void stopBackgroundMusic();
  protected abstract void playSoundEffect(String theEffectName);

}
