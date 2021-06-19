package Modèle;

public abstract class ObjetSoin extends ObjetRamassable{
    private int pointRegeneration;
    public ObjetSoin(int pt,double posL,double posH,String nom,String mapAction){
        super(nom,posL,posH,mapAction);
        pointRegeneration=pt;
    }
    public ObjetSoin(String nom,int pt,double posL,double posH ){
        super(nom,posL,posH);
        pointRegeneration=pt;
    }

}
