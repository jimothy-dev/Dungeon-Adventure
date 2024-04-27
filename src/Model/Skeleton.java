package Model;

import java.util.Random;

public class Skeleton extends Monster implements Healable {

    Random rand = new Random();

    public Skeleton() {
        super("Skeleton", 60, 25, 1, 0.0, new GameItem[] {});
        super.addItemToBag(getRandomLoot());
    }

    private GameItem getRandomLoot() {
        int num = rand.nextInt(4);
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

    @Override
    public void heal() {
        int healAmount = rand.nextInt(20) + 5;
        super.buffHP(healAmount);
    }
}
