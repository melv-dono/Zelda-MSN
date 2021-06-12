package Controleur;

import Modèle.*;
import Vue.ObjetVue;
import Vue.VueCoffre;
import Vue.VuePnj;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurObjet implements ListChangeListener<ElementMap> {
    @FXML
    private Pane plateau;
    private Environnement environnement;
    private ArrayList<ObjetVue>listeObjetVue;
    private IntegerProperty numEnv;

    public ObservateurObjet(Pane pane, Environnement environnement){
        plateau=pane;
        this.environnement=environnement;
        listeObjetVue=new ArrayList<>();
        numEnv=new SimpleIntegerProperty(environnement.getId());
    }
    @Override
    public void onChanged(Change<? extends ElementMap> change) {
        while(change.next())
            for(ElementMap objAdded:change.getAddedSubList()){
                if(objAdded instanceof Potion){
                    ajoutObjet(objAdded,"Vue/inventory_potionblue.gif");
                }else if(objAdded instanceof Rocher) {
                    ajoutObjet(objAdded,"Vue/item_stonefence.gif");
                }else if(objAdded instanceof Pioche){
                    ajoutObjet(objAdded,"Vue/pioche.png");
                }else if(objAdded instanceof Arbre){
                    ajoutObjet(objAdded,"Vue/liltree.gif");
                }else if(objAdded instanceof Pomme){
                    ajoutObjet(objAdded,"Vue/pomme.gif");
                }else if(objAdded instanceof PersoNonJouable){
                    ajoutObjet(objAdded,"Vue/pnjFace.png");
                }else if(objAdded instanceof Key){
                    ajoutObjet(objAdded,"Vue/keyTile.png");
                }else if(objAdded instanceof Coffre){
                    ajoutObjet(objAdded,"Vue/coffre.gif");
                }
            }
            for(ElementMap objRemoved: change.getRemoved()){
                if(objRemoved instanceof Potion){
                    retirerObjet(objRemoved.getId());
                }else if(objRemoved instanceof Rocher) {
                    retirerObjet(objRemoved.getId());
                }else if(objRemoved instanceof Pioche){
                    retirerObjet(objRemoved.getId());
                }else if(objRemoved instanceof Arbre){
                    retirerObjet(objRemoved.getId());
                }else if(objRemoved instanceof Pomme){
                    retirerObjet(objRemoved.getId());
                }else if(objRemoved instanceof Key){
                    retirerObjet(objRemoved.getId());
                }
            }
            for(ElementMap objChange: environnement.getObjetEnvironnement()){ // méthode de changement de l'image du coffre
                if(objChange instanceof Coffre){
                    if(((Coffre) objChange).tiensObjet()==false ){
                        for(ObjetVue objVue:listeObjetVue){
                            if(objChange.getId()==objVue.getId()){
                                objVue.setImg("Vue/coffreOpen.gif");
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
        }else if(obj instanceof Pomme){
            ObjetVue vuePomme=new ObjetVue(url,obj.getId());
            vuePomme.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vuePomme.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vuePomme.getImg());
            listeObjetVue.add(vuePomme);

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
    public void retirerObjet(int id){
        /*for(ObjetVue o:listeObjetVue){
            if(o.getId()==id){
            plateau.getChildren().remove(o.getImg());
            listeObjetVue.remove(o);
        }
        }*/
        for(int i=0;i<listeObjetVue.size();i++){
            if(listeObjetVue.get(i).getId()==id){
                plateau.getChildren().remove(listeObjetVue.get(i).getImg());
                listeObjetVue.remove(listeObjetVue.get(i));
                i--;
            }
        }
    }
    public void changerVue(int id){
        for(ObjetVue o:listeObjetVue){
            if(o.getId()==id){
                ObjetVue objetVue=new ObjetVue("Vue/coffreOpen.gif",id);
            }
        }
    }
    public IntegerProperty getNumEnv(){
        return numEnv;
    }
    public void deleteAll(){
        for(int i=0;i<listeObjetVue.size();i++){
            plateau.getChildren().remove(listeObjetVue.get(i).getImg());
            listeObjetVue.remove(listeObjetVue.get(i));
            i--;
        }
    }
}
