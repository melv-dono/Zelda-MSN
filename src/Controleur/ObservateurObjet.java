package Controleur;

import Modèle.*;
import Vue.ObjetVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurObjet implements ListChangeListener<Objet> {
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
    public void onChanged(Change<? extends Objet> change) {
        while(change.next())
            for(Objet objAdded:change.getAddedSubList()){
                if(objAdded instanceof Potion){
                    ajoutObjet(objAdded,"Vue/inventory_potionblue.gif");
                }else if(objAdded instanceof Rocher) {
                    ajoutObjet(objAdded,"Vue/item_stonefence.gif");
                }else if(objAdded instanceof Pioche){
                    ajoutObjet(objAdded,"Vue/pelle.gif");
                }else if(objAdded instanceof Arbre){
                    ajoutObjet(objAdded,"Vue/liltree.gif");
                }else if(objAdded instanceof Pomme){
                    ajoutObjet(objAdded,"Vue/pomme.gif");
                }
            }
            for(Objet objRemoved: change.getRemoved()){
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
                }
            }
    }

    public void ajoutObjet(Objet obj,String url){
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

        }


    }
    public void retirerObjet(int id){
  /*      for(ObjetVue o:listeObjetVue){
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
}
