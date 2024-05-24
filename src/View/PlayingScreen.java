package View;

import Controller.MathHelper;
import Controller.MathHelper.Direction;
import Controller.MazeGenerator;
import Controller.RoomData;
import Model.Dungeon;
import Model.PlayableHero;
import Model.GameScreen;
import Model.GameScreenStack;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import javax.imageio.ImageIO;

public class PlayingScreen extends GameScreen {

  private MazeGenerator generator;
  private Dungeon dungeon;
  private boolean[][] visited;
  private boolean showMaze;
  private int monsterX;
  private int monsterY;

  private Image monsterImg;
  private PlayableHero player;


    protected PlayingScreen(GameScreenStack stack) {
    super(stack);
    this.generator = new MazeGenerator();
    this.generateMaze();
    this.visited = new boolean[5][5];
    visited[0][0] = true;
    visited[4][4] = true;
    this.player = new PlayableHero((byte)0, FrameManager.getWidth()/2, FrameManager.getHeight()/2);
    this.showMaze = false;
    int[] monsterPos = generateMonsterPosition();
    this.monsterX = monsterPos[0];
    this.monsterY = monsterPos[1];

    try {
      monsterImg = ImageIO.read(new File("src/Assets/Images/skeleton1.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void loop() {
    this.player.move();
    this.dungeon.changeRoom(player);
    visited[dungeon.getCurrY()][dungeon.getCurrX()] = true;
    RoomData roomIn = this.dungeon.getRoom().getData();
    for(int i=0; i < roomIn.getSizeX(); i++){
      for(int j=0; j < roomIn.getSizeY(); j++){
        this.player.handleCollisionWith(this.dungeon.getRoom().getData().getTileAt(i, j));
      }
    }
  }

  @Override
  protected void render(Graphics graphics) {
//    Image imageOne = null;
//    for(int i = 0; i < generator.getWORLD_SIZE(); i++){
//      for(int j = 0; j < generator.getWORLD_SIZE(); j++) {
//        try {
//        imageOne = ImageIO.read(getRoom(generator.getDataForRoom(j, i)));
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//        graphics.drawImage(imageOne, j*50, i*50, 50,50,null);
//      }
//    }
      //tempRoom.getData().render(graphics);
      dungeon.getRoom().getData().render(graphics);
      //Maze Drawing
//      for(int i = 0; i < generator.getWORLD_SIZE(); i++){
//        for(int j = 0; j < generator.getWORLD_SIZE(); j++){
//          Image image = null;
//          Image imageTwo = null;
//          try {
//          image = ImageIO.read(getRoom(dungeon.getRoom(j, i).getData().getExits()));
//          imageTwo = ImageIO.read(getRoom(generator.getDirForRoom(j, i)));
//          } catch (IOException e) {
//          e.printStackTrace();
//          }
//          graphics.drawImage(imageTwo, 400+j*30, 200+i*30, 30, 30, null);
//        }
//      }
    this.player.render(graphics);
    if(showMaze){
      for(int i = 0; i < generator.getWORLD_SIZE(); i++){
        for(int j = 0; j < generator.getWORLD_SIZE(); j++){
          int x = FrameManager.getWidth() - (generator.getWORLD_SIZE() - j) * 33;
          int y = FrameManager.getHeight() - (generator.getWORLD_SIZE() - i) * 24;
          if(visited[i][j]) {
            Image image = null;
            try {
              image = ImageIO.read(getRoom(generator.getDirForRoom(j, i)));
            } catch (IOException e) {
              e.printStackTrace();
            }
            graphics.drawImage(image, x, y, 33, 24, null);
          }
          else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y, 33, 24);
          }
        }
      }
    }
    if(dungeon.getCurrX() + dungeon.getCurrY() == 1){
        graphics.drawImage(monsterImg, FrameManager.getWidth() / 2, FrameManager.getHeight() / 2, 33, 24, null);
    }
  }

  @Override
  protected void keyPressed(int keyCode) {
//
//    if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP && roomY > 0) this.roomY--;
//    else if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT && roomX > 0) this.roomX--;
//    else if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN && roomY < generator.getWORLD_SIZE()-1) this.roomY++;
//    else if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT && roomX < generator.getWORLD_SIZE()-1) this.roomX++;
//    else if(keyCode == KeyEvent.VK_ENTER) this.generateMaze();
    switch(keyCode) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        this.player.setMovingUp(true);
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        this.player.setMovingDown(true);
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        this.player.setMovingRight(true);
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        this.player.setMovingLeft(true);
        break;
      case KeyEvent.VK_T:
        this.showMaze = true;
        break;
        case KeyEvent.VK_ENTER:
          if(dungeon.getCurrY() + dungeon.getCurrX() == 1){
//            gameScreenStack.addScreen(new BattleScreen(gameScreenStack));
          }
          break;

    }
  }

  @Override
  protected void keyReleased(int keyCode) {
    switch(keyCode) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        this.player.setMovingUp(false);
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        this.player.setMovingDown(false);
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        this.player.setMovingRight(false);
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        this.player.setMovingLeft(false);
        break;
      case KeyEvent.VK_T:
        this.showMaze = false;
        break;
    }

  }

  @Override
  protected void playSoundEffect(String theEffectName) {

  }

  private void generateMaze() {
    this.generator.reset(5);
    while(!generator.finished()) {
      generator.generate(5);
    }
    this.dungeon = new Dungeon(this.generator.getRoomData(), generator.getWORLD_SIZE());
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

  private void collision(){}

  public int[] generateMonsterPosition() {
    Random rand = new Random();
    int[] result = new int[2];
    int worldSize = generator.getWORLD_SIZE();
    do {
      result[0] = rand.nextInt(worldSize+1);
      result[1] = rand.nextInt(worldSize+1);
    } while ((result[0] == 0 && result[1] == 0) || (result[0] == worldSize && result[1] == worldSize));

    return result;
  }
}
