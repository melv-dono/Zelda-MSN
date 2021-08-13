package Modèle.Arme;

import Modèle.Utils.Coordonnees;
import Modèle.Utils.Parametre;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class BouleDeFeu {
    static int num = 0;
    public String id;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private int dureeDeVie;
    private String direction;

    public BouleDeFeu(double x, double y, String orientation) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.dureeDeVie = 60; // equivaut à 60 action de 17ms
        num++;
        this.id = "b" + num;
        this.direction = orientation;
    }

    public String getDirection() {
        return direction;
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

    /**
     * Renvoit les coorodnnées du perso sur le tableau de donnee.
     * @return Emplacement du personnage
     */
    public Coordonnees getCoor() {
        int x = (int) (xProperty.getValue()/32) ;
        int y = (int) (yProperty.getValue()/32);
        Coordonnees c = new Coordonnees(x,y);
        return c;
    }

    public void setX(double n) {
        if (n<0) {
            this.xProperty.setValue(0);
        }
        else if (n> Parametre.LARGEUR) {
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

    public void droite() {
        setX(this.xProperty.getValue() + 1);
    }

    public void monter() {
        setY(this.yProperty.getValue() - 1);
    }

    public void descendre() {
        setY(this.yProperty.getValue() + 1);
    }


    public void tpsEcoule() {
        this.dureeDeVie--;
    }

    public boolean seDesintegre() {
        return this.getDureeDeVie()<=0;
    }
}
