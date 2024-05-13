package Controller;

import Controller.MathHelper.Direction;
import Model.Tile;
import View.FrameManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

public class RoomData {

  public static final int SIZE_X = FrameManager.getWidth() / 5;
  public static final int SIZE_Y = FrameManager.getHeight() / 5;
  private BufferedImage wallTexture;
  private BufferedImage tileTexture;
  private BufferedImage leftWall;
  private BufferedImage rightWall;
  private BufferedImage blank;

  private Tile[][] tilesData;
  private HashSet<Direction> exits;
  private int width;
  private int height;
  public RoomData(byte[][] tilesData, int width, int height, MathHelper.Direction... exits) {
    this.tilesData = new Tile[tilesData.length][tilesData[0].length];
    for(int i=0;i<this.tilesData.length;i++) {
      for(int j=0;j<this.tilesData[i].length;j++) {
        this.tilesData[i][j] = new Tile(tilesData[i][j], j, i, tilesData[i][j] == 1 || tilesData[i][j] == 2, SIZE_X, SIZE_Y);
      }
    }
    this.exits = new HashSet<>();
    for(MathHelper.Direction direction : exits) {
      this.exits.add(direction);
    }
    this.width = width;
    this.height = height;
    try {
      wallTexture = ImageIO.read(new File("src/Assets/Images/Wall.png"));
      tileTexture = ImageIO.read(new File("src/Assets/Images/Tile.png"));
      leftWall = ImageIO.read(new File("src/Assets/Images/leftWall.png"));
      rightWall = ImageIO.read(new File("src/Assets/Images/rightWall.png"));
      blank = ImageIO.read(new File("src/Assets/Images/blank.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Byte getTile(int x, int y){
    return tilesData[x][y].getID();
  }

  public int getSizeX() {
    return tilesData.length;
  }

  public int getSizeY() {
    return tilesData[0].length;
  }

  public void render(Graphics graphics) {
    for(int i =0; i < tilesData.length; i++){
      for(int j=0; j < tilesData[i].length; j++){
        if((i == 0 && j == 0) || (i == tilesData.length-1 && j == 0)
            || (i == tilesData.length -1 && j == tilesData.length -1)
            || (i == 0 && j == tilesData.length -1)) {
          graphics.drawImage(blank, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        } else if(this.tilesData[i][j].getID() == 0) {
          graphics.drawImage(tileTexture, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        } else if (this.tilesData[i][j].getID() == 1 && j == 0) {
          graphics.drawImage(leftWall, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        } else if (this.tilesData[i][j].getID() == 1 && j == tilesData[i].length -1) {
          graphics.drawImage(rightWall, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        }
        else {
          graphics.drawImage(wallTexture, j * SIZE_X, i * SIZE_Y, SIZE_X, SIZE_Y, null);
        }

      }
    }
  }

  public HashSet<MathHelper.Direction> getExits() {
    return exits;
  }

  public Tile getTileAt(int x, int y) {
    return tilesData[x][y];
  }

}
