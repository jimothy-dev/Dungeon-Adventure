package Model;

import Controller.AudioManager;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * This class is the base for each game screen on an abstract level.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public abstract class GameScreen {

  private final AudioManager mySoundManager;
  private final AudioManager myMusicManager;
  /**
   * gameScreenStack field is the stack that game screens will be put on
   */
  protected GameScreenStack myGameScreenStack;
  private Font myRetroGamingFont;



  /**
   * GameScreen constructor creates an instance of game screen and puts it
   * on the stack.
   *
   * @param manager is the game screen stack this game screen will be on
   */
  protected GameScreen(GameScreenStack manager) {
    myGameScreenStack = manager;
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Font loadedFont = Font.createFont(Font.TRUETYPE_FONT,
              Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Assets/fonts/Retro Gaming.ttf")));
      ge.registerFont(loadedFont);
      int desiredFontSize = 40;
      myRetroGamingFont = loadedFont.deriveFont((float) desiredFontSize);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
    myMusicManager = myGameScreenStack.getMusicManager();
    mySoundManager = myGameScreenStack.getSoundManager();

  }

  public Font getCustomFont() {
    return myRetroGamingFont;
  }

  protected void playBackgroundMusic(String musicKey) {
    if (myMusicManager != null) {
      myMusicManager.playAudio(musicKey, true);
    } else {
      System.out.println("DEBUG: null music file");
    }
  }

  protected void playSoundEffect(String effectKey) {
    if (mySoundManager != null) {
      mySoundManager.playAudio(effectKey, false);
    }
  }

  protected void stopBackgroundMusic() {
    mySoundManager.stopAudio();
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
