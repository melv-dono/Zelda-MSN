package Modèle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;

public class Squelette extends Personnage{
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;

    public Squelette(String n) {
        super(n,900,250);
    }

    public void monter(){
        this.setY(this.getY()-1);
    }

    public void descendre(){
        this.setY(this.getY()+1);
    }
}
