package Modèle;

import java.util.ArrayList;

public class Environnement { // Toutes les méthodes de cette classe ne sont pas encore utilisé dans le code
    private int width,height; // largeur == width - hauteur == height
    private ArrayList<Personnage> lesPerso; // Représente la liste des personnages présent dans l'environnement.
    private ArrayList<MapModele> decors; // Permet de faire l'historique de tous les éléments de décors présents au sein de l'environnement.
    private ArrayList<Objet>elementDecor; // potion et autre
    private MapModele mapActuelle; // La mapActuelle contient les données concernant la map courante sur laquelle se tient le perso c'est à dire celle du TilePane.

    public Environnement(int width, int height, MapModele background){
        this.width=width;
        this.height=height;
        this.lesPerso=new ArrayList<>();
        this.decors = new ArrayList<>();
        this.mapActuelle=background;
        this.decors.add(mapActuelle);
    }

    /**
     * Envoie la largeur de l'environnement.
     * @return largeur
     */
    public int getWidth(){ return width; }

    /**
     * Envoie la hauteur de l'environnement.
     * @return hauteur
     */
    public int getHeight(){ return height; }

    /**
     * Envoie la liste de tous les personnages de l'environnement.
     * @return liste de personnage
     */
    public ArrayList<Personnage>getPerso(){ // méthode non utilisé pour l'instant
        return lesPerso;
    }

    /**
     * Renvoie le personnage dont l'id correspond à celui rentré en paramètre.
     * @param id
     * @return un personnage précis
     */
    public Personnage getPersonnage(int id){ // méthode non utilisé pour l'instant
        for(Personnage p:lesPerso){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    /**
     * Envoie le nom de la map sur laquelle se trouve les personnages.
     * @return nom de mapActuelle
     */
    public String getNomMapCourante() {
        return this.mapActuelle.getNomMap();
    }

    /**
     * Envoie les données (tableau de chiffre) d'un élément du décors de l'envrionnement.
     * @param nomDecors
     * @return un tableau de donnée précis
     */
    public int[][] getCoordonneDecors(String nomDecors) { // renvoie null dans le cas ou le tableau n'existe pas
        for (MapModele m : this.decors) {
            if (nomDecors.equals(m.getNomMap())) {
                return m.getTableau();
            }
        }
        return null;
    }

    /**
     * Permet d'ajouter un personnage à la liste de ceux présents dans l'envrionnement.
     * @param p
     */
    public void addPerso(Personnage p){ // méthode non utilisé pour l'instant
        if(p.getDeplacementLargeur()>width || p.getDeplacementLargeur()<0|| p.getDeplacementHauteur()>height||p.getDeplacementHauteur()<0){
            return ;
        }
        lesPerso.add(p);
    }

    /**
     * Permet de retirer un personnage à la liste de ceux présents dans l'envrionnement.
     * @param p
     */
    public void deletePerso(Personnage p){ // méthode non utilisé pour l'instant
        lesPerso.remove(p);
    }

    private void collisionEmplacement(Personnage p){ // méthode pas codée et pas encore utile dans le code
        for (Personnage persoEnPlace:lesPerso){
            if(p.getDeplacementLargeur()==persoEnPlace.getDeplacementLargeur()&& p.getDeplacementHauteur()==persoEnPlace.getDeplacementHauteur()){

            }
        }
    }

    /**
     * Permet d'ajouter un décros à la liste de ceux présents dans l'envrionnement.
     * @param m
     */
    public void ajoutDecors(MapModele m) {
        this.decors.add(m);
    }

    /**
     * Permet de retirer un décros à la liste de ceux présents dans l'envrionnement.
     * @param m
     */
    public void retirerDecors(MapModele m) {
        this.decors.remove(m);
    }

    public void ajouterObjet(Objet obj){
        elementDecor.add(obj);
    }

}