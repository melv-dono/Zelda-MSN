package Modèle.Objet.objExt;

import Modèle.ElementMap;
import Modèle.Objet.ObjetContenantObjet;

public class Coffre extends ObjetContenantObjet {
    public Coffre(ElementMap obj, double posL, double posH, String mapAction){
        super(obj,posL,posH,"coffre",mapAction);
    }

}
