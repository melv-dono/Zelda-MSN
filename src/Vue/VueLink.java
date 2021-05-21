package Vue;

import Modèle.Personnage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueLink {

    @FXML
    private Pane plateau;

    private Personnage p;

    public VueLink(Personnage perso){
        this.p=perso;
    }

    public ImageView creeSprite() {
        ImageView link = new ImageView("Vue/link_32.gif");
        link.translateXProperty().bind(p.getXProperty());
        link.translateYProperty().bind(p.getYProperty());
        return link;
    }
}
