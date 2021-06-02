package Modèle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class BaguetteMagique extends Arme{
    private String nom;
    private double pointAttaque;
    private Personnage perso;
    private ObservableList<BouleDeFeu> boules;

    public BaguetteMagique(String nom, double pointAttaque, Personnage p) {
        super(nom, pointAttaque);
        this.perso = p;
        this.boules = FXCollections.observableArrayList();

    }

    public ObservableList<BouleDeFeu> getBoules() {
        return boules;
    }

    public void attaquer() {
        BouleDeFeu b = new BouleDeFeu(this.perso.getDeplacementLargeur(), this.perso.getDeplacementHauteur());

    }

    public void retirerBoule(BouleDeFeu bouleDeFeu) {
        if (boules.contains(bouleDeFeu)) {
            boules.remove(bouleDeFeu);
        }
    }

    public void creeBoule() {
        BouleDeFeu b = new BouleDeFeu(this.perso.getDeplacementLargeur(), this.perso.getDeplacementHauteur());
        this.boules.add(b);
//        return b;
    }

    public void lancerBouleDeFeu() {
        for (BouleDeFeu b : boules) {
            if (b.getDureeDeVie()>0) {
                b.gauche();
                b.tpsEcoule();
            }
        }
    }

}
