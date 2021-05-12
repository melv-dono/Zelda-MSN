package Vue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;


import java.io.*;

public class MapReader {
    @FXML
    private TilePane map;

    public MapReader(TilePane t){
        this.map = t;
    }

    public void chargerMap(int[] coordoneesCarte){
        int tuiles =0;
        for (int i=0; i<(coordoneesCarte.length); i++){
            switch (coordoneesCarte[tuiles]){
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
            tuiles++;
        }
    }
}
