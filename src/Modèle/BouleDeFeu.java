package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;

import java.sql.Time;

public class BouleDeFeu {
    static int num = 0;
    public String id;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private int dureeDeVie;

    public BouleDeFeu(double x, double y) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.dureeDeVie = 60; // equivaut à 60 action de 17ms
        num++;
        this.id = "b" + num;
    }

    public String getId() {
        return id;
    }

    public int getDureeDeVie() {
        return dureeDeVie;
    }

    public double getxProperty() {
        return xProperty.getValue();
    }

    public DoubleProperty xPropertyProperty() {
        return xProperty;
    }

    public double getyProperty() {
        return yProperty.getValue();
    }

    public DoubleProperty yPropertyProperty() {
        return yProperty;
    }

    public void setX(double n) {
        if (n<0) {
            this.xProperty.setValue(0);
        }
        else if (n>Parametre.LARGEUR) {
            this.xProperty.setValue(Parametre.LARGEUR);
        }
        else {
            this.xProperty.setValue(n);
        }
    }

    public void setY(double n) {
        if (n<0) {
            this.yProperty.setValue(0);
        }
        else if (n>Parametre.HAUTEUR) {
            this.yProperty.setValue(Parametre.HAUTEUR);
        }
        else {
            this.yProperty.setValue(n);
        }
    }

    public void gauche() {
        setX(this.xProperty.getValue() - 1);
    }

    public void tpsEcoule() {
        this.dureeDeVie--;
    }

    public boolean seDesintegre() {
        return this.getDureeDeVie()<=0;
    }
}
