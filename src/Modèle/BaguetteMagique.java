package Modèle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class BaguetteMagique extends Arme{
    private ObservableList<BouleDeFeu> boules;

    public BaguetteMagique(String nom, double pointAttaque) {
        super(nom, pointAttaque);
        this.boules = FXCollections.observableArrayList();

    }

    public ObservableList<BouleDeFeu> getBoules() {
        return boules;
    }


    public void creeBoule(double x, double y, String o) {
        BouleDeFeu b = new BouleDeFeu(x, y, o);
        this.boules.add(b);
    }

    /**
     * Cette méthode permet de lancer une boule de feu.
     */
    public void lancerBouleDeFeu() {
        for (BouleDeFeu b : boules) {
            if (b.getDureeDeVie()>0) {
                if (b.getDirection() == "gauche") {
                    b.gauche();
                }
                if (b.getDirection() == "droite") {
                    b.droite();
                }
                if (b.getDirection() == "monter") {
                    b.monter();
                }
                if (b.getDirection() == "descendre") {
                    b.descendre();
                }
                b.tpsEcoule();
            }
        }
    }

}
