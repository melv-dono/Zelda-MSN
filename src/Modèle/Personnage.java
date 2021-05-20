package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
    private String nom;
    private int id;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private Environnement env;
    private IntegerProperty pv;
    private IntegerProperty niveau;
    private DoubleProperty exp; // intervalle [0;1]avec 2 chiffres après virgules
    private static int numId=0;

    public Personnage(String n) {
        this.nom =n;
        this.id = numId++;
        this.xProperty = new SimpleDoubleProperty(620);
        this.yProperty = new SimpleDoubleProperty(250);
        pv=new SimpleIntegerProperty(100);
        niveau=new SimpleIntegerProperty(1);
        exp=new SimpleDoubleProperty(0);
    }

    public final void setX(double n) {
        this.xProperty.setValue(n);
    }

    public final double getX() {
        return this.xProperty.getValue();
    }

    public final DoubleProperty getXProperty() {
        return this.xProperty;
    }

    public final void setY(double n) {
        this.yProperty.setValue(n);
    }

    public final double getY() {
        return this.yProperty.getValue();
    }

    public final DoubleProperty getYProperty() {
        return this.yProperty;
    }

    public final void setPv(int pv){ this.pv.setValue(pv);}

    public final IntegerProperty pv(){return pv;}

    public final IntegerProperty niveau(){return niveau;}
    public final DoubleProperty exp(){return exp;}
    public final void setExp(double xp){exp.setValue(exp.getValue()+xp);}
    public final double getExp(){return exp.getValue();}
    public int getId(){return id; }

    public void decrementerPv(int pointEnMoins){
        if(pv.getValue()<=pointEnMoins){
            pv.setValue(0);
        }else {
            pv.setValue(pv.getValue()-pointEnMoins);
        }
    }
}
