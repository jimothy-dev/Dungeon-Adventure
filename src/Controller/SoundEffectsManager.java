package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * SoundEffectsManager class manages game sound effects
 *
 * @author Albert Meza, Austin maggert, and James Simpson
 * @version Spring 2024
 */
public class SoundEffectsManager {

    /**
     * myCurrentSoundEffect field is the sound effect to be played
     * at any time
     */
    private Clip myCurrentSoundEffect;

    /**
     * SOUND_EFFECTS constant is a map of strings to sound clips
     */
    private final Map<String, Clip> SOUND_EFFECTS;

    /**
     * SoundEffectsManager constructor initializes SOUND_EFFECTS constant
     */
    public SoundEffectsManager() {
        SOUND_EFFECTS = new HashMap<>();
    }

    /**
     * loadAllSoundEffects method retrieves a file containing sound effects,
     * and loads them via a call to loadAudioFiles
     */
    public void loadAllSoundEffects() {
        File directory = new File("src/Assets/SoundEffects");
        loadAudioFiles(directory);

    }

    /**
     * loadAudioFiles method loads the audio files into the sound effects map
     * from the passed file
     *
     * @param theDirectory is the file containing the audio clips
     */
    private void loadAudioFiles(File theDirectory) {
        File[] files = theDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && (file.getName().endsWith(".wav") || file.getName().endsWith(".mp3"))) {
                    try {
                        Clip clip = loadClip(file);
                        String key = file.getName().replaceFirst("[.][^.]+$", "").toLowerCase();
                        SOUND_EFFECTS.put(key, clip);
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        System.err.println("Error loading sound effect file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * loadClip method loads one clip from the passed file and returns it
     *
     * @param theFile is the file to retrieve a sound clip from
     * @return returns a sound clip
     * @throws IOException when file not found
     * @throws UnsupportedAudioFileException when audio clip is of unsupported format
     * @throws LineUnavailableException when no available line in the file exists
     */
    private Clip loadClip(File theFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(theFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }

    /**
     * playSoundEffect method plays the sound mapped to with the passed string key
     *
     * @param theKey is the string key to a sound clip in the sound effects map
     */
    public void playSoundEffect(String theKey) {
        stopSoundEffect();
        myCurrentSoundEffect = SOUND_EFFECTS.get(theKey.replaceFirst("[.][^.]+$", "").toLowerCase());
        if (myCurrentSoundEffect != null) {
            myCurrentSoundEffect.loop(1);
            myCurrentSoundEffect.start();
        } else {
            System.err.println("Sound effect not found for key: " + theKey);
        }
    }

    /**
     * stopSoundEffect stops playing the current sound effect
     */
    public void stopSoundEffect() {
        if (myCurrentSoundEffect != null && myCurrentSoundEffect.isRunning()) {
            myCurrentSoundEffect.stop();
        }
    }
}