package Modèle;

public class Rocher extends ElementMap {


    /**
     * CONSTRUCTEUR
     *
     * @param posLargeur
     * @param posHauteur
     */
    public Rocher( double posLargeur, double posHauteur,String mapAction) throws Exception{
        super("rocher", posLargeur, posHauteur,mapAction);
    }
}
