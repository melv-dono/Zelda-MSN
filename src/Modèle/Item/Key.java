package Modèle.Item;

import Modèle.Utils.Parametre;

public class Key extends ObjetRamassable {
    public Key( double posLargeur, double posHauteur) throws Exception {
        super("key", posLargeur, posHauteur);
        if(posLargeur<0 || posLargeur> Parametre.LARGEUR || posHauteur>Parametre.HAUTEUR || posHauteur<0) throw new Exception();
    }
}
