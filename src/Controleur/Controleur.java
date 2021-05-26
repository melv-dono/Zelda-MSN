package Controleur;

import Modèle.*;
import Vue.MapReader;
import Vue.VueLink;
import Vue.VueSquelette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Label labelNiveau;
    @FXML
    private ProgressBar ProgressBarExp;
    @FXML
    private Label ptDef;
    @FXML
    private Label ptExp;
    @FXML
    private Label ptAtt;
    @FXML
    private Label ptVie;

    @FXML
    private VBox menuPause;

    @FXML
    private Pane plateau;

    @FXML
    private TilePane map = new TilePane();
    @FXML
    private VBox LinkLife;

    private Timeline gameLoop;

    private int cpt;


    /**
     * Rend automatique le déplacement du squelette au sein de l'environnement.
     * @param s
     */
    private void animation(Squelette s){
        cpt=0;
        gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
				Duration.seconds(0.01),
				(ev ->{
				    if(cpt < 150) {
                        s.monter();
                    }
				    else if(cpt >=150){
				        s.descendre();
                    }
                    if(cpt==298){
                        cpt=0;
                    }
                    cpt++;
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}

    /**
     * Charge tous les éléments présent sur la map de départ.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MapModele spawn = new MapModele("testMap");
        MapReader m  = new MapReader(map);
        m.chargerMap(spawn.getTableau());
        Environnement env = new Environnement(Parametre.LARGEUR, Parametre.HAUTEUR,spawn);
        Link p = new Link(env);
        VueLink vue = new VueLink(p);
        ArrowGestion a = new ArrowGestion(p,plateau,menuPause);
        ImageView personnage = vue.creeSprite();
        this.ptVie.textProperty().bind(p.pv().asString());
        labelNiveau.textProperty().bind(p.niveau().asString());
        ProgressBarExp.setProgress(0.7);
        plateau.getChildren().add(personnage);
        plateau.setOnKeyPressed(a);
        Squelette s = new Squelette("Squelette",env);
        VueSquelette vueS = new VueSquelette(s);
        ImageView imageSquelette = vueS.creeSprite();
        plateau.getChildren().add(imageSquelette);

        animation(s);
        gameLoop.play();


    }



}
