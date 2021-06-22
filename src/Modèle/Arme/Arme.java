package Modèle.Arme;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Arme {
    private String nom;
    private DoubleProperty pointAttaque;

    public Arme(String nom, double pointAttaque) {
        this.nom = nom;
        this.pointAttaque = new SimpleDoubleProperty(pointAttaque);
    }

    public String getNom() {
        return nom;
    }

    public double getPointAttaque(){
        return this.pointAttaque.get();
    }

    public DoubleProperty getPointAttaqueProperty() {
        return getPointAttaqueProperty();
    }


}
