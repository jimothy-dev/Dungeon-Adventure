package View;

import Model.GameState;
import Model.GameStateStack;
import java.awt.Graphics;

public class MainMenu extends GameState {

    public MainMenu(GameStateStack manager) {
      super(manager);
    }

  @Override
  protected void init() {

  }

  @Override
  protected void loop() {

  }

  @Override
  protected void render(Graphics graphics) {
    graphics.fillRect(30,30,20,20);
  }

  @Override
  protected void keyPressed(int keyCode) {
  }

  @Override
  protected void keyReleased(int keyCode) {

  }


}
