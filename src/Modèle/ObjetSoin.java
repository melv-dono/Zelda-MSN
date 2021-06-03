package Modèle;

public class ObjetSoin extends Objet{
    private int pointRegeneration;
    public ObjetSoin(int pt,int posL,int posH,String nom){
        super(nom,posL,posH);
        pointRegeneration=pt;
    }
}
