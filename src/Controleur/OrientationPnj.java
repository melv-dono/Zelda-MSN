package Controleur;

import Modèle.Environnement;
import Vue.VuePnj;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class OrientationPnj implements ChangeListener<Number> {
    private VuePnj vuePnj;
    public OrientationPnj(VuePnj vuePnj){
        this.vuePnj=vuePnj;
    }
    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        vuePnj.setOrientation((Integer) t1);
    }
}
