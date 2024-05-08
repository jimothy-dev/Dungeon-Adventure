package Model;

import Controller.MathHelper;
import Controller.MathHelper.Direction;
import Controller.RoomData;
import View.FrameManager;
import java.util.HashSet;

public class Dungeon {
    private Room[][] rooms;
    private int currX;
    private int currY;
    int worldSize;
    public Dungeon(HashSet<MathHelper.Direction>[][] roomsData, int worldSize){
        this.rooms = new Room[roomsData.length][roomsData[0].length];
        this.worldSize = worldSize;
        for(int i=0; i < worldSize; i++){
            for(int j =0; j < worldSize; j++) {
                if (roomsData[i][j].contains(Direction.NORTH) && roomsData[i][j].contains(
                    Direction.EAST) &&
                    roomsData[i][j].contains(Direction.SOUTH) && roomsData[i][j].contains(
                    Direction.WEST)) {

                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST));
                } else if (roomsData[i][j].contains(Direction.NORTH) &&
                    roomsData[i][j].contains(Direction.SOUTH) && roomsData[i][j].contains(
                    Direction.WEST)) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.NORTH, Direction.SOUTH, Direction.WEST));
                } else if (roomsData[i][j].contains(Direction.NORTH) &&
                    roomsData[i][j].contains(Direction.EAST) && roomsData[i][j].contains(
                    Direction.WEST)) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.NORTH, Direction.WEST, Direction.EAST));
                } else if (roomsData[i][j].contains(Direction.NORTH) &&
                    roomsData[i][j].contains(Direction.SOUTH) && roomsData[i][j].contains(
                    Direction.EAST)) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.NORTH, Direction.SOUTH, Direction.EAST));
                }  else if (roomsData[i][j].contains(Direction.EAST) &&
                    roomsData[i][j].contains(Direction.SOUTH) && roomsData[i][j].contains(
                    Direction.WEST)) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.EAST, Direction.SOUTH, Direction.WEST));
                } else if (roomsData[i][j].contains(Direction.EAST) &&
                    roomsData[i][j].contains(Direction.NORTH) ) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.EAST, Direction.NORTH));
                } else if (roomsData[i][j].contains(Direction.SOUTH) &&
                    roomsData[i][j].contains(Direction.NORTH) ) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.SOUTH, Direction.NORTH));
                }else if (roomsData[i][j].contains(Direction.WEST) &&
                    roomsData[i][j].contains(Direction.NORTH) ) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.WEST, Direction.NORTH));
                } else if (roomsData[i][j].contains(Direction.SOUTH) &&
                    roomsData[i][j].contains(Direction.EAST) ) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.SOUTH, Direction.EAST));
                }else if (roomsData[i][j].contains(Direction.WEST) &&
                    roomsData[i][j].contains(Direction.EAST) ) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.WEST, Direction.EAST));
                }else if (roomsData[i][j].contains(Direction.WEST) &&
                    roomsData[i][j].contains(Direction.SOUTH) ) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.SOUTH, Direction.EAST));
                } else if (roomsData[i][j].contains(Direction.NORTH)) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.NORTH));
                } else if (roomsData[i][j].contains(Direction.EAST)) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.EAST));
                } else if (roomsData[i][j].contains(Direction.SOUTH)) {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.SOUTH));
                } else {
                    this.rooms[i][j] = new Room(new RoomData(new byte[][]{
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1}},
                        FrameManager.getWidth() / worldSize, FrameManager.getHeight() / worldSize,
                        Direction.WEST));
                }
            }
        }
        this.currX = 0;
        this.currY = 0;
    }

    public Room getRoom(int x, int y) {
        return rooms[x][y];
    }

    public Room getRoom() {
        return rooms[currX][currY];
    }

    public void changeRoom(PlayableHero player){
        if (player.getCenterX() < 0) {
            this.currX--;
            player.setCenterX(FrameManager.WIDTH);
        }
        else if (player.getCenterX() > FrameManager.WIDTH) {
            this.currX++;
            player.setCenterX(0);
        }
        if (player.getCenterY() < 0) {
            this.currY--;
            player.setCenterY(FrameManager.HEIGHT);
        }
        else if (player.getCenterY() > FrameManager.HEIGHT) {
            this.currY++;
            player.setCenterY(0);
        }
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }
}
