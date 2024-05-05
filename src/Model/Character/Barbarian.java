package Model.Character;

import Model.Items.GameItem;

/**
 * This class represents a Barbarian hero.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Barbarian extends Hero {

    /**
     * Barbarian constructor creates a Barbarian hero character
     */
    public Barbarian() {
        super("Barbarian", 150, 50, 4, 0.3, new GameItem[] {});
    }
}
