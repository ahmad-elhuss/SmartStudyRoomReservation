package smartstudyroomreservation.model;

public class TimeSlot {

    private String date;
    private String startTime;
    private String endTime;

    public TimeSlot(String date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getFullSlot() {
        return date + " " + startTime + " - " + endTime;
    }
}