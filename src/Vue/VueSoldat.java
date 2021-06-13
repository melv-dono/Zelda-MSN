package Vue;

import Modèle.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueSoldat extends VuePerso{

    public VueSoldat(int id, String url){
        super(url, id);
    }

    public void animation(String direction) {
        getImg().setImage(new Image("Vue/bad_greensold_swing1.gif"));
    }

    public void orientation(String direction) {getImg().setImage(new Image("Vue/bad_soldgreen_front1.gif"));
    }

}
