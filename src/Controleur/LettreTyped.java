package Controleur;

import Modèle.*;
import Vue.ObjetVue;
import Vue.VueBouleDeFeu;
import Vue.VueLink;
import Vue.VueSquelette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.time.Period;
import java.util.ArrayList;

public class LettreTyped implements EventHandler<KeyEvent> {

    @FXML
    private VBox menuPause;
    @FXML
    private Link perso;
    @FXML
    private Pane plateau;

    private Timeline gameloop;
    private ArrayList<Objet> objetEnvironnement;
    @FXML
    private ImageView lapotion;
    @FXML
    private Environnement env;
    @FXML
    private Inventaire inventaire;


    public LettreTyped(Link p, VBox vb, Pane map, Timeline t, Environnement environnement, Inventaire i, ObjetVue potionVue) {
        this.perso = p;
        menuPause=vb;
        plateau=map;
        menuPause.setVisible(false);
        this.plateau = map;
        this.gameloop = t;
        objetEnvironnement=environnement.getObjetEnvironnement();
        inventaire=i;
        lapotion=potionVue.objetVue();
        env=environnement;
        plateau.getChildren().add(potionVue.objetVue());

    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case I:
                menuPause.setVisible(true);
                break;
            case Q:
                menuPause.setVisible(false);
                break;
            case D:
                this.perso.getArmePrincipale().attaquer();
                this.perso.setMoving(true);
                break;
            case A:
                this.perso.getarmeSecondaire().creeBoule();
                break;
            case R:
                // d'abord boucle for puis tableau machin
                for(Objet obj:objetEnvironnement){
                    if(((obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()>=-32 &&obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()<=32) && obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()==0) || ((obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()<=32&&obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()>=-32)&& obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()==0) ){
                        plateau.getChildren().remove(lapotion);
                        env.deleteCoordExt(obj.getPositionLargeur().getValue(),obj.getPositionHauteur().getValue());
                        inventaire.addObjet(obj);
                        obj.setPositionHauteur(999);
                        obj.setPositionLargeur(999);
                    }
                }
                break;
            case P:
                perso.decrementerPv(5);
            break;
            case O:
                perso.augmenterPv(10);
            break;
            default:
                break;
        }
    }

//    public void affichageBoule(BouleDeFeu b) {
//        VueBouleDeFeu vue = new VueBouleDeFeu(b);
//        plateau.getChildren().add(vue.creeSprite());
//    }
}
