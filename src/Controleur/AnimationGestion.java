package Controleur;

import Vue.VueLink;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;


public class AnimationGestion implements ChangeListener<Number> {

    private VueLink vue;

    public AnimationGestion(VueLink vue) {
        this.vue = vue;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        if (number.equals(0)) {
            vue.desAniamtion();
            //vue.orientation();

        }
        else {
            vue.animation();
        }
    }
}
