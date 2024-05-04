package Model;

/**
 * This class represents a game item on an abstract level.
 *
 * @author Austin Maggert
 * @version Spring 2024
 */
public abstract class GameItem {
    /**
     * myName field is the name of the game item
     */
    String myName;

    /**
     * GameItem constructor creates an abstract level
     * game item and initializes myName
     *
     * @param theName is the name for this game item
     */
    public GameItem(String theName) {
        myName = theName;
    }

    /**
     * getItemName method returns the name of this game item
     *
     * @return returns the name of this item
     */
    public String getItemName() {
        return myName;
    }
}
