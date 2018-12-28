package com.maciek.steam.main;

import com.maciek.steam.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {

    @FXML TreeView<String> treeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> root = new TreeItem<>("");
        root.setExpanded(true);

        TreeItem<String> favourites = new TreeItem<>("FAVOURITES");
        TreeItem<String> games = new TreeItem<>("GAMES");
        root.getChildren().addAll(favourites, games);

        favourites.setExpanded(true);
        games.setExpanded(true);

        for (int i = 0; i < 20; i++) {
            favourites.getChildren().add(new TreeItem<>("game dupa" + i + 1));
        }

        List<String> userGames = Database.getInstance().getUserGames("q");
        userGames.forEach(g -> {
            games.getChildren().add(new TreeItem<>(g));
        });

        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }
}
