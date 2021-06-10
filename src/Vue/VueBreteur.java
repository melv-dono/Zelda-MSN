package Vue;

import Modèle.Personnage;
import javafx.scene.image.ImageView;

public class VueBreteur {
    private ImageView imgBretteur;
    private Personnage b;

    public VueBreteur(Personnage bretteur, String url){
        this.b=bretteur;
        imgBretteur=new ImageView(url);
        imgBretteur.translateXProperty().bind(b.getDeplacementLargeurProperty());
        imgBretteur.translateYProperty().bind(b.getDeplacementHauteurProperty());

    }

    /**
     * Crée l'image du squelette et l'associe au modèle.
     * @return
     */

    public ImageView getImgBretteur(){return imgBretteur;}
    public Personnage getS(){return b;}
}
