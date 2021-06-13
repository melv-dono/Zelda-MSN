package Modèle;

public abstract class Ennemi extends Personnage{
    private String mapAction;

    public Ennemi(String n, Environnement e, double pV, double pA, double pDef) {
        super(n,e,pV,pA,pDef);
    }

    public Ennemi(String n, double x, double y,Environnement e, double pV, double pA, double pDef, String m){
        super(n,x,y,e,pV,pA,pDef);
        this.mapAction = m;
    }

    public String getMapAction() {
        return mapAction;
    }

    public void setMapAction(String mapAction) {
        this.mapAction = mapAction;
    }

    public abstract void monter();
    public abstract void descendre();
    public abstract void gauche();
    public abstract void droite();
    public abstract void agir();
}
