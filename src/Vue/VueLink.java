package Vue;

import Modèle.Link;
import Modèle.Personnage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueLink implements VuePersonnage{

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
        ImageView link = new ImageView("Vue/Link32x32.png");
        link.translateXProperty().bind(p.getXProperty());
        link.translateYProperty().bind(p.getYProperty());
        return link;
    }
}
