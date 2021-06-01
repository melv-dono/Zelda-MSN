package Vue;

import Modèle.Objet;
import javafx.scene.image.ImageView;

public class ObjetVue {
    private Objet objet;
    public ObjetVue(Objet obj){
        objet=obj;
    }
    public ImageView CreerSpriteObjet(){
        ImageView spriteObj=new ImageView("Vue/inventory_potionblue.gif");
        spriteObj.translateXProperty().bind(objet.getPositionLargeur());
        spriteObj.translateYProperty().bind(objet.getPositionHauteur());
        return spriteObj;
    }
    public Objet contenuImage(){
        return objet;
    }
}
