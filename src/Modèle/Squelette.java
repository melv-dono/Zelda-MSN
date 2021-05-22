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
        super(n,0,600, env);
    }

    public void monter(){
        this.setDeplacementHauteur(this.getDeplacementHauteur()-1);
    }

    public void descendre(){
        this.setDeplacementHauteur(this.getDeplacementHauteur()+1);
    }

    @Override
    public void gauche() {
    }

    @Override
    public void droite() {

    }
}
