package Modèle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Inventaire {
    private ObservableList<Label> listeObjets;
    private int tailleInventaire;

    public Inventaire(){
        listeObjets= FXCollections.observableArrayList();
        tailleInventaire=15;
    }
    public ObservableList<Label> getListeObjets(){
        return listeObjets;
    }
    public int getTailleInventaire(){
        return tailleInventaire;
    }

    public void addObjet(Label obj){
        if(restePlaceInventaire()==false){
            System.out.println("L'inventaire ne peut plus stocker d'objet");
        }else{
            listeObjets.add(obj);
        }

    }

    private boolean restePlaceInventaire(){
        int placeUse=0;
        for(Label h:listeObjets){
            placeUse++;
        }
        if(placeUse>15){
            return false;
        }else{
            return true;
        }
    }
}
