package Modèle.Objet.ramassable;

import Modèle.Objet.ramassable.ObjetRamassable;

public class Pioche extends ObjetRamassable {
    /**
     * CONSTRUCTEUR
     *
     * @param posLargeur
     * @param posHauteur
     */
    public Pioche(double posLargeur, double posHauteur,String mapAction) {
        super("pioche", posLargeur, posHauteur,mapAction);
    }
}
