package Controleur.GestionaireObjet;

import Modèle.BouleDeFeu;
import Modèle.Environnement;
import Vue.VueBouleDeFeu;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateaurBouleDeFeu implements ListChangeListener<BouleDeFeu> {

    @FXML
    private Pane plateau;
    private Environnement env;
    private ArrayList<VueBouleDeFeu> tabBoule;

    public ObservateaurBouleDeFeu(Pane plateau, Environnement e) {
        this.plateau = plateau;
        env=e;
        tabBoule=new ArrayList<>();
    }

    @Override
    public void onChanged(Change<? extends BouleDeFeu> change) {
        while (change.next()) {
            for (BouleDeFeu nouvelle : change.getAddedSubList()) {
                affichageBoule(nouvelle);
            }
            for (BouleDeFeu desintegree : change.getRemoved()) {
                retirer(desintegree,env);
            }
        }
    }

    public void affichageBoule(BouleDeFeu b) {
        VueBouleDeFeu vue = new VueBouleDeFeu(b.getId(), "Vue/images/item_bomb_boom1.gif");
        vue.getBouleImg().translateXProperty().bind(b.xPropertyProperty());
        vue.getBouleImg().translateYProperty().bind(b.yPropertyProperty());
        this.plateau.getChildren().add(vue.getBouleImg());
        tabBoule.add(vue);
    }

    public void retirer(BouleDeFeu b,Environnement environnement) {
        this.plateau.getChildren().remove(b.getId());
        for(int i=0;i<tabBoule.size();i++){
            if(tabBoule.get(i).getIdBoule()==b.getId()){
                plateau.getChildren().remove(tabBoule.get(i).getBouleImg());
                tabBoule.remove(tabBoule.get(i));
                i--;
            }
        }
    }
}
