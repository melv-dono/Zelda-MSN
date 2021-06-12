package Controleur;

import Vue.VueLink;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class AnimationGestion implements ChangeListener<Number> {

    private VueLink vue;

    public AnimationGestion(VueLink vue) {
        this.vue = vue;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        if (t1.equals(0)) {
            vue.desAnimations();
        }
        else {
            vue.animation();
        }
    }
}
