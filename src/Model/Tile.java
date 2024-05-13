package Model;

import View.FrameManager;
import java.awt.Rectangle;

public class Tile extends Rectangle {

  private byte tileID;
  private boolean wall;
  private int sizeX;
  private int sizeY;

  public Tile(byte id, int posXinRoom, int posYinRoom, boolean isWall, int sizeX, int sizeY){
    super(posXinRoom * sizeX, posYinRoom * sizeY, sizeX, sizeY);
    this.tileID = id;
    this.wall = isWall;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
  }

  public byte getID() {
    return tileID;
  }

  public boolean isWall() {
    return wall;
  }

  public int getSizeX() { return sizeX; }
  public int getSizeY() { return sizeY; }
}
