package smartstudyroomreservation.model;

public class StudyRoom {

    private String roomId;
    private String roomName;
    private int capacity;
    private boolean isAvailable;
    private String location;

    public StudyRoom(String roomId, String roomName, int capacity, String location) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.location = location;
        this.isAvailable = true;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getLocation() {
        return location;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void displayRoomInfo() {
        System.out.println("Room ID: " + roomId + " | Name: " + roomName +
                " | Capacity: " + capacity + " | Available: " + isAvailable);
    }
}