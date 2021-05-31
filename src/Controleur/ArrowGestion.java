package Controleur;

import Modèle.Link;
import Modèle.Personnage;
import Vue.VueLink;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
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

public class ArrowGestion implements EventHandler<KeyEvent>{

    @FXML
    private VBox menuPause;
    @FXML
    private Link perso;
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
                this.perso.monter();
                this.perso.sens("monter");
                break;
            case DOWN:
                this.perso.descendre();
                this.perso.sens("descendre");
                break;
            case LEFT:
                this.perso.gauche();
                this.perso.sens("gauche");
                break;
            case RIGHT:
                this.perso.droite();
                this.perso.sens("droite");
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
