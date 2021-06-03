package Vue;

import Modèle.Objet;
import javafx.scene.image.ImageView;

public class ObjetVue {
    private ImageView img;
    public ObjetVue(String url){
        img=new ImageView(url);
    }
    public ImageView objetVue(){return img;}
}
