package com.maciek.steam.register;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML TextField loginField;
    @FXML TextField emailField;
    @FXML PasswordField passwordField;
    @FXML PasswordField repeatPasswordField;
    @FXML Button registerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerButton.setDisable(true);

        loginField.textProperty().addListener((observableValue, s, t1) -> {
            registerButton.setDisable(loginField.getText().isEmpty() || emailField.getText().isEmpty()
                    || passwordField.getText().isEmpty() || repeatPasswordField.getText().isEmpty()
                    || !passwordField.getText().equals(repeatPasswordField.getText()));
        });

        emailField.textProperty().addListener((observableValue, s, t1) -> {
            registerButton.setDisable(loginField.getText().isEmpty() || emailField.getText().isEmpty()
                    || passwordField.getText().isEmpty() || repeatPasswordField.getText().isEmpty()
                    || !passwordField.getText().equals(repeatPasswordField.getText()));
        });

        passwordField.textProperty().addListener((observableValue, s, t1) -> {
            registerButton.setDisable(loginField.getText().isEmpty() || emailField.getText().isEmpty()
                    || passwordField.getText().isEmpty() || repeatPasswordField.getText().isEmpty()
                    || !passwordField.getText().equals(repeatPasswordField.getText()));
        });

        repeatPasswordField.textProperty().addListener((observableValue, s, t1) -> {
            registerButton.setDisable(loginField.getText().isEmpty() || emailField.getText().isEmpty()
                    || passwordField.getText().isEmpty() || repeatPasswordField.getText().isEmpty()
                    || !passwordField.getText().equals(repeatPasswordField.getText()));
        });
    }
}
