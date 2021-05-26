package Vue;

import Modèle.Personnage;
import javafx.scene.image.ImageView;

public class VueLink {

    private Personnage p;

    public VueLink(Personnage perso){
        this.p=perso;
    }

    public ImageView creeSprite() {
        ImageView link = new ImageView("Vue/link_front2.gif");
        link.translateXProperty().bind(p.getXProperty());
        link.translateYProperty().bind(p.getYProperty());
        return link;
    }
}
