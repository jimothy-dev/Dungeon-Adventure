package View.Battle;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

/**
 * Utility class used to draw hero and monster as well as their platforms on the screen while maintaining aspect ratio.
 *
 * @author James
 */
public class PlaceChars {

    private static int heroWidth;
    private static int monsterX;

    /**
     * Draws hero, monster, and platforms for characters.
     * Places monster top right. hero middle left.
     * Places platform behind and slightly under hero and monster.
     *
     * @param theGraphics Graphics object used to paint on screen.
     */
    public static void placeChars(final Graphics theGraphics) {
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
//        if (theMonster.getName() == "skeleton") {
//            desiredMonsterHeight = 300;
//        }
        int desiredPlatformWidth = 250;
        int desiredPlatformHeight = 100;

        // Calculate scaling factors to maintain aspect ratio for monster image
        int originalMonsterWidth = monsterImage.getWidth(null);
        int originalMonsterHeight = monsterImage.getHeight(null);
        double scaleMonster = scaleImage(desiredMonsterHeight, desiredMonsterWidth, originalMonsterWidth, originalMonsterHeight);

        // Calculate scaling factors to maintain aspect ratio for hero image
        int originalHeroWidth = heroImage.getWidth(null);
        int originalHeroHeight = heroImage.getHeight(null);
        double scaleHero = scaleImage(desiredHeroHeight, desiredHeroWidth, originalHeroHeight, originalHeroWidth);

        // Calculate scaling factors to maintain aspect ratio for platform image
        int originalPlatformWidth = platformImage.getWidth(null);
        int originalPlatformHeight = platformImage.getHeight(null);
        double scalePlatform = scaleImage(desiredPlatformHeight, desiredPlatformWidth, originalPlatformHeight, originalPlatformWidth);

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
        monsterX = panelWidth - (int)(originalMonsterWidth * scaleMonster) - 40;
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


        heroWidth = scaledHeroImage.getWidth(null) + heroX;
    }

    /**
     * Scales images to be displayed on battlescreen.
     * @param theDesiredHeight Desired height of image.
     * @param theDesiredWidth Desired width of image.
     * @param theActualHeight The original height.
     * @param theActualWidth The original height.
     * @return double - How much the image height and width will be scaled by.
     */
    private static double scaleImage(final int theDesiredHeight, final int theDesiredWidth,
                                   final int theActualHeight, final int theActualWidth) {
        double scaleWidth = (double) theDesiredWidth / theActualWidth;
        double scaleHeight = (double) theDesiredHeight / theActualHeight;
        return Math.min(scaleWidth, scaleHeight);
    }

    /**
     * What is the width of the hero image?
     * @return int The width of the hero image.
     */
    public static int getHeroWidth() {
        return heroWidth;
    }

    /**
     * What is the x placement of the Monster status bar
     * @return int - The x placement of the Monster status bar.
     */
    public static int getMonsterStatusX() {
        return monsterX;
    }
}
