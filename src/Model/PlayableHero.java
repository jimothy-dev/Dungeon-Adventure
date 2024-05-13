package Model;

import Model.Character.AbstractCharacter;
import View.FrameManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayableHero extends Rectangle {

  private byte entityID;
  protected boolean up;
  protected boolean down;
  protected boolean left;
  protected boolean right;
  private Image heroImage;

  public PlayableHero(byte id, int x, int y) {
    super(x, y, 34, 24);
    this.entityID = id;
    this.up = false;
    this.down = false;
    this.left = false;
    this.right = false;
    try {
      this.heroImage = ImageIO.read(new File("src/Assets/Images/wizard.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public byte getID() {
    return entityID;
  }

  public void move() {
    if(up) {
      super.y-=5;
    }
    if(down) {
      super.y+=5;
    }
    if(left) {
      super.x-=5;
    }
    if(right) {
      super.x+=5;
    }
  }

  public void setMovingUp(boolean up) {
    this.up = up;
  }

  public void setMovingDown(boolean down) {
    this.down = down;
  }

  public void setMovingLeft(boolean left) {
    this.left = left;
  }

  public void setMovingRight(boolean right) {
    this.right = right;
  }

  public void setCenterX(int x) {
    super.x = x - super.width/2;
  }

  public void setCenterY(int y) {
    super.y = y - super.height/2;
  }

  public void render(Graphics graphics){
    graphics.drawImage(heroImage, super.x, super.y, 50, 50, null);
  }

  public void handleCollisionWith(Tile tile) {
    Rectangle intersection = this.intersection(tile);
    if(intersection.isEmpty() || !tile.isWall())
      return;
    if(intersection.width > intersection.height) {
      if(this.y < tile.y)
        this.y = tile.y - this.height;
      else
        this.y = tile.y + this.height;
    }
    else {
      if(this.x < tile.x)
        this.x = tile.x - this.width;
      else
        this.x = tile.x + this.width;
    }
  }
}
