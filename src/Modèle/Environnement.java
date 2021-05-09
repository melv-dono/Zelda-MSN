package Modèle;

import java.util.ArrayList;

public class Environnement {
    private int width,height;
    private ArrayList<Personnage> lesPerso;

    public Environnement(int width,int height){
        super();
        this.width=width;
        this.height=height;
        lesPerso=new ArrayList<>();
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public ArrayList<Personnage>getPerso(){
        return lesPerso;
    }
    public Personnage getPersonnage(int id){
        for(Personnage p:lesPerso){
            return p;
        }
        return null;
    }
    public void addPerso(Personnage p){
        lesPerso.add(p);
    }
    public void deletePerso(Personnage p){
        lesPerso.remove(p);
    }
}