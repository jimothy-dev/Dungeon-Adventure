package Model;

import Controller.AudioManager;

import java.awt.Graphics;

public abstract class GameState {
  protected GameStateStack gameStateManager;
  protected AudioManager musicManager;
  protected AudioManager soundManager;

  protected  GameState(GameStateStack manager) {
    this.gameStateManager = manager;
  }

  public void setAudioManagers(AudioManager musicManager, AudioManager soundManager) {
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
