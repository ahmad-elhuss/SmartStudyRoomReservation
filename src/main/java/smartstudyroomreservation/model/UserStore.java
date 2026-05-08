package smartstudyroomreservation.model;

import java.util.ArrayList;

public class UserStore {

    private static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new Student("S001", "Ahmed Ali", "1234", "CS"));
        users.add(new Admin("A001", "Admin", "admin"));
    }

    public static User login(String id, String password) {
        for (User u : users) {
            if (u.getUserId().equals(id) && u.checkPassword(password)) {
                return u;
            }
        }
        return null;
    }
}