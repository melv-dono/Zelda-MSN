package Modèle;

import Vue.ObjetVue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
    private ObservableList<String> listeObjets=FXCollections.observableArrayList();
    private int tailleInventaire=15;

    public ObservableList<String> getListeObjets(){
        return listeObjets;
    }
    public int getTailleInventaire(){
        int tailleActuel=0;
        for(String o:listeObjets){
            tailleActuel++;
        }
        return tailleActuel;
    }

    public void addObjet(Objet obj){
        if(restePlaceInventaire()==false){
            System.out.println("L'inventaire ne peut plus stocker d'objet");
        }else{
            listeObjets.add(obj.toString());
        }

    }
    public void removeObjet(Objet obj){
        listeObjets.remove(obj.toString());
    }

    private boolean restePlaceInventaire(){
        int placeUse=0;
        for(String o:listeObjets){
            placeUse++;
        }
        if(placeUse>15){
            return false;
        }else{
            return true;
        }
    }

}
