package com.animation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EffetEtAnimationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EffetEtAnimationApplication.class.getResource("effet-et-animation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Effets et Animations!");
        stage.setScene(scene);
        stage.show();
        //A1
        EffetEtAnimationController controleur=fxmlLoader.getController();
        controleur.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}