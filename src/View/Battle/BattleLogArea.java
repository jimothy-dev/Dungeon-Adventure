package View.Battle;

import View.FrameManager;

import javax.swing.JTextArea;
import java.awt.Color;

/**
 * Object to be set as BattleScreen PrintStream to display battle text on screen.
 *
 * @author James
 */
public class BattleLogArea extends JTextArea {

    /**
     * Constructor initializes BattleLogArea.
     */
    public BattleLogArea() {
        super();
        this.setEditable(false); // read-only
        this.setBackground(new Color(50, 50, 50, 180));
        this.setForeground(Color.white);

        // Enable line wrapping and wrap style
        this.setLineWrap(true);
        this.setWrapStyleWord(true);

        // Set the size and position of battleLogArea
        this.setBounds(10, FrameManager.getHeight() - FrameManager.getHeight() / 5,
                FrameManager.getWidth() - 20, FrameManager.getHeight() / 5);
    }
}
