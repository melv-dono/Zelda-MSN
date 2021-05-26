package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Objet {
    private IntegerProperty positionLargeur;
    private IntegerProperty positionHauteur;
    private int pointsRegeneration;

    public Objet(int posLargeur,int posHauteur,int ptRegen){
        positionLargeur=new SimpleIntegerProperty(posLargeur);
        positionHauteur=new SimpleIntegerProperty(posHauteur);
        pointsRegeneration=ptRegen;
    }

}
