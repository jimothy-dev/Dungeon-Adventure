package View.Battle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PlaceChars {

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
}
