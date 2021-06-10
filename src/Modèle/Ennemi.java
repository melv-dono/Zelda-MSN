package Modèle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Ennemi extends Personnage{


    public Ennemi(String n, Environnement e, double pV, double pA, double pDef) {
        super(n,e,pV,pA,pDef);
    }

    public Ennemi(String n, int x, int y,Environnement e, double pV, double pA, double pDef){
        super(n,x,y,e,pV,pA,pDef);
    }

    public abstract void monter();
    public abstract void descendre();
    public abstract void gauche();
    public abstract void droite();
    public abstract void agir();
}
