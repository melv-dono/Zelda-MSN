package Vue;

import Modèle.Personnage;
import javafx.scene.image.ImageView;

public class VueLink {

    private Personnage p;

    public VueLink(Personnage perso){
        this.p=perso;
    }

    public ImageView creeSprite() {
        ImageView link = new ImageView("Vue/Link32x32.png");
        link.translateXProperty().bind(p.getXProperty());
        link.translateYProperty().bind(p.getYProperty());
        return link;
    }
}
