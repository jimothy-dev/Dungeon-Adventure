package View.Battle;

import Model.Character.Hero;
import Model.Character.Monster;

import java.util.Objects;

/**
 * Class instantiated to manage battle turns. Calculates turn based on character speeds.
 * @author James
 */
public class BattleTurnManager {

    /**
     * Monster object for battle.
     */
    private final Monster myMonster;

    /**
     * Hero object for battle.
     */
    private final Hero myHero;

    /**
     * True if it's the hero's turn.
     */
    private boolean myCurrentTurn;

    /**
     * Stacks the hero's speed per turn to see whose turn it currently is.
     */
    private int myHeroTime;

    /**
     * Stacks the monster's speed per turn to see whose turn it currently is.
     */
    private int myMonsterTime;

    /**
     * Constructor. Initializes fields. Makes call to calculate turn.
     * @param theHero Hero object for calculating turn
     * @param theMonster Monster object for calculating turn.
     */
    public BattleTurnManager(final Hero theHero, final Monster theMonster) {
        super();
        myHero = Objects.requireNonNull(theHero);
        myMonster = Objects.requireNonNull(theMonster);
        myHeroTime += myHero.getSpeed();
        myMonsterTime += myMonster.getSpeed();
        calculateTurn();
    }

    /**
     * Calculates current turn. Larger 'speed' value will attack more often.
     * Increments turn of non-turn character.
     */
    public void calculateTurn() {
        myCurrentTurn = myHeroTime >= myMonsterTime;
        if (myCurrentTurn) {
            myMonsterTime += myMonster.getSpeed();
        } else {
            myHeroTime += myHero.getSpeed();
        }
    }

    /**
     * Tells whether it's user's turn.
     * @return True if user's turn.
     */
    public boolean getTurn() {
        return myCurrentTurn;
    }
}
