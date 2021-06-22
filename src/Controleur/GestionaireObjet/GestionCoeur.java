package Controleur.GestionaireObjet;

import Modèle.Env.Environnement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;

public class GestionCoeur implements ChangeListener<Number> {
    private ImageView coeur1, coeur2, coeur3, coeur4,coeur5;
    private Environnement env;

    public GestionCoeur(ImageView coeur1, ImageView coeur2, ImageView coeur3, ImageView coeur4, ImageView coeur5, Environnement environnement){
        this.coeur1=coeur1;
        this.coeur2=coeur2;
        this.coeur3=coeur3;
        this.coeur4=coeur4;
        this.coeur5=coeur5;
        env=environnement;
    }
    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        if(env.getLink().pv()<=80){
            coeur5.setVisible(false);
        }else{
            coeur5.setVisible(true);
        }
        if(env.getLink().pv()<=60){
            coeur4.setVisible(false);
        }else{
            coeur4.setVisible(true);
        }
        if(env.getLink().pv()<=40){
            coeur3.setVisible(false);
        }else{
            coeur3.setVisible(true);
        }
        if(env.getLink().pv()<=20){
            coeur2.setVisible(false);
        }else{
            coeur2.setVisible(true);
        }
    }
}
