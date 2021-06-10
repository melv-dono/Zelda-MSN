package Modèle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Environnement { // Toutes les méthodes de cette classe ne sont pas encore utilisé dans le code
    private int width,height; // largeur == width - hauteur == height
    private ArrayList<Personnage> lesPerso; // Représente la liste des personnages présent dans l'environnement.
    private ArrayList<MapModele> decors; // Permet de faire l'historique de tous les éléments de décors présents au sein de l'environnement.
    private ObservableList<Objet> objetEnvironnement; // Liste de tous les objets qui seront ramassable,trouvable dans un coffre ou donné par un PNJ
    private MapModele mapActuelle; // La mapActuelle contient les données concernant la map courante sur laquelle se tient le perso c'est à dire celle du TilePane.
    private Link utilisateur;
    private final Inventaire inventaire=new Inventaire();

    /**
     * CONSTRUCTEUR
     */
    public Environnement(int width, int height){
        this.width=width;
        this.height=height;
        this.lesPerso=new ArrayList<>();
        this.decors = new ArrayList<>();
        this.mapActuelle= new MapModele("map1");
        this.decors.add(mapActuelle);
        objetEnvironnement= FXCollections.observableArrayList();
    }

    /**
     * GETTERS
     */

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

    public MapModele getMapActuelle() {
        return mapActuelle;
    }

    public Link getLink() {
        return this.utilisateur;
    }
    public Inventaire getInventaire(){
        return inventaire;
    }
    public ObservableList<Objet> getObjetEnvironnement(){
        return objetEnvironnement;
    }

    /**
     * SETTERS
     */

    /**
     * METHODES
     */

    /**
     * Permet d'ajouter un personnage à la liste de ceux présents dans l'envrionnement.
     * @param p
     */
    public void addPerso(Personnage p){
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


    public void init() {
        this.utilisateur = new Link(this);
        addPerso(this.utilisateur);
        BaguetteMagique baguette = new BaguetteMagique("Elder Wand", 30);
        Epe epe = new Epe("Excalibur", 10, this.utilisateur);
        this.utilisateur.setArmePrincipale(epe);
        this.utilisateur.setArmeSecondaire(baguette);
        creeEnnemi(); // Attention je l'ai mis dès le début uniquement car je suis sur la map de base
    }

    public void creeEnnemi() {
        Squelette s = new Squelette("Squelette", this);
        Breteur b = new Breteur("Gardes", this);
        addPerso(s);
        addPerso(b);
    }

    public void faireUntour() {
        this.utilisateur.getarmeSecondaire().lancerBouleDeFeu();
        this.utilisateur.declencherAnimation();
        cibleTouche();
        retirerBouleDeFeu();
        for (Personnage p : this.lesPerso) {
            if (p instanceof Squelette) {
                ((Squelette)p).agir();
            }
            if (p instanceof Breteur) {
                ((Breteur)p).agir();
            }
        }


    }

    public void retirerBouleDeFeu() {
        this.utilisateur.getarmeSecondaire().getBoules().removeIf(BouleDeFeu::seDesintegre);
    }

    public void cibleTouche() { // Boucle For each ne marche pas
        double haut, bas, gauche, droite;
        for (Personnage ennemi : lesPerso) {
            for (int i=0; i< this.utilisateur.getarmeSecondaire().getBoules().size(); i++) {
                haut= this.utilisateur.getarmeSecondaire().getBoules().get(i).getyProperty()-16;
                bas= this.utilisateur.getarmeSecondaire().getBoules().get(i).getyProperty()+16;
                gauche= this.utilisateur.getarmeSecondaire().getBoules().get(i).getxProperty()-5;
                droite= this.utilisateur.getarmeSecondaire().getBoules().get(i).getxProperty()+5;

                if(		(ennemi.getDeplacementHauteur() >= haut && ennemi.getDeplacementHauteur() <= bas) &&
                        (ennemi.getDeplacementLargeur() >= gauche && ennemi.getDeplacementLargeur() <= droite) &&
                        ennemi instanceof Squelette
                )
                {
                    ennemi.perteDePv(this.utilisateur.getDommageArmeSecondaire());
                    this.utilisateur.getarmeSecondaire().getBoules().remove(this.utilisateur.getarmeSecondaire().getBoules().get(i));
                }
            }
        }
    }
    public void addObjetDansEnv(Objet objet){
        objetEnvironnement.add(objet);
    }
    public void miseEnPlaceObjetFirstMap(){
        Potion potion=new Potion(520,608);;
        Rocher rocher =new Rocher(392,608);
        Pioche pioche=new Pioche(840,160);
        //Pomme pomme=new Pomme(488,160);
        Arbre arbre=new Arbre(488,160,3);
        objetEnvironnement.addAll(potion,rocher,pioche,arbre);


    }

    /**
     * FONCTIONS
     */
    public double nbElementExt(){
        return objetEnvironnement.size();
    }

    public HashMap<Coordonnees, Coordonnees> bfs(Coordonnees depart) {
        Queue<Coordonnees> frontiere = new ArrayDeque<Coordonnees>();
        ArrayList<Coordonnees> marquer = new ArrayList<Coordonnees>();
//        Coordonnees.setTree(depart);
        frontiere.add(depart);
        HashMap<Coordonnees, Coordonnees> antecedent =new HashMap<Coordonnees, Coordonnees>();
        antecedent.put(depart, null);
        marquer.add(depart);

        while (!frontiere.isEmpty()) {
            Coordonnees pointCourant = frontiere.poll();
//            pointCourant.marked();

//            if (pointCourant.isEqual(arrive)) {
//                System.out.println("Trouvé");
//                return antecedent;
//            }


                for (Coordonnees voisins : pointCourant.voisins()) {
                    if ( !marquer.contains(voisins)  /*&& this.mapActuelle.getTableau()[voisins.getLigne()][voisins.getColonne()]==1*/) {
                        frontiere.add(voisins);
//                        lien.put(voisins, pointCourant);
                        antecedent.put(voisins, pointCourant);
                        marquer.add(voisins);
                    }
                }
            }
        System.out.println(antecedent.size());
        return antecedent;
    }

}