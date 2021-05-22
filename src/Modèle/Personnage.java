package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

// Les coordonnés X et Y ont une valeur max pour ne pas sortir du cadre.
public abstract class Personnage {
    private String nom;
    private int id;
    private DoubleProperty deplacementLargeur; // on doit avoir : X >= 0 ET X <= largeur de l'environnement
    private DoubleProperty deplacementHauteur; // on doit avoir : Y >= 0 ET Y <= hauteur de l'environnement
    private Environnement env; // permet de délimiter le personnage dans sur la map et aussi l'interaction avec les autres perso
    private static int numId=0; // permet d'auto incrémenter l'attribut id pour chaque personnage que l'on crée

    public Personnage(String n, Environnement e) {
        this.nom =n;
        this.id = numId++;
        this.deplacementLargeur = new SimpleDoubleProperty(520); // 544
        this.deplacementHauteur = new SimpleDoubleProperty();
        this.env = e;
    }

    public final void setDeplacementLargeur(double n) {
        if (n<0) {
            this.deplacementLargeur.setValue(0);
        }
        else if (n>env.getWidth()) {
            this.deplacementLargeur.setValue(this.env.getWidth());
        }
        else {
            this.deplacementLargeur.setValue(n);
        }
    }

    public final double getDeplacementLargeur() {
        return this.deplacementLargeur.getValue();
    }

    public final DoubleProperty getDeplacementLargeurProperty() {
        return this.deplacementLargeur;
    }

    public final void setDeplacementHauteur(double n) {
        if (n<0) {
            this.deplacementHauteur.setValue(0);
        }
        else if (n>env.getHeight()) {
            this.deplacementHauteur.setValue(this.env.getHeight());
        }
        else {
            this.deplacementHauteur.setValue(n);
        }
    }

    public final double getDeplacementHauteur() {
        return this.deplacementHauteur.getValue();
    }

    public final DoubleProperty getDeplacementHauteurProperty() {
        return this.deplacementHauteur;
    }

    public int getId(){return id; }

    public Environnement getEnv() {
        return env;
    }

    public abstract void monter();
    public abstract void descendre();
    public abstract void gauche();
    public abstract void droite();

}
