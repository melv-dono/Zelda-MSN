package Modèle.Objet.ramassable;

import Modèle.ElementMap;

public class ObjetRamassable extends ElementMap {


    /**
     * CONSTRUCTEUR
     *
     * @param nom
     * @param posLargeur
     * @param posHauteur
     */
    public ObjetRamassable(String nom, double posLargeur, double posHauteur,String mapAction) {
        super(nom, posLargeur, posHauteur,mapAction);
    }
    public ObjetRamassable(String nom, double posLargeur, double posHauteur) {
        super(nom, posLargeur, posHauteur);
    }
}
