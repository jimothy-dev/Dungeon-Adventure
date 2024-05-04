package Model;

import Controller.MusicManager;
import Controller.SoundEffectsManager;

import java.awt.Graphics;

/**
 * This class is the base for each game screen on an abstract level.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public abstract class GameScreen {

  /**
   * gameScreenStack field is the stack that game screens will be put on
   */
  protected GameScreenStack gameScreenStack;

  /**
   * musicManager is the manager implementing game screens can get music from
   */
  protected MusicManager musicManager;

  /**
   * soundEffectsManager is the manager the game screen gets sound effects from
   */
  protected SoundEffectsManager soundEffectsManager;

  /**
   * GameScreen constructor creates an instance of game screen and puts it
   * on the stack.
   *
   * @param manager is the game screen stack this game screen will be on
   */
  protected GameScreen(GameScreenStack manager) {
    this.gameScreenStack = manager;
  }

  /**
   * setSoundManager method sets the sound manager for implementing game screens
   *
   * @param musicManager is the music manager to be used
   */
  public void setSoundManager(MusicManager musicManager) {
    this.musicManager = musicManager;
  }

  /**
   * loop method for any looping behaviors in implementing game screens
   */
  protected abstract void loop();

  /**
   * render method is for diplaying graphics in implementing game screens
   * @param graphics
   */
  protected abstract void render(Graphics graphics);

  /**
   * keyPressed allows key pressed listening events to be managed
   *
   * @param keyCode is the code for the key pressed
   */
  protected abstract void keyPressed(int keyCode);

  /**
   * keyReleased allows key released listening events to be managed
   *
   * @param keyCode is the code for the key released
   */
  protected abstract void keyReleased(int keyCode);

  /**
   * playBackgroundMusic enables the playing of background music
   */
  protected abstract void playBackgroundMusic();

  /**
   * stopBackgoundMusic enables stopping of background music
   */
  protected abstract void stopBackgroundMusic();

  /**
   * playSoundEfect enables playing sound effects
   *
   * @param theEffectName is the name of the sound effect to be played
   */
  protected abstract void playSoundEffect(String theEffectName);

}
