package Model;

import java.util.ArrayList;
import java.util.Random;

public class AbstractCharacter {

    String myName;
    int myHP;
    int myDamage;
    int mySpeed;
    double myDodgeRate;

    Bag myBag;
    Random rand = new Random();
  public  AbstractCharacter(String theName, int theHP, int theDamage, int theSpeed, double theDodgeRate, GameItem[] theItems) {
      myName = theName;
      myHP = theHP;
      myDamage = theDamage;
      mySpeed = theSpeed;
      myDodgeRate = theDodgeRate;
      myBag = new Bag(theItems);
  }

  public boolean attacked(int theDamage) {
      boolean hitLanded = false;
      double dodge = rand.nextDouble();
      if (dodge >= myDodgeRate) {
          takeDamage(theDamage);
          hitLanded = true;
      }
      return hitLanded;
  }

  public void takeDamage(int theDamage) {
      myHP = myHP - theDamage;
  }

  public int getHP() {
      return myHP;
  }

  public int getDamage() {
      return myDamage;
  }

  public int getSpeed() {
      return mySpeed;
  }

  public boolean attack(AbstractCharacter theOtherCharacter) {
      boolean attackLanded = theOtherCharacter.attacked(myDamage);
      return attackLanded;
  }

  public void buffHP(int theHP) {
      myHP += theHP;
  }

  public void buffDamage(int theDamage) {
      myDamage += theDamage;
  }

  public void buffSpeed(int theSpeed) {
      mySpeed += theSpeed;
  }

  public void buffDodgeRate(double theDodgeRate) {
      if (myDodgeRate <= 0.6) {
          myDodgeRate += theDodgeRate;
      }
  }

  public void addItemToBag(GameItem theItem) {
      myBag.addItem(theItem);
  }

  public String useItem(GameItem theItem) {
      String result = "Bag does not contain this item!";
      if (myBag.hasItem(theItem)) {
          switch (theItem.getItemName()) {
              case "Health Potion":
                  int hp = rand.nextInt(15) + 15;
                  buffHP(hp);
                  myBag.removeItem(theItem);
                  result = "Hero drank health potion. Hero's health increased " + hp + " points!";
              case "Damage Potion":
                  int dp = rand.nextInt(10) + 5;
                  buffDamage(dp);
                  myBag.removeItem(theItem);
                  result = "Hero drank damage potion. Hero's damage increased " + dp + " points!";
              case "Speed Potion":
                  int sp = rand.nextInt(3) + 1;
                  buffSpeed(sp);
                  myBag.removeItem(theItem);
                  result = "Hero drank speed potion. Hero's speed increased " + sp + " points!";
              case "Evasion Potion":
                  if (myDodgeRate <= 0.6) {
                      double ep = 0.1 * (rand.nextInt(3) + 1);
                      buffDodgeRate(ep);
                      myBag.removeItem(theItem);
                      result = "Hero drank evasion potion. Hero's dodge rate increased by " + (ep * 100) + " percent!";
                  } else result = "Hero's dodge rate is maximized! Potion was not used.";
              case "Archaic Boots":
                  buffSpeed(1);
                  myBag.removeItem(theItem);
                  result = "Hero donned the Archaic Boots. Hero's speed increased by 1 point!";
              case "Bone Sword":
                  buffDamage(10);
                  myBag.removeItem(theItem);
                  result = "Hero picked up Bone Sword. Hero's damage increased by 10 points!";
              case "Gold Coin" :
                  result = "Hero admired the gold coin, then put it back in the bag for later.";
          }
      }
      return result;
  }

  public class Bag {
      ArrayList<GameItem> myBag;

      public Bag(GameItem[] theItems) {
          myBag = new ArrayList<GameItem>();
          for (GameItem item : theItems) {
              myBag.add(item);
          }
      }

      public void addItem(GameItem theItem) {
          myBag.add(theItem);
      }

      public void removeItem(GameItem theItem) {
          myBag.remove(theItem);
      }

      public GameItem getItem(int theIndex) {
          return myBag.get(theIndex);
      }

      public GameItem[] getItems() {
          GameItem[] items = new GameItem[myBag.size()];
          int i = 0;
          for (GameItem item : myBag) {
              items[i++] = item;
          }
          return items;
      }

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
