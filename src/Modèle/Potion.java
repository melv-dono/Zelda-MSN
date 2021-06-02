package Modèle;

public class Potion extends Objet{
    public Potion(int posL,int posH,Environnement environnement){
        super("potion",posL,posH,10,environnement);
    }
    public Potion(Environnement environnement){
        super("potion",environnement);
    }
}
