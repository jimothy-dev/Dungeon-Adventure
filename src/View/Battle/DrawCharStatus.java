package View.Battle;

import Model.Character.Hero;
import Model.Character.Monster;
import View.FrameManager;

import java.awt.*;
import java.util.Random;

public class DrawCharStatus {
    private static final Random random = new Random();



    public static void drawCharStatus(final Graphics theGraphics, final Hero theHero, final Monster theMonster) {

        // Calculate the height of the character status areas.
        int boxHeight = FrameManager.getHeight() / 8;

        // Set color and draw the background of the character status area
        theGraphics.setColor(new Color(50, 50, 50, 180)); // Semi-transparent dark color

        // Monster Status
        Rectangle monStatus = new Rectangle(PlaceChars.getMonsterStatusX() - 15 - FrameManager.getWidth() * 2 / 5,
                20, FrameManager.getWidth() * 2 / 5, boxHeight);
        theGraphics.fillRect(monStatus.x, monStatus.y, monStatus.width, monStatus.height);

        // Hero Status
        Rectangle heroStatus = new Rectangle(PlaceChars.getHeroWidth() - 15, FrameManager.getHeight() - boxHeight - 140,
                FrameManager.getWidth() * 2 / 5, boxHeight);
        theGraphics.fillRect(heroStatus.x, heroStatus.y, heroStatus.width, heroStatus.height);

        writeCharNames(theGraphics, heroStatus, monStatus, theHero, theMonster);
        addHealth(theGraphics, heroStatus, monStatus, theHero, theMonster);
    }

    private static void writeCharNames(final Graphics theGraphics,
                                       final Rectangle theHeroStat, final Rectangle theMonStat,
                                       final Hero theHero, final Monster theMonster) {
        theGraphics.setColor(Color.white);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 14));
        theGraphics.drawString(theHero.getName(), theHeroStat.x + 10, theHeroStat.y + 20);
        theGraphics.drawString(theMonster.getName(), theMonStat.x + 10, theMonStat.y + 20);
    }

    private static void addHealth(final Graphics theGraphics, final Rectangle theHeroStatus, final Rectangle theMonStatus,
                                  final Hero theHero, final Monster theMonster) {
        // Calculate the full length of the health bars (same for hero and monster).
        int fullBarWidth = theHeroStatus.width - 110;

        // Calculate the percentage of health remaining for hero and monster.
        double heroHealthPercent = (double) theHero.getHP() / theHero.getMaxHP();
        double monsterHealthPercent = (double) theMonster.getHP() / theMonster.getMaxHP();

        // Calculate the length of the red rectangles based on the health percentages.
        int heroHealthBarWidth = (int) (fullBarWidth * heroHealthPercent);
        int monsterHealthBarWidth = (int) (fullBarWidth * monsterHealthPercent);

        // Draw black rectangles representing the background of the health bars.
        theGraphics.setColor(Color.black);
        theGraphics.fillRect(theHeroStatus.x + 17, theHeroStatus.y + 34, fullBarWidth + 6, 18);
        theGraphics.fillRect(theMonStatus.x + 17, theMonStatus.y + 34, fullBarWidth + 6, 18);

        // Draw red rectangles representing the current health as a percentage of max HP.
        theGraphics.setColor(Color.red);
        theGraphics.fillRect(theHeroStatus.x + 20, theHeroStatus.y + 35, heroHealthBarWidth, 16);
        theGraphics.fillRect(theMonStatus.x + 20, theMonStatus.y + 35, monsterHealthBarWidth, 16);

        // Display the HP values as text
        theGraphics.setColor(Color.white);
        theGraphics.drawString(theHero.getHP() + "/" + theHero.getMaxHP(),
                theHeroStatus.x + 20 + fullBarWidth + 5,
                theHeroStatus.y + 50);
        theGraphics.drawString(theMonster.getHP() + "/" + theMonster.getMaxHP(),
                theMonStatus.x + 20 + fullBarWidth + 5,
                theMonStatus.y + 50);
    }

}
