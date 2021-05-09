package Controleur;

import Modèle.Personnage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private TilePane plateau;

    public void creeSprite(Personnage p) {
        Circle c = new Circle(3);
        c.setFill(Color.RED);
        p.getXProperty().addListener((old, ol, nouv) -> p.setX(c.getCenterX()));
        p.getYProperty().addListener((old, ol, nouv) -> p.setY(c.getCenterY()));
        plateau.getChildren().add(c);

    }

    public void deplacement() {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
