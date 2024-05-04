package View.Battle;

import View.FrameManager;

import java.awt.*;
import java.util.Random;

public class DrawCharStatus {
    private static final Random random = new Random();



    public static void drawCharStatus(final Graphics theGraphics) {
        drawStatusAreas(theGraphics);
    }

    private static void drawStatusAreas(final Graphics theGraphics) {
        // Calculate the height of the character status areas.
        int boxHeight = FrameManager.getHeight() / 6;
        int bowWidth = FrameManager.getWidth() / 3;

        // Set color and draw the background of the character status area
        theGraphics.setColor(new Color(50, 50, 50, 180)); // Semi-transparent dark color

        // Monster Status
        Rectangle monStatus = new Rectangle(PlaceChars.getMonsterStatusX() - 15 - FrameManager.getWidth() * 2 / 5, 20, FrameManager.getWidth() * 2 / 5, boxHeight);
        theGraphics.fillRect(monStatus.x, monStatus.y, monStatus.width, monStatus.height);

        // Hero Status
        Rectangle heroStatus = new Rectangle(PlaceChars.getHeroWidth() - 15, FrameManager.getHeight() - boxHeight - 140,
                FrameManager.getWidth() * 2 / 5, boxHeight);
        theGraphics.fillRect(heroStatus.x, heroStatus.y, heroStatus.width, heroStatus.height);

        writeCharNames(theGraphics, heroStatus, monStatus);
        addHealth(theGraphics, heroStatus, monStatus);
    }

    private static void writeCharNames(final Graphics theGraphics,
                                       final Rectangle theHeroStat, final Rectangle theMonStat) {
        theGraphics.setColor(Color.white);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 14));
        theGraphics.drawString("Elf", theHeroStat.x + 10, theHeroStat.y + 15);
        theGraphics.drawString("Leech", theMonStat.x + 10, theMonStat.y + 15);
    }

    private static void addHealth(Graphics theGraphics, Rectangle heroStatus, Rectangle monStatus) {
        int heroHealth = 100; //will this be percent?
        int heroHealthBarPercent = (heroStatus.width - 40) / 100;
        heroHealthBarPercent = heroHealthBarPercent * heroHealth;

        int monsterHealth = 100;
        int monHealthBarPercent = (monStatus.width - 40) / 100;
        monHealthBarPercent = monHealthBarPercent * monsterHealth;

        theGraphics.setColor(Color.black);
        theGraphics.fillRect(heroStatus.x + 17, heroStatus.y + 23, heroHealthBarPercent, 20);
        theGraphics.fillRect(monStatus.x + 17, monStatus.y + 23, monHealthBarPercent, 20);

        theGraphics.setColor(Color.red);
        theGraphics.fillRect(heroStatus.x + 20, heroStatus.y + 25, heroHealthBarPercent - 6, 16);
        theGraphics.fillRect(monStatus.x + 20, monStatus.y + 25, monHealthBarPercent - 6, 16);

        theGraphics.drawString(heroHealth + "/" + 100,
                heroStatus.x + 20 + heroHealthBarPercent,
                heroStatus.y + 35);
        theGraphics.drawString(monsterHealth + "/" + 100,
                monStatus.x + 20 + heroHealthBarPercent,
                monStatus.y + 35);
    }
}
