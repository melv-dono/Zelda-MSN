package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Bouclier extends ElementMap{
    private double pointDef;
    private DoubleProperty pointDefProperty;
    private boolean hold;

    public Bouclier(double posLarge,double posHaut)throws Exception{
        super("bouclier",posLarge,posHaut);
        pointDef=100;
        hold=false;
        this.pointDefProperty = new SimpleDoubleProperty(pointDef);
    }
    public void setHold(){
        if(hold==false){
            hold=true;
        }else{
            hold=false;
        }
    }
}
