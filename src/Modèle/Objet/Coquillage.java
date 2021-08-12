package Modèle.Objet;

import Modèle.Objet.ramassable.ObjetRamassable;

public class Coquillage extends ObjetRamassable {
    /**
     * CONSTRUCTEUR
     *
     * @param nom
     * @param posLargeur
     * @param posHauteur
     */
    public Coquillage(String nom, double posLargeur, double posHauteur) {
        super(nom, posLargeur, posHauteur);
    }
}
