package Controleur;

import Modèle.Environnement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public class ObservateurEnvironnement implements ChangeListener<Number> {
    private ObservateurObjet obsObj;
    private ObservateurPersonnage obsPerso;
    public ObservateurEnvironnement(ObservateurObjet obsObj,ObservateurPersonnage obsPerso){
        this.obsObj=obsObj;
        this.obsPerso=obsPerso;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        System.out.println("on passe par là");
        if(newValue!=observableValue){
            obsObj.deleteAll();
        }

    }


}
