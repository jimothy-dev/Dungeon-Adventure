package Model;
import java.util.Random;

public class Elf extends Hero implements Healable {

    Random rand = new Random();
   public Elf() {
       super("Elf", 100,25, 3, 0.1, new GameItem[] {});
   }

    public void heal() {
        int healAmount = rand.nextInt(30) + 10;
        super.buffHP(healAmount);
    }
}
