package Modèle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Odelin extends Ennemi  {

    static int nbrInvocation =10;
    private int chargementAttaque;
    ObservableList<Ennemi> invocations;
    public Odelin(double x, double y, Environnement e, String m) {
        super("Odelin", x, y, e, 5000, 10, 0, m);
        this.invocations = FXCollections.observableArrayList();
        this.chargementAttaque=30;
    }

    public ObservableList<Ennemi> getInvocations() {
        return invocations;
    }

    public void invoquer() {

        if (nbrInvocation != 0) {

            if (nbrInvocation % 2 == 0) {
                Squelette sqt = new Squelette(getDeplacementLargeur(), getDeplacementHauteur(), getEnv(), getMapAction());
                invocations.add(sqt);
                getEnv().getLesPersos().add(sqt);
            }
            else {
                Soldat sdt = new Soldat(getEnv(), getDeplacementLargeur(), getDeplacementHauteur(), getMapAction());
                invocations.add(sdt);
                getEnv().getLesPersos().add(sdt);
            }
            nbrInvocation--;
        }
    }


    @Override
    public void monter() {

    }

    @Override
    public void descendre() {

    }

    @Override
    public void gauche() {

    }

    @Override
    public void droite() {

    }

    @Override
    public void agir() {
        if (this.chargementAttaque != 0) {
            this.chargementAttaque--;
        }
        if (this.chargementAttaque == 0) {
            invoquer();
            this.chargementAttaque=30;
        }
    }
}
