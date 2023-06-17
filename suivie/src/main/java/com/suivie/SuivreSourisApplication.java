package com.suivie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SuivreSourisApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SuivreSourisApplication.class.getResource("suivre-souris-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Suivre Souris");
        stage.setScene(scene);
        stage.show();

        SuivreSourisController controleur=fxmlLoader.getController();
        controleur.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}