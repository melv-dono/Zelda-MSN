package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Arbre extends Objet{
    private Pomme appleInTree;
    public Arbre(double posL,double posH,Pomme pomme){
        super("arbre",posL,posH);
        appleInTree=pomme;
    }
}
