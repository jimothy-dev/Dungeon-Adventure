package Model;

import Controller.AudioManager;

import java.awt.Graphics;

public abstract class GameScreen {
  protected GameScreenStack gameScreenStack;
  protected AudioManager musicManager;
  protected AudioManager soundManager;

  protected GameScreen(GameScreenStack manager) {
    this.gameScreenStack = manager;
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
