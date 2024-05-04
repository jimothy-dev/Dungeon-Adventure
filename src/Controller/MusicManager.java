package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * MusicManager class manages music for the game
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class MusicManager {

    /**
     * myCurrentBackgroundMusic field is the
     * background music currently being played in the game
     */
    private Clip myCurrentBackgroundMusic;

    /**
     * BACKGROUND_MUSIC constant is a map that maps
     * strings to sound clips to be used in the game
     */
    private final Map<String, Clip> BACKGROUND_MUSIC;

    /**
     * MusicManager constructor initializes the
     * background music map
     */
    public MusicManager() {
        BACKGROUND_MUSIC = new HashMap<>();
    }

    /**
     * loadAllBackgroundMusic method gets the file
     * containing the background music and calls method loadAudioFiles
     */
    public void loadAllBackgroundMusic() {
        File directory = new File("src/Assets/BackgroundMusic");
        loadAudioFiles(directory);
    }

    /**
     * loadAudioFiles method recieves a file and adds all audio clips from
     * the file into the background music map.
     *
     * @param theDirectory is the file to retrieve audio clips from
     */
    private void loadAudioFiles(File theDirectory) {
        File[] files = theDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && (file.getName().endsWith(".wav") || file.getName().endsWith(".mp3"))) {
                    try {
                        Clip clip = loadClip(file);
                        String key = file.getName().replaceFirst("[.][^.]+$", "").toLowerCase();
                        BACKGROUND_MUSIC.put(key, clip);
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        System.err.println("Error loading background music file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * loadClip method loads clips from the passed file
     *
     * @param theFile is the file to retrieve clips from
     * @return returns an audio clip from the passed file
     * @throws IOException when file passed is not found/ of correct format
     * @throws UnsupportedAudioFileException when audio format is unsupported
     * @throws LineUnavailableException when no line of text is in the file
     */
    private Clip loadClip(File theFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(theFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }

    /**
     * playBackgroundMusic method plays the music mapped from the passed string
     *
     * @param theKey is the string key we want to use to get a sound clip
     */
    public void playBackgroundMusic(String theKey) {
        stopBackgroundMusic();
        myCurrentBackgroundMusic = BACKGROUND_MUSIC.get(theKey.replaceFirst("[.][^.]+$", "").toLowerCase());
        if (myCurrentBackgroundMusic != null) {
            myCurrentBackgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            myCurrentBackgroundMusic.start();
        }
    }

    /**
     * stopBackgroundMusic stops playing the current background music
     */
    public void stopBackgroundMusic() {
        if (myCurrentBackgroundMusic != null && myCurrentBackgroundMusic.isRunning()) {
            myCurrentBackgroundMusic.stop();
        }
    }
}