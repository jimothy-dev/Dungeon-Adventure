package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Parent class for managing audio files and clips.
 */
public abstract class AudioManager {

    /**
     * Map of audio clips.
     */
    protected final Map<String, Clip> audioClips;

    /**
     * The clip that is currently playing.
     */
    protected Clip currentClip;

    /**
     * Constructor for AudioManager.
     */
    public AudioManager() {
        audioClips = new HashMap<>();
    }

    /**
     * Loads all audio files from a specified directory.
     *
     * @param directory containing all audio assets.
     */
    public void loadAllAudio(File directory) {
        loadAudioFiles(directory);
    }

    /**
     * Loads all .wav and .mp3 files from a file path.
     * Removes the file extension and makes the key lowercase.
     * Puts audio clips in the map.
     *
     * @param directory containing all audio assets.
     */
    private void loadAudioFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && (file.getName().endsWith(".wav") || file.getName().endsWith(".mp3"))) {
                    try {
                        Clip clip = loadClip(file);
                        String key = file.getName().replaceFirst("[.][^.]+$", "").toLowerCase();
                        audioClips.put(key, clip);
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        System.err.println("Error loading audio file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Creates a 'clip' from an audio stream made from the audio file.
     *
     * @param file containing audio.
     * @return a clip made from the audio file.
     */
    private Clip loadClip(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }

    /**
     * Stops the current audio clip.
     */
    public void stopAudio() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
        }
    }
}
