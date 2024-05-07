package View.Battle;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


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
     * @param theBattleAssets BattleAssets object containing scaled images.
     */
    public static void placeChars(final Graphics theGraphics,
                                  final BattleAssets theBattleAssets) {
        // Get scaled images from BattleAssets
        Image scaledMonsterImage = theBattleAssets.getScaledMonsterImage();
        Image scaledHeroImage = theBattleAssets.getScaledHeroImage();
        Image scaledPlatformImage = theBattleAssets.getScaledPlatformImage();

        // Retrieve panel width and height from Graphics object
        Rectangle clipBounds = theGraphics.getClipBounds();
        int panelWidth = clipBounds.width;
        int panelHeight = clipBounds.height;

        // Calculate coordinates for the monster image (top right)
        monsterX = panelWidth - scaledMonsterImage.getWidth(null) - 40;
        int monsterY = 20;

        // Calculate coordinates for the hero image (middle left)
        int heroX = 30;
        int heroY = panelHeight / 2 - scaledHeroImage.getHeight(null) / 2 + 60;

        // Calculate platform coordinates
        int platformMonsterX = panelWidth - scaledMonsterImage.getWidth(null) / 2
                               - scaledPlatformImage.getWidth(null) / 2;
        int platformMonsterY = monsterY + scaledMonsterImage.getHeight(null) - 40;
        int platformHeroX = heroX + scaledHeroImage.getWidth(null) / 2
                            - scaledPlatformImage.getWidth(null) / 2;
        int platformHeroY = heroY + scaledHeroImage.getHeight(null) - 60;

        theGraphics.drawImage(scaledPlatformImage, platformMonsterX - 30, platformMonsterY, null);
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
    public static double scaleImage(final int theDesiredHeight, final int theDesiredWidth,
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
