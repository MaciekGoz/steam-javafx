package com.maciek.steam.login;

import com.maciek.steam.Database;
import com.maciek.steam.main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML Button loginButton;
    @FXML TextField loginField;
    @FXML PasswordField passwordField;
    @FXML Button noAccountButton;

    Database database = Database.getInstance();

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {


        if (database.login(loginField.getText(), passwordField.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_scene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            MainController controller = loader.getController();
            controller.initData(loginField.getText());

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Failure");
            alert.setContentText("Bad job");
            alert.showAndWait();
        }
    }

    @FXML
    private void onNoAccountButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register_scene.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setDisable(true);

        loginField.textProperty().addListener((o, oldValue, newValue) -> {
            loginButton.setDisable(loginField.getText().isEmpty() || passwordField.getText().isEmpty());
        });

        passwordField.textProperty().addListener((o, oldValue, newValue) -> {
            loginButton.setDisable(loginField.getText().isEmpty() || passwordField.getText().isEmpty());
        });
    }


}
