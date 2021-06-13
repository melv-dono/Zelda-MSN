package Modèle;

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

    private ArrayList<Ennemi>lesPersos;// Représente la liste de tous les personnages présent dans l'environnement.
    private ObservableList<Ennemi> persoMapActu;

    private ArrayList<MapModele> decors; // Permet de faire l'historique de tous les éléments de décors présents au sein de l'environnement.

    private ArrayList<ElementMap> objetEnvironnement; // Liste de tous les objets qui seront ramassable,trouvable dans un coffre ou donné par un PNJ
    private ObservableList<ElementMap>objEnvAct; // <liste des objets de la map où on se trouve

    private MapModele mapActuelle; // La mapActuelle contient les données concernant la map courante sur laquelle se tient le perso c'est à dire celle du TilePane.
    private Link user;
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
    public ObservableList<Ennemi>getPerso(){ // méthode non utilisé pour l'instant
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
        return this.user;
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



    public void init() {
        creationLink();
        //creeEnnemi(); // Attention je l'ai mis dès le début uniquement car je suis sur la map de base
    }

    public void creationLink() {
        BaguetteMagique baguette = new BaguetteMagique("Elder Wand", 30);
        Epe epe = new Epe("Excalibur", 10);
        if(id.getValue()==1) {
            this.user = new Link(this, epe, baguette);
        }
//        addPerso(this.user);
    }


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

        if(this.user.getExp() > 1){
            this.user.setExp(0);
            this.user.niveau().set(this.user.getNiveau()+1);
        }

    }

    public void retirerBouleDeFeu() {
        this.user.getarmeSecondaire().getBoules().removeIf(BouleDeFeu::seDesintegre);
    }
    public void retirerSquelette(){
        if(lesPersos.size()>0){
            if(persoMapActu.size()>0){
                for(int i=0;i<persoMapActu.size();i++){
                    if(this.getPerso().get(i) instanceof Squelette){
                        persoMapActu.remove(getPerso().get(i));
                    }
                }
            }

        }

    }


    public void cibleTouche() { // Boucle For each ne marche pas
        double haut, bas, gauche, droite;

        if(lesPersos.size()>0){
            if(persoMapActu.size()>0){
                for(int a=0;a<persoMapActu.size();a++){
                    for (int i=0; i< this.user.getarmeSecondaire().getBoules().size(); i++) {
                        haut= this.user.getarmeSecondaire().getBoules().get(i).getyProperty()-16;
                        bas= this.user.getarmeSecondaire().getBoules().get(i).getyProperty()+16;
                        gauche= this.user.getarmeSecondaire().getBoules().get(i).getxProperty()-5;
                        droite= this.user.getarmeSecondaire().getBoules().get(i).getxProperty()+5;

                        if(		(persoMapActu.get(a).getDeplacementHauteur() >= haut && persoMapActu.get(a).getDeplacementHauteur() <= bas) &&
                                (persoMapActu.get(a).getDeplacementLargeur() >= gauche && persoMapActu.get(a).getDeplacementLargeur() <= droite) &&
                                persoMapActu.get(a) instanceof Squelette
                        )
                        {
                            persoMapActu.get(a).perteDePv(this.user.getDommageArmeSecondaire());
                            if(persoMapActu.get(a).retirerEnv()==true){
                                break;
                            }
                            this.user.getarmeSecondaire().getBoules().remove(this.user.getarmeSecondaire().getBoules().get(i));
                        }
                    }
                }
            }
        }


    }
    public void chargerTousLesObj(){
        try{
            Key key=new Key(1000,480);
            if(key.getPositionLargeur().getValue()>1200 || key.getPositionLargeur().getValue()<0 || key.getPositionHauteur().getValue()>1200 ||key.getPositionHauteur().getValue()<0) {
                throw new NombreInvalide();
            }
            PersoNonJouable pnj=new PersoNonJouable(1000,480,key,"bob","map1");
            objetEnvironnement.add(pnj);
        }catch (NombreInvalide nombreInvalide){
            nombreInvalide.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
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
        PersoNonJouable pn=new PersoNonJouable(1160,64,coquillage,"jacob","map3");
        Bouclier bouclier=new Bouclier(200,352);
        Coffre coffre=new Coffre(bouclier,200,352,"map2");
        objetEnvironnement.add(potion);
        objetEnvironnement.add(rocher);
        objetEnvironnement.add(rocher1);
        objetEnvironnement.add(rocher2);
        objetEnvironnement.add(rocher3);
        objetEnvironnement.add(rocher4);
        objetEnvironnement.add(rocher5);
        objetEnvironnement.add(pioche);
        objetEnvironnement.add(arbre);
        objetEnvironnement.add(pn);
        objetEnvironnement.add(coffre);

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
        Soldat soldat1 = new Soldat("soldat", this, 40, 104, "map2");
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
                    if ( !marquer.contains(voisins)  && this.mapActuelle.getTableau()[voisins.getLigne()][voisins.getColonne()]==1) {
                        frontiere.add(voisins);
//                        lien.put(voisins, pointCourant);
                        antecedent.put(voisins, pointCourant);
                        marquer.add(voisins);
                    }
                }
            }
        return antecedent;
    }

}