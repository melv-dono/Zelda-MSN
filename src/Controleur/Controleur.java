package Controleur;

import Modèle.Link;
import Modèle.MapModele;
import Modèle.Personnage;
import Vue.MapReader;
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
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Pane plateau; // Attention remettre tilePane

    @FXML
    private TilePane tile = new TilePane();

    public ImageView creeSprite(Personnage p) {
        ImageView link = new ImageView("Vue/link-bouclier-de-base.png");
        link.translateXProperty().bind(p.getXProperty());
        link.translateYProperty().bind(p.getYProperty());
        plateau.getChildren().add(link);
        return link;

    }

    @FXML
    public void click(MouseEvent me) {
        System.out.println(me.getX());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("ok");
        int[] coordoneesCarte = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

        MapModele spawn = new MapModele(1,coordoneesCarte);
        MapReader m  = new MapReader(plateau);
        m.chargerMap(spawn.getTableau());

        System.out.println("ici");
        //MapReader map = new MapReader("1");
        //map.chargerMap();
        //this.plateau.setFocusTraversable(true);
        System.out.println("ici");
        Link p = new Link();
        ImageView personnage = creeSprite(p);
        ArrowGestion a = new ArrowGestion(p);
        plateau.setOnKeyPressed(a);
        /*
        this.plateau.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                System.out.println("ici");
                switch(event.getCode()) {
                    case Z :
                        System.out.println("ok"); break;
                    case Q :
                        System.out.println("ok"); break;
                    case S :
                        System.out.println("ok"); break;
                    case D :
                        System.out.println("ok"); break;
                }
            }
        });*/
    }



}
