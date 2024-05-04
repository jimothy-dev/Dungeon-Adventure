package View.Battle;

import View.FrameManager;

import javax.swing.*;
import java.awt.*;

public class DrawBattleLog {
    public static void drawBattleLog(final Graphics theGraphics, final JTextArea theLogArea) {
        // Calculate the height of the battle log box (1/5th of the screen height)
        int boxHeight = FrameManager.getHeight() / 5;

        // Set color and draw the background of the battle log area
        theGraphics.setColor(new Color(50, 50, 50, 180)); // Semi-transparent dark color
        theGraphics.fillRect(0, FrameManager.getHeight() - boxHeight, FrameManager.getWidth() * 2 / 3, boxHeight);

        // Draw "Battle Log:" text at the top of the battle log area
        theGraphics.setColor(Color.white);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 20));
        theGraphics.drawString("Battle Log:", 10, FrameManager.getHeight() - boxHeight + 20);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 14));
        // Draw the text from the battleLogArea
        String logText = theLogArea.getText();
        String[] lines = logText.split("\n");
        int yOffset = FrameManager.getHeight() - boxHeight + 40; // Adjust offset as needed

        // Iterate through each line and draw it
        for (String line : lines) {
            theGraphics.drawString(line, 10, yOffset);
            yOffset += 15; // Adjust line spacing as needed
        }
    }
}
