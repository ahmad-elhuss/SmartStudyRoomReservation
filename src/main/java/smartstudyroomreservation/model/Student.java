package smartstudyroomreservation.model;

public class Student extends User {

    private String major;

    public Student(String userId, String name, String password, String major) {
        super(userId, name, password);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public void displayRole() {
        System.out.println("Student: " + getName() + " | Major: " + major);
    }
}