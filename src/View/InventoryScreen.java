package View;

import Model.Character.AbstractCharacter;
import Model.Character.Hero;
import Model.GameScreen;
import Model.GameScreenStack;
import Model.Items.GameItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class InventoryScreen extends GameScreen {
    private static final String EMPTY = "Empty";
    private final Hero myHero;
    private String[] itemOptions;
    private final GameItem[] gameItems;
    private int selected;
    private Image inventoryBackgroundImage;

    protected InventoryScreen(final GameScreenStack theStack, final Hero theHero) {
        super(Objects.requireNonNull(theStack));
        myHero = Objects.requireNonNull(theHero);
        AbstractCharacter.Bag myInventory = myHero.getBag();
        gameItems = myInventory.getItems();
        selected = 0;

        List<String> tempList = new ArrayList<>();
        for (GameItem item : myInventory.getItems()) {
            tempList.add(item.getItemName());
        }
        itemOptions = tempList.toArray(new String[tempList.size()]);
        try {
            inventoryBackgroundImage = ImageIO.read(new File(
//                    "src/Assets/Images/inventoryBackground.png"));
                    "src/Assets/Images/dungeon background 4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(final Graphics theGraphics) {
        theGraphics.drawImage(inventoryBackgroundImage, 0, 0,
                FrameManager.getWidth(),
                FrameManager.getHeight(), null);
        theGraphics.setFont(getCustomFont());
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 24));
        theGraphics.setColor(Color.WHITE);
        int startY = FrameManager.getHeight() / 2;
        if (itemOptions.length == 0) {
            itemOptions = new String[] {EMPTY};
        }
        FontMetrics fontMetrics = theGraphics.getFontMetrics();
        int maxOptionWidth = 0;
        for (String option : itemOptions) {
            int optionWidth = fontMetrics.stringWidth(option);
            if (optionWidth > maxOptionWidth) {
                maxOptionWidth = optionWidth;
            }
        }
        int startX = (FrameManager.getWidth() - maxOptionWidth) / 2;
        for (int i = 0; i < itemOptions.length; i++) {
            boolean isSelected = (i == selected);
            if (isSelected) {
                theGraphics.setColor(Color.YELLOW);
            }
            theGraphics.drawString(itemOptions[i], startX, startY + i * 30);
            theGraphics.setColor(Color.WHITE);
        }
    }

    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (selected > 0) selected--;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (selected < itemOptions.length - 1) selected++;
                break;
            case KeyEvent.VK_ESCAPE:
                gameScreenStack.backToPreviousState();
                break;
            case KeyEvent.VK_ENTER:
                if (!itemOptions[selected].equals(EMPTY)) {
                    gameScreenStack.backToPreviousState();
                    System.out.println(myHero.useItem(gameItems[selected]));
                }
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
