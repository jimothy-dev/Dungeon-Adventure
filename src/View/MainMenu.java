package View;

import Controller.MusicManager;
import Controller.SoundEffectsManager;
import Model.GameState;
import Model.GameStateStack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

//Music: “Misty Dungeon”, from PlayOnLoop.com
//Licensed under Creative Commons by Attribution 4.0
public class MainMenu extends GameState {
  private static final String START_GAME = "Start Game";
  private static final String QUIT_GAME = "Quit Game";
  private static final String SELECT_EFFECT = "steelsword.wav";
  private static final String SWITCH_EFFECT = "215029__taira-komori__extracting_knife.wav";
  private static final String START_MENU_MUSIC = "POL-misty-dungeon-short.wav";
  private final String[] optionMenu;
  private int selected;
  private Image selectorImage;


  public MainMenu(GameStateStack manager, MusicManager theMM, SoundEffectsManager theSEM) {
      super(manager);
      this.optionMenu = new String[] {START_GAME, QUIT_GAME};
      this.musicManager = theMM;
      this.soundEffectsManager = theSEM;
      this.selected = 0;

      playBackgroundMusic();

    try {
      selectorImage = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void loop() {
  }

  @Override
  protected void render(Graphics graphics) {
    graphics.setColor(new Color(30, 30, 70));
    graphics.fillRect(0, 0, WindowManager.getWidth(), WindowManager.getHeight());
    graphics.setFont(new Font("Arial", Font.PLAIN, 25));
    int optionHeight = graphics.getFontMetrics().getHeight();
    int totalHeight = optionMenu.length * optionHeight;
    int yStart = (WindowManager.getHeight() - totalHeight) / 2;
    for (int i = 0; i < optionMenu.length; i++) {
      String optionText = optionMenu[i];
      int textWidth = graphics.getFontMetrics().stringWidth(optionText);
      int xStart = (WindowManager.getWidth() - textWidth) / 2;
      if (i == selected) {
        graphics.setColor(Color.magenta);
        graphics.drawImage(selectorImage, xStart - selectorImage.getWidth(null) - 5, yStart + i * optionHeight - optionHeight / 2, null);
      } else {
        graphics.setColor(Color.white);
      }
      graphics.drawString(optionText, xStart, yStart + i * optionHeight);

    }
  }

  @Override
  protected void keyPressed(int keyCode) {
    switch(keyCode) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_W:
        if(this.selected > 0) this.selected--;
        playSoundEffect(SWITCH_EFFECT);
        break;
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        if(this.selected < this.optionMenu.length-1) this.selected++;
        playSoundEffect(SWITCH_EFFECT);

        break;
      case KeyEvent.VK_ENTER:
        playSoundEffect(SELECT_EFFECT);
        switch(this.optionMenu[selected]) {
          case START_GAME:
            //start game
            break;
          case QUIT_GAME:
            System.exit(0);
            break;
        }
        break;
    }
  }

  @Override
  protected void keyReleased(int keyCode) {
  }

  @Override
  protected void playBackgroundMusic() {
    musicManager.playBackgroundMusic(START_MENU_MUSIC);
  }

  @Override
  protected void stopBackgroundMusic() {
    musicManager.stopBackgroundMusic();
  }

  @Override
  protected void playSoundEffect(String effectName) {
    soundEffectsManager.playSoundEffect(effectName);
  }
}
