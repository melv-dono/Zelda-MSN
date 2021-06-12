package Vue;

import javafx.scene.image.ImageView;

public class VuePerso {

    private ImageView img;
    private int id;

    public VuePerso(String url, int id){
        img =new ImageView(url);
        this.id=id;
        /*imgSquelette.translateXProperty().bind(s.getDeplacementLargeurProperty());
        imgSquelette.translateYProperty().bind(s.getDeplacementHauteurProperty());*/

    }

    /**
     * Crée l'image du squelette et l'associe au modèle.
     * @return
     */

    public ImageView getImg(){return img;}
    public int getId(){
        return id;
    }
    
}
