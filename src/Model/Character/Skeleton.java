package Model.Character;

import Model.Items.*;

import java.util.Random;

/**
 * This class represents a skeleton monster
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Skeleton extends Monster implements Healable {

    /**
     * RAND constant is for randomness for skeletons
     */
    final Random RAND = new Random();

    /**
     * Skeleton constructor creates a skeleton character and adds 1 random item
     * to it's bag to be looted by heros.
     */
    public Skeleton() {
        super("Skeleton", 60, 25, 1, 0.0, new GameItem[] {});
        super.addItemToBag(getRandomLoot());
    }

    /**
     * getRandomLoot method gets 1 of 4 random game items
     * to be stored in the skeleton's bag
     *
     * @return returns a random game item for the bag
     */
    private GameItem getRandomLoot() {
        int num = RAND.nextInt(4);
        GameItem result = null;
        switch (num) {
            case 0:
                result = new BoneSword();
            case 1:
                result = new HealthPotion();
            case 2:
                result = new ArchaicBoots();
            case 3:
                result = new GoldCoin();
        }
        return result;
    }

    /**
     * heal method increases skeleton's hp between 5 and 25 points at random
     */
    @Override
    public void heal() {
        int healAmount = RAND.nextInt(20) + 5;
        super.buffHP(healAmount);
    }
}
