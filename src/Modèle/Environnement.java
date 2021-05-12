package Modèle;

import java.util.ArrayList;

public class Environnement { // Toutes les méthodes de cette classe ne sont pas encore utilisé dans le code
    private int width,height;
    private ArrayList<Personnage> lesPerso;

    public Environnement(int width,int height){ // méthode non utilisé pour l'instant
        this.width=width;
        this.height=height;
        lesPerso=new ArrayList<>();
    }
    public int getWidth(){ return width; } // méthode non utilisé pour l'instant
    public int getHeight(){ return height; } // méthode non utilisé pour l'instant
    public ArrayList<Personnage>getPerso(){ // méthode non utilisé pour l'instant
        return lesPerso;
    }
    public Personnage getPersonnage(int id){ // méthode non utilisé pour l'instant
        for(Personnage p:lesPerso){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }
    public void addPerso(Personnage p){ // méthode non utilisé pour l'instant
        if(p.getX()>width || p.getX()<0|| p.getY()>height||p.getY()<0){
            return ;
        }
        lesPerso.add(p);
    }
    public void deletePerso(Personnage p){ // méthode non utilisé pour l'instant
        lesPerso.remove(p);
    }
    private void collisionEmplacement(Personnage p){ // méthode pas codée et pas encore utile dans le code
        for (Personnage persoEnPlace:lesPerso){
            if(p.getX()==persoEnPlace.getX()&& p.getY()==persoEnPlace.getY()){

            }
        }
    }
}