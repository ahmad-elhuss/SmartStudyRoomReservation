package smartstudyroomreservation.model;

public abstract class User {

    private String userId;
    private String name;
    private String password;

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public abstract void displayRole();
}