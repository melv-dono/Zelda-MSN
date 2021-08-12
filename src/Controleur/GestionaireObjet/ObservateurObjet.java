package Controleur.GestionaireObjet;

import Controleur.GestionairePersonnage.OrientationPnj;
import Modèle.*;
import Modèle.Objet.objExt.Arbre;
import Modèle.Objet.arme.Bouclier;
import Modèle.Objet.objExt.Coffre;
import Modèle.Objet.objExt.Rocher;
import Modèle.Objet.ramassable.Key;
import Modèle.Objet.ramassable.Pioche;
import Modèle.Objet.ramassable.Pomme;
import Modèle.Objet.ramassable.Potion;
import Modèle.perso.PersoNonJouable;
import Vue.ObjetVue;
import Vue.VueCoffre;
import Vue.VuePnj;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

public class ObservateurObjet implements ListChangeListener<ElementMap> {
    @FXML
    private Pane plateau;
    private Environnement environnement;
    private ArrayList<ObjetVue>listeObjetVue;
    public ObservateurObjet(Pane pane, Environnement environnement){
        plateau=pane;
        this.environnement=environnement;
        listeObjetVue=new ArrayList<>();
    }
    @Override
    public void onChanged(Change<? extends ElementMap> change) {
        while(change.next())
            for(ElementMap objAdded:change.getAddedSubList()){
                if(objAdded instanceof Potion){
                    ajoutObjet(objAdded, "Vue/images/objets/inventory_potionblue.gif");
                }else if(objAdded instanceof Rocher) {
                    ajoutObjet(objAdded, "Vue/images/objets/item_stonefence.png");
                }else if(objAdded instanceof Pioche){
                    ajoutObjet(objAdded, "Vue/images/objets/pioche.png");
                }else if(objAdded instanceof Arbre){
                    ajoutObjet(objAdded, "Vue/images/objets/liltree.gif");
                }else if(objAdded instanceof Pomme){
                    ajoutObjet(objAdded, "Vue/images/objets/pomme.gif");
                }else if(objAdded instanceof PersoNonJouable){
                    ajoutObjet(objAdded, "Vue/images/pnj/pnjFace.png");
                }else if(objAdded instanceof Key){
                    ajoutObjet(objAdded, "Vue/images/objets/keyTile.png");
                }else if(objAdded instanceof Coffre){
                    ajoutObjet(objAdded, "Vue/images/objets/coffre.gif");
                }else if(objAdded instanceof Bouclier){
                    ajoutObjet(objAdded, "Vue/images/objets/bouclier.png");
                }
            }
            for(ElementMap objRemoved: change.getRemoved()){
                if(objRemoved instanceof Potion){
                    retirerObjet(objRemoved);
                }else if(objRemoved instanceof Rocher) {
                    retirerObjet(objRemoved);
                }else if(objRemoved instanceof Pioche){
                    retirerObjet(objRemoved);
                }else if(objRemoved instanceof Arbre){
                    retirerObjet(objRemoved);
                }else if(objRemoved instanceof Pomme){
                    retirerObjet(objRemoved);
                }else if(objRemoved instanceof Key){
                    retirerObjet(objRemoved);
                }else if(objRemoved instanceof Bouclier){
                    retirerObjet(objRemoved);
                }
            }
            for(ElementMap objChange: environnement.getObjEnvAct()){ // méthode de changement de l'image du coffre
                if(objChange instanceof Coffre){
                    if(((Coffre) objChange).tiensObjet()==false ){
                        for(ObjetVue objVue:listeObjetVue){
                            if(objChange.getId()==objVue.getId()){
                                objVue.setImg("Vue/images/objets/coffreOpen.gif");
                            }
                        }
                    }
                }
            }
    }

    public void ajoutObjet(ElementMap obj, String url){
        if(obj instanceof Potion) {
            ObjetVue vuePotion=new ObjetVue(url,obj.getId());
            ajoutImgObjVue(vuePotion,obj);
        }else if(obj instanceof Rocher) {
            ObjetVue vueRocher=new ObjetVue(url, obj.getId());
            ajoutImgObjVue(vueRocher,obj);
        }else if(obj instanceof Pioche){
            ObjetVue vuePioche=new ObjetVue(url,obj.getId());
            ajoutImgObjVue(vuePioche,obj);
        }else if(obj instanceof Arbre){
            ObjetVue vueArbre=new ObjetVue(url, obj.getId());
            ajoutImgObjVue(vueArbre,obj);
        }else if(obj instanceof Pomme) {
            ObjetVue vuePomme = new ObjetVue(url, obj.getId());
            ajoutImgObjVue(vuePomme,obj);

        }else if(obj instanceof Bouclier) {
            ObjetVue vueBouclier = new ObjetVue(url, obj.getId());
            ajoutImgObjVue(vueBouclier,obj);

        }else if(obj instanceof PersoNonJouable){
            VuePnj vuePnj=new VuePnj(url,obj.getId());
            vuePnj.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vuePnj.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vuePnj.getImg());
            OrientationPnj orientationPnj=new OrientationPnj(vuePnj);
            ((PersoNonJouable) obj).getOrientation().addListener(orientationPnj);
            listeObjetVue.add(vuePnj);
        }else if(obj instanceof Key){
            ObjetVue vueKey=new ObjetVue(url, obj.getId());
            ajoutImgObjVue(vueKey,obj);
        }else if(obj instanceof Coffre){
            VueCoffre vueCoffre=new VueCoffre(url, obj.getId());
            ajoutImgObjVue(vueCoffre,obj);
        }


    }
    public void retirerObjet(ElementMap obj){
        for(int i=0;i<listeObjetVue.size();i++){
            if(listeObjetVue.get(i).getId()==obj.getId()){
                plateau.getChildren().remove(listeObjetVue.get(i).getImg());
                listeObjetVue.remove(listeObjetVue.get(i));
                environnement.retirerObjEnvAct(obj);
                i--;
            }
        }
    }
    public void deleteAll(){
        for(int i=0;i<listeObjetVue.size();i++){
            plateau.getChildren().remove(listeObjetVue.get(i).getImg());
            listeObjetVue.remove(listeObjetVue.get(i));
            i--;
        }
    }

    private void ajoutImgObjVue(ObjetVue objVue,ElementMap obj){
        objVue.getImg().translateXProperty().bind(obj.getPositionLargeur());
        objVue.getImg().translateYProperty().bind(obj.getPositionHauteur());
        plateau.getChildren().add(objVue.getImg());
        listeObjetVue.add(objVue);
    }
}