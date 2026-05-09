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

import java.io.*;
import java.net.Socket;
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

    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 1234;

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
        String studentId = "S001";

        String command = "RESERVE|" + roomId + "|" + date + "|" + start + "|" + end + "|" + studentId;

        String response = sendToServer(command);
        System.out.println("Server Response: " + response);

        if (response != null && response.startsWith("RESERVE_SUCCESS")) {
            System.out.println("✅ Reservation Confirmed Successfully!");

            // تحديث البيانات محلياً بعد الحجز الناجح
            ReservationManager.makeReservation(new Reservation("RES" + System.currentTimeMillis(),
                    studentId, roomId, new TimeSlot(date, start, end)));

            loadAvailableRooms();           // تحديث ComboBox
            handleBackToHome(event);        // الرجوع للـ Home
        } else {
            System.out.println("❌ Reservation Failed: " + response);
        }
    }

    private String sendToServer(String message) {
        try (Socket socket = new Socket(SERVER_IP, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(message);
            return in.readLine();

        } catch (IOException e) {
            System.out.println("Cannot connect to server");
            return "ERROR|Connection failed";
        }
    }

    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 650, 500));
            stage.setTitle("Home - Smart Study Room");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}