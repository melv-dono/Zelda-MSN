package Vue;

import Modèle.Personnage;
import Modèle.Squelette;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueSquelette implements VuePersonnage{

    @FXML
    private Pane plateau;

    private Squelette s;

    public VueSquelette(Squelette squelette){
        this.s=squelette;
    }

    /**
     * Crée l'image du squelette et l'associe au modèle.
     * @return
     */
    public ImageView creeSprite() {
        ImageView squelette = new ImageView("Vue/bad_skeleton.gif");
        squelette.translateXProperty().bind(s.getDeplacementLargeurProperty());
        squelette.translateYProperty().bind(s.getDeplacementHauteurProperty());
        return squelette;
    }
}
