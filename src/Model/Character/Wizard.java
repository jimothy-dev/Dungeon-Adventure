package Model.Character;

import Model.Items.GameItem;
import Model.Items.SoulCharm;
import Model.Items.TimeTurner;

/**
 * This class represents a Wizard hero
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Wizard extends Hero {

    /**
     * Wizard constructor creates a Wizard hero character
     */
    public Wizard() {
        super("Wizard", 100, 25, 2, 0.1,
                new GameItem[] {new TimeTurner(), new SoulCharm()});
    }
}
