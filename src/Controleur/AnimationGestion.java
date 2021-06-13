package Controleur;

import Modèle.Personnage;
import Vue.VueLink;
import Vue.VuePerso;
import com.sun.prism.image.ViewPort;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class AnimationGestion implements ChangeListener<Number> {

    private VuePerso vue;
    private Personnage perso;

    public AnimationGestion(VuePerso vue, Personnage p) {
        this.vue = vue;
        perso=p;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        if (number.equals(0)) {
            if (perso.getNom() == "Link") {
                if (perso.getOrientation() == "monter") {
                    vue.orientation("monter");
                } else if (perso.getOrientation() == "descendre") {
                    vue.orientation("descendre");
                } else if (perso.getOrientation() == "droite") {
                    vue.orientation("droite");
                } else {
                    vue.orientation("gauche");
                }
            }
//            else {
//                vue.orientation("descendre");
//            }

        }
        else {
            if (perso.getNom() == "Link") {
                if (perso.getOrientation() == "monter") {
                    vue.animation("monter");
                } else if (perso.getOrientation() == "descendre") {
                    vue.animation("descendre");
                } else if (perso.getOrientation() == "droite") {
                    vue.animation("droite");
                } else {
                    vue.animation("gauche");
                }
            }
            else {
                if (number.doubleValue() > 30) {
                    vue.orientation("descendre");
                }
                else {
                    vue.animation("descendre");

                }
            }
        }
    }
}
