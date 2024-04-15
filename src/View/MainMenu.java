package View;

import Model.GameState;
import Model.GameStateStack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MainMenu extends GameState {
  private static final String START_GAME = "Start Game";
  private static final String QUIT_GAME = "Quit Game";
  private String[] optionMenu;
  private int selected;

    public MainMenu(GameStateStack manager) {
      super(manager);
      this.optionMenu = new String[] {START_GAME, QUIT_GAME};
      this.selected = 0;
    }

  @Override
  protected void loop() {

  }

  @Override
  protected void render(Graphics graphics) {
      graphics.setColor(new Color(30,30,70));
      graphics.fillRect(0, 0, WindowManager.WIDTH, WindowManager.HEIGHT);

    graphics.setFont(new Font("Araial", Font.PLAIN, 25));
    for(int i = 0; i < optionMenu.length; i++) {
        if (i == selected) graphics.setColor(Color.magenta);
        else graphics.setColor(Color.white);
        graphics.drawString(this.optionMenu[i], 20, 50 + i * 20);
      }
  }

  @Override
  protected void keyPressed(int keyCode) {
    switch(keyCode) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_W:
        if(this.selected > 0) this.selected--;
        break;
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_S:
        if(this.selected < this.optionMenu.length-1) this.selected++;
        break;
      case KeyEvent.VK_ENTER:
        switch(this.optionMenu[selected]) {
          case START_GAME:
            //start game
            break;
          case QUIT_GAME:
            System.exit(0);
            break;
        }
        break;
    }
  }

  @Override
  protected void keyReleased(int keyCode) {}


}
