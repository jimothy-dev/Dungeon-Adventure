package Model.Character;

import Model.Items.GameItem;

/**
 * This class represents all heros at an abstract level.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class Hero extends AbstractCharacter {

    /**
     * Hero constructor provides a level of polymorphism
     * to interact with hero characters under one type
     *
     * @param theName is the name of the hero
     * @param theHP is the health points for the hero
     * @param theDamage is the damage points for the hero
     * @param theSpeed is the speed points for the hero
     * @param theDodgeRate is the dodge rate for the hero
     * @param theItems is the initial items in the hero's bag
     */
    public Hero(String theName, int theHP, int theDamage, int theSpeed, double theDodgeRate, GameItem[] theItems) {
        super(theName, theHP, theDamage, theSpeed, theDodgeRate, theItems);
    }
}
