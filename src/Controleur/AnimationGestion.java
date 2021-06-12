package Controleur;

import Modèle.Environnement;
import Modèle.Personnage;
import Vue.VueLink;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class AnimationGestion implements ChangeListener<Number> {

    private VueLink vue;
    private Personnage p;

    public AnimationGestion(VueLink vue, Environnement env) {
        this.vue = vue;
        p=env.getLink();
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        if (number.equals(0)) {
            if(p.getOrientation()=="monter"){
                vue.orientation("monter");
            }else if(p.getOrientation()=="descendre"){
                vue.orientation("descendre");
            }else if(p.getOrientation()=="droite"){
                vue.orientation("droite");
            }else{
                vue.orientation("gauche");
            }

        }
        else {
            if(p.getOrientation()=="monter"){
                vue.animation("monter");
            }else if(p.getOrientation()=="descendre"){
                vue.animation("descendre");
            }else if(p.getOrientation()=="droite"){
                vue.animation("droite");
            }else{
                vue.animation("gauche");
            }

        }
    }
}
