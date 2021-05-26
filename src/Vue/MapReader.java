package Vue;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;


import java.io.*;
import java.util.ArrayList;

public class MapReader {
    @FXML
    private TilePane map;

    public MapReader(TilePane t){
        this.map = t;
    }

    /**
     * Charge les tuiles du TilePane avec les images de la vue.
     * @param coordoneesCarte
     */
    public void chargerMap(int[][] coordoneesCarte){
        /*int tuiles =0;
        for (int i=0; i<23; i++){
            for (int j=0; j<40; j++) {
                switch (coordoneesCarte[i][j]) {
                    case 1:
                        ImageView affichageHerbe = new ImageView("Vue/herbe32x32.png");
                        map.getChildren().add(affichageHerbe);
                        break;
                    case 2:
                        ImageView affichageMurs = new ImageView("Vue/mur32x32.png");
                        map.getChildren().add(affichageMurs);
                        break;
                    default:
                        break;
                }
            }
        }*/



        Image tileset = new Image("Vue/Tuile.png");
        ImageView image;

        for (int i=0; i<23; i++){
            for(int j=0; j<40;j++){
                image = new ImageView(tileset);
                int ligne = ((coordoneesCarte[i][j] - (coordoneesCarte[i][j] % 10)) /10 ) * 32;
                int colonne = ((coordoneesCarte[i][j] % 10)-1) * 32;
                Rectangle2D r = new Rectangle2D(colonne,ligne,32,32);
                image.setViewport(r);
                map.getChildren().add(image);
            }
        }
    }
}
