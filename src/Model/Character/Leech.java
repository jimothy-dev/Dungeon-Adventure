package Model.Character;

import Model.Items.*;

import java.util.Random;

/**
 * This class represents a leech monster.
 * Leeches have the ability to drain health from their enemies.
 * They also carry random loot items.
 *
 * @version Spring 2024
 */
public class Leech extends Monster implements Healable {

    /**
     * RAND constant is for randomness for leeches.
     */
    static final Random RAND = new Random();

    /**
     * Leech constructor creates a leech character and adds 1 random item
     * to its bag to be looted by heroes.
     */
    public Leech() {
        super("Leech", 80, 40, 1, 0, new GameItem[]{getRandomLoot()});
    }

    /**
     * getRandomLoot method gets 1 of 4 random game items
     * to be stored in the leech's bag.
     *
     * @return returns a random game item for the bag.
     */
    private static GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        return switch (num) {
            case 0 -> new DamagePotion();
            case 1 -> new HealthPotion();
            case 2 -> new SoulCharm();
            case 3 -> new TimeTurner();
            default -> null;
        };
    }

    /**
     * heal method increases leech's hp between 3 and 15 points at random.
     */
    @Override
    public void heal() {
        int healAmount = RAND.nextInt(13) + 3;
        super.buffHP(healAmount);
    }
}
