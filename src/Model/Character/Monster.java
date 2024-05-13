package Model.Character;

import Model.Items.GameItem;

import java.util.Random;

/**
 * This class represents all monsters at an abstract level.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Monster extends AbstractCharacter {
    private static final String[] MONSTERS = {"Skeleton", "Leech"};
    private static final Random random = new Random();

    private final GameItem[] myItems;



    /**
     * Monster constructor provides a level of polymorphism
     * to interact with monster characters under one type
     *
     * @param theName is the name of the monster
     * @param theHP is the health points for the monster
     * @param theDamage is the damage points for the monster
     * @param theSpeed is the speed points for the monster
     * @param theDodgeRate is the dodge rate for the monster
     * @param theItems is the initial items in the monster's bag
     */
    public Monster(String theName, int theHP, int theDamage, int theSpeed, double theDodgeRate, GameItem[] theItems) {
        super(theName, theHP, theDamage, theSpeed, theDodgeRate, theItems);
        myItems = theItems;
    }

//    public Monster() {
//        this(MONSTERS[random.nextInt(MONSTERS.length)]);
//    }

//    public Monster(String theMonster) {
//        switch (theMonster) {
//            case "Skeleton":
//                this(theMonster, S)
//                break;
//
//        }
//    }


    public GameItem[] getReward() {
        return myItems;
    }
}
