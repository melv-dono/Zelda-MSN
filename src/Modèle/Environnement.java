package Modèle;

import Controleur.OrientationPnj;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement { // Toutes les méthodes de cette classe ne sont pas encore utilisé dans le code
    private int width,height; // largeur == width - hauteur == height
    private IntegerProperty id;
    private ArrayList<Personnage> lesPerso; // Représente la liste des personnages présent dans l'environnement.

    private ArrayList<MapModele> decors; // Permet de faire l'historique de tous les éléments de décors présents au sein de l'environnement.

    private ArrayList<ElementMap> objetEnvironnement; // Liste de tous les objets qui seront ramassable,trouvable dans un coffre ou donné par un PNJ
    private ObservableList<ElementMap>objEnvAct; // <liste des objets de la map où on se trouve

    private MapModele mapActuelle; // La mapActuelle contient les données concernant la map courante sur laquelle se tient le perso c'est à dire celle du TilePane.
    private Link utilisateur;
    private final Inventaire inventaire=new Inventaire();

    private final ArrayList<ElementMap>elementMap1=new ArrayList<>();
    /**
     * CONSTRUCTEUR
     */
    public Environnement(int width, int height, int id, String nomMap){
        this.width=width;
        this.height=height;
        this.id=new SimpleIntegerProperty(id);
        this.lesPerso=new ArrayList<>();
        this.decors = new ArrayList<>();
        this.mapActuelle= new MapModele(nomMap);
        this.decors.add(mapActuelle);
        objEnvAct=FXCollections.observableArrayList();
        objetEnvironnement=new ArrayList<>();
    }

    public Environnement(int width, int height, int id, String nomMap, Link utilisateur){
        this.width=width;
        this.height=height;
        this.id=new SimpleIntegerProperty(id);
        this.lesPerso=new ArrayList<>();
        this.decors = new ArrayList<>();
        this.mapActuelle= new MapModele(nomMap);
        this.decors.add(mapActuelle);
        this.utilisateur=utilisateur;
        objEnvAct=FXCollections.observableArrayList();
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
    public ObservableList<ElementMap> getObjEnvAct(){
        return objEnvAct;
    }
    public int getId(){
        return this.id.getValue();
    }
    public IntegerProperty getTheID(){
        return id;
    }

    /**
     * SETTERS
     */
    public void setId(int id){
        this.id.setValue(id);

    }

    public void setMapActuelle(String nomMap){
        this.mapActuelle = new MapModele(nomMap);
    }

    /**
     * METHODES
     */

    /**
     * Permet d'ajouter un personnage à la liste de ceux présents dans l'envrionnement.
     * @param p
     */
    public void addPerso(Personnage p){
        lesPerso.add(p);
    }
    /**
     * Permet de retirer un personnage à la liste de ceux présents dans l'envrionnement.
     * @param p
     */
    public void deletePerso(Personnage p){ // méthode non utilisé pour l'instant
        lesPerso.remove(p);
    }
    public void deleteAllPerso(){
        for (int i=0;i<lesPerso.size();i++){
            lesPerso.remove(lesPerso.get(i));
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


    public void init() {
        if(id.getValue()==1){
            this.utilisateur = new Link(this);
        }
        addPerso(this.utilisateur);
        BaguetteMagique baguette = new BaguetteMagique("Elder Wand", 30);
        Epe epe = new Epe("Excalibur", 10, this.utilisateur);
        this.utilisateur.setArmePrincipale(epe);
        this.utilisateur.setArmeSecondaire(baguette);
        //creeEnnemi(); // Attention je l'ai mis dès le début uniquement car je suis sur la map de base
    }

    public void creeEnnemi() {
        Squelette s = new Squelette("Squelette", this);
        addPerso(s);
    }
    public void ajoutMap1(ElementMap elementMap){
        elementMap1.add(elementMap);
    }

    public void faireUntour() {
        this.utilisateur.getarmeSecondaire().lancerBouleDeFeu();
        this.utilisateur.declencherAnimation();
        cibleTouche();
        retirerBouleDeFeu();
        for (Personnage p : this.lesPerso) {
            if (p instanceof Squelette) {
                ((Squelette)p).animationSquelette1(this);
            }
        }
        for(Personnage p:lesPerso){
            if(p instanceof Squelette){
                ((Squelette) p).attaquer(this);
            }
        }

        if(utilisateur.getExp() > 1){
            utilisateur.setExp(0);
            utilisateur.niveau().set(utilisateur.getNiveau()+1);
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
    public void addObjetDansEnv(ElementMap objet){
        objetEnvironnement.add(objet);
    }
    public void chargerTousLesObj(){
        Potion potion=new Potion(520,608);;
        Rocher rocher =new Rocher(392,608);
        Pioche pioche=new Pioche(840,160);
        Arbre arbre=new Arbre(488,160,3);
        Key key=new Key(1000,480);
        PersoNonJouable pnj=new PersoNonJouable(1000,480,key);
        Bouclier bouclier=new Bouclier(200,352);
        Coffre coffre=new Coffre(bouclier,200,352);
        objetEnvironnement.add(potion);
        System.out.println(objetEnvironnement);
        objetEnvironnement.add(rocher);
        objetEnvironnement.add(pioche);
        objetEnvironnement.add(arbre);
        objetEnvironnement.add(pnj);
        objetEnvironnement.add(coffre);
        System.out.println(objetEnvironnement);
        //objetEnvironnement.addAll(potion,rocher,pioche,arbre,pnj,coffre);

    }
    public void chargerObjMap1(){
        System.out.println(objetEnvironnement);
        for(ElementMap elMap: objetEnvironnement){
            if(!(elMap instanceof Coffre)){
                objEnvAct.add(elMap);
            }
        }
    }
    public void setUpSecondMap(){
        System.out.println(objetEnvironnement);
        for(ElementMap elMap: objetEnvironnement){
            if(elMap instanceof Coffre){
                objEnvAct.add(elMap);
            }
        }
    }
    public void retirerCollision(){
        for(int i=0;i<objEnvAct.size();i++){
            objEnvAct.remove(objEnvAct.get(i));
            i--;
        }
    }
    public void retirerObjEnvAct(ElementMap obj){
        objEnvAct.remove(obj);
        objetEnvironnement.remove(obj);
    }

    /**
     * FONCTIONS
     */
    public double nbElementExt(){
        return objetEnvironnement.size();
    }


}