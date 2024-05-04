package View.Battle;

import View.FrameManager;

import javax.swing.*;
import java.awt.*;

public class BattleLogArea extends JTextArea {
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
