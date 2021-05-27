package Controleur;

import Modèle.Environnement;
import Modèle.Link;
import Modèle.Objet;
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

public class SceneEventGestion implements EventHandler<KeyEvent> {

    @FXML
    private VBox menuPause;
    @FXML
    private Personnage perso;
    @FXML
    private Pane plateau;
    @FXML
    private Objet potion;

    public SceneEventGestion(Link p, Pane pane, VBox vb, Objet potion) {
        perso = p;
        menuPause=vb;
        menuPause.setVisible(false);
        this.potion=potion;
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
            case I:
                menuPause.setVisible(true);
                break;
            case Q:
                menuPause.setVisible(false);
                break;
            case R:
                /*if(potion.ramasserObjet(perso.getDeplacementLargeur(),perso.getDeplacementHauteur())==true){
                    plateau.se
                }*/

            default:
                break;
        }
    }
}
