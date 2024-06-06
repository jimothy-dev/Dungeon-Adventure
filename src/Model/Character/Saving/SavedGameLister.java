package Model.Character.Saving;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for listing saved games.
 */
public class SavedGameLister {

    private static final String SAVED_GAME_DIR = "src/SavedGame/";

    /**
     * List all saved game files in the SavedGame directory.
     *
     * @return a list of file names.
     */
    public static List<String> listSavedGames() {
        File directory = new File(SAVED_GAME_DIR);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".sav"));
        List<String> savedGames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                savedGames.add(file.getName());
            }
        }
        return savedGames;
    }
}
