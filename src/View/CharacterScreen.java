package View;

import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.File;
import Model.Character.*;

public class CharacterScreen extends GameScreen {

  private final String chooseText = "Choose your Hero";
  private final String elf = "Elf";
  private final String rogue = "Rogue";
  private final String wizard = "Wizard";
  private final String barbarian = "Barbarian";
  private Image barbarianImage;
  private Image characterSelectionBackgroundImage;
  private Image elfImage;
  private Image rogueImage;
  private Image wizardImage;
  private static final String SELECT_EFFECT = "steelsword";
  private static final String SWITCH_EFFECT = "215029__taira-komori__extracting_knife";
  private Hero myHero;

  int selected;

  protected CharacterScreen(GameScreenStack theStack) {
    super(theStack);
    selected = 0;

    try {
      elfImage = ImageIO.read(new File("src/Assets/Images/elf.png"));
      wizardImage = ImageIO.read(new File("src/Assets/Images/wizard.png"));
      rogueImage = ImageIO.read(new File("src/Assets/Images/rogue.png"));
      barbarianImage = ImageIO.read(new File("src/Assets/Images/barbarian.png"));
      characterSelectionBackgroundImage = ImageIO.read(new File("src/Assets/Images/characterScreen.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void loop() {

  }

  @Override
  protected void render(Graphics theGraphics) {
    theGraphics.drawImage(characterSelectionBackgroundImage, 0, 0, FrameManager.getWidth(),
            FrameManager.getHeight(), null);
    theGraphics.setColor(new Color(30, 30, 70, 120));
    theGraphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
    theGraphics.setColor(Color.white);
    theGraphics.setFont(getCustomFont().deriveFont(Font.PLAIN, 25));
    theGraphics.drawImage(elfImage, 20, 350, 170, 250, null);
    theGraphics.drawImage(wizardImage, 200, 350, 250, 250, null);
    theGraphics.drawImage(rogueImage, 400, 350, 200, 250, null);
    theGraphics.drawImage(barbarianImage, 550, 350, 250, 250, null);
    theGraphics.drawString(chooseText, 220, 120);
    if (selected == 0) {
      theGraphics.setColor(Color.magenta);
    }
    theGraphics.drawString(elf, 100, 300);
    theGraphics.setColor(Color.white);
    if (selected == 1) {
      theGraphics.setColor(Color.magenta);
    }
    theGraphics.drawString(wizard, 250, 300);
    theGraphics.setColor(Color.white);
    if (selected == 2) {
      theGraphics.setColor(Color.magenta);
    }
    theGraphics.drawString(rogue, 420, 300);
    theGraphics.setColor(Color.white);
    if (selected == 3) {
      theGraphics.setColor(Color.magenta);
    }
    theGraphics.drawString(barbarian, 600, 300);
    theGraphics.setColor(Color.white);
  }

  @Override
  protected void keyPressed(int keyCode) {
    switch (keyCode) {
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_D:
        if (selected < 3) {
          selected++;
          playSoundEffect(SWITCH_EFFECT);
        }
        break;
      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_A:
        if (selected > 0) {
          selected--;
          playSoundEffect(SWITCH_EFFECT);
        }
        break;
      case KeyEvent.VK_ENTER:
        playSoundEffect(SELECT_EFFECT);
        switch (selected) {
          case 0:
            myHero = new Elf();
            break;
          case 1:
            myHero = new Wizard();
            break;
          case 2:
            myHero = new Rogue();
            break;
          case 3:
            myHero = new Barbarian();
            break;
        }
        myGameScreenStack.clearStack();
//        stopBackgroundMusic();
        myGameScreenStack.addScreen(new MainMenu(myGameScreenStack, myHero));
    }
  }

  @Override
  protected void keyReleased(int keyCode) {

  }
}
