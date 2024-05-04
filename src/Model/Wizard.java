package Model;

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
        super("Wizard", 100, 25, 3, 0.1,
                new GameItem[] {new TimeTurner(), new SoulCharm()});
    }
}
