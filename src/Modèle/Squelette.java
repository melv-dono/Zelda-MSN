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

    public void attaquer() {
    }
}
