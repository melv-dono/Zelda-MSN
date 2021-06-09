package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PersoNonJouable extends ElementMap{
    private ElementMap objet;
    private boolean canGiveObject;
    private IntegerProperty orientation;
    /**
     * CONSTRUCTEUR
     *
     * @param posLargeur
     * @param posHauteur
     */
    public PersoNonJouable(double posLargeur, double posHauteur,ElementMap objet) {
        super("pnj", posLargeur, posHauteur);
        this.objet=objet;
        canGiveObject=true;
        orientation=new SimpleIntegerProperty(1);
    }
    public ElementMap donObjet(){
        canGiveObject=false;
        return objet;
    }
    public boolean persoTiensObjet(){
        return canGiveObject;
    }
    public void setOrientation(int newOrientation){
        orientation.setValue(newOrientation);
    }
    public IntegerProperty getOrientation(){
        return orientation;
    }
}
