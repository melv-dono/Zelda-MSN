package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Epe extends Arme{
    private String nom;
    private DoubleProperty pointAttaqueProperty = new SimpleDoubleProperty(10);
    private Link perso;

    public Epe(String nom, double degat, Link perso) {
        super(nom, degat);
        this.perso = perso;
        //pointAttaqueProperty = new SimpleDoubleProperty(degat);
    }

    @Override
    public double getPointAttaque() {
        return getPointAttaque();
    }

    public DoubleProperty getPointAttaqueProperty(){
        return this.pointAttaqueProperty;
    }
}
