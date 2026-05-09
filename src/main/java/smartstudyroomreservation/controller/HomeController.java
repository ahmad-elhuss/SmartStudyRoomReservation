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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Reservation.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 500, 450));
            stage.setTitle("Make Reservation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMyReservations(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MyReservations.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 650, 500));
            stage.setTitle("My Reservations");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAdminPanel(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 500));
            stage.setTitle("Admin Panel");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 450, 350));
            stage.setTitle("Smart Study Room Reservation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}