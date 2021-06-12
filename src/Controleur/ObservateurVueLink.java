package Controleur;

import Vue.VueLink;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObservateurVueLink implements ChangeListener<String> {
    private VueLink vueL;

    public ObservateurVueLink(VueLink vue){
        this.vueL=vue;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String ancien, String nouv) {
        if(nouv.equals("monter")){
            vueL.orientation("monter");
        }
        else if (nouv.equals("descendre")){
            vueL.orientation("descendre");
        }
        else if(nouv.equals("gauche")){
            vueL.orientation("gauche");
        }
        else if(nouv.equals("droite")){
            vueL.orientation("droite");
        }
    }
}
