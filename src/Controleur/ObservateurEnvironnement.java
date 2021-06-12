package Controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObservateurEnvironnement implements ChangeListener<Number> {
    private ObservateurObjet obsObj;
    public ObservateurEnvironnement(ObservateurObjet obsObj ){
        this.obsObj=obsObj;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        if(newValue!=observableValue){
            obsObj.deleteAll();
        }

    }
}
