package Controleur;

import Modèle.Environnement;
import Modèle.Link;
import Modèle.MapModele;
import Modèle.Personnage;
import Vue.MapReader;
import Vue.VueLink;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    //Attention on a du retirer 2 tile (64) par rapport aux valeurs initials, afin de rester dans le cadre.
    //Attention la bonne résolution a été rétabli
    static int LARGEUR = 1280;
    static int HAUTEUR = 736;

    @FXML
    private Pane plateau;

    @FXML
    private TilePane map;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MapModele spawn = new MapModele("testMap");
        MapReader m  = new MapReader(map);
        m.chargerMap(spawn.getTableau());
        Environnement env = new Environnement(LARGEUR, HAUTEUR,spawn);
        Link p = new Link(env);
        VueLink vue = new VueLink(p);
        ImageView personnage = vue.creeSprite();
        ArrowGestion a = new ArrowGestion(p);
        plateau.getChildren().add(personnage);
        plateau.setOnKeyPressed(a);
    }
}
