package Modèle;

public class Bouclier extends ElementMap{
    private double pointDef;
    private boolean hold;

    public Bouclier(double posLarge,double posHaut){
        super("bouclier",posLarge,posHaut);
        pointDef=100;
        hold=false;
    }
    public void setHold(){
        if(hold==false){
            hold=true;
        }else{
            hold=false;
        }
    }
}
