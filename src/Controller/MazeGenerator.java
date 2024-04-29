package Controller;

import Controller.MathHelper.Direction;
import java.util.HashSet;

public class MazeGenerator {
  public int WORLD_SIZE = 5;
  private int posX;
  private int posY;

  private HashSet<MathHelper.Direction>[][] roomData;
  private boolean generated[][];


  @SuppressWarnings("unchecked")
  public void reset(int size){
    WORLD_SIZE = size;
    roomData = new HashSet[WORLD_SIZE][WORLD_SIZE];
    generated = new boolean[WORLD_SIZE][WORLD_SIZE];
    for(int i=0; i < roomData.length; i++){
      for(int j=0; j < roomData[i].length; j++){
        this.roomData[i][j] = new HashSet<>();
        this.generated[i][j] = false;
      }
    }
    setRandomPosition();
  }
  public void generate(int size) {
    WORLD_SIZE = size;
    MathHelper.Direction direction = MathHelper.randomDirection();
    if(this.isValidPosition(posX + direction.dirX, posY + direction.dirY)) {
      if(!this.generated[posX + direction.dirX][posY + direction.dirY]) {
        this.roomData[posX][posY].add(direction);
        this.roomData[posX + direction.dirX][posY + direction.dirY].add(direction.opposite);
      }
      this.posX += direction.dirX;
      this.posY += direction.dirY;
      this.generated[posX][posY] = true;
    }
    else {
      this.generate(WORLD_SIZE);
    }
  }

  private void move() {
    switch (MathHelper.randomDirection()) {
      case NORTH:
        if(isValidPosition(posX, posY-1)) {
          if(!this.roomData[posX][posY].contains(Direction.NORTH)) this.roomData[posX][posY].add(Direction.NORTH);
          this.posY++;
          if(!this.roomData[posX][posY].contains(Direction.SOUTH) && !this.generated[posX][posY])
            this.roomData[posX][posY].add(Direction.SOUTH);
          this.generated[posX][posY] = true;
        }
        break;
      case SOUTH:
        if(isValidPosition(posX, posY+1)) {
          if(!this.roomData[posX][posY].contains(Direction.SOUTH)) this.roomData[posX][posY].add(Direction.SOUTH);
          this.posY--;
          if(!this.roomData[posX][posY].contains(Direction.NORTH) && !this.generated[posX][posY])
            this.roomData[posX][posY].add(Direction.NORTH);
          this.generated[posX][posY] = true;
        }
        break;
      case WEST:
        if(isValidPosition(posX-1, posY)) {
          if(!this.roomData[posX][posY].contains(Direction.WEST)) this.roomData[posX][posY].add(Direction.WEST);
          this.posX--;
          if(!this.roomData[posX][posY].contains(Direction.EAST) && !this.generated[posX][posY])
            this.roomData[posX][posY].add(Direction.EAST);
          this.generated[posX][posY] = true;
        }
        break;
      case EAST:
        if(isValidPosition(posX-1, posY)) {
          if(!this.roomData[posX][posY].contains(Direction.EAST)) this.roomData[posX][posY].add(Direction.EAST);
          this.posX++;
          if(!this.roomData[posX][posY].contains(Direction.WEST) && !this.generated[posX][posY])
            this.roomData[posX][posY].add(Direction.WEST);
          this.generated[posX][posY] = true;
        }
        break;
    }
  }
  private void setRandomPosition(){
    this.posX = MathHelper.randomInt(WORLD_SIZE);
    this.posY = MathHelper.randomInt(WORLD_SIZE);
    this.generated[posX][posY] = true;
  }

  private boolean isValidPosition(int x, int y) {
    return x >= 0 && y >= 0 && x < WORLD_SIZE && y < WORLD_SIZE;
  }

  public boolean finished() {
    for (int i = 0; i < generated.length; i++){
      for(int j = 0; j < generated[i].length; j++) {
        if (!this.generated[i][j]) return false;
      }
    }
    return true;
  }

  public HashSet<Direction> getDataForRoom(int x, int y){
    return this.roomData[x][y];
  }

  public int getWORLD_SIZE(){
    return WORLD_SIZE;
  }
}
