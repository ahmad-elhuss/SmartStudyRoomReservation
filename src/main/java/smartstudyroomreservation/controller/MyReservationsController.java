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
import smartstudyroomreservation.model.Reservation;

import java.io.IOException;

public class MyReservationsController {

    @FXML
    private ListView<String> reservationsListView;

    @FXML
    private void initialize() {
        loadReservations();
    }

    private void loadReservations() {
        ObservableList<String> items = FXCollections.observableArrayList();

        for (Reservation r : ReservationManager.getReservationsByStudent("S001")) {
            String info = "Room: " + r.getRoomId() + " | " + r.getTimeSlot().getFullSlot()
                    + " | Status: " + r.getStatus();
            items.add(info);
        }

        reservationsListView.setItems(items);
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadReservations();
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