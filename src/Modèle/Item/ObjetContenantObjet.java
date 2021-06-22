package Modèle.Item;

import Modèle.Env.ElementMap;

public class ObjetContenantObjet extends ElementMap {
    private ElementMap objet;
    private boolean canGiveObject;
    public ObjetContenantObjet(ElementMap obj,double posL,double posH,String nom,String mapAction){
        super(nom,posL,posH,mapAction);
        objet=obj;
        canGiveObject=true;
    }
    public boolean tiensObjet(){
        return canGiveObject;
    }
    public ElementMap donObjet(){
        canGiveObject=false;
        return objet;
    }

}
