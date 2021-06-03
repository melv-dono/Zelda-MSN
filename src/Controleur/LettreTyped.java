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

public class LettreTyped implements EventHandler<KeyEvent> {

    @FXML
    private VBox menuPause;
    @FXML
    private Link perso;
    @FXML
    private Pane plateau;

    private Timeline gameloop;
    @FXML
    private Objet potion;
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
        this.potion= potionVue.objetImg();
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
                this.perso.attaquer();
                break;
            case A:
//                Timeline chargementAttaque = new Timeline( à conserver surement utile pour l'animation de l'attaque
//                        new KeyFrame(Duration.millis(500),
//                            e -> {
//                                this.perso.attaquer();
//                                System.out.println("Tentative d'attaque!");
//                            }
//                        )
//                );
//                chargementAttaque.setCycleCount(1);
//                chargementAttaque.play();

//                if (this.perso.getarmeSecondaire() instanceof BaguetteMagique) {
//                    BouleDeFeu b1 = this.perso.getarmeSecondaire().creeBoule();
//                    ImageView vue;
//                    VueBouleDeFeu vueBouleDeFeu = new VueBouleDeFeu(b1);
//                    vue = vueBouleDeFeu.creeSprite();
//                    plateau.getChildren().add(vue);
//                    this.perso.getarmeSecondaire().lancerBouleDeFeu(b1, gameloop);
////                    plateau.getChildren().remove(vue);
//
//                }
                /*
                Le plus opti serait que ici on est juste personnage.lancerBouleDeFeu
                 */
                this.perso.getarmeSecondaire().creeBoule();
                break;
            case R:
                if(((potion.getPositionHauteur().getValue()-perso.getDeplacementHauteur()>=-32 &&potion.getPositionHauteur().getValue()-perso.getDeplacementHauteur()<=32) && potion.getPositionLargeur().getValue()-perso.getDeplacementLargeur()==0) || ((potion.getPositionLargeur().getValue()-perso.getDeplacementLargeur()<=32&&potion.getPositionLargeur().getValue()-perso.getDeplacementLargeur()>=-32)&& potion.getPositionHauteur().getValue()-perso.getDeplacementHauteur()==0) ){
                    plateau.getChildren().remove(lapotion);
                    env.deleteCoordExt(potion.getPositionLargeur().getValue(),potion.getPositionHauteur().getValue());
                    inventaire.addObjet(potion);
                    potion.setPositionHauteur(999);
                    potion.setPositionLargeur(999);
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
