package Modèle;

public class ObjetContenantObjet extends ElementMap{
    private ElementMap objet;
    private boolean canGiveObject;
    public ObjetContenantObjet(ElementMap obj,double posL,double posH,String nom){
        super(nom,posL,posH);
        objet=obj;
        canGiveObject=true;
    }
    public boolean persoTiensObjet(){
        return canGiveObject;
    }
    public ElementMap donObjet(){
        canGiveObject=false;
        return objet;
    }

}
