package Vue;

import Modèle.BouleDeFeu;
import Modèle.Personnage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueBouleDeFeu {

    private ImageView bouleImg;

    private BouleDeFeu b;

    public VueBouleDeFeu(BouleDeFeu boule,String url){
        this.b= boule;
        bouleImg=new ImageView(url);
        bouleImg.translateXProperty().bind(b.xPropertyProperty());
        bouleImg.translateYProperty().bind(b.yPropertyProperty());
        bouleImg.setId(String.valueOf(b.getId()));
    }
    public ImageView getBouleImg(){
        return bouleImg;
    }


    /**
     * Crée l'image de Link et l'associe au modèle.
     * @return
     */
}
