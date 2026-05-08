package smartstudyroomreservation.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;
import smartstudyroomreservation.model.ReservationManager;
import smartstudyroomreservation.model.StudyRoom;

import java.io.IOException;

public class HomeController {

    @FXML
    private ListView<String> roomsListView;

    @FXML
    private void initialize() {
        loadAvailableRooms();
    }

    private void loadAvailableRooms() {
        ObservableList<String> roomItems = FXCollections.observableArrayList();

        for (StudyRoom room : ReservationManager.getAvailableRooms()) {
            String info = room.getRoomId() + " - " + room.getRoomName()
                    + " (" + room.getCapacity() + " students) - "
                    + room.getLocation();
            roomItems.add(info);
        }

        roomsListView.setItems(roomItems);
    }

    @FXML
    private void handleRefreshRooms(ActionEvent event) {
        loadAvailableRooms();
    }

    @FXML
    private void handleMakeReservation(ActionEvent event) {
        openScreen(event, "/view/Reservation.fxml", "Make Reservation", 500, 450);
    }

    @FXML
    private void handleMyReservations(ActionEvent event) {
        openScreen(event, "/view/MyReservations.fxml", "My Reservations", 650, 500);
    }

    @FXML
    private void handleAdminPanel(ActionEvent event) {
        openScreen(event, "/view/Admin.fxml", "Admin Panel", 600, 500);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        openScreen(event, "/view/Login.fxml", "Smart Study Room Reservation", 450, 350);
    }

    private void openScreen(ActionEvent event, String fxmlPath, String title, int width, int height) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, width, height));
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}