package Model;

/**
 * This class represents a Rogue hero
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Rogue extends Hero {

    /**
     * Rogue constructor creates an rogue hero character
     */
    public Rogue() {
        super("Rogue", 100, 30, 6, 0.5, new GameItem[] {});
    }
}
