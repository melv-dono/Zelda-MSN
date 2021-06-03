package Modèle;

public abstract class ObjetSoin extends ObjetRamassable{
    private int pointRegeneration;
    public ObjetSoin(int pt,double posL,double posH,String nom){
        super(nom,posL,posH);
        pointRegeneration=pt;
    }
}
