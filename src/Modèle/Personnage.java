package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
    private String nom;
    private int id;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Environnement env;
    private static int numId=0;

    public Personnage(String n) {
        this.nom =n;
        this.id = numId++;
        this.xProperty = new SimpleIntegerProperty(0);
        this.yProperty = new SimpleIntegerProperty(0);
        //this.env = new Environnement();
    }

    public void setxProperty(int n) {
        this.xProperty.setValue(n);
    }

    public int getX() {
        return this.xProperty.getValue();
    }



    public void setY(int n) {
        this.yProperty.setValue(n);
    }




}
