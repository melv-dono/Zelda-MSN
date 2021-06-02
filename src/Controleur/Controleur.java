package Controleur;

import Modèle.*;
import Vue.MapReader;
import Vue.VueBouleDeFeu;
import Vue.VueLink;
import Vue.VueSquelette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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

    private Environnement env;

    private ArrowGestion arrow;

    private LettreTyped action;

    /**
     * Rend automatique le déplacement du squelette au sein de l'environnement.
     * @param s
     */
//    private void animation(Squelette s, VueLink vue){
    private void animation(Squelette s, Timeline gameLoop, VueLink vue){ //L'animation du suqellete marche plus vu qu'il est considéré comme un perso
        cpt=0;
        KeyFrame kf = new KeyFrame(
				Duration.seconds(0.017),
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
                    this.env.faireUntour();
                    vue.orientation();

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
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        this.env = new Environnement(Parametre.LARGEUR, Parametre.HAUTEUR);
        this.env.init();
        MapReader m  = new MapReader(map);
        m.chargerMap(env.getMapActuelle().getTableau());
        affichage();
        connexion();
        gestionBouleDeFeu();
        plateau.setOnKeyReleased(action);
        plateau.setOnKeyPressed(arrow);

//        Squelette s = new Squelette("Squelette",env);
//        VueSquelette vueS = new VueSquelette(s);
//        env.addPerso(s);
//        ImageView imageSquelette = vueS.creeSprite();
//        plateau.getChildren().add(imageSquelette);

        animation((Squelette) env.getPerso().get(1), gameLoop, vue);
        gameLoop.play();


    }

    public void affichage() {
        for (Personnage p : this.env.getPerso()) {
            if (p instanceof Link) {
                VueLink l = new VueLink(p);
                plateau.getChildren().add(l.creeSprite());
            }
            if (p instanceof Squelette) {
                VueSquelette s = new VueSquelette(p);
                plateau.getChildren().add(s.creeSprite());
            }
        }
    }

    public void connexion() {
        arrow = new ArrowGestion(env.getLink());
        action = new LettreTyped(env.getLink(), menuPause, plateau, gameLoop);
        this.ptVie.textProperty().bind(env.getLink().pvProperty().asString());
        labelNiveau.textProperty().bind(env.getLink().niveau().asString());
        ProgressBarExp.setProgress(0.7);
    }

    public void gestionBouleDeFeu() {
        ObservateaurBouleDeFeu obs1 = new ObservateaurBouleDeFeu(plateau);
        this.env.getLink().getarmeSecondaire().getBoules().addListener(obs1);
    }



}
