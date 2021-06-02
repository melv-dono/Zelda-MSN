package Modèle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;
import Modèle.Environnement;

public class Squelette extends Personnage{
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private int cpt;

    public Squelette(String n,Environnement env) {
        super(n,423,600, env, 100, 5, 5);
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

    @Override
    public void gauche() {
        this.setDeplacementLargeur(this.getDeplacementLargeur()-1);
    }

    @Override
    public void droite() {
        this.setDeplacementLargeur(this.getDeplacementLargeur()+1);
    }

    /**
     * première animation du premier squelette dans la première map qui servira dans la gameloop
     */
    public void animation1(){
        if(cpt < 150) {
            this.monter();
        }
        else if(cpt >=150){
            this.descendre();
        }
        if(cpt==298){
            cpt=0;
        }
        cpt++;
    }

    public void attaquer() {

    }
}
