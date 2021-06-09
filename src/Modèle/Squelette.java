package Modèle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;
import Modèle.Environnement;

public class Squelette extends Personnage{
    private int orientation;
    private int cpt;

    public Squelette(String n,Environnement env) {
        super(n,423,600, env, 100, 5, 5);
        orientation=1;
    }

    /**
     * Déplace le squelette vers le haut
     */
    public void monter(){
        this.setDeplacementHauteur(this.getDeplacementHauteur()-1);
    }

    /**
     * Déplace le squelette vers le bas
     */
    public void descendre(){
        this.setDeplacementHauteur(this.getDeplacementHauteur()+1);
    }

    /**
     * Déplace le squelette vers la gauche
     */
    @Override
    public void gauche() {
        this.setDeplacementLargeur(this.getDeplacementLargeur()-1);
    }

    /**
     * Déplace le squelette vers la droite
     */
    @Override
    public void droite() {
        this.setDeplacementLargeur(this.getDeplacementLargeur()+1);
    }

    /**
     * première animation du premier squelette dans la première map qui servira dans la gameloop
     */
    public void animationSquelette1(Environnement environnement){
            if(deplacementPossible(getDeplacementLargeur(),getDeplacementHauteur(),environnement,orientation)==1){
                orientation=1;
                this.monter();
                this.gauche();
            }else if(deplacementPossible(getDeplacementLargeur(),getDeplacementHauteur(),environnement,orientation)==2){
                orientation=2;
                this.monter();
                this.droite();
            }else if(deplacementPossible(getDeplacementLargeur(),getDeplacementHauteur(),environnement,orientation)==3){
                orientation=3;
                this.descendre();
                this.droite();
            }else{
                orientation=4;
                this.gauche();
                this.descendre();
            }
    }
    public int deplacementPossible(double coordLarge,double coordHaut,Environnement environnement,int orientationActuelle){
        if(orientationActuelle==1){
            if(prochainDepPossible((int)(coordLarge-32)/32,(int)(coordHaut-32)/32,environnement)==true){
                return 1;
            }else if(prochainDepPossible((int)(coordLarge+32)/32,(int)(coordHaut-32)/32,environnement)==true){
                return 2;
            }else{
                return 4;
            }
        }
        if(orientationActuelle==2){
            if(prochainDepPossible((int)(coordLarge+32)/32,(int)(coordHaut-32)/32,environnement)==true){
                return 2;
            }else if(prochainDepPossible((int)(coordLarge-32)/32,(int)(coordHaut-32)/32,environnement)==true){
                return 1;
            }else{
                return 3;
            }
        }
        if(orientationActuelle==3){
            if(prochainDepPossible((int)(coordLarge+32)/32,(int)(coordHaut+32)/32,environnement)==true){
                return 3;
            }else if(prochainDepPossible((int)(coordLarge+32)/32,(int)(coordHaut-32)/32,environnement)==true){
                return 2;
            }else{
                return 4;
            }
        }
        if(orientationActuelle==4){
            if(prochainDepPossible((int)(coordLarge-32)/32,(int)(coordHaut+32)/32,environnement)==true){
                return 4;
            }else if(prochainDepPossible((int)(coordLarge-32)/32,(int)(coordHaut-32)/32,environnement)==true) {
                return 1;
            }else{
                return 3;
            }
        }
        return 0;
    }
    public boolean prochainDepPossible(int depLarge,int depHaut,Environnement environnement){
        if(environnement.getMapActuelle().getTableau()[depLarge][depHaut]==2){
            return false;
        }
        return true;
    }

    public void attaquer() {

    }
}
