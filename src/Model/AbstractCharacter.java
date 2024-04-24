package Model;

import java.util.Random;

public class AbstractCharacter {

    String myName;
    int myHP;
    int myDamage;
    int mySpeed;
    double myDodgeRate;
    Random rand = new Random();
  public  AbstractCharacter(String theName, int theHP, int theDamage, int theSpeed, int theDodgeRate) {
      myName = theName;
      myHP = theHP;
      myDamage = theDamage;
      mySpeed = theSpeed;
      myDodgeRate = theDodgeRate;
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

  public void useHealthPotion() {
      myHP += 25;
  }

  public void useDamagePotion() {
      myDamage += 10;
  }

  public void useSpeedPotion() {
      mySpeed += 1;
  }

  public void useEvasionPotion() {
      if (myDodgeRate < 0.6) {
          myDodgeRate += 0.1;
      }
  }
  public void heal() {
      int healthReturned = rand.nextInt(25) + 5;
      myHP += healthReturned;
  }
}
