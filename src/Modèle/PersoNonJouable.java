package Modèle;

public class PersoNonJouable extends ElementMap{
    private ElementMap objet;
    private boolean canGiveObject;
    /**
     * CONSTRUCTEUR
     *
     * @param posLargeur
     * @param posHauteur
     */
    public PersoNonJouable(double posLargeur, double posHauteur,ElementMap objet) {
        super("pnj", posLargeur, posHauteur);
        this.objet=objet;
        canGiveObject=true;
    }
    public ElementMap donObjet(){
        canGiveObject=false;
        return objet;
    }
    public boolean persoTiensObjet(){
        return canGiveObject;
    }
}
