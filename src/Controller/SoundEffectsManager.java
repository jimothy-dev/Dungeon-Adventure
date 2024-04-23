package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SoundEffectsManager {
    private Clip currentSoundEffect;
    private final Map<String, Clip> soundEffects;

    public SoundEffectsManager() {
        soundEffects = new HashMap<>();
    }

    public void loadAllSoundEffects() {
        File directory = new File("src/Assets/SoundEffects");
        loadAudioFiles(directory);

    }

    private void loadAudioFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && (file.getName().endsWith(".wav") || file.getName().endsWith(".mp3"))) {
                    try {
                        Clip clip = loadClip(file);
                        String key = file.getName().replaceFirst("[.][^.]+$", "").toLowerCase();
                        soundEffects.put(key, clip);
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        System.err.println("Error loading sound effect file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Clip loadClip(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }

    public void playSoundEffect(String key) {
        stopSoundEffect();
        currentSoundEffect = soundEffects.get(key.replaceFirst("[.][^.]+$", "").toLowerCase());
        if (currentSoundEffect != null) {
            currentSoundEffect.loop(1);
            currentSoundEffect.start();
        } else {
            System.err.println("Sound effect not found for key: " + key);
        }
    }

    public void stopSoundEffect() {
        if (currentSoundEffect != null && currentSoundEffect.isRunning()) {
            currentSoundEffect.stop();
        }
    }
}