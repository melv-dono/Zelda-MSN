package Modèle;

public class Coquillage extends ObjetRamassable{
    private boolean hold;
    /**
     * CONSTRUCTEUR
     *
     * @param nom
     * @param posLargeur
     * @param posHauteur
     */
    public Coquillage(String nom, double posLargeur, double posHauteur) {
        super(nom, posLargeur, posHauteur);
        hold=false;
    }
    public void coquillageObtenu(){
        hold=true;
    }
}
