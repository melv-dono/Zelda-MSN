package Vue;

import Modèle.Link;
import Modèle.Personnage;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueLink extends ImageView{

    private Link p;

    public VueLink(Link perso){
        this.p=perso;
    }

    /**
     * Crée l'image de Link et l'associe au modèle.
     * @return
     */
    public ImageView creeSprite() {
        super.setImage(new Image("Vue/link_front2.gif"));
        super.translateXProperty().bind(p.getDeplacementLargeurProperty());
        super.translateYProperty().bind(p.getDeplacementHauteurProperty());
        super.setId(this.p.getNom());
        return this;
    }

    public void animation() {
        if (this.p.getOrientation() == "gauche") {
            super.setImage(new Image("Vue/link_swing8.gif"));
        }
        if (this.p.getOrientation() == "droite") {
            super.setImage(new Image("Vue/link_swing4.gif"));
        }
        if (this.p.getOrientation() == "monter") {
            super.setImage(new Image("Vue/link_swing5.gif"));
        }
        if (this.p.getOrientation() == "descendre") {
            super.setImage(new Image("Vue/link_swing.gif"));
        }
    }

    public void desAniamtion() {
        super.setImage(new Image("Vue/link_front2.gif"));
    }

    public void orientation(){
        switch (p.getOrientation()){
            case "monter" :
                super.setImage(new Image("Vue/link_back.gif"));
                break;

            case "descendre" :
                super.setImage(new Image("Vue/link_front2.gif"));
                break;

            case "gauche" :
                super.setImage(new Image("Vue/link_left.gif"));
                break;

            case "droite" :
                super.setImage(new Image("Vue/link_right.gif"));
                break;
        }
    }
}
