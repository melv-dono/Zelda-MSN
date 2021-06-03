package Controleur;

import Modèle.Objet;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ObservateurObjet implements ListChangeListener<Objet> {
    @FXML
    private Pane plateau;
    public ObservateurObjet(Pane pane){
        plateau=pane;
    }
    @Override
    public void onChanged(Change<? extends Objet> change) {
        while(change.next()){
            for(Objet obj:change.getRemoved()){
                plateau.getChildren().remove(obj);
            }
        }
    }
}
