package Controleur;

import Controleur.GestionairePersonnage.AnimationGestion;
import Modèle.*;

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
                    ajoutPerso(persoAdd, "Vue/images/ennemis/bad_skeleton.gif");
                }else if(persoAdd instanceof Soldat){
                    ajoutPerso(persoAdd, "Vue/images/ennemis/bad_soldgreen_front1.gif");
                }
                else if(persoAdd instanceof Odelin){
                    ajoutPerso(persoAdd, "Vue/images/ennemis/boss_agahnim.gif");
                }
            }
            for(Personnage persoDelete: change.getRemoved()){
                if(persoDelete instanceof Squelette ){
                    deletePerso(persoDelete);
                }else if(persoDelete instanceof Soldat){
                    deletePerso(persoDelete);
                }
                else if(persoDelete instanceof Odelin){
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
        if (p instanceof Soldat) {
            VueSoldat b = new VueSoldat(p.getId(), "Vue/images/ennemis/bad_soldgreen_front1.gif");
            b.getImg().translateXProperty().bind(p.getDeplacementLargeurProperty());
            b.getImg().translateYProperty().bind(p.getDeplacementHauteurProperty());
            AnimationGestion anim = new AnimationGestion(b,p);
            ((Soldat) p).animationPropertyProperty().addListener(anim);
            plateau.getChildren().add(b.getImg());
            listePersoObs.add(b);
        }
        if (p instanceof Odelin) {
            VuePerso vueBoss = new VuePerso(url, p.getId());
            vueBoss.getImg().translateXProperty().bind(p.getDeplacementLargeurProperty());
            vueBoss.getImg().translateYProperty().bind(p.getDeplacementHauteurProperty());
            plateau.getChildren().add(vueBoss.getImg());
            listePersoObs.add(vueBoss);
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
