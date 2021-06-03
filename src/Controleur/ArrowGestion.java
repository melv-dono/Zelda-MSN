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

public class ArrowGestion implements EventHandler<KeyEvent> {

    @FXML
    private Link perso;
    private boolean b=false;

    public ArrowGestion(Link p) {
        this.perso = p;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        // Attention les coordonnées des y sont inversés.
        switch (keyEvent.getCode()) {
            case UP:
                if(b==true){
                    this.perso.setOrientation("monter");
                }
                else{
                    this.perso.monter();
                    this.perso.setOrientation("monter");
                }
                break;
            case DOWN:
                if(b==true){
                    this.perso.setOrientation("descendre");
                }
                else{
                    this.perso.descendre();
                    this.perso.setOrientation("descendre");
                }
                break;
            case LEFT:
                if(b==true){
                    this.perso.setOrientation("gauche");
                }
                else{
                    this.perso.gauche();
                    this.perso.setOrientation("gauche");
                }
                break;
            case RIGHT:
                if(b==true){
                    this.perso.setOrientation("droite");
                }
                else{
                    this.perso.droite();
                    this.perso.setOrientation("droite");
                }
                break;
            case W:
                b=true;
                break;
            case X:
                b=false;
                break;
            default:
                break;
        }
    }
}