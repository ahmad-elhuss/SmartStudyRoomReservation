package smartstudyroomreservation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import smartstudyroomreservation.model.Student;
import smartstudyroomreservation.model.UserStore;   // ← مهم جداً

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField userIdField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField majorField;

    @FXML
    private void handleRegister(ActionEvent event) {
        String userId = userIdField.getText().trim();
        String name = nameField.getText().trim();
        String password = passwordField.getText().trim();
        String major = majorField.getText().trim();

        if (userId.isEmpty() || name.isEmpty() || password.isEmpty()) {
            System.out.println("Please fill required fields");
            return;
        }

        Student newStudent = new Student(userId, name, password, major);
        UserStore.addUser(newStudent);

        System.out.println("Student Registered Successfully: " + name);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Smart Study Room Reservation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Smart Study Room Reservation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}