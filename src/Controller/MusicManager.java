package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MusicManager {
    private Clip currentBackgroundMusic;
    private final Map<String, Clip> backgroundMusic;

    public MusicManager() {
        backgroundMusic = new HashMap<>();
    }

    public void loadAllBackgroundMusic() {
        File directory = new File("src/Assets/BackgroundMusic");
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
                        backgroundMusic.put(key, clip);
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        System.err.println("Error loading background music file: " + file.getName());
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

    public void playBackgroundMusic(String key) {
        stopBackgroundMusic();
        currentBackgroundMusic = backgroundMusic.get(key.replaceFirst("[.][^.]+$", "").toLowerCase());
        if (currentBackgroundMusic != null) {
            currentBackgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            currentBackgroundMusic.start();
        }
    }

    public void stopBackgroundMusic() {
        if (currentBackgroundMusic != null && currentBackgroundMusic.isRunning()) {
            currentBackgroundMusic.stop();
        }
    }
}