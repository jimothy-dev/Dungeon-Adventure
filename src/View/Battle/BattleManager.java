package View.Battle;

import Model.Character.Hero;
import Model.Character.Monster;

import java.util.Objects;

/**
 * BattleManager runs battle when enemy is encountered.
 * @author James
 */
public class BattleManager {

    /**
     * Hero object for battle.
     */
    private final Hero myHero;

    /**
     * Monster object for battle.
     */
    private final Monster myMonster;

    /**
     * Used to manage whose turn it is currently.
     */
    private final BattleTurnManager myTurnManager;

    /**
     * Manages battle.
     * @param theHero Hero object used in battle.
     * @param theMonster Monster object used in battle.
     * @param theTurnManager TurnManager used to check whose turn it is.
     */
    public BattleManager(final Hero theHero, final Monster theMonster, final BattleTurnManager theTurnManager) {
        myHero = Objects.requireNonNull(theHero);
        myMonster = Objects.requireNonNull(theMonster);
        myTurnManager = Objects.requireNonNull(theTurnManager);
    }

    /**
     * Takes place if user selects 'Attack'.
     * Recalculates turn.
     */
    public void heroAttack() {
        myMonster.attacked(myHero.getDamage());
        myTurnManager.calculateTurn();
        System.out.println(myHero.getName() + " did " + myHero.getDamage() + " damage to " + myMonster.getName());
    }

    /**
     * Monster attacks/heals on their turn.
     * Recalculates turn.
     */
    public void monsterAttack() {
        myHero.attacked(myMonster.getDamage());
        myTurnManager.calculateTurn();
        System.out.println(myMonster.getName() + " did " + myMonster.getDamage() + " damage to " + myHero.getName());
    }
}
