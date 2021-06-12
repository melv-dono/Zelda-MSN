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
    private int mapAction;

    public Squelette(String n,Environnement env) {
        super(n,423,600, env, 100, 5, 5);
        orientation=1;
        cpt=0;
        this.mapAction=2;
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
            if(deplacementPossible(getDeplacementHauteur(),getDeplacementLargeur(),environnement,orientation)==1){ // 1=NO
                orientation=1;
                this.monter();
                this.gauche();
            }else if(deplacementPossible(getDeplacementHauteur(),getDeplacementLargeur(),environnement,orientation)==2){ // 2=NE
                orientation=2;
                this.monter();
                this.droite();
            }else if(deplacementPossible(getDeplacementHauteur(),getDeplacementLargeur(),environnement,orientation)==3){ // 3=SE
                orientation=3;
                this.descendre();
                this.droite();
            }else{ // 4=SO
                orientation=4;
                this.gauche();
                this.descendre();
            }
    }
    public int deplacementPossible(double coordHaut,double coordLarge,Environnement environnement,int orientationActuelle){
        if(orientationActuelle==1){
            if(prochainDepPossible((int)(coordHaut-32)/32,(int)(coordLarge-32)/32,environnement)==true){
                return 1;
            }else if(prochainDepPossible((int)(coordHaut-32)/32,(int)(coordLarge+32)/32,environnement)==true){
                return 2;
            }else{
                return 4;
            }
        }
        if(orientationActuelle==2){
            if(prochainDepPossible((int)(coordHaut-32)/32,(int)(coordLarge+32)/32,environnement)==true){
                return 2;
            }else if(prochainDepPossible((int)(coordHaut-32)/32,(int)(coordLarge-32)/32,environnement)==true){
                return 1;
            }else{
                return 3;
            }
        }
        if(orientationActuelle==3){
            if(prochainDepPossible((int)(coordHaut+32)/32,(int)(coordLarge+32)/32,environnement)==true){
                return 3;
            }else if(prochainDepPossible((int)(coordHaut-32)/32,(int)(coordLarge+32)/32,environnement)==true){
                return 2;
            }else{
                return 4;
            }
        }
        if(orientationActuelle==4){
            if(prochainDepPossible((int)(coordHaut+32)/32,(int)(coordLarge-32)/32,environnement)==true){
                return 4;
            }else if(prochainDepPossible((int)(coordHaut-32)/32,(int)(coordLarge-32)/32,environnement)==true) {
                return 1;
            }else{
                return 3;
            }
        }
        return 0;
    }
    public boolean prochainDepPossible(int depHaut,int depLarge,Environnement environnement){
        if(!(environnement.getMapActuelle().getTableau()[depHaut][depLarge]<10)){
            return false;
        }
        return true;
    }

    public void attaquer(Environnement e) {
        if(e.getLink().getDeplacementLargeur()-this.getDeplacementLargeur()>-32 && e.getLink().getDeplacementLargeur()-this.getDeplacementLargeur()<32&& e.getLink().getDeplacementHauteur()-this.getDeplacementHauteur()>-32 && e.getLink().getDeplacementHauteur()-this.getDeplacementHauteur()<32 ){
            if(cpt!=60){
                cpt++;
            }else{
                cpt=0;
                e.getLink().decrementerPv(5);
            }
        }

    }
    public int getMapAction(){
        return mapAction;
    }
}
