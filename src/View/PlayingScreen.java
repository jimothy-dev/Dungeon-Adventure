package View;

import Controller.MathHelper;
import Controller.MathHelper.Direction;
import Controller.MazeGenerator;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

public class PlayingScreen extends GameScreen {

  private MazeGenerator generator;

  protected PlayingScreen(GameScreenStack stack) {
    super(stack);
    generator = new MazeGenerator();
    this.generateMaze();

//    try {
//      roomImage = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

  @Override
  protected void loop() {

  }

  @Override
  protected void render(Graphics graphics) {
    Image image = null;
    for(int i = 0; i < generator.getWORLD_SIZE(); i++){
      for(int j = 0; j < generator.getWORLD_SIZE(); j++) {
        try {
        image = ImageIO.read(getRoom(generator.getDataForRoom(j, i)));
      } catch (IOException e) {
        e.printStackTrace();
      }
        graphics.drawImage(image, j*50, i*50, 50,50,null);
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

  @Override
  protected void playBackgroundMusic() {

  }

  @Override
  protected void stopBackgroundMusic() {

  }

  @Override
  protected void playSoundEffect(String theEffectName) {

  }

  private void generateMaze() {
    this.generator.reset(10);
    while(!generator.finished()) {
      generator.generate(10);
    }
    for(int i = 0; i < generator.getWORLD_SIZE(); i++){
      for(int j = 0; j < generator.getWORLD_SIZE(); j++){
        System.out.println(generator.getDataForRoom(j,i).toString());
      }
    }
  }

	public static File getRoom(HashSet<Direction> dirs) {
		if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST) && dirs.contains(MathHelper.Direction.NORTH))
			return new File("src/Assets/Directions/nswe.png");
		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/nes.png");
		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/sew.png");
		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.NORTH))
			return new File("src/Assets/Directions/nsw.png");
		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/new.png");
		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.SOUTH))
			return new File("src/Assets/Directions/ns.png");
		else if(dirs.contains(MathHelper.Direction.WEST) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/we.png");
		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/ne.png");
		else if(dirs.contains(MathHelper.Direction.NORTH) && dirs.contains(MathHelper.Direction.WEST))
			return new File("src/Assets/Directions/nw.png");
		else if(dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/se.png");
		else if(dirs.contains(MathHelper.Direction.SOUTH) && dirs.contains(MathHelper.Direction.WEST))
			return new File("src/Assets/Directions/sw.png");
		else if(dirs.contains(MathHelper.Direction.NORTH))
			return new File("src/Assets/Directions/n.png");
		else if(dirs.contains(MathHelper.Direction.SOUTH))
			return new File("src/Assets/Directions/s.png");
		else if(dirs.contains(MathHelper.Direction.WEST))
			return new File("src/Assets/Directions/w.png");
		else if(dirs.contains(MathHelper.Direction.EAST))
			return new File("src/Assets/Directions/e.png");
		else
			return new File("src/Assets/Directions/deh.png");
	}
}
