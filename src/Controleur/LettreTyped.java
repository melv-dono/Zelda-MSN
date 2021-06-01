package Controleur;

import Modèle.*;
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


    public LettreTyped(Link p, VBox vb, Pane map, Timeline t) {
        this.perso = p;
        menuPause=vb;
        menuPause.setVisible(false);
        this.plateau = map;
        this.gameloop = t;
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
            default:
                break;
        }
    }

//    public void affichageBoule(BouleDeFeu b) {
//        VueBouleDeFeu vue = new VueBouleDeFeu(b);
//        plateau.getChildren().add(vue.creeSprite());
//    }
}
