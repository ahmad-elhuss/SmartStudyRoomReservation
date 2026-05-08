package smartstudyroomreservation.model;

public class Reservation {

    private String reservationId;
    private String studentId;
    private String roomId;
    private TimeSlot timeSlot;
    private String status;

    public Reservation(String reservationId, String studentId, String roomId, TimeSlot timeSlot) {
        this.reservationId = reservationId;
        this.studentId = studentId;
        this.roomId = roomId;
        this.timeSlot = timeSlot;
        this.status = "Pending";
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRoomId() {
        return roomId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId + " | Room: " + roomId
                + " | Student: " + studentId + " | Time: " + timeSlot.getFullSlot());
    }
}