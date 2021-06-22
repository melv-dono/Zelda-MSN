package Modèle.Item;

import Modèle.Env.ElementMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
    private ObservableList<String> listeObjets=FXCollections.observableArrayList();

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

    public void addObjet(ElementMap obj){
        if(restePlaceInventaire()==false){
            System.out.println("L'inventaire ne peut plus stocker d'objet");
        }else{
            listeObjets.add(obj.getNom());
        }

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
    public void deleteObjetString(String str){
        int a=0;
        for(int i=0;i<listeObjets.size();i++){
            if(listeObjets.get(i)==str && a==0){
                listeObjets.remove(str);
                i--;
                a=1;
            }
        }
    }
    public boolean inventairePossede(String nomObj){
        for(String obj:listeObjets){
            if(obj.equals(nomObj)){
                return true;
            }
        }
        return false;
    }



}
