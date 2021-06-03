package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Arbre extends Objet{
    private ArrayList<Pomme>appleInTree;
    public Arbre(double posL,double posH){
        super("arbre",posL,posH);
        appleInTree=new ArrayList<>();
    }

}
