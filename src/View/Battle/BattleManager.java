package View.Battle;

import Model.Character.*;

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
     * If monster is below health threshold, they will attempt to heal.
     */
    private final int monsterHealthThreshold;

    /**
     * Ensures the monster can only heal once in battle.
     */
    private boolean monsterHealed = false;


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
        monsterHealthThreshold = myMonster.getMaxHP() / 3;
    }

    /**
     * Takes place if user selects 'Attack'.
     * Recalculates turn.
     */
    public void heroAttack() {
        if (myHero.attack(myMonster)) {
            System.out.println(myHero.getName() + " did " + myHero.getDamage() + " damage to " + myMonster.getName());
        } else {
            System.out.println(myHero.getName() + "'s attack missed!");
        }
        if (myMonster.checkIfDead()) {
            System.out.print("Victory! " + myHero.getName() + " received: ");
            myHero.addRewardsToBag(myMonster.getReward());
        }
        myTurnManager.calculateTurn();
    }

    /**
     * Monster attacks/heals on their turn.
     * Recalculates turn.
     */
    public void monsterTurn() {
        if (myMonster.getName().equals("Skeleton") && myMonster.getHP() < monsterHealthThreshold && !monsterHealed) {
            int previousHP = myMonster.getHP();
            ((Skeleton)myMonster).heal();
            System.out.println(myMonster.getName() + " healed " + (myMonster.getHP() -  previousHP) + " HP");
            monsterHealed = true;
        } else if (myMonster.attack(myHero)) {
            System.out.println(myMonster.getName() + " did " + myMonster.getDamage() + " damage to " + myHero.getName());
        } else {
            System.out.println(myMonster.getName() + "'s attack missed!");
        }
        if (myHero.checkIfDead()) {
            System.out.println(myHero.getName() + " took too much damage! The hero has perished.");
        }
        myTurnManager.calculateTurn();
    }

    public void healHero() {
        if (myHero.getName().equals("Elf")) {
            int previousHP = myHero.getHP();
            ((Elf)myHero).heal();
            System.out.println(myHero.getName() + " healed " + (myHero.getHP() -  previousHP) + " HP");
            myTurnManager.calculateTurn();
        }
    }
}
