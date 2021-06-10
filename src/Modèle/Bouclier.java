package Modèle;

public class Bouclier extends ElementMap{
    private double pointDef;
    private boolean hold;

    public Bouclier(double posLarge,double posHaut,double ptDef){
        super("bouclier",posLarge,posHaut);
        pointDef=ptDef;
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
