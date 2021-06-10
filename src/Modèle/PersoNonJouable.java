package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PersoNonJouable extends ObjetContenantObjet{
    private IntegerProperty orientation;
    /**
     * CONSTRUCTEUR
     *
     * @param posLargeur
     * @param posHauteur
     */
    public PersoNonJouable(double posLargeur, double posHauteur,ElementMap objet) {
        super(objet, posLargeur, posHauteur,"pnj");
        orientation=new SimpleIntegerProperty(1);
    }

    public void setOrientation(int newOrientation){
        orientation.setValue(newOrientation);
    }
    public IntegerProperty getOrientation(){
        return orientation;
    }
}
