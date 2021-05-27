package Modèle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
    private ObservableList<String> listeObjets;
    private int tailleInventaire;

    public Inventaire(){
        listeObjets= FXCollections.observableArrayList();
        tailleInventaire=15;
    }
    public ObservableList<String> getListeObjets(){
        return listeObjets;
    }
    public int getTailleInventaire(){
        return tailleInventaire;
    }

    public void addObjet(Objet obj){
        if(restePlaceInventaire()==false){
            System.out.println("L'inventaire ne peut plus stocker d'objet");
        }else{
            listeObjets.add(obj.getNom());
        }

    }

    private boolean restePlaceInventaire(){
        int placeUse=0;
        for(String h:listeObjets){
            placeUse++;
        }
        if(placeUse>15){
            return false;
        }else{
            return true;
        }
    }
}
