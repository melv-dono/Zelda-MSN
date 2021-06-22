package Modèle.Item;

import Modèle.Item.ObjetRamassable;

public class Coquillage extends ObjetRamassable {
    /**
     * CONSTRUCTEUR
     *
     * @param nom
     * @param posLargeur
     * @param posHauteur
     */
    public Coquillage(String nom, double posLargeur, double posHauteur)throws Exception {
        super(nom, posLargeur, posHauteur);
    }
}
