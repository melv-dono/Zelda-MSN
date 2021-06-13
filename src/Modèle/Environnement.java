package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement { // Toutes les méthodes de cette classe ne sont pas encore utilisé dans le code
    private int width,height; // largeur == width - hauteur == height
    private IntegerProperty id;

    private ArrayList<Personnage>lesPersos;// Représente la liste de tous les personnages présent dans l'environnement.
    private ObservableList<Personnage> persoMapActu;
    
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
    public Environnement(int id, String nomMap){
        this.width=Parametre.LARGEUR;
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

    public Environnement(int width, int height, int id, String nomMap, Link utilisateur){
        this.width=width;
        this.height=height;
        this.id=new SimpleIntegerProperty(id);
        this.persoMapActu =FXCollections.observableArrayList();
        this.decors = new ArrayList<>();
        this.mapActuelle= new MapModele(nomMap);
        this.decors.add(mapActuelle);
        lesPersos=new ArrayList<>();
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
    public ObservableList<Personnage>getPerso(){ // méthode non utilisé pour l'instant
        return persoMapActu;
    }
    /**
     * Renvoie le personnage dont l'id correspond à celui rentré en paramètre.
     * @param id
     * @return un personnage précis
     */
    public Personnage getPersonnage(int id){ // méthode non utilisé pour l'instant
        for(Personnage p: persoMapActu){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    public ObservableList<Personnage> getPersoMapActu() {
        return persoMapActu;
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
     * Permet de retirer un personnage à la liste de ceux présents dans l'envrionnement.
     * @param p
     */
    public void deletePerso(Personnage p){ // méthode non utilisé pour l'instant
        persoMapActu.remove(p);
        lesPersos.remove(p);
    }
    public void deleteAllPerso(){
        for (int i = 0; i< persoMapActu.size(); i++){
            persoMapActu.remove(persoMapActu.get(i));
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


    public void initLink() {
        if(id.getValue()==1){
            this.utilisateur = new Link(this);
        }
        BaguetteMagique baguette = new BaguetteMagique("Elder Wand", 30);
        Epe epe = new Epe("Excalibur", 10, this.utilisateur);
        this.utilisateur.setArmePrincipale(epe);
        this.utilisateur.setArmeSecondaire(baguette);
    }


    public void faireUntour() {
        this.utilisateur.getarmeSecondaire().lancerBouleDeFeu();
        this.utilisateur.declencherAnimation();
        cibleTouche();
        retirerBouleDeFeu();
        for (Personnage p : this.persoMapActu) {
            if (p instanceof Squelette) {
                ((Squelette)p).animationSquelette1(this);
            }
        }
        for(Personnage p: persoMapActu){
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

        if(lesPersos.size()>0){
            if(persoMapActu.size()>0){
                for(int a=0;a<persoMapActu.size();a++){
                    for (int i=0; i< this.utilisateur.getarmeSecondaire().getBoules().size(); i++) {
                        haut= this.utilisateur.getarmeSecondaire().getBoules().get(i).getyProperty()-16;
                        bas= this.utilisateur.getarmeSecondaire().getBoules().get(i).getyProperty()+16;
                        gauche= this.utilisateur.getarmeSecondaire().getBoules().get(i).getxProperty()-5;
                        droite= this.utilisateur.getarmeSecondaire().getBoules().get(i).getxProperty()+5;

                        if(		(persoMapActu.get(a).getDeplacementHauteur() >= haut && persoMapActu.get(a).getDeplacementHauteur() <= bas) &&
                                (persoMapActu.get(a).getDeplacementLargeur() >= gauche && persoMapActu.get(a).getDeplacementLargeur() <= droite) &&
                                persoMapActu.get(a) instanceof Squelette
                        )
                        {
                            persoMapActu.get(a).perteDePv(this.utilisateur.getDommageArmeSecondaire());
                            if(persoMapActu.get(a).retirerEnv()==true){
                                break;
                            }
                            this.utilisateur.getarmeSecondaire().getBoules().remove(this.utilisateur.getarmeSecondaire().getBoules().get(i));
                        }
                    }
                }
            }
        }


      /*  for (Personnage ennemi : persoMapActu) {

        }*/
    }
    public void chargerTousLesObj(){
        Potion potion=new Potion(520,608);;
        Rocher rocher =new Rocher(392,608);
        Rocher rocher1=new Rocher(72,448);
        Rocher rocher2=new Rocher(72,416);
        Rocher rocher3=new Rocher(72,384);
        Rocher rocher4=new Rocher(72,352);
        Rocher rocher5=new Rocher(72,320);
        Pioche pioche=new Pioche(840,160);
        Arbre arbre=new Arbre(488,160,3);
        Key key=new Key(1000,480);
        PersoNonJouable pnj=new PersoNonJouable(1000,480,key);
        Bouclier bouclier=new Bouclier(200,352);
        Coffre coffre=new Coffre(bouclier,200,352);
        objetEnvironnement.add(potion);
        System.out.println(objetEnvironnement);
        objetEnvironnement.add(rocher);
        objetEnvironnement.add(rocher1);
        objetEnvironnement.add(rocher2);
        objetEnvironnement.add(rocher3);
        objetEnvironnement.add(rocher4);
        objetEnvironnement.add(rocher5);
        objetEnvironnement.add(pioche);
        objetEnvironnement.add(arbre);
        objetEnvironnement.add(pnj);
        objetEnvironnement.add(coffre);

    }
    public void chargerObjMap1(){
        System.out.println(objetEnvironnement);
        for(ElementMap elMap: objetEnvironnement){
            if(!(elMap instanceof Coffre)){
                objEnvAct.add(elMap);
                System.out.println("ajout de"+elMap);
            }
        }
    }
    public void setUpSecondMap(){
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
    public void miseEnPlaceEnnemi(){
        Squelette squelette=new Squelette(423,600,this);
        //Squelette squelette1=new Squelette(600,600,this);
        lesPersos.add(squelette);
        //lesPersos.add(squelette1);
    }
    public void chargerEnnemiMap(){
        if(lesPersos.size()>0){
            for(int i=0;i<lesPersos.size();i++){
                persoMapActu.add(lesPersos.get(i));
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


}