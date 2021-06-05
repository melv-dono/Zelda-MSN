package Controleur;

import Modèle.*;
import Vue.ObjetVue;
import Vue.VueBouleDeFeu;
import Vue.VueLink;
import Vue.VueSquelette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
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
    private ObservableList<Objet> objetEnvironnement;

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
                System.out.println("début "+objetEnvironnement);
                System.out.println(objetEnvironnement.size());
                if(objetEnvironnement.size()>=1){
                    for(int i=0;i<objetEnvironnement.size();i++){
                        System.out.println("var I="+i);
                        if(((objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()>=-32 &&objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()<=32) && objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()==0) || ((objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()<=32&&objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()>=-32)&& objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()==0) ){
                            if(objetEnvironnement.get(i) instanceof ObjetRamassable){
                                inventaire.addObjet(objetEnvironnement.get(i));
                                objetEnvironnement.remove(objetEnvironnement.get(i));
                            }else if(objetEnvironnement.get(i) instanceof Rocher){
                                if(env.getInventaire().inventairePossede("pioche")){
                                    objetEnvironnement.remove(objetEnvironnement.get(i));
                                }

                            }else if(objetEnvironnement.get(i) instanceof Pioche){
                                inventaire.addObjet(objetEnvironnement.get(i));
                                objetEnvironnement.remove(objetEnvironnement.get(i));
                            }
                        }

                        System.out.println("end"+objetEnvironnement);
                        System.out.println("");
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
