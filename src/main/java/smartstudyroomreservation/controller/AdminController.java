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

public class AdminController {

    @FXML
    private ListView<String> allRoomsListView;

    @FXML
    private void initialize() {
        loadAllRooms();
    }

    private void loadAllRooms() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (StudyRoom room : ReservationManager.getAllRooms()) {
            String status = room.isAvailable() ? "Available" : "Reserved";
            String info = room.getRoomId() + " - " + room.getRoomName()
                    + " (" + room.getCapacity() + ") - " + status;
            items.add(info);
        }
        allRoomsListView.setItems(items);
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadAllRooms();
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