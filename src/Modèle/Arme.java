package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Arme {
    private String nom;
    private double pointAttaque;
    private IntegerProperty id;
    static int num= 1;

    public Arme(String nom, double pointAttaque) {
        this.nom = nom;
        this.pointAttaque = pointAttaque;
        this.id = new SimpleIntegerProperty(num);
        num++;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom;
    }

    public double getPointAttaque() {
        return pointAttaque;
    }
}
