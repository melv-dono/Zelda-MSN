package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Personnage {
    private String nom;
    private int id;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private Environnement env;
    private static int numId=0;

    public Personnage(String n) {
        this.nom =n;
        this.id = numId++;
        this.xProperty = new SimpleDoubleProperty(620);
        this.yProperty = new SimpleDoubleProperty(250);
        //this.env = new Environnement();
    }

    public final void setX(double n) {
        this.xProperty.setValue(n);
    }

    public final double getX() {
        return this.xProperty.getValue();
    }

    public final DoubleProperty getXProperty() {
        return this.xProperty;
    }

    public final void setY(double n) {
        this.yProperty.setValue(n);
    }

    public final double getY() {
        return this.yProperty.getValue();
    }

    public final DoubleProperty getYProperty() {
        return this.yProperty;
    }

    public int getId(){return id; }
}
