package Model;

import Controller.MusicManager;
import Controller.SoundEffectsManager;

import java.awt.Graphics;

public abstract class GameScreen {
  protected GameScreenStack gameScreenStack;
  protected MusicManager musicManager;
  protected SoundEffectsManager soundEffectsManager;

  protected GameScreen(GameScreenStack manager) {
    this.gameScreenStack = manager;
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
