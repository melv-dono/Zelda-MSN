package Controleur;

import Modèle.Environnement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObservateurEnvironnement implements ChangeListener<Number> {
    private ObservateurObjet obsObj;
    private Environnement e;
    public ObservateurEnvironnement(ObservateurObjet obsObj, Environnement env){
        this.obsObj=obsObj;
        e=env;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        System.out.println("on passe par là");
        if(newValue!=observableValue){
            obsObj.deleteAll();
        }

    }
}
