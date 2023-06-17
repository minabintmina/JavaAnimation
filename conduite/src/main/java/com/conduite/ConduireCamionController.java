package com.conduite;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConduireCamionController implements EventHandler<KeyEvent> {
    public static final int PAS = 10;
    private Stage stage;
    @FXML
    private ImageView imgCamion;
    @FXML
    private AnchorPane pRoute;

    public void setStage(Stage stage) {
        this.stage = stage;
        pRoute.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        ecouteursScene();
    }

    private void ecouteursScene() {
        stage.getScene().setOnKeyPressed(this);
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        switch (keyEvent.getCode()) {
            case UP:
                if (imgCamion.getLayoutY() > 0)
                    imgCamion.setLayoutY(imgCamion.getLayoutY() - PAS);
                break;
            case DOWN:
                if (imgCamion.getLayoutY() < pRoute.getHeight() - imgCamion.getImage().getHeight())
                    imgCamion.setLayoutY(imgCamion.getLayoutY() + PAS);
                break;
            case LEFT:
                if (imgCamion.getLayoutX() > 0)
                    imgCamion.setLayoutX(imgCamion.getLayoutX() - PAS);
                break;
            case RIGHT:
                if (imgCamion.getLayoutX() < pRoute.getWidth() - imgCamion.getImage().getWidth())
                    imgCamion.setLayoutX(imgCamion.getLayoutX() + PAS);
                break;
            case R:
                animerRotation();
                break;
            case T:
                animerTranslation();
                break;
        }
    }

    private void animerTranslation() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), imgCamion);
        tt.setByX(400f);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
        tt.play();
    }

    private void animerRotation() {
        RotateTransition rt = new RotateTransition(Duration.millis(3000), imgCamion);
        rt.setByAngle(360);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        rt.play();
    }
}