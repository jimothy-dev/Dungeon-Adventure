package Model;

import Controller.AudioManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
  private Font retroGamingFont;
  protected AudioManager musicManager;
  protected AudioManager soundManager;


  /**
   * GameScreen constructor creates an instance of game screen and puts it
   * on the stack.
   *
   * @param manager is the game screen stack this game screen will be on
   */
  protected GameScreen(GameScreenStack manager) {
    this.gameScreenStack = manager;
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Font loadedFont = Font.createFont(Font.TRUETYPE_FONT,
              Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Assets/fonts/Retro Gaming.ttf")));
      ge.registerFont(loadedFont);
      int desiredFontSize = 40;
      retroGamingFont = loadedFont.deriveFont((float) desiredFontSize);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
    musicManager = new AudioManager();
    soundManager = new AudioManager();
    musicManager.loadAllAudio(new File("src/Assets/BackgroundMusic"));
    soundManager.loadAllAudio(new File("src/Assets/SoundEffects"));
  }

  public Font getCustomFont() {
    return retroGamingFont;
  }

  protected void playBackgroundMusic(String musicKey) {
    if (musicManager != null) {
      musicManager.playAudio(musicKey, true);
    } else {
      System.out.println("DEBUG: null music file");
    }
  }

  /**
   * setSoundManager method sets the sound manager for implementing game screens
   *
   * @param musicManager is the music manager to be used
   */
  public void setSoundManager(MusicManager musicManager) {
    this.musicManager = musicManager;
  protected void stopBackgroundMusic() {
    if (musicManager != null) {
      musicManager.stopAudio();
    }
  }

  protected void playSoundEffect(String effectKey) {
    if (soundManager != null) {
      soundManager.playAudio(effectKey, false);
    }
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
}
