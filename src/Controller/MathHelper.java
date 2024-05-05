package Controller;

import java.util.Random;

/**
 * MathHelper class provides various math related functions for the game
 *
 * @author Albert Meza, Austin Maggert, and James Simpson
 * @version Spring 2024
 */
public class MathHelper {

  /**
   * RANDOM constant is for all random generation number in the game
   */
  private static final Random RANDOM = new Random();

  /**
   * randomInt method provides a random value between 0 and
   * the passed param upper bound, not including the upper bound
   *
   * @param upperBound is the value 1 more than the max random value
   * @return returns a random int between 0 inclusive and the upperBound exclusive
   */
  public static int randomInt(int upperBound){
    return RANDOM.nextInt(upperBound);
  }

  /**
   * randomDirection method provides a random direction enum
   *
   * @return returns a random direction enum
   */
  public static Direction randomDirection() {
    return Direction.values()[RANDOM.nextInt(Direction.values().length)];
  }

  /**
   * nested Direction enum is for game directions
   */
  public enum Direction {

    /**
     * NORTH enum is the direction toward top of the screen
     */
    NORTH(0, -1),

    /**
     * SOUTH enum is the direction toward bottom of the screen
     */
    SOUTH(0,1),

    /**
     * WEST enum is the direction toward left side of the screen
     */
    WEST(-1, 0),

    /**
     * EAST enum is the direction toward right side of the screen
     */
    EAST(1,0);

    /**
     * dirX variable is the change in x for the direction of the enum
     */
    public int dirX;

    /**
     * dirY variable is the change in y for the direction of the enum
     */
    public int dirY;

    /**
     * opposite variable is the opposite direction enum for each direction enum
     */
    public Direction opposite;

    /**
     * declares opposite directions for each direction
     */
    static {
      NORTH.opposite = SOUTH;
      SOUTH.opposite = NORTH;
      WEST.opposite = EAST;
      EAST.opposite = WEST;
    }

    /**
     * Direction constructor creates a direction enum based on
     * changes in x and y.
     *
     * @param dirX is the change in x
     * @param dirY is the change in y
     */
    private Direction(int dirX, int dirY) {
      this.dirX = dirX;
      this.dirY = dirY;
    }
  }
}
