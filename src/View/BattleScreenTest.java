package View;

import static org.junit.jupiter.api.Assertions.*;

import Model.GameScreenStack;
import View.Battle.BattleLogArea;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class BattleScreenTest {

    // Create a GameScreenStack instance to pass into BattleScreen constructor
    private final GameScreenStack gameScreenStack = new GameScreenStack();

    @Test
    void testBattleScreenConstructor() throws Exception {
        // Create an instance of BattleScreen
        BattleScreen battleScreen = new BattleScreen(gameScreenStack);

        // Use reflection to access protected fields
        Field optionMenuField = BattleScreen.class.getDeclaredField("optionMenu");
        Field selectedField = BattleScreen.class.getDeclaredField("selected");
        Field battleBackgroundImageField = BattleScreen.class.getDeclaredField("battleBackgroundImage");
        Field battleLogAreaField = BattleScreen.class.getDeclaredField("battleLogArea");

        // Make the fields accessible
        optionMenuField.setAccessible(true);
        selectedField.setAccessible(true);
        battleBackgroundImageField.setAccessible(true);
        battleLogAreaField.setAccessible(true);

        // Retrieve the values of the fields
        String[] optionMenu = (String[]) optionMenuField.get(battleScreen);
        int selected = (int) selectedField.get(battleScreen);
        Image battleBackgroundImage = (Image) battleBackgroundImageField.get(battleScreen);
        BattleLogArea battleLogArea = (BattleLogArea) battleLogAreaField.get(battleScreen);

        // Perform assertions to check the values
        assertNotNull(optionMenu);
        assertEquals(0, selected);
        assertNotNull(battleBackgroundImage);
        assertNotNull(battleLogArea);
    }

    @Test
    void testRenderMethod() throws Exception {
        GameScreenStack gameScreenStack = new GameScreenStack();
        BattleScreen battleScreen = new BattleScreen(gameScreenStack);

        // Use reflection to access the protected render method
        Method renderMethod = BattleScreen.class.getDeclaredMethod("render", Graphics.class);
        renderMethod.setAccessible(true);

        // Create a BufferedImage and obtain a Graphics object from it
        BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();

        // Make sure the clip bounds are set
        Rectangle clipBounds = new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        graphics.setClip(clipBounds);

        // Invoke the method and verify the output
        renderMethod.invoke(battleScreen, graphics);
    }

    @Test
    void testKeyPressedMethod() throws Exception {
        BattleScreen battleScreen = new BattleScreen(gameScreenStack);
        Method keyPressedMethod = BattleScreen.class.getDeclaredMethod("keyPressed", int.class);
        keyPressedMethod.setAccessible(true);
        keyPressedMethod.invoke(battleScreen, KeyEvent.VK_ENTER);

        // Perform assertions or verifications here based on expected behavior
    }

    @Test
    void testKeyReleasedMethod() throws Exception {
        BattleScreen battleScreen = new BattleScreen(gameScreenStack);

        // Use reflection to access the protected keyReleased method
        Method keyReleasedMethod = BattleScreen.class.getDeclaredMethod("keyReleased", int.class);
        keyReleasedMethod.setAccessible(true);

        // Invoke the method with a sample key code and verify the results
        keyReleasedMethod.invoke(battleScreen, KeyEvent.VK_ESCAPE);

        // Perform assertions or verifications here based on expected behavior
    }
}
