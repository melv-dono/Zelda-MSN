package Modèle;

public class Pomme extends Objet{
    public Pomme(int posL,int posH,Environnement environnement){
        super("pomme",posL,posH,5,environnement);
    }
    public Pomme(Environnement environnement){
        super("potion",environnement);
    }
}
