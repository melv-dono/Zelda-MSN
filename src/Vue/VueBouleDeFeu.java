package Vue;

import Modèle.BouleDeFeu;
import Modèle.Personnage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueBouleDeFeu implements VuePersonnage{

    @FXML
    private Pane plateau;

    private BouleDeFeu b;

    public VueBouleDeFeu(BouleDeFeu boule){
        this.b= boule;
    }


    /**
     * Crée l'image de Link et l'associe au modèle.
     * @return
     */
    public ImageView creeSprite() {
        ImageView fire = new ImageView("Vue/item_bomb_boom1.gif");
        fire.translateXProperty().bind(b.xPropertyProperty());
        fire.translateYProperty().bind(b.yPropertyProperty());
        fire.setId(String.valueOf(b.getId()));
        return fire;
    }
}
