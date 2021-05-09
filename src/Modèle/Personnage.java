package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
    private String nom;
    private int id;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Environnement env;
    private static int numId=0;

    public Personnage(String n) {
        this.nom =n;
        this.id = numId++;
        this.xProperty = new SimpleIntegerProperty(0);
        this.yProperty = new SimpleIntegerProperty(0);
        //this.env = new Environnement();
    }

    public final void setX(int n) {
        this.xProperty.setValue(n);
    }

    public final int getX() {
        return this.xProperty.getValue();
    }

    public final IntegerProperty getXProperty() {
        return this.xProperty;
    }

    public final void setY(int n) {
        this.yProperty.setValue(n);
    }

    public final int getY() {
        return this.yProperty.getValue();
    }

    public final IntegerProperty getYProperty() {
        return this.yProperty;
    }


}
