package Model;

import Controller.AudioManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class GameScreen {
  protected GameScreenStack gameScreenStack;
  private Font retroGamingFont;
  protected AudioManager musicManager;
  protected AudioManager soundManager;


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

  protected abstract void loop();
  protected abstract void render(Graphics graphics);
  protected abstract void keyPressed(int keyCode);
  protected abstract void keyReleased(int keyCode);
}
