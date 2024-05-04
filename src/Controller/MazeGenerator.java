package Controller;

import Controller.MathHelper.Direction;
import java.util.ArrayList;

/**
 * MazeGenerator class creates random 5 x 5 mazes
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class MazeGenerator {

  /**
   * WORLD_SIZE constant is the dimensions for the maze
   */
  public static final int WORLD_SIZE = 5;

  /**
   * myPosX is the x position for the hero sprite in the maze
   */
  private int myPosX;

  /**
   * myPosY is the y position for the hero sprite in the maze
   */
  private int myPosY;

  /**
   * myRoomData field is a 2D arraylist of directions for rooms
   */
  private ArrayList<MathHelper.Direction>[][] myRoomData;

  /**
   * myGenerated field is a 2D array of boolean
   * where true when location is traversable, and false otherwise
   */
  private boolean myGenerated[][];

  /**
   * MazeGenerator constructor initializes fields and creates
   * a random maze. It them sets hero location in the maze
   */
  @SuppressWarnings("unchecked")
  public MazeGenerator(){
    myRoomData = new ArrayList[WORLD_SIZE][WORLD_SIZE];
    myGenerated = new boolean[WORLD_SIZE][WORLD_SIZE];
    for(int i = 0; i < myRoomData.length; i++){
      for(int j = 0; j < myRoomData[i].length; j++){
        this.myRoomData[i][j] = new ArrayList<>();
        this.myGenerated[i][j] = false;
      }
    }
    setRandomPosition();
  }

  /**
   * generate method gets a random direction, sees if it is within
   * the bounds of the dungeon, and if so, makes the location traversable
   * by assigning its location in myGenerated to true
   */
  public void generate() {
    MathHelper.Direction direction = MathHelper.randomDirection();
    if(this.isValidPosition(myPosX + direction.dirX, myPosY + direction.dirY)) {
      if(!this.myGenerated[myPosX + direction.dirX][myPosY + direction.dirY]) {
        this.myRoomData[myPosX][myPosY].add(direction);
        this.myRoomData[myPosX + direction.dirX][myPosY + direction.dirY].add(direction.opposite);
      }
      this.myPosX += direction.dirX;
      this.myPosY += direction.dirY;
      this.myGenerated[myPosX][myPosY] = true;
    }
    else {
      this.generate();
    }
  }

  /**
   * move method creates a new path from the current location randomly
   * while verifying it is within the bounds of the dungeon
   */
  private void move() {
    switch (MathHelper.randomDirection()) {
      case NORTH:
        if(isValidPosition(myPosX, myPosY -1)) {
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.NORTH)) this.myRoomData[myPosX][myPosY].add(Direction.NORTH);
          this.myPosY++;
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.SOUTH) && !this.myGenerated[myPosX][myPosY])
            this.myRoomData[myPosX][myPosY].add(Direction.SOUTH);
          this.myGenerated[myPosX][myPosY] = true;
        }
        break;
      case SOUTH:
        if(isValidPosition(myPosX, myPosY +1)) {
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.SOUTH)) this.myRoomData[myPosX][myPosY].add(Direction.SOUTH);
          this.myPosY--;
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.NORTH) && !this.myGenerated[myPosX][myPosY])
            this.myRoomData[myPosX][myPosY].add(Direction.NORTH);
          this.myGenerated[myPosX][myPosY] = true;
        }
        break;
      case WEST:
        if(isValidPosition(myPosX -1, myPosY)) {
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.WEST)) this.myRoomData[myPosX][myPosY].add(Direction.WEST);
          this.myPosX--;
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.EAST) && !this.myGenerated[myPosX][myPosY])
            this.myRoomData[myPosX][myPosY].add(Direction.EAST);
          this.myGenerated[myPosX][myPosY] = true;
        }
        break;
      case EAST:
        if(isValidPosition(myPosX -1, myPosY)) {
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.EAST)) this.myRoomData[myPosX][myPosY].add(Direction.EAST);
          this.myPosX++;
          if(!this.myRoomData[myPosX][myPosY].contains(Direction.WEST) && !this.myGenerated[myPosX][myPosY])
            this.myRoomData[myPosX][myPosY].add(Direction.WEST);
          this.myGenerated[myPosX][myPosY] = true;
        }
        break;
    }
  }

  /**
   * setRandomPosition method sets the position
   * to one randomly in the dungeon maze
   */
  private void setRandomPosition(){
    this.myPosX = MathHelper.randomInt(WORLD_SIZE);
    this.myPosY = MathHelper.randomInt(WORLD_SIZE);
    this.myGenerated[myPosX][myPosY] = true;
  }

  /**
   * isValidPosition method indicates if a location in the maze is
   * traversable
   *
   * @param theX is the x position
   * @param theY is the y position
   * @return returns boolean true if the location is traversable, false otherwise
   */
  private boolean isValidPosition(int theX, int theY) {
    return theX >= 0 && theY >= 0 && theX < WORLD_SIZE && theY < WORLD_SIZE;
  }

  /**
   * finished method checks that every location in the generated maze
   * has been generated and returns true if so, false otherwise
   *
   * @return returns true when maze is completed, false otherwise
   */
  public boolean finished() {
    for (int i = 0; i < myGenerated.length; i++){
      for(int j = 0; j < myGenerated[i].length; j++) {
        if (!this.myGenerated[i][j]) return false;
      }
    }
    for (int i = 0; i < myGenerated.length; i++){
      for(int j = 0; j < myGenerated[i].length; j++) {
        System.out.println(myRoomData[i][j].toString());
      }
    }
    return true;
  }
}
