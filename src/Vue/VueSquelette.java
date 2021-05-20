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

    public ImageView creeSprite() {
        ImageView squelette = new ImageView("Vue/squelette.png");
        squelette.translateXProperty().bind(s.getXProperty());
        squelette.translateYProperty().bind(s.getYProperty());
        return squelette;
    }
}
