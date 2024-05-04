package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Parent class for managing audio files and clips.
 */
public class AudioManager {

    /**
     * Map of audio clips.
     */
    private final Map<String, Clip> audioClips;

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
    public Clip loadClip(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Clip clip = null;

        // Get the audio input stream from the file
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
            // Get the original audio format
            AudioFormat originalFormat = audioInputStream.getFormat();

            // Define the desired audio format
            AudioFormat desiredFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    originalFormat.getSampleRate(),
                    16, // 16-bit sample size
                    originalFormat.getChannels(), // Channels
                    originalFormat.getChannels() * 2, // Frame size
                    originalFormat.getSampleRate(), // Frame rate
                    false // Little-endian
            );

            // Convert the audio input stream to the desired format if necessary
            AudioInputStream convertedAudioStream = audioInputStream;
            if (!originalFormat.matches(desiredFormat)) {
                convertedAudioStream = AudioSystem.getAudioInputStream(desiredFormat, audioInputStream);
            }

            // Create a new audio clip
            clip = AudioSystem.getClip();
            clip.open(convertedAudioStream);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.err.println("Error loading audio file: " + file.getName());
            e.printStackTrace();
        }
        return clip;
    }

    /**
     * Called from game states to play their requested audio.
     *
     * @param key used to access clip within the hash map.
     */
    public void playAudio(String key, final boolean theLoop) {
        stopAudio();
        currentClip = audioClips.get(key.toLowerCase());
        if (currentClip != null) {
            currentClip.setFramePosition(0); // Start from the beginning
            if (theLoop){
                currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            currentClip.start(); // Start playing
        } else {
            System.err.println("Audio clip not found for key: " + key);
        }
    }

    /**
     * Stops the current audio clip.
     */
    public void stopAudio() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.setFramePosition(0); // Reset the clip to the beginning
        }
    }
}
