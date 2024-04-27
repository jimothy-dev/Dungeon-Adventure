package View;

import Controller.MazeGenerator;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlayingScreen extends GameScreen {

  private MazeGenerator generator;

  protected PlayingScreen(GameScreenStack stack) {
    super(stack);
    generator = new MazeGenerator();
    this.generateMaze();
  }

  @Override
  protected void loop() {

  }

  @Override
  protected void render(Graphics graphics) {
    for(int i = 0; i < MazeGenerator.WORLD_SIZE; i++){
      for(int j = 0; j < MazeGenerator.WORLD_SIZE; j++) {

      }
    }
  }

  @Override
  protected void keyPressed(int keyCode) {
    if(keyCode == KeyEvent.VK_ENTER) this.generateMaze();
  }

  @Override
  protected void keyReleased(int keyCode) {

  }

  protected void playBackgroundMusic() {

  }

  @Override
  protected void stopBackgroundMusic() {

  }

  @Override
  protected void playSoundEffect(String theEffectName) {

  }

  private void generateMaze() {
    while(!generator.finished()) {
      generator.generate();
    }
  }
}
