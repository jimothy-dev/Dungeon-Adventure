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
        setEditable(false); // read-only
        setBackground(new Color(50, 50, 50, 180));
        setForeground(Color.white);

        // Enable line wrapping and wrap style
        setLineWrap(true);
        setWrapStyleWord(true);
        int boxHeight = FrameManager.getHeight() / 5;
        setBounds(0, FrameManager.getHeight() - boxHeight, FrameManager.getWidth() * 2 / 3, boxHeight - 10);
    }
}
