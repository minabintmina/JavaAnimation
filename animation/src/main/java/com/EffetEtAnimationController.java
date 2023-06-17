package com.animation;


import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class EffetEtAnimationController implements Initializable, EventHandler<MouseEvent> {
    private static final double PAS = 10;
    @FXML
    private ImageView imgEtoile;
    //C1
    private Stage stage;
    //C10
    private ImageView imgCourante;
    @FXML
    private AnchorPane pSurface;
    @FXML
    private Button btnGauche, btnHaut, btnBas, btnDroite;
    @FXML
    private Button btnAR, btnAT, btnAE;

    //C2
    public void setStage(Stage stage) {
        this.stage = stage;
        pSurface.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //C3
        ecouteursStage();
        //C7
        ecouteursScene();
    }

    //C4
    private void ecouteursStage() {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                //C5
                confirmer(windowEvent);
            }
        });
    }

    //C8
    private void ecouteursScene() {
        stage.getScene().setOnMouseEntered(this);
        stage.getScene().setOnMouseClicked(this);
    }

    //C6
    private void confirmer(WindowEvent windowEvent) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("PConfirmation de la fermeture");
        dialog.setHeaderText("Etes vous sûr de quitter?");
        Optional<ButtonType> reponse = dialog.showAndWait();
        if (reponse.get() == ButtonType.OK) {
            //Ne rien faire et la fenêtre se ferme
        } else
            windowEvent.consume();
    }

    //C9
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == stage.getScene()) {
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED)
                deplacer(mouseEvent);
            else if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED
                    && mouseEvent.getTarget() == pSurface) {
                ajouterEtoile(mouseEvent);
                System.out.println("ajouterEtoile");
            }
        } else {
            selectionner(mouseEvent);
            System.out.println("selectionner");
        }
    }

    //C12
    private void ajouterEtoile(MouseEvent mouseEvent) {
        ImageView img = new ImageView(imgEtoile.getImage());
        img.setOnMouseClicked(this);
        pSurface.getChildren().add(img);
        Random r = new Random();
        img.setLayoutX(r.nextDouble(pSurface.getWidth()));
        img.setLayoutY(r.nextDouble(pSurface.getHeight()));
    }

    private void deplacer(MouseEvent mouseEvent) {
        imgCourante.setLayoutX(mouseEvent.getX() - (imgCourante.getImage().getWidth() / 2));
        imgCourante.setLayoutY(mouseEvent.getY() - (imgCourante.getImage().getHeight() / 2));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgCourante = imgEtoile;
        ecouteurs();
    }

    private void ecouteurs() {
        //C11
        imgEtoile.setOnMouseClicked(this);
        btnGauche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gauche();
            }
        });
        btnHaut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                haut();
            }
        });
        btnBas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bas();
            }
        });
        btnDroite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                droite();
            }
        });
        btnAR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                animerRotation();
            }
        });
        btnAT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                animerTranslation();
            }
        });
        btnAE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                animerEchelle();
            }
        });
    }

    private void selectionner(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {  //Bouton gauche
            if (mouseEvent.getClickCount() == 1) { //Simple click
                imgCourante.setEffect(null);
                imgCourante = (ImageView) mouseEvent.getSource();
                Reflection reflection = new Reflection();
                imgCourante.setEffect(reflection);
                System.out.println("1");
            }
            //C13
            else { //Double click
                animerRotation();
                animerTranslation();
                animerEchelle();
                System.out.println("2");
            }
        } else { //Bpouton droit
//            if (mouseEvent.getSource() != imgEtoile)
            pSurface.getChildren().remove(imgCourante);
            System.out.println("3");
        }
    }

    private void gauche() {
        imgCourante.setLayoutX(imgCourante.getLayoutX() - PAS);
    }

    private void haut() {
        imgCourante.setLayoutY(imgCourante.getLayoutY() - PAS);
    }

    private void bas() {
        imgCourante.setLayoutY(imgCourante.getLayoutY() + PAS);
    }

    private void droite() {
        imgCourante.setLayoutX(imgCourante.getLayoutX() + PAS);
    }

    private void animerRotation() {
        RotateTransition rt = new RotateTransition(Duration.millis(2000), imgCourante);
        rt.setByAngle(360);
        rt.setCycleCount(2);
        rt.setAutoReverse(true);
        rt.play();
    }

    private void animerTranslation() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), imgCourante);
        tt.setByX(400f);
        tt.setCycleCount(2);
        tt.setAutoReverse(true);
        tt.play();
    }

    private void animerEchelle() {
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), imgCourante);
        st.setByX(1.5f);
        st.setByY(1.5f);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();
    }
}