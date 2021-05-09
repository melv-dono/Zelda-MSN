package Controleur;

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

    public void creeSprite() {
        Circle c = new Circle(3);
        c.setFill(Color.RED);
        plateau.getChildren().add(c);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
