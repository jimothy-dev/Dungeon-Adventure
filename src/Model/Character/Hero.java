package Model.Character;

import Model.Items.GameItem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    /**
     * Save the hero to a file.
     *
     * @param theFilePath is the path to the file where the hero should be saved.
     */
    public void saveHero(String theFilePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(theFilePath))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a hero from a file.
     *
     * @param theFilePath is the path to the file from which the hero should be loaded.
     * @return the loaded hero.
     */
    public static Hero loadHero(String theFilePath) {
        Hero hero = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(theFilePath))) {
            hero = (Hero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hero;
    }
}

