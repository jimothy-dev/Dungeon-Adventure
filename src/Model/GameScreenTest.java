package Model;

import static org.junit.jupiter.api.Assertions.*;

import Controller.AudioManager;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class GameScreenTest {

    private static class TestGameScreen extends GameScreen {
        protected TestGameScreen(GameScreenStack manager) {
            super(manager);
        }

        @Override
        protected void loop() {

        }

        @Override
        protected void render(Graphics graphics) {

        }

        @Override
        protected void keyPressed(int keyCode) {

        }

        @Override
        protected void keyReleased(int keyCode) {

        }
    }

    @Test
    void testProtectedMembers() throws Exception {
        GameScreenStack gameScreenStack = new GameScreenStack();
        TestGameScreen testGameScreen = new TestGameScreen(gameScreenStack);

        // Use reflection to access protected members
        Field gameScreenStackField = GameScreen.class.getDeclaredField("gameScreenStack");
        Field musicManagerField = GameScreen.class.getDeclaredField("musicManager");
        Field soundManagerField = GameScreen.class.getDeclaredField("soundManager");

        // Make the fields accessible
        gameScreenStackField.setAccessible(true);
        musicManagerField.setAccessible(true);
        soundManagerField.setAccessible(true);

        // Retrieve the values of the fields
        GameScreenStack retrievedGameScreenStack = (GameScreenStack) gameScreenStackField.get(testGameScreen);
        AudioManager retrievedMusicManager = (AudioManager) musicManagerField.get(testGameScreen);
        AudioManager retrievedSoundManager = (AudioManager) soundManagerField.get(testGameScreen);

        // Perform assertions
        assertNotNull(retrievedGameScreenStack);
        assertNotNull(retrievedMusicManager);
        assertNotNull(retrievedSoundManager);
    }

    @Test
    void testProtectedMethods() throws Exception {
        GameScreenStack gameScreenStack = new GameScreenStack();
        TestGameScreen testGameScreen = new TestGameScreen(gameScreenStack);

        // Use reflection to access protected methods
        Method playBackgroundMusicMethod = GameScreen.class.getDeclaredMethod("playBackgroundMusic", String.class);
        Method stopBackgroundMusicMethod = GameScreen.class.getDeclaredMethod("stopBackgroundMusic");
        Method playSoundEffectMethod = GameScreen.class.getDeclaredMethod("playSoundEffect", String.class);

        // Make the methods accessible
        playBackgroundMusicMethod.setAccessible(true);
        stopBackgroundMusicMethod.setAccessible(true);
        playSoundEffectMethod.setAccessible(true);

        // Invoke the methods
        playBackgroundMusicMethod.invoke(testGameScreen, "test");
        stopBackgroundMusicMethod.invoke(testGameScreen);
        playSoundEffectMethod.invoke(testGameScreen, "test");
    }
}
