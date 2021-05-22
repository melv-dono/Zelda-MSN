package Modèle;

import java.util.ArrayList;

public class Environnement { // Toutes les méthodes de cette classe ne sont pas encore utilisé dans le code
    private int width,height; // largeur == width - hauteur == height
    private ArrayList<Personnage> lesPerso;
    private ArrayList<MapModele> decors; // Permet de faire l'historique de tous les éléments de décors présents au sein de l'environnement.
    private MapModele mapActuelle; // La mapActuelle contient les données concrenant la map courante sur laquelle se tient le prso c'est à dire celle du TilePane.
    public Environnement(int width, int height, MapModele background){
        this.width=width;
        this.height=height;
        this.lesPerso=new ArrayList<>();
        this.decors = new ArrayList<>();
        this.mapActuelle=background;
        this.decors.add(mapActuelle);
    }
    public int getWidth(){ return width; }

    public int getHeight(){ return height; }

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
        if(p.getDeplacementLargeur()>width || p.getDeplacementLargeur()<0|| p.getDeplacementHauteur()>height||p.getDeplacementHauteur()<0){
            return ;
        }
        lesPerso.add(p);
    }

    public void deletePerso(Personnage p){ // méthode non utilisé pour l'instant
        lesPerso.remove(p);
    }

    private void collisionEmplacement(Personnage p){ // méthode pas codée et pas encore utile dans le code
        for (Personnage persoEnPlace:lesPerso){
            if(p.getDeplacementLargeur()==persoEnPlace.getDeplacementLargeur()&& p.getDeplacementHauteur()==persoEnPlace.getDeplacementHauteur()){

            }
        }
    }

    public void ajoutDecors(MapModele m) {
        this.decors.add(m);
    }

    public void retirerDecors(MapModele m) {
        this.decors.remove(m);
    }

    public String getNomMapCourante() {
        return this.mapActuelle.getNomMap();
    }

    public int[][] getCoordonneDecors(String nomDecors) { // renvoie null dans le cas ou le tableau n'existe pas
        for (MapModele m : this.decors) {
            if (nomDecors.equals(m.getNomMap())) {
                return m.getTableau();
            }
        }
        return null;
    }
}