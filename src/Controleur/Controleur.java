package Controleur;

import Modèle.Link;
import Modèle.Personnage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane plateau; // Attention remettre tilePane

    public Circle creeSprite(Personnage p) {
        Circle c = new Circle(3);
        c.setFill(Color.RED);
        p.getXProperty().addListener((old, ol, nouv) -> p.setX(c.getCenterX()));
        p.getYProperty().addListener((old, ol, nouv) -> p.setY(c.getCenterY()));
        plateau.getChildren().add(c);
        return c;

    }

    @FXML
    public void click(MouseEvent me) {
        System.out.println(me.getX());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ici");
        this.plateau.setFocusTraversable(true);
        System.out.println("ici");
        /*Link p = new Link();
        Circle c = creeSprite(p);
        ArrowGestion a = new ArrowGestion(c);
        plateau.setOnKeyPressed(a);*/
        this.plateau.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                System.out.println("ici");
                switch(event.getCode()) {
                    case Z :
                        System.out.println("ok"); break;
                    case Q :
                        System.out.println("ok"); break;
                    case S :
                        System.out.println("ok"); break;
                    case D :
                        System.out.println("ok"); break;
                }
            }
        });
        System.out.println("ok");
    }


}
