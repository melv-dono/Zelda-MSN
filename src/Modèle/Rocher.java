package Modèle;

public class Rocher extends ElementMap {


    /**
     * CONSTRUCTEUR
     *
     * @param posLargeur
     * @param posHauteur
     */
    public Rocher( double posLargeur, double posHauteur,String mapAction) {
        super("rocher", posLargeur, posHauteur,mapAction);
    }
}
