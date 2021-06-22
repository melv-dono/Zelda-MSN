package Vue.Perso;

import Vue.VueAnimation;
import javafx.scene.image.ImageView;

public class VuePerso implements VueAnimation {

    private ImageView img;
    private int id;

    public VuePerso(String url, int id){
        img =new ImageView(url);
        this.id=id;

    }

    /**
     * Crée l'image du squelette et l'associe au modèle.
     * @return
     */

    public ImageView getImg(){return img;}
    public int getId(){
        return id;
    }

    @Override
    public void animation(String direction) {

    }

    @Override
    public void orientation(String directrion) {

    }
}
