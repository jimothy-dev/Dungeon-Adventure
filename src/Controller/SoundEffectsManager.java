package Controller;

import Controller.AudioManager;

import java.io.File;

public class SoundEffectsManager extends AudioManager {

    /**
     * Constructor for SoundEffectsManager.
     */
    public SoundEffectsManager() {
        super();
    }

    /**
     * Called from game states to play their requested audio.
     *
     * @param key used to access clip within the hash map.
     */
    public void playSoundEffect(String key) {
        stopAudio();
        currentClip = audioClips.get(key.replaceFirst("[.][^.]+$", "").toLowerCase());
        if (currentClip != null) {
            currentClip.loop(1);
            currentClip.start();
        } else {
            System.err.println("Sound effect not found for key: " + key);
        }
    }

    @Override
    public void loadAllAudio(File directory) {
        super.loadAllAudio(directory);
    }
}