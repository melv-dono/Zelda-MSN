package Controleur.GestionaireObjet;

import Controleur.GestionairePersonnage.OrientationPnj;
import Modèle.Env.Arbre;
import Modèle.Env.ElementMap;
import Modèle.Env.Environnement;
import Modèle.Env.Rocher;
import Modèle.Item.*;
import Modèle.Perso.PersoNonJouable;
import Modèle.Protection.Bouclier;
import Vue.Item.ObjetVue;
import Vue.Item.VueCoffre;
import Vue.Perso.VuePnj;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

public class ObservateurObjet implements ListChangeListener<ElementMap> {
    @FXML
    private Pane plateau;
    private Environnement environnement;
    private ArrayList<ObjetVue>listeObjetVue;
    @FXML
    private ImageView bouclier;

    public ObservateurObjet(Pane pane, Environnement environnement, ImageView bouclier){
        plateau=pane;
        this.environnement=environnement;
        listeObjetVue=new ArrayList<>();
        this.bouclier=bouclier;
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
                    objRemoved.setInteraction();
                    retirerObjet(objRemoved);
                }else if(objRemoved instanceof Rocher) {
                    objRemoved.setInteraction();
                    retirerObjet(objRemoved);
                }else if(objRemoved instanceof Pioche){
                    objRemoved.setInteraction();
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
            vuePotion.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vuePotion.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vuePotion.getImg());
            listeObjetVue.add(vuePotion);
        }else if(obj instanceof Rocher) {
            ObjetVue vueRocher=new ObjetVue(url, obj.getId());
            vueRocher.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vueRocher.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vueRocher.getImg());
            listeObjetVue.add(vueRocher);
        }else if(obj instanceof Pioche){
            ObjetVue vuePioche=new ObjetVue(url,obj.getId());
            vuePioche.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vuePioche.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vuePioche.getImg());
            listeObjetVue.add(vuePioche);
        }else if(obj instanceof Arbre){
            ObjetVue vueArbre=new ObjetVue(url, obj.getId());
            vueArbre.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vueArbre.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vueArbre.getImg());
            listeObjetVue.add(vueArbre);
        }else if(obj instanceof Pomme) {
            ObjetVue vuePomme = new ObjetVue(url, obj.getId());
            vuePomme.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vuePomme.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vuePomme.getImg());
            listeObjetVue.add(vuePomme);

        }else if(obj instanceof Bouclier) {
            ObjetVue vueBouclier = new ObjetVue(url, obj.getId());
            vueBouclier.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vueBouclier.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vueBouclier.getImg());
            listeObjetVue.add(vueBouclier);

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
            vueKey.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vueKey.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vueKey.getImg());
            listeObjetVue.add(vueKey);
        }else if(obj instanceof Coffre){
            VueCoffre vueCoffre=new VueCoffre(url, obj.getId());
            vueCoffre.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vueCoffre.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vueCoffre.getImg());
            listeObjetVue.add(vueCoffre);
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
}