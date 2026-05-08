package smartstudyroomreservation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import smartstudyroomreservation.model.ReservationManager;
import smartstudyroomreservation.model.StudyRoom;
import smartstudyroomreservation.model.TimeSlot;
import smartstudyroomreservation.model.Reservation;

import java.io.IOException;

public class ReservationController {

    @FXML
    private ComboBox<String> roomComboBox;
    @FXML
    private TextField dateField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;

    @FXML
    private void initialize() {
        loadAvailableRooms();
    }

    private void loadAvailableRooms() {
        roomComboBox.getItems().clear();
        for (StudyRoom room : ReservationManager.getAvailableRooms()) {
            roomComboBox.getItems().add(room.getRoomId() + " - " + room.getRoomName());
        }
    }

    @FXML
    private void handleConfirmReservation(ActionEvent event) {
        String selectedRoom = roomComboBox.getValue();
        String date = dateField.getText().trim();
        String start = startTimeField.getText().trim();
        String end = endTimeField.getText().trim();

        if (selectedRoom == null || date.isEmpty() || start.isEmpty() || end.isEmpty()) {
            System.out.println("Please fill all fields");
            return;
        }

        String roomId = selectedRoom.split(" - ")[0];

        TimeSlot slot = new TimeSlot(date, start, end);
        Reservation res = new Reservation("RES" + System.currentTimeMillis(), "S001", roomId, slot);

        boolean success = ReservationManager.makeReservation(res);

        if (success) {
            System.out.println("✅ Reservation Confirmed Successfully!");
            loadAvailableRooms();
            handleBackToHome(event);
        } else {
            System.out.println("❌ Reservation Failed! Room not available.");
        }
    }

    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 650, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}