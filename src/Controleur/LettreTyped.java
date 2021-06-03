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
    private Environnement env;
    @FXML
    private Inventaire inventaire;


    public LettreTyped(VBox vb, Pane map, Timeline t, Environnement environnement) {
        this.perso = environnement.getLink();
        menuPause=vb;
        plateau=map;
        menuPause.setVisible(false);
        this.gameloop = t;
        objetEnvironnement=environnement.getObjetEnvironnement();
        inventaire=environnement.getInventaire();
        env=environnement;

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
                for(Objet obj:objetEnvironnement){
                    if(obj instanceof ObjetRamassable){
                        if(((obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()>=-32 &&obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()<=32) && obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()==0) || ((obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()<=32&&obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()>=-32)&& obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()==0) ){
                            plateau.getChildren().remove(obj);
                            env.deleteCoordExt(obj.getPositionLargeur().getValue(),obj.getPositionHauteur().getValue());
                            inventaire.addObjet(obj);
                            obj.setPositionHauteur(999);
                            obj.setPositionLargeur(999);
                        }
                    }else if(obj instanceof Rocher){
                        if(((obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()>=-32 &&obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()<=32) && obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()==0) || ((obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()<=32&&obj.getPositionLargeur().getValue()-perso.getDeplacementLargeur()>=-32)&& obj.getPositionHauteur().getValue()-perso.getDeplacementHauteur()==0) ){
                            plateau.getChildren().remove(obj);
                            env.deleteCoordExt(obj.getPositionLargeur().getValue(),obj.getPositionHauteur().getValue());
                            obj.setPositionHauteur(999);
                            obj.setPositionLargeur(999);
                        }
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

}
