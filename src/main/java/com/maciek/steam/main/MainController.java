package com.maciek.steam.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML Button storeButton;
    @FXML Button libraryButton;
    @FXML Button communityButton;

    @FXML Button userButton;

    @FXML AnchorPane anchorPane;

    public void initData(String username) {
        userButton.setText(username);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/fxml/store_pane.fxml"));
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onStoreButtonClick(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/store_pane.fxml"));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(pane);
    }

    @FXML
    public void onLibraryButtonClick(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/library_pane.fxml"));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(pane);
    }

    @FXML
    public void onCommunityButtonClick(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/community_pane.fxml"));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(pane);
    }

    @FXML
    public void onUserButtonClick(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/username_pane.fxml"));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(pane);
    }
}
