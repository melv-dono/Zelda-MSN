package Modèle;

public class Arbre extends Objet{
    private int nbPomme;
    public Arbre(double posL,double posH,int nbPomme){
        super("arbre",posL,posH);
        this.nbPomme=nbPomme;
    }
    public void retirerPomme(){
        nbPomme--;
    }
    public int getNbPomme(){
        return nbPomme;
    }

}
