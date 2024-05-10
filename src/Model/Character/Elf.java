package Model.Character;
import Model.Items.GameItem;

import java.util.Random;

/**
 * This class represents an Elf hero.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Elf extends Hero implements Healable {

    /**
     * RAND constant is for randomness in Elf class
     */
    final Random RAND = new Random();

    /**
     * Elf constructor creates an elf hero character
     */
   public Elf() {
       super("Elf", 100,25, 1, 0.1, new GameItem[] {});
   }

    /**
     * heal method satisfies healable interface requirement
     * it heals the elf at random between 10 and 40 hp
     */
    public void heal() {
        int healAmount = RAND.nextInt(30) + 10;
        super.buffHP(healAmount);
    }
}
