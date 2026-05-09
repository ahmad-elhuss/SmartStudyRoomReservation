package smartstudyroomreservation.util;

public class RoomAlreadyReservedException extends Exception {
    public RoomAlreadyReservedException(String message) {
        super(message);
    }
}