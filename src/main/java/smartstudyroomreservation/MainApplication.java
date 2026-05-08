package smartstudyroomreservation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import smartstudyroomreservation.model.ReservationManager;
import smartstudyroomreservation.model.StudyRoom;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeRooms();

        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle("Smart Study Room Reservation");
        primaryStage.setScene(new Scene(root, 450, 350));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initializeRooms() {
        if (ReservationManager.getAllRooms().isEmpty()) {
            StudyRoom r1 = new StudyRoom("R101", "Room 101", 4, "First Floor");
            StudyRoom r2 = new StudyRoom("R102", "Room 102", 6, "Second Floor");
            StudyRoom r3 = new StudyRoom("R201", "Room 201", 5, "Second Floor");
            StudyRoom r4 = new StudyRoom("R301", "Room 301", 3, "Third Floor");

            ReservationManager.addRoom(r1);
            ReservationManager.addRoom(r2);
            ReservationManager.addRoom(r3);
            ReservationManager.addRoom(r4);

            System.out.println("Default rooms added successfully!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}