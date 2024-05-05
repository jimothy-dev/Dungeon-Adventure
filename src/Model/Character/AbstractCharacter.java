package Model.Character;

import Model.Items.GameItem;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is the abstract level implementation for all game characters.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public class AbstractCharacter {

    /**
     * myName field is the name of the character.
     */
    private String myName;

    /**
     * myHP field is the character's health points.
     */
    private int myHP;

    /**
     * myMaxHP field is the character's max health points.
     */
    private int myMaxHP;

    /**
     * myDamage field is the character's damage points.
     */
    private int myDamage;

    /**
     * mySpeed field is the character's speed points.
     */
    private int mySpeed;

    /**
     * myDodgeRate field is the character's dodge rate on scale 0.0 - 0.7 (0% to 70%)
     */
    private double myDodgeRate;

    /**
     * myBag field is the character's inventory bag of game items.
     */
    private Bag myBag;

    /**
     * myDeathStatus is true iff character dies (hp drops to 0)
     */
    private boolean myDeathStatus;

    /**
     * rand is a Random generator for class usage.
     */
    private final Random RANDOM = new Random();

    /**
     * AbstractCharacter constructor initializes all fields.
     *
     * @param theName is the character's name
     * @param theHP is the character's health points
     * @param theDamage is the character's damaeage points
     * @param theSpeed is the character's speed points
     * @param theDodgeRate is the character's dodge rate
     * @param theItems are the items to be included in initial bag (can be empty)
     */
  public  AbstractCharacter(String theName, int theHP, int theDamage, int theSpeed,
                            double theDodgeRate, GameItem[] theItems) {
      myName = theName;
      myHP = theHP;
      myMaxHP = theHP;
      myDamage = theDamage;
      mySpeed = theSpeed;
      myDodgeRate = theDodgeRate;
      myBag = new Bag(theItems);
      myDeathStatus = false;
  }

    /**
     * attacked method receives an attack damage. Then it determines if the hit
     * lands using the dodge rate.If the hit lands, takeDamage is called.
     *
     * @param theDamage is the damage that will be inflicted on this character
     *                   if the attack lands
     * @return returns boolean true when the attack lands, and false otherwise
     */
  public boolean attacked(int theDamage) {
      boolean hitLanded = false;
      double dodge = RANDOM.nextDouble();
      if (dodge >= myDodgeRate) {
          takeDamage(theDamage);
          hitLanded = true;
      }
      return hitLanded;
  }

    /**
     * takeDamage method receives an integer for incoming damage, and removes
     * that much damage from this character's health points. If damage
     * inflicted causes the character's health points to drop below 1,
     * this character's isDead is assigned true.
     *
     * @param theDamage is the damage to be deducted from this character's hp
     */
  public void takeDamage(int theDamage) {
      myHP = myHP - theDamage;
      if (myHP < 1) {
          myHP = 0;
          myDeathStatus = true;
      }
  }

    /**
     * getHP method returns the character's current health points
     *
     * @return returns character's current health points
     */
  public int getHP() {
      return myHP;
  }

    /**
     * getDamage method returns the character's current damage points
     *
     * @return returns character's current damage points
     */
  public int getDamage() {
      return myDamage;
  }

    /**
     * getSpeed method returns the character's current speed points
     *
     * @return returns character's current speed points
     */
  public int getSpeed() {
      return mySpeed;
  }

    /**
     * attack method receives another character and attempts an attack on that
     * character. It will return truee if the attack lands, and false otherwise.
     *
     * @param theOtherCharacter is the character to be attacked
     * @return returns true when attack lands and false otherwise
     */
  public boolean attack(AbstractCharacter theOtherCharacter) {
      boolean attackLanded = theOtherCharacter.attacked(myDamage);
      return attackLanded;
  }

    /**
     * buffHP method receives a number of points to be added to
     * the character's health points up to it's max hp.
     *
     * @param theHP is the points to be added to hp, up to max hp
     */
  public void buffHP(int theHP) {
      myHP += theHP;
      if (myHP > myMaxHP) {
          myHP = myMaxHP;
      }
  }

    /**
     * buffMaxHP method receives a number of points to be added to
     * the characters maximum health points and heals character to the max.
     *
     * @param theHP is the points to be added to max HP
     */
    public void buffMaxHP(int theHP) {
        myMaxHP += theHP;
        myHP = myMaxHP;
    }

    /**
     * buffDamage method receives damage points, and adds
     * them to the character's current damage points.
     *
     * @param theDamage are the damage points to be added.
     */
  public void buffDamage(int theDamage) {
      myDamage += theDamage;
  }

    /**
     * buffSpeed method receives speed points, and adds
     * them to the character's current speed points.
     *
     * @param theSpeed are the speed points to be added.
     */
  public void buffSpeed(int theSpeed) {
      mySpeed += theSpeed;
  }

    /**
     * buffDodgeRate method receives a dodge rate, and adds
     * it to the character's current dodge rate up to the max.
     *
     * @param theDodgeRate is the dodge rate to be added up to max.
     */
  public void buffDodgeRate(double theDodgeRate) {
      myDodgeRate += theDodgeRate;
      if (myDodgeRate > 0.7) {
          myDodgeRate = 0.7;
      }
  }

    /**
     * checkIfDead method gives status of character's death
     *
     * @return boolean true when dead and false otherwise
     */
    public boolean checkIfDead() {
        return myDeathStatus;
    }

    /**
     * addItemToBag receives an item and puts it into the character's bag
     *
     * @param theItem is the item to be stored in the bag
     */
  public void addItemToBag(GameItem theItem) {
      myBag.addItem(theItem);
  }

    /**
     * useItem method checks if the item is in the characters bag,
     * and if it is, it will use it. When using it, it will call methods
     * to provide certain staus buff, and if applicable, destroy the object.
     *
     * @param theItem is the game item attempting to be used
     * @return returns a string as to what the item does, or that the bag didn't
     *          have the item
     */
  public String useItem(GameItem theItem) {
      String result = "Bag does not contain this item!";
      if (myBag.hasItem(theItem)) {
          switch (theItem.getItemName()) {
              case "Health Potion":
                  int hp = RANDOM.nextInt(15) + 15;
                  buffHP(hp);
                  myBag.removeItem(theItem);
                  result = "Hero drank health potion."
                            + " Hero's health increased " + hp + " points!";
              case "Damage Potion":
                  int dp = RANDOM.nextInt(10) + 5;
                  buffDamage(dp);
                  myBag.removeItem(theItem);
                  result = "Hero drank damage potion."
                            + " Hero's damage increased " + dp + " points!";
              case "Speed Potion":
                  int sp = RANDOM.nextInt(3) + 1;
                  buffSpeed(sp);
                  myBag.removeItem(theItem);
                  result = "Hero drank speed potion."
                            + " Hero's speed increased " + sp + " points!";
              case "Evasion Potion":
                  if (myDodgeRate <= 0.6) {
                      double ep = 0.1 * (RANDOM.nextInt(3) + 1);
                      buffDodgeRate(ep);
                      myBag.removeItem(theItem);
                      result = "Hero drank evasion potion."
                                + " Hero's dodge rate increased by "
                                + (ep * 100) + " percent!";
                  } else result = "Hero's dodge rate is maximized!"
                                    + " Potion was not used.";
              case "Archaic Boots":
                  buffSpeed(1);
                  myBag.removeItem(theItem);
                  result = "Hero donned the Archaic Boots."
                            + " Hero's speed increased by 1 point!";
              case "Bone Sword":
                  buffDamage(10);
                  myBag.removeItem(theItem);
                  result = "Hero picked up Bone Sword."
                            + " Hero's damage increased by 10 points!";
              case "Gold Coin" :
                  result = "Hero admired the gold coin,"
                            + " then put it back in the bag for later.";
          }
      }
      return result;
  }

    /**
     * inner class Bag is an inventory of game items for the character
     *
     * @author Austin Maggert
     * @version 03may2024
     */
  public class Bag {

        /**
         * myBag field stores the game items in an arraylist
         */
      ArrayList<GameItem> myBag;

        /**
         * Bag constructor recieves an array of items, cometimes empty,
         * and initializes myBag to the contents of the array.
         *
         * @param theItems the array of game items to be initialized with
         */
      public Bag(GameItem[] theItems) {
          myBag = new ArrayList<GameItem>();
          for (GameItem item : theItems) {
              myBag.add(item);
          }
      }

        /**
         * addItem method adds a game item to the bag
         *
         * @param theItem is the game item to be added to myBag
         */
      public void addItem(GameItem theItem) {
          myBag.add(theItem);
      }

        /**
         * removeItem method removes the item from the bag,
         * essentially destroying the game item.
         *
         * @param theItem is the game item to be removed
         */
      public void removeItem(GameItem theItem) {
          myBag.remove(theItem);
      }

        /**
         * getItem method provides the item at an index of the myBag arraylist
         *
         * @param theIndex is the index that we want the item from
         * @return returns the game item at theIndex in myBag
         */
      public GameItem getItem(int theIndex) {
          return myBag.get(theIndex);
      }

        /**
         * getItems method provides external class the contents of the bag
         *
         * @return returns an array containing each item in the bag
         */
      public GameItem[] getItems() {
          GameItem[] items = new GameItem[myBag.size()];
          int i = 0;
          for (GameItem item : myBag) {
              items[i++] = item;
          }
          return items;
      }

        /**
         * hasItem method checks if an item is in myBag
         *
         * @param theItem is the item to be checked if it is in the bag
         * @return returns true if the item is in the bag, false otherwise
         */
      public Boolean hasItem(GameItem theItem) {
          Boolean result = false;
          for (GameItem item : myBag) {
              if (item.getItemName().equals(theItem.getItemName())) {
                  result = true;
              }
          }
          return result;
      }
  }
}
