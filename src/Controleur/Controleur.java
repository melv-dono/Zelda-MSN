package Controleur;

import Modèle.Link;
import Modèle.MapModele;
import Modèle.Personnage;
import Modèle.Squelette;
import Vue.MapReader;
import Vue.VueLink;
import Vue.VuePersonnage;
import Vue.VueSquelette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
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
				        if(cpt==300){
				            cpt=0;
                        }
                    }
                    cpt++;
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MapModele spawn = new MapModele("testMap");
        MapReader m  = new MapReader(map);
        m.chargerMap(spawn.getTableau());


        Link p = new Link();
        VueLink vue = new VueLink(p);
        ArrowGestion a = new ArrowGestion(p);
        ImageView personnage = vue.creeSprite();
        this.ptVie.textProperty().bind(p.pv().asString());
        labelNiveau.textProperty().bind(p.niveau().asString());
        ProgressBarExp.setProgress(0.7);
        plateau.getChildren().add(personnage);
        plateau.setOnKeyPressed(a);

        Squelette s = new Squelette("Squelette");
        VueSquelette vueS = new VueSquelette(s);
        ImageView imageSquelette = vueS.creeSprite();
        plateau.getChildren().add(imageSquelette);

        animation(s);
        gameLoop.play();


    }



}
