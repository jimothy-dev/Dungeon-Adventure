package View;

import Model.Character.*;
import Model.Character.Saving.HeroSave;
import Model.Character.Saving.SavedGameLister;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.io.File;
import java.util.List;

//Music: “Misty Dungeon”, from PlayOnLoop.com
//Licensed under Creative Commons by Attribution 4.0
public class MainMenu extends GameScreen {
  private static final String START_GAME = "Start Game";
  private static final String POLYMORPHISM = "Polymorphism";
  private static final String ENCAPSULATION = "Encapsulation";
  private static final String INHERITANCE = "Inheritance";
  private static final String ABSTRACTION = "Abstraction";
  private static String MYSTERY = "???????";
  private static final String QUIT_GAME = "Quit Game";
  private static final String SAVE_GAME = "Save Game";
  private static final String LOAD_GAME = "Load Game";

  private static final String BATTLE_SCREEN = "DEBUG Battle Screen";
  private static final String SELECT_EFFECT = "steelsword";
  private static final String SWITCH_EFFECT = "215029__taira-komori__extracting_knife";
  private static final String START_MENU_MUSIC = "POL-misty-dungeon-short";
  private final String[] optionMenu;
  private int selected;
  private Image selectorImage;
  private Image menuBackgroundImage;
  private boolean abstractionUnlock;
  private boolean inheritanceUnlock;
  private boolean encapsulationUnlock;
  private boolean polymorphismUnlock;
  private boolean mysteryUnlock;
  private Hero myHero;
  private final File saveLocation;




  public MainMenu(GameScreenStack manager) {
    super(manager);
    myHero = new Elf();
    String MYSTERY = "???????";
    optionMenu = new String[] {START_GAME, POLYMORPHISM, ENCAPSULATION, INHERITANCE, ABSTRACTION,
            MYSTERY, QUIT_GAME, BATTLE_SCREEN, SAVE_GAME, LOAD_GAME};
    selected = 0;
    abstractionUnlock = false;
    inheritanceUnlock = false;
    encapsulationUnlock = false;
    polymorphismUnlock = false;
    mysteryUnlock = false;
    playBackgroundMusic(START_MENU_MUSIC);
    saveLocation = new File("src/SavedGame");
    try {
      selectorImage = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
      menuBackgroundImage = ImageIO.read(new File("src/Assets/Images/title.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void loop() {
  }

  @Override
  protected void render(Graphics theGraphics) {
    theGraphics.drawImage(menuBackgroundImage, 0, 0, FrameManager.getWidth(),
                       FrameManager.getHeight(), null);
    theGraphics.setColor(new Color(30, 30, 70,120));
    theGraphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
    theGraphics.setFont(getCustomFont());
    theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 25));
    int optionHeight = theGraphics.getFontMetrics().getHeight();
    int totalHeight = optionMenu.length * optionHeight;
    int yStart = (FrameManager.getHeight() - totalHeight) / 2;
    for (int i = 0; i < optionMenu.length; i++) {
      String optionText = optionMenu[i];
      int textWidth = theGraphics.getFontMetrics().stringWidth(optionText);
      int xStart = (FrameManager.getWidth() - textWidth) / 2;
      if (i == selected) {
        theGraphics.setColor(Color.magenta);
        theGraphics.drawImage(selectorImage, xStart - selectorImage.getWidth(null) - 5,
                           yStart + i * optionHeight - optionHeight / 2, null);
      } else {
        if (isOptionEnabled(i)) {
          theGraphics.setColor(Color.white);
        } else {
          theGraphics.setColor(Color.gray); // Grey out disabled options
        }
      }
      theGraphics.drawString(optionText, xStart, yStart + i * optionHeight);
    }
  }

  @Override
  protected void keyPressed(int keyCode) {
    switch(keyCode) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_W:
        if(selected > 0) selected--;
        playSoundEffect(SWITCH_EFFECT);
        break;
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        if(selected < optionMenu.length - 1) selected++;
        playSoundEffect(SWITCH_EFFECT);
        break;
      case KeyEvent.VK_ENTER:
        playSoundEffect(SELECT_EFFECT);
        switch(optionMenu[selected]) {
          case START_GAME:
            stopBackgroundMusic();
            gameScreenStack.addScreen(new CharacterScreen(gameScreenStack));
            break;

          case QUIT_GAME:
            System.exit(0);
            break;

          case BATTLE_SCREEN:
            stopBackgroundMusic();
            gameScreenStack.addScreen(new BattleScreen(gameScreenStack, myHero, new Skeleton()));
            break;

          case SAVE_GAME:
            String saveFileName = JOptionPane.showInputDialog("Enter a name for your save file:");
            if (saveFileName != null && !saveFileName.trim().isEmpty()) {
              HeroSave.saveHero(myHero, saveFileName + ".sav");
              JOptionPane.showMessageDialog(null, "Game saved successfully.");
            } else {
              JOptionPane.showMessageDialog(null, "Invalid file name. Game not saved.");
            }
            break;

          case LOAD_GAME:
            List<String> savedGames = SavedGameLister.listSavedGames();
            if (savedGames.isEmpty()) {
              JOptionPane.showMessageDialog(null, "No saved games found.");
            } else {
              String selectedGame = (String) JOptionPane.showInputDialog(
                      null,
                      "Select a saved game to load:",
                      "Load Game",
                      JOptionPane.PLAIN_MESSAGE,
                      null,
                      savedGames.toArray(),
                      savedGames.get(0)
              );
              if (selectedGame != null) {
                myHero = HeroSave.loadHero(selectedGame);
                JOptionPane.showMessageDialog(null, "Game loaded successfully.");
                // Transition to the game screen or perform any additional setup
              }
            }
            break;
        }
        break;
    }
  }

  @Override
  protected void keyReleased(int keyCode) {
  }

  private boolean isOptionEnabled(int index) {
    return switch (index) {
      case 0, 6, 7, 8, 9 -> true;
      case 1 -> polymorphismUnlock;
      case 2 -> encapsulationUnlock;
      case 3 -> inheritanceUnlock;
      case 4 -> abstractionUnlock;
      case 5 -> mysteryUnlock;
      default -> false;
    };
  }

  private void unlockMystery(){
    optionMenu[5] = "Final Level";
  }

  private void unlockLevel(int index) {
    switch (index) {
      case 1:
        polymorphismUnlock = true;
      case 2:
        encapsulationUnlock = true;
      case 3:
        inheritanceUnlock = true;
      case 4:
        abstractionUnlock = true;
      case 5:
        mysteryUnlock = true;
    }
  }
}
