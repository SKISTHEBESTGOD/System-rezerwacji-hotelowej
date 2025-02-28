public class Room {
    private int roomNumber;
    private RoomType roomType;
    private boolean available;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Pokój " + roomNumber + " (" + roomType + ") - " + (available ? "Dostępny" : "Zarezerwowany");
    }
}
