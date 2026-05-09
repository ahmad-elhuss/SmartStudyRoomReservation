package smartstudyroomreservation.model;

import java.util.ArrayList;

public class ReservationManager {

    private static ArrayList<StudyRoom> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void addRoom(StudyRoom room) {
        rooms.add(room);
    }

    public static ArrayList<StudyRoom> getAllRooms() {
        return rooms;
    }

    public static ArrayList<StudyRoom> getAvailableRooms() {
        ArrayList<StudyRoom> available = new ArrayList<>();
        for (StudyRoom room : rooms) {
            if (room.isAvailable()) {
                available.add(room);
            }
        }
        return available;
    }

    public static boolean makeReservation(Reservation reservation) {
        String roomId = reservation.getRoomId();

        for (StudyRoom room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                if (room.isAvailable()) {
                    reservations.add(reservation);
                    room.setAvailable(false);
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Reservation> getReservationsByStudent(String studentId) {
        ArrayList<Reservation> studentReservations = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getStudentId().equals(studentId)) {
                studentReservations.add(r);
            }
        }
        return studentReservations;
    }
}