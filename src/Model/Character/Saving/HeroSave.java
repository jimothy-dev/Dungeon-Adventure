package Model.Character.Saving;

import Model.Character.Hero;

import java.io.*;

public class HeroSave {
    private static final String SAVED_GAME_DIR = "src/SavedGame/";

    /**
     * Save the hero to a file in the SavedGame directory.
     *
     * @param theHero the hero to save.
     * @param theFileName the name of the file.
     */
    public static void saveHero(final Hero theHero, final String theFileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SAVED_GAME_DIR + theFileName))) {
            outputStream.writeObject(theHero);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a hero from a file in the SavedGame directory.
     *
     * @param theFileName the name of the file.
     * @return the loaded hero.
     */
    public static Hero loadHero(final String theFileName) {
        Hero hero = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVED_GAME_DIR + theFileName))) {
            hero = (Hero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hero;
    }
}

