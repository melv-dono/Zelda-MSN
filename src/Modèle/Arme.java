package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Arme {
    private String nom;
    private double pointAttaque;
    private DoubleProperty pointAttaqueProperty;

    public Arme(String nom, double pointAttaque) {
        this.nom = nom;
        this.pointAttaque = pointAttaque;
        pointAttaqueProperty = new SimpleDoubleProperty(pointAttaque);
    }

    public String getNom() {
        return nom;
    }

    public double getPointAttaque(){
        return this.pointAttaque;
    }

    public DoubleProperty getPointAttaqueProperty() {
        return getPointAttaqueProperty();
    }


}
