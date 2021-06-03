package Vue;

import Modèle.Objet;
import javafx.scene.image.ImageView;

public class ObjetVue {
    private Objet objet;
    private ImageView img;
    public ObjetVue(Objet obj,String url){
        objet=obj;
        img=new ImageView(url);
        img.translateXProperty().bind(objet.getPositionLargeur());
        img.translateYProperty().bind(objet.getPositionHauteur());
    }
    public Objet objetImg(){
        return objet;
    }
    public ImageView objetVue(){return img;}
}
