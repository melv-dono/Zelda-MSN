package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

// Attention il faut mettre un invariant pour que les pv ne puissent dépasser les pv max
public abstract class Personnage {
    private String nom;
    private int id; // numéro unique pour chaque personnage
    private DoubleProperty deplacementLargeur; // on doit avoir : X >= 0 ET X <= largeur de l'environnement
    private DoubleProperty deplacementHauteur; // on doit avoir : Y >= 0 ET Y <= hauteur de l'environnement
    private Environnement env; // permet de délimiter le personnage dans sur la map et aussi l'interaction avec les autres perso
    private IntegerProperty pv;
    private IntegerProperty niveau;
    private DoubleProperty exp; // Compteur allant de 0 à 100
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

    public Personnage(String n, int x, int y,Environnement e){
        this.nom =n;
        this.id = numId++;
        this.deplacementLargeur = new SimpleDoubleProperty(x);
        this.deplacementHauteur = new SimpleDoubleProperty(y);
        env=e;
        //this.env = new Environnement();
    }

    public abstract void monter();
    public abstract void descendre();
    public abstract void gauche();
    public abstract void droite();

    /**
     * Envoie la colonne sur laquelle se trouve le personnage.
     * @return l'abscisse du personnage
     */
    public final double getDeplacementLargeur() {
        return this.deplacementLargeur.getValue();
    }

    /**
     * Enovie la property du deplacementLargeur
     * @return une property
     */
    public final DoubleProperty getDeplacementLargeurProperty() {
        return this.deplacementLargeur;
    }

    /**
     * Envoie la ligne sur laquelle se trouve le personnage.
     * @return l'ordonnée du personnage
     */
    public final double getDeplacementHauteur() {
        return this.deplacementHauteur.getValue();
    }

    /**
     * Enovie la property du deplacementHauteur
     * @return une property
     */
    public final DoubleProperty getDeplacementHauteurProperty() {
        return this.deplacementHauteur;
    }

    /**
     * Envoie le numéro du personnage.
     * @return un identifiant
     */
    public int getId(){return id; }

    /**
     * Envoie les points d'expérience d'un personnage.
     * @return XP
     */
    public final double getExp(){return exp.getValue();}

    /**
     * Envoie la property de exp
     * @return une property
     */
    public final DoubleProperty exp(){return exp;}

    /**
     * Envoie l'environnement dans lequel se trouve le personnage.
     * @return Env
     */
    public Environnement getEnv() {
        return env;
    }

    /**
     * Envoie la property de pv
     * @return une property
     */
    public final IntegerProperty pv(){return pv;}

    /**
     * Envoie la property de niveau
     * @return une property
     */
    public final IntegerProperty niveau(){return niveau;}

    /**
     * Modifie la colonne sur laquelle se trouve le personnage.
     * La postion du personnage varie entre 0 et la largeur de l'environnement.
     * @param n
     */
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

    /**
     * MOdifie la ligne sur laquelle se trouve le personnage.
     * La postion du personnage varie entre 0 et la hauteur de l'environnement.
     * @param n
     */
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

    /**
     * Modifie les pv du personnage.
     * Les pv ne peuvent être supérieur au pv max.
     * @param pv
     */
    public final void setPv(int pv){ this.pv.setValue(pv);}

    /**
     * Modifie les points d'expérience du personnage.
     * Réinitialisation une fois 100 atteint.
     * Augmentation d'un niveau une fois 100 atteint.
     * @param xp
     */
    public final void setExp(double xp){
        if (xp + getExp() <0) {
            this.exp.setValue(0);

        }
        else if (xp + getExp() > 100) {
            this.niveau.setValue(niveau.getValue() + 1);
            this.exp.setValue(0);
        }
        else {
            this.exp.setValue(exp.getValue()+xp);
        }
    }

}
