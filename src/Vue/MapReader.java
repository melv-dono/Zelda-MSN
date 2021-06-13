package Vue;
import Modèle.Parametre;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

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
        Image tileset = new Image("Vue/images/Tuile.png");
        ImageView image;

        for (int i = 0; i< Parametre.LIGNE; i++){
            for(int j=0; j< Parametre.COLONNE;j++){
                image = new ImageView(tileset);
                int ligne = ((coordoneesCarte[i][j] - (coordoneesCarte[i][j] % 10)) /10 ) * Parametre.TUILE_SIZE;
                int colonne = ((coordoneesCarte[i][j] % 10)-1) * Parametre.TUILE_SIZE;
                Rectangle2D r = new Rectangle2D(colonne,ligne,Parametre.TUILE_SIZE,Parametre.TUILE_SIZE);
                image.setViewport(r);
                map.getChildren().add(image);
            }
        }
    }

    public void reset(){
        for(int i= map.getChildren().size()-1; i>=0;i--){
            map.getChildren().remove(i);
        }
    }
}
