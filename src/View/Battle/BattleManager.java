package View.Battle;

import Controller.AudioManager;
import Model.Character.*;
import Model.GameScreenStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * BattleManager runs battle when enemy is encountered.
 * @author James
 */
public class BattleManager {

    private static final Random RANDOM = new Random();

    private static final int HIT_CLIPS = 3;

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

    private final AudioManager mySoundEffectManager;

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
    public BattleManager(final GameScreenStack theGameScreenStack, final Hero theHero, final Monster theMonster, final BattleTurnManager theTurnManager) {
        myHero = Objects.requireNonNull(theHero);
        myMonster = Objects.requireNonNull(theMonster);
        myTurnManager = Objects.requireNonNull(theTurnManager);
        monsterHealthThreshold = myMonster.getMaxHP() / 3;
        mySoundEffectManager = theGameScreenStack.getSoundManager();
    }

    /**
     * Takes place if user selects 'Attack'.
     * Recalculates turn.
     */
    public void heroAttack() {
        if (myHero.attack(myMonster)) {
            System.out.println(myHero.getName() + " did " + myHero.getDamage() + " damage to " + myMonster.getName());
            mySoundEffectManager.playAudio("BattleHit" + RANDOM.nextInt(1, HIT_CLIPS + 1),false);
        } else {
            System.out.println(myHero.getName() + "'s attack missed!");
            mySoundEffectManager.playAudio("BattleMiss",false);

        }
        if (myMonster.checkIfDead()) {
            System.out.print("Victory! " + myHero.getName() + " received: ");
            myHero.addRewardsToBag(myMonster.getReward());
            mySoundEffectManager.playAudio("BattleChest",false);

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
            ((Skeleton) myMonster).heal();
            System.out.println(myMonster.getName() + " healed " + (myMonster.getHP() - previousHP) + " HP");
            mySoundEffectManager.playAudio("BattleHeal", false);
            monsterHealed = true;
        }
        if (myMonster.getName().equals("Leech") && myMonster.getHP() < monsterHealthThreshold && !monsterHealed) {
            int previousHP = myMonster.getHP();
            System.out.println("Leech used Drain!");
            myMonster.attack(myHero);
            System.out.println(myMonster.getName() + " did " + myMonster.getDamage() + " damage to " + myHero.getName());
            ((Leech)myMonster).heal();
            System.out.println(myMonster.getName() + " restored " + (myMonster.getHP() -  previousHP) + " HP");
            mySoundEffectManager.playAudio("BattleHeal",false);
            monsterHealed = true;

        } else if (myMonster.attack(myHero)) {
            System.out.println(myMonster.getName() + " did " + myMonster.getDamage() + " damage to " + myHero.getName());
            mySoundEffectManager.playAudio("BattleHit" + RANDOM.nextInt(1, HIT_CLIPS + 1),false);
        } else {
            System.out.println(myMonster.getName() + "'s attack missed!");
            mySoundEffectManager.playAudio("BattleMiss",false);;
        }
        if (myHero.checkIfDead()) {
            System.out.println(myHero.getName() + " took too much damage! The hero has perished.");
            mySoundEffectManager.playAudio("BattleDeath",false);
        }
        myTurnManager.calculateTurn();
    }

    public void healHero() {
        if (myHero.getName().equals("Elf")) {
            int previousHP = myHero.getHP();
            ((Elf)myHero).heal();
            System.out.println(myHero.getName() + " healed " + (myHero.getHP() -  previousHP) + " HP");
            mySoundEffectManager.playAudio("BattleHeal",false);
            myTurnManager.calculateTurn();
        }
    }
}
