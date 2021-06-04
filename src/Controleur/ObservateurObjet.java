package Controleur;

import Modèle.Environnement;
import Modèle.Objet;
import Modèle.Potion;
import Modèle.Rocher;
import Vue.ObjetVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ObservateurObjet implements ListChangeListener<Objet> {
    @FXML
    private Pane plateau;
    private Environnement environnement;
    public ObservateurObjet(Pane pane, Environnement environnement){
        plateau=pane;
        this.environnement=environnement;
    }
    @Override
    public void onChanged(Change<? extends Objet> change) {
        while(change.next())
            for(Objet obj:change.getAddedSubList()){
                if(obj instanceof Potion){
                    ajoutObjet(obj,"Vue/inventory_potionblue.gif");
                }else if(obj instanceof Rocher) {
                    ajoutObjet(obj,"Vue/item_stonefence.gif");
                }
            }
            for(Objet obj: change.getRemoved()){
                retirerObjet(obj);
            }
    }

    public void ajoutObjet(Objet obj,String url){
        if(obj instanceof Potion) {
            ObjetVue vuePotion=new ObjetVue(url);
            vuePotion.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vuePotion.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vuePotion.getImg());
        }else if(obj instanceof Rocher) {
            ObjetVue vueRocher=new ObjetVue(url);
            vueRocher.getImg().translateXProperty().bind(obj.getPositionLargeur());
            vueRocher.getImg().translateYProperty().bind(obj.getPositionHauteur());
            plateau.getChildren().add(vueRocher.getImg());
        }


    }
    public void retirerObjet(Objet obj){
        plateau.getChildren().remove(obj);
    }
}
