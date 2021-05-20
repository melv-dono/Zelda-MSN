package Controleur;

import Modèle.Link;
import Modèle.MapModele;
import Vue.MapReader;
import Vue.VueLink;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MapModele spawn = new MapModele("testMap");
        MapReader m  = new MapReader(map);
        m.chargerMap(spawn.getTableau());


        Link p = new Link();
        VueLink vue = new VueLink(p);
        ArrowGestion a = new ArrowGestion(p,plateau,menuPause);
        ImageView personnage = vue.creeSprite();
        this.ptVie.textProperty().bind(p.pv().asString());
        labelNiveau.textProperty().bind(p.niveau().asString());
        ProgressBarExp.setProgress(0.7);
        plateau.getChildren().add(personnage);
        plateau.setOnKeyPressed(a);



    }



}
