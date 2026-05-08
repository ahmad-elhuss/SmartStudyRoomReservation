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

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        String userId = userIdField.getText();
        String password = passwordField.getText();

        if (userId.isEmpty() || password.isEmpty()) {
            System.out.println("Please fill all fields");
            return;
        }

        System.out.println("Login attempt - UserID: " + userId);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 650, 500));
            stage.setTitle("Home - Smart Study Room");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}