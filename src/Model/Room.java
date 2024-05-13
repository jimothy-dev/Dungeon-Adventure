package Model;

import Controller.RoomData;

public class Room {

  private RoomData data;

  public Room(RoomData data) {
    this.data = data;
  }

  public RoomData getData() {
    return data;
  }

}
