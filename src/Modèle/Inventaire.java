package Modèle;

import java.util.ArrayList;

public class Inventaire {
    private Objet[][] listeObjets;

    public Inventaire(){
        listeObjets=new Objet[4][4];
    }

    public void addObjet(Objet obj){
        if(restePlaceInventaire()==false){
            System.out.println("L'inventaire ne peut plus stocker d'objet");
        }else{
            for(int ligne=0;ligne<4;ligne++){
                for(int colonne=0;colonne<4;colonne++){
                    if(!(listeObjets[ligne][colonne] instanceof Objet)) {
                        listeObjets[ligne][colonne] = obj;
                    }
                }
            }
        }

    }

    private boolean restePlaceInventaire(){
        int placeUse=0;
        for(int ligne=0;ligne<4;ligne++){
            for(int colonne=0;colonne<4;colonne++){
                if(listeObjets[ligne][colonne] instanceof Objet){
                    placeUse++;
                }else{
                    return true;
                }
            }
        }
        return false;
    }
}
