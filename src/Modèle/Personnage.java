package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

// Les coordonnés X et Y ont une valeur max pour ne pas sortir du cadre.
public abstract class Personnage {
    private String nom;
    private int id;
    private DoubleProperty xProperty; // on doit avoir : X >= 0 ET X <= largeur de l'environnement
    private DoubleProperty yProperty; // on doit avoir : Y >= 0 ET Y <= hauteur de l'environnement
    private Environnement env; // permet de délimiter le personnage dans sur la map et aussi l'interaction avec les autres perso
    private static int numId=0; // permet d'auto incrémenter l'attribut id pour chaque personnage que l'on crée

    public Personnage(String n, Environnement e) {
        this.nom =n;
        this.id = numId++;
        this.xProperty = new SimpleDoubleProperty(620);
        this.yProperty = new SimpleDoubleProperty(250);
        this.env = e;
    }

    public final void setX(double n) {
        if (n<0) {
            this.xProperty.setValue(0);
        }
        else if (n>env.getWidth()) {
            this.xProperty.setValue(this.env.getWidth());
        }
        else {
            this.xProperty.setValue(n);
        }
    }

    public final double getX() {
        return this.xProperty.getValue();
    }

    public final DoubleProperty getXProperty() {
        return this.xProperty;
    }

    public final void setY(double n) {
        if (n<0) {
            this.yProperty.setValue(0);
        }
        else if (n>env.getHeight()) {
            this.yProperty.setValue(this.env.getHeight());
        }
        else {
            this.yProperty.setValue(n);
        }
    }

    public final double getY() {
        return this.yProperty.getValue();
    }

    public final DoubleProperty getYProperty() {
        return this.yProperty;
    }

    public int getId(){return id; }
}
