package Modèle.Env;

import Modèle.Env.ElementMap;

public class Arbre extends ElementMap {
    private int nbPomme;
    public Arbre(double posL,double posH,int nbPomme,String mapAction) throws Exception{
        super("arbre",posL,posH,mapAction);
        this.nbPomme=nbPomme;
    }
    public void retirerPomme(){
        nbPomme--;
    }
    public int getNbPomme(){
        return nbPomme;
    }

}
