package View;

import Model.Character.*;
import Model.GameScreen;
import Model.GameScreenStack;
import View.Battle.BattleAssets;
import View.Battle.BattleLogArea;
import View.Battle.BattleLogOut;
import View.Battle.DrawBattleLog;
import View.Battle.DrawCharStatus;
import View.Battle.PlaceChars;

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
import java.util.Arrays;
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
     * Battle options array.
     */
    private final String[] optionMenu;

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


    /**
     * Constructor. Sets hero and monster. Sets background image.
     * @param theStack Stack of gamescreens, this screen is added to the top of the stack.
     */
    protected BattleScreen(final GameScreenStack theStack, final Hero theHero, final Monster theMonster) {
        super(Objects.requireNonNull(theStack));
        myHero = Objects.requireNonNull(theHero);
        myMonster = Objects.requireNonNull(theMonster);

        optionMenu = new String[] {BASE_ATTACK, INVENTORY, ESCAPE};
        selected = 0;
        playBackgroundMusic(BATTLE_MUSIC);
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
    }

    @Override
    protected void loop() {
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
        theGraphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
        theGraphics.setFont(getCustomFont());

        drawOptions(theGraphics);
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
        int totalHeight = optionMenu.length * optionHeight;
        int xStart = FrameManager.getWidth() * 3 / 4;
        int yStart = FrameManager.getHeight() - totalHeight; // padding

        for (int i = 0; i < optionMenu.length; i++) {
            String optionText = optionMenu[i];
            if (i == selected) {
                theGraphics.setColor(Color.magenta);
            } else {
                theGraphics.setColor(Color.white);
            }
            theGraphics.drawString(optionText, xStart, yStart + i * optionHeight);
        }
    }


    /**
     * Calculates the width of each option in the menu to find the max.
     * @param graphics Graphics object used for drawing.
     * @return int representing max width in options.
     */
    private int getMaxOptionWidth(Graphics graphics) {
        FontMetrics metrics = graphics.getFontMetrics();
        int maxWidth = 0;
        for (String option : optionMenu) {
            int width = metrics.stringWidth(option);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    /**
     * Navigates the battle options.
     * @param keyCode the key that has been pressed.
     */
    @Override
    protected void keyPressed(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if(this.selected > 0) this.selected--;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if(this.selected < this.optionMenu.length-1) this.selected++;
//                super.soundManager.playAudio();
                break;
            case KeyEvent.VK_ENTER:
//                super.soundManager.playAudio();
                switch(this.optionMenu[selected]) {
                    case BASE_ATTACK:
System.out.println("DEBUG: Attack");
                        myMonster.attacked(myHero.getDamage());
                        if (myMonster.checkIfDead()) {
                            System.out.println("Victory! You received " + Arrays.toString(myMonster.getReward()));
                        }
                        break;
                    case INVENTORY:
System.out.println("DEBUG: Inventory");
                        break;
                    case ESCAPE:
                        gameScreenStack.backToPreviousState();
                }
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
