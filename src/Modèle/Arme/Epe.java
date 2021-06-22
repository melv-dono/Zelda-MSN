package Modèle.Arme;

import Modèle.Arme.Arme;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Epe extends Arme {
    private String nom;
    private DoubleProperty pointAttaqueProperty = new SimpleDoubleProperty(10);

    public Epe(String nom, double degat) {
        super(nom, degat);
    }

    @Override
    public double getPointAttaque() {
        return pointAttaqueProperty.getValue();
    }

    public DoubleProperty getPointAttaqueProperty(){
        return this.pointAttaqueProperty;
    }
}
