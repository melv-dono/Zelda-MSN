package Vue;

import Modèle.Link;
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

    /**
     * Crée l'image de Link et l'associe au modèle.
     * @return
     */
    public ImageView creeSprite() {
        ImageView link = new ImageView("Vue/link_front2.gif");
        link.translateXProperty().bind(p.getDeplacementLargeurProperty());
        link.translateYProperty().bind(p.getDeplacementHauteurProperty());
        return link;
    }
}
