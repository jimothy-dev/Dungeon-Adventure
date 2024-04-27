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

  public void buffDamage() {
      myDamage += 10;
  }

  public void buffSpeed(int theSpeed) {
      mySpeed += theSpeed;
  }

  public void buffDodgeRate(double theDodgeRate) {
      if (myDodgeRate <= 0.6) {
          myDodgeRate += theDodgeRate;
      }
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
  }
}
