package Vue;

import Modèle.Personnage;
import Modèle.Squelette;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueSquelette{

    private ImageView imgSquelette;
    private Personnage s;

    public VueSquelette(Personnage squelette,String url){
        this.s=squelette;
        imgSquelette=new ImageView(url);
        imgSquelette.translateXProperty().bind(s.getDeplacementLargeurProperty());
        imgSquelette.translateYProperty().bind(s.getDeplacementHauteurProperty());
    }

    /**
     * Crée l'image du squelette et l'associe au modèle.
     * @return
     */

    public ImageView getImgSquelette(){return imgSquelette;}
    public Personnage getS(){return s;}
    
}
