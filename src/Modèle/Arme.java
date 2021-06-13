package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Arme {
    private String nom;
    private DoubleProperty pointAttaqueProperty;

    public Arme(String nom, double pointAttaque) {
        this.nom = nom;
        this.pointAttaque = pointAttaque;
    }

    public String getNom() {
        return nom;
    }

    public double getPointAttaque(){
        return this.pointAttaqueProperty.get();
    }

    public DoubleProperty getPointAttaqueProperty() {
        return getPointAttaqueProperty();
    }


}
