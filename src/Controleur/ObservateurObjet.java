package Controleur;

import Modèle.Environnement;
import Modèle.Objet;
import Modèle.Potion;
import Modèle.Rocher;
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
                }
            }
            for(Objet objRemoved: change.getRemoved()){
                if(objRemoved instanceof Potion){
                    retirerObjet(objRemoved.getId());
                }else if(objRemoved instanceof Rocher) {
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
        }


    }
    public void retirerObjet(int id){
        for(ObjetVue o:listeObjetVue){
            if(o.getId()==id){
                System.out.println("okkk");
                listeObjetVue.remove(o);
                plateau.getChildren().remove(o.getImg());
            }
        }
    }
}
