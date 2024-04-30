package View;

import Controller.AudioManager;
import Model.GameScreen;
import Model.GameScreenStack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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
//    private static final String SKILLS = "Skills";

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
    //    private Hero myHero;
//    private Monster myMonster;

    /**
     * Currently selected battle option.
     */
    private int selected;

    /**
     * Image to be used as background.
     */
    private Image battleBackgroundImage;

    /**
     * Represents if the hero is currently able to escape.
     */
    private final boolean runUnlock;

    /**
     * Constructor. Sets hero and monster. Sets background image.
     * @param theStack Stack of gamescreens, this screen is added to the top of the stack.
     */
    protected BattleScreen(GameScreenStack theStack) {
        super(theStack);
        optionMenu = new String[] {BASE_ATTACK, INVENTORY, ESCAPE};
        selected = 0;
//        myHero = theHero;
//        myMonster = theMonster;
        runUnlock = false;
        playBackgroundMusic(BATTLE_MUSIC);
        try {
            Random random = new Random();
            battleBackgroundImage = ImageIO.read(
                    new File("src/Assets/Images/dungeon background "
                            + random.nextInt(1, DEFAULT_NUM_IMAGES + 1) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loop() {
    }

    @Override
    protected void render(Graphics theGraphics) {
        theGraphics.drawImage(battleBackgroundImage, 0, 0, FrameManager.getWidth(),
                FrameManager.getHeight(), null);
        theGraphics.setColor(new Color(30, 30, 70,120));
        theGraphics.fillRect(0, 0, FrameManager.getWidth(), FrameManager.getHeight());
        theGraphics.setFont(getCustomFont());
        FontMetrics metrics = theGraphics.getFontMetrics();
        int optionHeight = metrics.getHeight();
        int totalHeight = optionMenu.length * optionHeight;
        int xStart = FrameManager.getWidth() - getMaxOptionWidth(theGraphics) - 20; // 20 for padding
        int yStart = FrameManager.getHeight() - totalHeight - 20; // 20 for padding

        for (int i = 0; i < optionMenu.length; i++) {
            String optionText = optionMenu[i];
            if (i == selected) {
                theGraphics.setColor(Color.magenta);
            } else {
                theGraphics.setColor(isOptionEnabled(i) ? Color.white : Color.gray);
            }
            theGraphics.drawString(optionText, xStart, yStart + i * optionHeight);
        }
        placeChars(theGraphics);
        placeHealth(20, 12); //will take monster and hero as parameters.
    }

    /**
     * Draws hero, monster, and platforms for characters.
     * Places monster top right. hero middle left.
     * Places platform behind and slightly under hero and monster.
     *
     * @param theGraphics Graphics object used to paint on screen.
     */
    private void placeChars(Graphics theGraphics) {
        Image monsterImage;
        Image heroImage;
        Image platformImage;
        try {
            // Load images
            monsterImage = ImageIO.read(new File("src/Assets/Images/leech.png"));
            heroImage = ImageIO.read(new File("src/Assets/Images/elf.png"));
            platformImage = ImageIO.read(new File("src/Assets/Images/battlePlatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Desired dimensions for images
        int desiredHeroWidth = 250;
        int desiredHeroHeight = 400;
        int desiredMonsterWidth = 250;
        int desiredMonsterHeight = 250;
        int desiredPlatformWidth = 250;
        int desiredPlatformHeight = 100;

        // Calculate scaling factors to maintain aspect ratio for monster image
        int originalMonsterWidth = monsterImage.getWidth(null);
        int originalMonsterHeight = monsterImage.getHeight(null);
        double scaleMonsterWidth = (double) desiredMonsterWidth / originalMonsterWidth;
        double scaleMonsterHeight = (double) desiredMonsterHeight / originalMonsterHeight;
        double scaleMonster = Math.min(scaleMonsterWidth, scaleMonsterHeight);

        // Calculate scaling factors to maintain aspect ratio for hero image
        int originalHeroWidth = heroImage.getWidth(null);
        int originalHeroHeight = heroImage.getHeight(null);
        double scaleHeroWidth = (double) desiredHeroWidth / originalHeroWidth;
        double scaleHeroHeight = (double) desiredHeroHeight / originalHeroHeight;
        double scaleHero = Math.min(scaleHeroWidth, scaleHeroHeight);

        // Calculate scaling factors to maintain aspect ratio for platform image
        int originalPlatformWidth = platformImage.getWidth(null);
        int originalPlatformHeight = platformImage.getHeight(null);
        double scalePlatformWidth = (double) desiredPlatformWidth / originalPlatformWidth;
        double scalePlatformHeight = (double) desiredPlatformHeight / originalPlatformHeight;
        double scalePlatform = Math.min(scalePlatformWidth, scalePlatformHeight);

        // Scale the images while maintaining aspect ratio
        Image scaledMonsterImage = monsterImage.getScaledInstance(
                (int)(originalMonsterWidth * scaleMonster), (int)(originalMonsterHeight * scaleMonster), Image.SCALE_SMOOTH);
        Image scaledHeroImage = heroImage.getScaledInstance(
                (int)(originalHeroWidth * scaleHero), (int)(originalHeroHeight * scaleHero), Image.SCALE_SMOOTH);
        Image scaledPlatformImage = platformImage.getScaledInstance(
                (int)(originalPlatformWidth * scalePlatform), (int)(originalPlatformHeight * scalePlatform), Image.SCALE_SMOOTH);

        // Draw images in the graphics object
        Rectangle clipBounds = theGraphics.getClipBounds();
        int panelWidth = clipBounds.width;
        int panelHeight = clipBounds.height;

        // Calculate coordinates for the monster image (top right)
        int monsterX = panelWidth - (int)(originalMonsterWidth * scaleMonster) - 40;
        int monsterY = 20;

        // Calculate coordinates for the hero image (middle left)
        int heroX = 30;
        int heroY = panelHeight / 2 - (int)(originalHeroHeight * scaleHero) / 2 + 60;

        // Calculate platform coordinates
        int platformMonsterX = panelWidth - desiredMonsterWidth / 2 - desiredPlatformWidth / 2 ;
        int platformMonsterY = monsterY + (int)(originalMonsterHeight * scaleMonster) - 40;
        int platformHeroX = heroX + desiredHeroWidth / 2 - desiredPlatformWidth / 2;
        int platformHeroY = heroY + (int)(originalHeroHeight * scaleHero) - 60;

        theGraphics.drawImage(scaledPlatformImage, platformMonsterX, platformMonsterY, null);
        theGraphics.drawImage(scaledPlatformImage, platformHeroX, platformHeroY, null);
        theGraphics.drawImage(scaledMonsterImage, monsterX, monsterY, null);
        theGraphics.drawImage(scaledHeroImage, heroX, heroY, null);
    }

    /**
     * Places health bars on screen.
     * @param theMonster The monster character.
     * @param theHero The hero character.
     */
    private void placeHealth(final int theMonster, final int theHero) {
        // ensure these are updated every move.
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
     * Used to check if an option is enabled. Not clickable if not enabled.
     * @param index int representing the option in menu.
     * @return
     */
    private boolean isOptionEnabled(int index) {
        return switch (index) {
            case 0 -> true; 
            default -> false;
        };
    }

    @Override
    protected void keyPressed(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if(this.selected > 0) this.selected--;
//                super.soundManager.playAudio();
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
                    case INVENTORY:
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
