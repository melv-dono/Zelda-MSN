package Controleur;

import Modèle.Link;
import Modèle.Personnage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/*
 * Cette classe est un gestionnaire d'éveènement permettant de gerer des déplacements via le clavier.
 * Chaque déplacement change les coordonnées du personnage dans le modèle et cela se répercute dans la vue.
 */

public class ArrowGestion implements EventHandler<KeyEvent> {

    @FXML
    private VBox menuPause;
    @FXML
    private Personnage perso;
    @FXML
    private Pane plateau;

    public ArrowGestion(Link p,Pane pane,VBox vb) {
        this.perso = p;
        plateau=pane;
        menuPause=vb;
        menuPause.setVisible(false);
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        // Attention les coordonnées des y sont inversés.
        switch (keyEvent.getCode()) {
            case UP:
                this.perso.setY(this.perso.getY() - 4);
                break;
            case DOWN:
                this.perso.setY(this.perso.getY() + 4);
                break;
            case LEFT:
                this.perso.setX(this.perso.getX() - 4);
                break;
            case RIGHT:
                this.perso.setX(this.perso.getX() + 4);
                break;
            case I:
                menuPause.setVisible(true);
                break;
            case Q:
                menuPause.setVisible(false);
                break;
            default:
                break;
        }
    }
}
