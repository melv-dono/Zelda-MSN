package Controleur.clavier;

import Modèle.Link;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

/*
 * Cette classe est un gestionnaire d'éveènement permettant de gerer des évènements via le clavier.
 * Chaque déplacement change les coordonnées du personnage dans le modèle et cela se répercute dans la vue.
 */

public class ArrowGestion implements EventHandler<KeyEvent> {

    @FXML
    private Link perso;
    private int orientation;

    public ArrowGestion(Link p) {
        this.perso = p;
        orientation=0;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        // Attention les coordonnées des y sont inversés.
        switch (keyEvent.getCode()) {
            case UP:
                if(orientation!=1){ // bouge sur toi même monte
                    this.perso.setOrientation("monter");
                    orientation=1;
                }
                else{
                    this.perso.monter();
                    this.perso.setOrientation("monter");
                }
                break;
            case DOWN:
                if(orientation!=2){
                    this.perso.setOrientation("descendre");
                    orientation=2;
                }
                else{
                    this.perso.descendre();
                    this.perso.setOrientation("descendre");
                }
                break;
            case LEFT:
                if(orientation!=3){
                    this.perso.setOrientation("gauche");
                    orientation=3;
                }
                else{
                    this.perso.gauche();
                    this.perso.setOrientation("gauche");
                }
                break;
            case RIGHT:
                if(orientation!=4){
                    this.perso.setOrientation("droite");
                    orientation=4;
                }
                else{
                    this.perso.droite();
                    this.perso.setOrientation("droite");
                }
                break;
            default:
                break;
        }
    }
}