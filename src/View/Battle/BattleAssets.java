package View.Battle;

import Model.Character.Hero;
import Model.Character.Monster;
import View.FrameManager;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class BattleAssets {
    private Image scaledMonsterImage;
    private Image scaledHeroImage;
    private Image scaledPlatformImage;

    /**
     * Initialize the images and scale them according to the desired dimensions.
     *
     * @param theHero The hero character object.
     * @param theMonster The monster character object.
     */
    public void initialize(Hero theHero, Monster theMonster) {
        int panelWidth = FrameManager.getWidth();
        int panelHeight = FrameManager.getHeight();

        Image monsterImage;
        Image heroImage;
        Image platformImage;
        // Load images
        try {
            monsterImage = ImageIO.read(new File(theMonster.getImage()));
            heroImage = ImageIO.read(new File(theHero.getImage()));
            platformImage = ImageIO.read(new File("src/Assets/Images/battlePlatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Desired dimensions for images
        int desiredHeroWidth = 350;
        int desiredHeroHeight = 400;
        int desiredMonsterWidth = 250;
        int desiredMonsterHeight = 300;
        int desiredPlatformWidth = 250;
        int desiredPlatformHeight = 100;

        // Calculate scaling factors and scale images
        double scaleMonster = PlaceChars.scaleImage(desiredMonsterHeight, desiredMonsterWidth, monsterImage.getHeight(null), monsterImage.getWidth(null));
        double scaleHero = PlaceChars.scaleImage(desiredHeroHeight, desiredHeroWidth, heroImage.getHeight(null), heroImage.getWidth(null));
        double scalePlatform = PlaceChars.scaleImage(desiredPlatformHeight, desiredPlatformWidth, platformImage.getHeight(null), platformImage.getWidth(null));

        scaledMonsterImage = monsterImage.getScaledInstance(
                (int) (monsterImage.getWidth(null) * scaleMonster),
                (int) (monsterImage.getHeight(null) * scaleMonster),
                Image.SCALE_SMOOTH
        );

        scaledHeroImage = heroImage.getScaledInstance(
                (int) (heroImage.getWidth(null) * scaleHero),
                (int) (heroImage.getHeight(null) * scaleHero),
                Image.SCALE_SMOOTH
        );

        scaledPlatformImage = platformImage.getScaledInstance(
                (int) (platformImage.getWidth(null) * scalePlatform),
                (int) (platformImage.getHeight(null) * scalePlatform),
                Image.SCALE_SMOOTH
        );
    }

    // Getters for the scaled images
    public Image getScaledMonsterImage() {
        return scaledMonsterImage;
    }

    public Image getScaledHeroImage() {
        return scaledHeroImage;
    }

    public Image getScaledPlatformImage() {
        return scaledPlatformImage;
    }
}
