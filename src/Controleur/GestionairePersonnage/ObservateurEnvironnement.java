package Controleur.GestionairePersonnage;

import Controleur.GestionaireObjet.ObservateurObjet;
import Controleur.ObservateurPersonnage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
