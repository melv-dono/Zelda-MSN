package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

// Les coordonnés X et Y ont une valeur max pour ne pas sortir du cadre.
public abstract class Personnage {
    private String nom;
    private int id;
    private DoubleProperty deplacementLargeur; // on doit avoir : X >= 0 ET X <= largeur de l'environnement
    private DoubleProperty deplacementHauteur; // on doit avoir : Y >= 0 ET Y <= hauteur de l'environnement
    private Environnement env; // permet de délimiter le personnage dans sur la map et aussi l'interaction avec les autres perso
    private IntegerProperty pv;
    private IntegerProperty niveau;
    private DoubleProperty exp;
    private static int numId=0; // permet d'auto incrémenter l'attribut id pour chaque personnage que l'on crée

    public Personnage(String n, Environnement e) {
        this.nom =n;
        this.id = numId++;
        this.deplacementLargeur = new SimpleDoubleProperty(520); // 544
        this.deplacementHauteur = new SimpleDoubleProperty();
        this.env = e;
        pv=new SimpleIntegerProperty(100);
        niveau=new SimpleIntegerProperty(1);
        exp=new SimpleDoubleProperty(0);
    }

    public Personnage(String n, int x, int y){
        this.nom =n;
        this.id = numId++;
        this.deplacementLargeur = new SimpleDoubleProperty(x);
        this.deplacementHauteur = new SimpleDoubleProperty(y);
        //this.env = new Environnement();
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

    public final void setPv(int pv){ this.pv.setValue(pv);}

    public final IntegerProperty pv(){return pv;}

    public final IntegerProperty niveau(){return niveau;}
    public final DoubleProperty exp(){return exp;}
    public final void setExp(double xp){exp.setValue(exp.getValue()+xp);}
    public final double getExp(){return exp.getValue();}
    public int getId(){return id; }

    public Environnement getEnv() {
        return env;
    }

    public abstract void monter();
    public abstract void descendre();
    public abstract void gauche();
    public abstract void droite();

}
