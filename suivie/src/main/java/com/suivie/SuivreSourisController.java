package com.suivie;

import javafx.animation.RotateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SuivreSourisController implements EventHandler{
    public static final int PAS=10;
    private Stage stage;
    @FXML
    private ImageView imgChat;
    public void setStage(Stage stage) {
        this.stage = stage;
        ecouteursScene();
    }
    private void ecouteursScene() {
        stage.getScene().setOnMouseMoved((EventHandler<? super MouseEvent>)
                this);
    }
    @Override
    public void handle(Event event) {
        if(event instanceof MouseEvent){
            MouseEvent me=(MouseEvent) event;
            if(imgChat.getLayoutX()<me.getX())
                imgChat.setLayoutX(imgChat.getLayoutX()+PAS);
            else if(imgChat.getLayoutX()>me.getX())
                imgChat.setLayoutX(imgChat.getLayoutX()-PAS);
            if(imgChat.getLayoutY()<me.getY())
                imgChat.setLayoutY(imgChat.getLayoutY()+PAS);
            else if(imgChat.getLayoutY()>me.getY())
                imgChat.setLayoutY(imgChat.getLayoutY()-PAS);
            if(imgChat.getLayoutX()==me.getX() &&
                    imgChat.getLayoutY()==me.getY())
                animerRotation(imgChat);
        }
    }
    private void animerRotation(ImageView img) {
        RotateTransition rt = new RotateTransition(Duration.millis(3000),
                img);
        rt.setByAngle(360);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        rt.play();
    }
}
