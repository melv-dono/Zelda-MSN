package Controleur;

import Modèle.BouleDeFeu;
import Vue.VueBouleDeFeu;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ObservateaurBouleDeFeu implements ListChangeListener<BouleDeFeu> {

    @FXML
    private Pane plateau;

    public ObservateaurBouleDeFeu(Pane plateau) {
        this.plateau = plateau;
    }

    @Override
    public void onChanged(Change<? extends BouleDeFeu> change) {
        while (change.next()) {
            for (BouleDeFeu nouvelle : change.getAddedSubList()) {
                affichageBoule(nouvelle);
            }

            for (BouleDeFeu desintegree : change.getRemoved()) {
                retirer(desintegree);
            }
        }
    }

    public void affichageBoule(BouleDeFeu b) {
        VueBouleDeFeu vue = new VueBouleDeFeu(b);
        this.plateau.getChildren().add(vue.creeSprite());
    }

    public void retirer(BouleDeFeu b) {
        this.plateau.getChildren().remove(this.plateau.lookup("#"+b.getId()));
    }
}
