package com.conduite;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ConduireCamionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConduireCamionApplication.class.getResource("conduire-camion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Conduire Camion");
        stage.setScene(scene);
        stage.show();
        ConduireCamionController controleur=fxmlLoader.getController();
        controleur.setStage(stage);

    }


    public static void main(String[] args) {
        launch();
    }
}