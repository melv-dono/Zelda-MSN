package Controleur;

import Modèle.*;

import Vue.VuePerso;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurPersonnage implements ListChangeListener<Personnage> {
    @FXML
    private Pane plateau;
    private ArrayList<VuePerso> listePersoObs;
    private Link link;

    public ObservateurPersonnage(Pane pane, Environnement environnement){
        plateau=pane;;
        listePersoObs =new ArrayList<>();
        link= environnement.getLink();
    }


    @Override
    public void onChanged(Change<? extends Personnage> change) {
        while(change.next()){
            for(Personnage persoAdd: change.getAddedSubList()){
                if(persoAdd instanceof Squelette){
                    ajoutPerso(persoAdd,"Vue/bad_skeleton.gif");
                }
            }
            for(Personnage persoDelete: change.getRemoved()){
                if(persoDelete instanceof Squelette ){
                    deletePerso(persoDelete);
                }
            }
        }
    }
    public void ajoutPerso(Personnage p, String url) {
        if (p instanceof Squelette) {
            VuePerso vueSquelette = new VuePerso(url, p.getId());
            vueSquelette.getImg().translateXProperty().bind(p.getDeplacementLargeurProperty());
            vueSquelette.getImg().translateYProperty().bind(p.getDeplacementHauteurProperty());
            plateau.getChildren().add(vueSquelette.getImg());
            listePersoObs.add(vueSquelette);
        }
    }
    public void deletePerso(Personnage p){
        if(listePersoObs.size()>0){
            for(int i = 0; i< listePersoObs.size(); i++){
                if(listePersoObs.get(i).getId()==p.getId()){
                    plateau.getChildren().remove(listePersoObs.get(i).getImg());
                    listePersoObs.remove(listePersoObs.get(i));
                    i--;
                }
            }
        }

    }

}
