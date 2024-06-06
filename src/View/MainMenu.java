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
import java.util.Arrays;
import java.util.List;

import static Model.Character.Monster.getRandomMonster;

//Music: “Misty Dungeon”, from PlayOnLoop.com
//Licensed under Creative Commons by Attribution 4.0
public class MainMenu extends GameScreen {

  private static final String QUIT_GAME = "Quit Game";
  private static final String NEW_GAME = "New Game";
  private static final String SAVE_GAME = "Save Game";
  private static final String LOAD_GAME = "Load Game";

  private static final String BATTLE_SCREEN = "Start Battle";
  private static final String SELECT_EFFECT = "steelsword";
  private static final String SWITCH_EFFECT = "215029__taira-komori__extracting_knife";
  private static final String START_MENU_MUSIC = "POL-misty-dungeon-short";
  private final String[] myOptionMenu;
  private int mySelected;
  private Image mySelectorImage;
  private Image myMenuBackgroundImage;
  private Hero myHero;
  private Monster myMonster;
  private final File mySaveLocation;




  public MainMenu(GameScreenStack manager) {
    super(manager);
    myHero = null;
    myOptionMenu = new String[] {NEW_GAME, LOAD_GAME, QUIT_GAME};
    mySelected = 0;
    playBackgroundMusic(START_MENU_MUSIC);
    mySaveLocation = new File("src/SavedGame");
    try {
      mySelectorImage = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
      myMenuBackgroundImage = ImageIO.read(new File("src/Assets/Images/DungeonAdventure.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public MainMenu(GameScreenStack manager, Hero theHero) {
    super(manager);
    if (theHero != null && !theHero.checkIfDead()) {
      myHero = theHero;
    }
    myOptionMenu = new String[] {NEW_GAME, BATTLE_SCREEN, SAVE_GAME, LOAD_GAME, QUIT_GAME};
    mySelected = 1;
//    playBackgroundMusic(START_MENU_MUSIC);
    mySaveLocation = new File("src/SavedGame");
    try {
      mySelectorImage = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
      myMenuBackgroundImage = ImageIO.read(new File("src/Assets/Images/DungeonAdventure.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void loop() {
  }

  @Override
  protected void render(Graphics theGraphics) {
    theGraphics.drawImage(myMenuBackgroundImage, 0, 0, FrameManager.getWidth(),
                       FrameManager.getHeight(), null);
    theGraphics.setColor(new Color(30, 30, 70,120));
    theGraphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
    theGraphics.setFont(getCustomFont());
    theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 25));
    int optionHeight = theGraphics.getFontMetrics().getHeight();
    int totalHeight = myOptionMenu.length * optionHeight;
    int yStart = (FrameManager.getHeight() - totalHeight) / 2;
    for (int i = 0; i < myOptionMenu.length; i++) {
      String optionText = myOptionMenu[i];
      int textWidth = theGraphics.getFontMetrics().stringWidth(optionText);
      int xStart = (FrameManager.getWidth() - textWidth) / 2;
      if (i == mySelected) {
        theGraphics.setColor(Color.CYAN);
        theGraphics.drawImage(mySelectorImage, xStart - mySelectorImage.getWidth(null) - 5,
                           yStart + i * optionHeight - optionHeight / 2, null);
      } else {
        theGraphics.setColor(Color.magenta);
      }
      theGraphics.drawString(optionText, xStart, yStart + i * optionHeight);
    }
  }

  @Override
  protected void keyPressed(int keyCode) {
    switch(keyCode) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_W:
        if(mySelected > 0) mySelected--;
        playSoundEffect(SWITCH_EFFECT);
        break;

      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        if(mySelected < myOptionMenu.length - 1) mySelected++;
        playSoundEffect(SWITCH_EFFECT);
        break;

      case KeyEvent.VK_ENTER:
        playSoundEffect(SELECT_EFFECT);
        switch(myOptionMenu[mySelected]) {
          case NEW_GAME:
            myGameScreenStack.addScreen(new CharacterScreen(myGameScreenStack));
            break;

            case QUIT_GAME:
            System.exit(0);
            break;

          case BATTLE_SCREEN:
            stopBackgroundMusic();
            if (myMonster == null || !myMonster.checkIfDead()) {
              myMonster = getRandomMonster();
            }
            myGameScreenStack.addScreen(new BattleScreen(myGameScreenStack, myHero, myMonster));
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
              }
              if (!Arrays.asList(myOptionMenu).contains(BATTLE_SCREEN)) {
                myGameScreenStack.clearStack();
                myGameScreenStack.addScreen(new MainMenu(myGameScreenStack, myHero));
              }
            }
            break;
          default:
            throw new IllegalStateException("Unexpected value: " + myOptionMenu[mySelected]);
        }
        break;
    }
  }

  @Override
  protected void keyReleased(int keyCode) {
  }
}
