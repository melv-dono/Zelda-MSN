package Vue;

import Modèle.Link;
import Modèle.Personnage;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueLink extends ImageView{

    @FXML
    private Pane plateau;

    private Link p;

    private ImageView image;

    public VueLink(Link perso, ImageView i){
        this.p=perso;
        i=creeSprite();
        this.image=i;
    }

    /**
     * Crée l'image de Link et l'associe au modèle.
     * @return
     */
    public ImageView creeSprite() {
        //ImageView link = new ImageView("Vue/link_front2.gif");
        super.setImage(new Image("Vue/link_front2.gif"));
        super.translateXProperty().bind(p.getDeplacementLargeurProperty());
        super.translateYProperty().bind(p.getDeplacementHauteurProperty());
        return this;
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
