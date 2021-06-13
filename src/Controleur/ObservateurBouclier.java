package Controleur;

import Modèle.Bouclier;
//import Modèle.ElementMap;
import Modèle.ElementMap;
import Modèle.Environnement;
import Modèle.Inventaire;
import Vue.ObjetVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class ObservateurBouclier implements ListChangeListener<Inventaire> {
    @FXML
    private Pane plateau;
    private Environnement environnement;
    private ImageView bouclier;

    public ObservateurBouclier(Pane pane, Environnement environnement, ImageView b){
        this.plateau=pane;
        this.environnement=environnement;
        this.bouclier=b;
        //this.bouclier.setVisible(false);
    }


    @Override
    public void onChanged(Change<? extends Inventaire> change) {
        for (ElementMap elem: environnement.getObjetEnvironnement()) {
                if (elem instanceof Bouclier){
                    environnement.getLink().setPointDefense(environnement.getLink().getPointDefense() + ((Bouclier) elem).getPointDef());
                }
        }
    }
}
