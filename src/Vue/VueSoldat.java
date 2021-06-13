package Vue;

import Modèle.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueSoldat implements VuePerso{
    private ImageView imgSoldat;
    private Personnage b;

    public VueSoldat(Personnage soldat, String url){
        this.b=soldat;
        imgSoldat =new ImageView(url);
        imgSoldat.translateXProperty().bind(b.getDeplacementLargeurProperty());
        imgSoldat.translateYProperty().bind(b.getDeplacementHauteurProperty());
    }

    public void animation(String direction) {
        this.imgSoldat.setImage(new Image("Vue/bad_greensold_swing1.gif"));
    }

    public void orientation(String direction) {
        this.imgSoldat.setImage(new Image("Vue/bad_soldgreen_front1.gif"));
    }

    /**
     * Crée l'image du squelette et l'associe au modèle.
     * @return
     */


    public ImageView getImgSoldat(){return imgSoldat;}
    public Personnage getS(){return b;}
}
