package Controleur;

import Modèle.*;

import Vue.VueLink;
import Vue.VuePerso;
import Vue.VueSoldat;
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
                }else if(persoAdd instanceof Soldat){
                    ajoutPerso(persoAdd,"Vue/bad_soldgreen_front1.gif");
                }
            }
            for(Personnage persoDelete: change.getRemoved()){
                if(persoDelete instanceof Squelette ){
                    deletePerso(persoDelete);
                }else if(persoDelete instanceof Soldat){
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
            listePersoObs.add(vueSquelette);
        }
        if (p instanceof Soldat) {
            VueSoldat b = new VueSoldat(p.getId(),"Vue/bad_soldgreen_front1.gif");
            b.getImg().translateXProperty().bind(p.getDeplacementLargeurProperty());
            b.getImg().translateYProperty().bind(p.getDeplacementHauteurProperty());
            AnimationGestion anim = new AnimationGestion(b,p);
            ((Soldat) p).animationPropertyProperty().addListener(anim);
            plateau.getChildren().add(b.getImg());
            listePersoObs.add(b);
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
