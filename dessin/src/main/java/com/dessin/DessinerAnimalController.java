package com.dessin;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class DessinerAnimalController implements Initializable,
        EventHandler<MouseEvent> {
    @FXML
    private AnchorPane pDessin;
    @FXML
    private ImageView imgLion, imgElephant, imgPanda, imgRenard, imgToucan;
    private ImageView imgAnimalCourant;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgAnimalCourant = imgLion;
        ecouteurs();
    }
    private void ecouteurs() {
        pDessin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dessinerAnimal(mouseEvent);
            }
        });
        imgLion.setOnMouseClicked(this);
        imgElephant.setOnMouseClicked(this);
        imgPanda.setOnMouseClicked(this);
        imgRenard.setOnMouseClicked(this);
        imgToucan.setOnMouseClicked(this);
    }
    private void dessinerAnimal(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            ImageView img = new ImageView(imgAnimalCourant.getImage());
            img.setLayoutX(mouseEvent.getX());
            img.setLayoutY(mouseEvent.getY());
            img.setOnMouseClicked(this);
            pDessin.getChildren().add(img);
        }
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            ImageView imgSource = (ImageView) mouseEvent.getSource();
            if (imgSource == imgLion || imgSource == imgElephant
                    || imgSource == imgPanda || imgSource == imgRenard
                    || imgSource == imgToucan)
                choisir(imgSource);
            else {
                if(mouseEvent.getButton()== MouseButton.PRIMARY)
                    animerEchelle(imgSource);
                else
                    supprimer(imgSource);
            }
        }
    }
    private void choisir(ImageView imgSource) {
        imgAnimalCourant.setEffect(null);
        imgAnimalCourant = imgSource;
        Reflection reflection = new Reflection();
        imgAnimalCourant.setEffect(reflection);
    }
    private void animerEchelle(ImageView img) {
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), img);
        st.setByX(1.5f);
        st.setByY(1.5f);
        st.setCycleCount(4);
        st.setAutoReverse(true);
        st.play();
    }
    private void supprimer(ImageView img){
        pDessin.getChildren().remove(img);
    }
}
