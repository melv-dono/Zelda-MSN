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
 * Cette classe est un gestionnaire d'éveènement permettant de gerer des évènements via le clavier.
 * Chaque déplacement change les coordonnées du personnage dans le modèle et cela se répercute dans la vue.
 */

public class ArrowGestion implements EventHandler<KeyEvent> {

    @FXML
    private Personnage perso;

    public ArrowGestion(Link p) {
        this.perso = p;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        // Attention les coordonnées des y sont inversés.
        switch (keyEvent.getCode()) {
            case UP:
                this.perso.monter();
                break;
            case DOWN:
                this.perso.descendre();
                break;
            case LEFT:
                this.perso.gauche();
                break;
            case RIGHT:
                this.perso.droite();
                break;
            default:
                break;
        }
    }
}
