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
        if(p.getX()>width || p.getX()<0){
            return ;
        }
        lesPerso.add(p);
    }
    public void deletePerso(Personnage p){
        lesPerso.remove(p);
    }
    private void collisionEmplacement(Personnage p){
        for (Personnage persoEnPlace:lesPerso){
            if(p.getX()==persoEnPlace.getX()){

            }
        }
    }
}