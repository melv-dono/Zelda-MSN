package Controleur;

import Modèle.*;

import Vue.VueLink;
import Vue.VuePerso;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurPersonnage implements ListChangeListener<Personnage> {
    @FXML
    private Pane plateau;
    private Environnement environnement;
    private ArrayList<VuePerso> listePerso;
    private Link link;

    public ObservateurPersonnage(Pane pane, Environnement environnement){
        plateau=pane;
        this.environnement=environnement;
        listePerso=new ArrayList<>();
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
            System.out.println("ok");
            VuePerso vueSquelette = new VuePerso(url, p.getId());
            vueSquelette.getImg().translateXProperty().bind(p.getDeplacementLargeurProperty());
            vueSquelette.getImg().translateYProperty().bind(p.getDeplacementHauteurProperty());
            plateau.getChildren().add(vueSquelette.getImg());
            listePerso.add(vueSquelette);
        }
    }
    public void deletePerso(Personnage p){
        for(int i=0;i<listePerso.size();i++){
            if(listePerso.get(i).getId()==p.getId()){
                plateau.getChildren().remove(listePerso.get(i).getImg());
                listePerso.remove(listePerso.get(i));
                environnement.deletePerso(p);
                i--;
            }
        }
    }

}
