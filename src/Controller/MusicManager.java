package Controller;

import Controller.AudioManager;

import javax.sound.sampled.Clip;
import java.io.File;

public class MusicManager extends AudioManager {

    /**
     * Constructor for MusicManager.
     */
    public MusicManager() {
        super();
    }

    /**
     * Called from game states to play their requested audio.
     *
     * @param key used to access clip within the hash map.
     */
    public void playAudio(String key) {
        stopAudio();
        currentClip = audioClips.get(key.replaceFirst("[.][^.]+$", "").toLowerCase());
        if (currentClip != null) {
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            currentClip.start();
        }
    }

    @Override
    public void loadAllAudio(File directory) {
        super.loadAllAudio(directory);
    }
}