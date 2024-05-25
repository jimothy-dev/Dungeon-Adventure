package View;

import Model.Character.*;
import Model.GameScreen;
import Model.GameScreenStack;
import View.Battle.*;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Random;

/**
 * Battle screen created when a monster is encountered. Constructor takes monster and hero.
 * Random background.
 *
 * @author James
 * @version 1.0
 */
public class BattleScreen extends GameScreen {

    /**
     * The number of background images in assets.
     */
    private static final int DEFAULT_NUM_IMAGES = 12;

    /**
     * Music to play on battle screen.
     */
    private static final String BATTLE_MUSIC = "08 The Dark Lord's Palace - rock version";

    /**
     * Battle option 'attack'.
     */
    private static final String BASE_ATTACK = "Attack";

    /**
     * Battle option 'items'.
     */
    private static final String INVENTORY = "Items";

    /**
     * Battle option 'run'.
     */
    private static final String ESCAPE = "Escape";

    /**
     * Battle option 'return' only available upon victory.
     */
    private static final String RETURN = "Return";

    private static final String END_GAME = "Game Over";
    private static final String HEAL = "Heal";

    /**
     * Battle options array.
     */
    private final String[] optionMenu;

    private final String[] gameOverMenu;
    private final String[] returnMenu;

    /**
     * Currently selected battle option.
     */
    private int selected;

    /**
     * Image to be used as background.
     */
    private Image battleBackgroundImage;

    /**
     * Object used to display battle text on screen.
     */
    private final BattleLogArea battleLogArea;

    private final Monster myMonster;
    private final Hero myHero;
    private final BattleAssets myBattleAssets;
    private final BattleAssets myWinningAssets;
    private final BattleTurnManager myTurnManager;
    private final BattleManager myBattleManager;
    private Boolean healed = false;

    /**
     * Counter to give enemy time during their turn.
     */
    private int enemyTurnCount = 0;

    /**
     * Constructor. Sets hero and monster. Sets background image.
     * @param theStack Stack of gamescreens, this screen is added to the top of the stack.
     */
    protected BattleScreen(final GameScreenStack theStack, final Hero theHero, final Monster theMonster) {
        super(Objects.requireNonNull(theStack));
        myHero = Objects.requireNonNull(theHero);
        myMonster = Objects.requireNonNull(theMonster);
        if (myHero.getName().equals("Elf")) {
            optionMenu = new String[] {BASE_ATTACK, HEAL, INVENTORY, ESCAPE};
        } else {
            optionMenu = new String[] {BASE_ATTACK, INVENTORY, ESCAPE};
        }
        gameOverMenu = new String[] {END_GAME};
        returnMenu = new String[] {RETURN};
        selected = 0;
        playBackgroundMusic(BATTLE_MUSIC);
        playSoundEffect("BattleStart");
        try {
            Random random = new Random();
            battleBackgroundImage = ImageIO.read(
                    new File("src/Assets/Images/dungeon background "
                            + random.nextInt(1, DEFAULT_NUM_IMAGES + 1) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the JTextArea for the battle log
        battleLogArea = new BattleLogArea();

        // Set BattleLogOut as the output stream
        BattleLogOut battleLogOut = new BattleLogOut(battleLogArea);
        System.setOut(new PrintStream(battleLogOut));

        myBattleAssets = new BattleAssets();
        myWinningAssets = new BattleAssets();
        myBattleAssets.initialize(myHero, myMonster, false);
        myWinningAssets.initialize(myHero, myMonster, true);
        myTurnManager = new BattleTurnManager(myHero, myMonster);
        myBattleManager = new BattleManager(myGameScreenStack, myHero, myMonster, myTurnManager);
    }

    @Override
    protected void loop() {
        if (!myTurnManager.getTurn() && enemyTurnCount == 20) {
            myBattleManager.monsterTurn();
            enemyTurnCount = 0;
        } else if (!myTurnManager.getTurn()){
            enemyTurnCount++;
        }
    }

    /**
     * Draws background image. Sets font.
     * Makes method calls to draw the battle options, character images, and battle log.
     *
     * @param theGraphics Graphics object used to paint BattleScreen components.
     */
    @Override
    protected void render(Graphics theGraphics) {
        // Draw the battle background
        theGraphics.drawImage(battleBackgroundImage, 0, 0, FrameManager.getWidth(),
                FrameManager.getHeight(), null);
        theGraphics.setColor(new Color(30, 30, 70,120));
        theGraphics.setFont(getCustomFont());
        if (myTurnManager.getTurn()) {
            drawOptions(theGraphics);
        }
        if (myMonster.checkIfDead()) {
            PlaceChars.placeChars(theGraphics, myWinningAssets);
        } else {
            PlaceChars.placeChars(theGraphics, myBattleAssets);
        }
        DrawBattleLog.drawBattleLog(theGraphics, battleLogArea);
        DrawCharStatus.drawCharStatus(theGraphics, myHero, myMonster);
    }

    /**
     * Displays the battle options on the screen in the bottom right.
     * @param theGraphics Graphics object used to draw options.
     */
    private void drawOptions(final Graphics theGraphics) {
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 24));
        FontMetrics metrics = theGraphics.getFontMetrics();
        int optionHeight = metrics.getHeight();
        String[] menu = getCurrentMenu();
        int totalHeight = menu.length * optionHeight;
        int xStart = FrameManager.getWidth() * 3 / 4;
        int yStart = FrameManager.getHeight() - totalHeight; // padding

        for (int i = 0; i < menu.length; i++) {
            String optionText = menu[i];
            if (i == selected) {
                theGraphics.setColor(Color.magenta);
            } else {
                theGraphics.setColor(Color.white);
            }
            theGraphics.drawString(optionText, xStart, yStart + i * optionHeight);
        }
    }

    /**
     * Determine which menu should be displayed based on the current state.
     * @return The appropriate menu array.
     */
    private String[] getCurrentMenu() {
        if (myHero.checkIfDead()) {
            return gameOverMenu;
        } else if (myMonster.checkIfDead()) {
            return returnMenu;
        } else {
            return optionMenu;
        }
    }


    /**
     * Navigates the battle options.
     * @param keyCode the key that has been pressed.
     */
    @Override
    protected void keyPressed(int keyCode) {
        String[] currentMenu;
        switch(keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if(selected > 0) selected--;
                playSoundEffect("BattleSwitchEffect");
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                currentMenu = getCurrentMenu();
                if (selected < currentMenu.length - 1) selected++;
                playSoundEffect("BattleSwitchEffect");
                break;
            case KeyEvent.VK_ENTER:
                currentMenu = getCurrentMenu();
                String selectedOption = currentMenu[selected];
                switch (selectedOption) {
                    case BASE_ATTACK:
                        if (myTurnManager.getTurn()) {
                            myBattleManager.heroAttack();
                        }
                        break;
                    case HEAL:
                        if (myHero.getMaxHP() == myHero.getHP()) {
                            System.out.println(myHero.getName() + " is already at max health!");
                        } else if (!healed) {
                            myBattleManager.healHero();
                            healed = true;
                        } else {
                            System.out.println(myHero.getName() + " can only heal once per battle!");
                        }
                        break;
                    case INVENTORY:
                        playSoundEffect("InventoryOpen");
                        myGameScreenStack.addScreen(new InventoryScreen(myGameScreenStack, myHero));
                        break;
                    case ESCAPE:
                        if (currentMenu == optionMenu) {
                            playSoundEffect("BattleRun");
                            // Handle escape option in battle
                        }
                    case RETURN:
                        if (currentMenu == returnMenu) {
                            // Handle return option upon victory
                        }
                        myGameScreenStack.backToPreviousState();
                        break;
                    case END_GAME:
                        if (currentMenu == gameOverMenu) {
                            myGameScreenStack.clearStack();
                            myGameScreenStack.addScreen(new MainMenu(myGameScreenStack));
                            // Handle end game scenario
                        }
                        break;
                }
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }



}
