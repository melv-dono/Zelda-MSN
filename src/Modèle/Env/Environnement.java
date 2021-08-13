package Modèle.Env;

import Modèle.Arme.BaguetteMagique;
import Modèle.Arme.BouleDeFeu;
import Modèle.Arme.Epe;
import Modèle.Item.Inventaire;
import Modèle.Item.*;
import Modèle.NombreInvalide;
import Modèle.Perso.*;
import Modèle.Protection.Bouclier;
import Modèle.Utils.Coordonnees;
import Modèle.Utils.Parametre;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Environnement { // Toutes les méthodes de cette classe ne sont pas encore utilisé dans le code
    private int width,height; // largeur == width - hauteur == height
    private IntegerProperty id;

    private ArrayList<Ennemi>lesPersos;// Représente la liste de tous les ennemis présent dans l'environnement.
    private ObservableList<Ennemi> persoMapActu; // Représente la liste des ennemis sur la map courante.

    private ArrayList<MapModele> decors; // Permet de faire l'historique de tous les éléments de décors présents au sein de l'environnement.

    private ArrayList<ElementMap> objetEnvironnement; // Liste de tous les objets qui seront ramassable,trouvable dans un coffre ou donné par un PNJ
    private ObservableList<ElementMap>objEnvAct; // <liste des objets de la map où on se trouve

    private MapModele mapActuelle; // La mapActuelle contient les données concernant la map courante sur laquelle se tient le perso c'est à dire celle du TilePane.
    private Link user;
    private final Inventaire inventaire=new Inventaire();
    private Odelin boss;

    private final ArrayList<ElementMap>elementMap1=new ArrayList<>();
    /**
     * CONSTRUCTEUR
     */
    public Environnement(int id, String nomMap){
        this.width= Parametre.LARGEUR;
        this.height=Parametre.HAUTEUR;
        this.id=new SimpleIntegerProperty(id);
        this.persoMapActu =FXCollections.observableArrayList();
        this.decors = new ArrayList<>();
        this.mapActuelle= new MapModele(nomMap);
        this.decors.add(mapActuelle);
        lesPersos=new ArrayList<>();
        objEnvAct=FXCollections.observableArrayList();
        objetEnvironnement=new ArrayList<>();
    }

    public Environnement(int width, int height, int id, String nomMap, Link user){
        this.width=width;
        this.height=height;
        this.id=new SimpleIntegerProperty(id);
        this.persoMapActu =FXCollections.observableArrayList();
        this.decors = new ArrayList<>();
        this.mapActuelle= new MapModele(nomMap);
        this.decors.add(mapActuelle);
        lesPersos=new ArrayList<>();
        this.user=user;
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
    public ObservableList<Ennemi> getEnnemiMap(){ // méthode non utilisé pour l'instant
        return persoMapActu;
    }

    public ArrayList<Ennemi> getEnnemi() {
        return lesPersos;
    }

    public MapModele getMapActuelle() {
        return mapActuelle;
    }

    public Link getLink() {
        return this.user;
    }

    public Odelin getBoss() {
        return this.boss;
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
     * Permet de retirer un personnage à la liste de ceux présents dans l'envrionnement.
     * @param p
     */
    public void deletePerso(Personnage p){ // méthode non utilisé pour l'instant
        persoMapActu.remove(p);
        lesPersos.remove(p);
    }

    /**
     * Vide le contenu de la liste d'ennemi de la map actuelle
     */
    public void deleteAllPerso(){
        for (int i = 0; i< persoMapActu.size(); i++){
            persoMapActu.remove(persoMapActu.get(i));
        }
    }

    public void init() {
        creationLink();
        creationOdelin();
    }

    /**
     * Initialisation de Link
     */
    public void creationLink() {
        BaguetteMagique baguette = new BaguetteMagique("Elder Wand", 30);
        Epe epe = new Epe("Excalibur", 10);
        this.user = new Link(this, epe, baguette);
    }

    /**
     * Initialisation du Boss
     */
    public void creationOdelin() {
        this.boss = new Odelin (480, 512, this, "map6");
        lesPersos.add(this.boss);
    }

    /**
     * Fait interragir les perso et les animations
     */
    public void faireUntour() {
        this.user.getarmeSecondaire().lancerBouleDeFeu();
        this.user.declencherAnimation();
        cibleTouche();
        retirerBouleDeFeu();
        for (Personnage p : this.persoMapActu) {
            if (p instanceof Ennemi) {
                ((Ennemi)p).agir();
            }
        }
    }

    /**
     * Retire une boule de feu de la liste si elle est entrain de se désintégrer
     */
    public void retirerBouleDeFeu() {
        this.user.getarmeSecondaire().getBoules().removeIf(BouleDeFeu::seDesintegre);
    }

    /**
     * Retire tous les squelette d'une map
     */
    public void retirerSquelette(){
        if(lesPersos.size()>0){
            if(persoMapActu.size()>0){
                for(int i=0;i<persoMapActu.size();i++){
                    if(this.getEnnemiMap().get(i) instanceof Squelette){
                        persoMapActu.remove(getEnnemiMap().get(i));
                    }
                }
            }
        }
    }


    public void cibleTouche() { // Boucle For each ne marche pas

        if(lesPersos.size()>0){
            if(persoMapActu.size()>0){
                for(int a=0;a<persoMapActu.size();a++){
                    for (int i=0; i< this.user.getarmeSecondaire().getBoules().size(); i++) {
                        Coordonnees centre = this.user.getarmeSecondaire().getBoules().get(i).getCoor();
                        BouleDeFeu boule = this.user.getarmeSecondaire().getBoules().get(i);
                        // Attention les coordonées des perso sont décalés de 1 sur les colonnes
                        if(persoMapActu.get(a).getCoor().isInside(1, centre))
                        {
                            persoMapActu.get(a).perteDePv(this.user.getDommageArmeSecondaire());
                            this.user.getarmeSecondaire().getBoules().remove(boule);
                            if(persoMapActu.get(a).retirerEnv()){
                                break;
                            }
                        }
                    }
                }
            }
        }


    }
    public void chargerTousLesObj(){
        try{
            Key key=new Key(1000,480);
            if(key.positionL().getValue()>1200 || key.positionL().getValue()<0 || key.positionH().getValue()>1200 ||key.positionH().getValue()<0) {
                throw new NombreInvalide();
            }
            Potion potion=new Potion(520,608,"map1");;
            Rocher rocher =new Rocher(392,608,"map1");
            Rocher rocher1=new Rocher(72,448,"map1");
            Rocher rocher2=new Rocher(72,416,"map1");
            Rocher rocher3=new Rocher(72,384,"map1");
            Rocher rocher4=new Rocher(72,352,"map1");
            Rocher rocher5=new Rocher(72,320,"map1");
            Pioche pioche=new Pioche(840,160,"map1");
            Arbre arbre=new Arbre(488,160,3,"map1");
            Coquillage coquillage=new Coquillage("coquillage",1160,64);
            Bouclier bouclier=new Bouclier(200,352);
            PersoNonJouable pnj=new PersoNonJouable(1000,480,key,"bob","map1");
            PersoNonJouable pn=new PersoNonJouable(1160,64,coquillage,"jacob","map3");
            Coffre coffre=new Coffre(bouclier,200,352,"map2");
            objetEnvironnement.add(arbre);
            objetEnvironnement.add(pnj);
            objetEnvironnement.add(potion);
            objetEnvironnement.add(rocher);
            objetEnvironnement.add(rocher1);
            objetEnvironnement.add(rocher2);
            objetEnvironnement.add(rocher3);
            objetEnvironnement.add(rocher4);
            objetEnvironnement.add(rocher5);
            objetEnvironnement.add(pioche);
            objetEnvironnement.add(pn);
            objetEnvironnement.add(coffre);
        }catch (NombreInvalide nombreInvalide){
            nombreInvalide.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void chargerObjMap(String nomMap){
        for(ElementMap elMap: objetEnvironnement){
            if(elMap.getMapAction()==nomMap ){
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
    public void miseEnPlaceEnnemi(){
        Squelette squelette=new Squelette(600,600,this, "map3");
        Squelette squelette1=new Squelette(984,600,this, "map3");
        Squelette squelette2=new Squelette(120,600,this, "map3");
        Soldat soldat1 = new Soldat(this, 40, 104, "map2");
        lesPersos.add(squelette);
        lesPersos.add(squelette1);
        lesPersos.add(squelette2);
        lesPersos.add(soldat1);
    }
    public void chargerEnnemiMap(String nomMap){
        if(lesPersos.size()>0){
            for(int i=0;i<lesPersos.size();i++){
                if (lesPersos.get(i).getMapAction() == nomMap) {
                    persoMapActu.add(lesPersos.get(i));
                }
            }
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
    public int taillePersoMapActu(){
        int taille=0;
        for(Personnage p:persoMapActu){
            taille++;
        }
        return taille;
    }

    public HashMap<Coordonnees, Coordonnees> bfs(Coordonnees depart) {
        Queue<Coordonnees> frontiere = new ArrayDeque<Coordonnees>();
        ArrayList<Coordonnees> marquer = new ArrayList<Coordonnees>();
        frontiere.add(depart);
        HashMap<Coordonnees, Coordonnees> antecedent =new HashMap<Coordonnees, Coordonnees>();
        antecedent.put(depart, null);
        marquer.add(depart);

        while (!frontiere.isEmpty()) {
            Coordonnees pointCourant = frontiere.poll();


                for (Coordonnees voisins : pointCourant.voisins()) {
                    if ( !marquer.contains(voisins)  && this.mapActuelle.getTableau()[voisins.getLigne()][voisins.getColonne()]==1) {
                        frontiere.add(voisins);
                        antecedent.put(voisins, pointCourant);
                        marquer.add(voisins);
                    }
                }
            }
        return antecedent;
    }

}