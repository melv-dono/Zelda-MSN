package Modèle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Link extends Personnage{

    private String orientation;

    private Arme armePrincipale;
    private BaguetteMagique armeSecondaire;

    public Link(Environnement e) {
        super("Link", e, 100, 10, 0);
        //this.orientation.equals("descendre");
        orientation="descendre";
    }

    public String getOrientation(){
        return this.orientation;
    }

    public void sens(String s){
        this.orientation = s;
    }

    public Arme getArmePrincipale() {
        return armePrincipale;
    }

    public BaguetteMagique getarmeSecondaire() {return armeSecondaire;}

    public void setArmePrincipale(Arme armePrincipale) {
        this.armePrincipale = armePrincipale;
    }

    public void setArmeSecondaire(BaguetteMagique b) {this.armeSecondaire = b;}

    /**
     * Déplace Link vers le haut
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void monter() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if((getDeplacementHauteur()/32)-1<0){
            System.out.println("bordure de map");
            collisionAffCoord(carte);
        }else if (carte[(int) ((getDeplacementHauteur()/Parametre.TUILE_SIZE)-1)][(int) (getDeplacementLargeur()/Parametre.TUILE_SIZE)] == 1&&collisionExterneEnv(getDeplacementLargeur(),getDeplacementHauteur()-Parametre.TUILE_SIZE)==true) {//deplacement vers le haut bloquer
            setDeplacementHauteur(getDeplacementHauteur() - Parametre.TUILE_SIZE);
            depAffCoord(carte);
        }
        else {
            collisionAffCoord(carte);
        }
        this.orientation = "monter";
    }

    /**
     * Déplace Link vers le bas
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void descendre() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if(((getDeplacementHauteur()/Parametre.TUILE_SIZE)+1)>=Parametre.LIGNE){
            System.out.println("bordure de map");
            collisionAffCoord(carte);
        }else if (carte[(int) ((getDeplacementHauteur()/Parametre.TUILE_SIZE)+1)][(int) (getDeplacementLargeur()/Parametre.TUILE_SIZE)] == 1&&collisionExterneEnv(getDeplacementLargeur(),getDeplacementHauteur()+Parametre.TUILE_SIZE)==true)  {//deplacement vers le bas bloquer
            setDeplacementHauteur(getDeplacementHauteur() + Parametre.TUILE_SIZE);
            depAffCoord(carte);
        }
        else {
            collisionAffCoord(carte);
        }
        this.orientation = "descendre";
    }

    /**
     * Déplace Link vers la gauche
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void gauche() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if((getDeplacementLargeur()/Parametre.TUILE_SIZE)-1<0){
            System.out.println("bordure de map");
            collisionAffCoord(carte);
        }else if (carte[(int) (getDeplacementHauteur()/Parametre.TUILE_SIZE)][(int) ((getDeplacementLargeur()/Parametre.TUILE_SIZE)-1)] == 1&&collisionExterneEnv(getDeplacementLargeur()-Parametre.TUILE_SIZE,getDeplacementHauteur())==true) {
            setDeplacementLargeur(getDeplacementLargeur() - Parametre.TUILE_SIZE);
            depAffCoord(carte);
        }
        else {
            collisionAffCoord(carte);
        }
        this.orientation = "gauche";
    }

    /**
     * Déplace Link vers la droite
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void droite() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if(((getDeplacementLargeur()/Parametre.TUILE_SIZE)+1)>=40){
            System.out.println("bordure de map");
            collisionAffCoord(carte);
        }else if (carte[(int) (getDeplacementHauteur()/Parametre.TUILE_SIZE)][(int) ((getDeplacementLargeur()/Parametre.TUILE_SIZE)+1)] == 1&&collisionExterneEnv(getDeplacementLargeur()+Parametre.TUILE_SIZE,getDeplacementHauteur())==true) {
            setDeplacementLargeur(getDeplacementLargeur() + Parametre.TUILE_SIZE);
            depAffCoord(carte);
        }
        else {
            collisionAffCoord(carte);
        }
        this.orientation = "droite";
    }

    /**
     * Affiche la position de Link sur le tableau mais aussi dans l'environnement.
     * @param carte
     */
    private void depAffCoord(int[][]carte){
        System.out.println("case du tableau: " + carte[(int) (getDeplacementHauteur()/Parametre.TUILE_SIZE)][(int) (getDeplacementLargeur()/Parametre.TUILE_SIZE)]);
        System.out.println("coordonées réels: " + getDeplacementHauteur() + " " + getDeplacementLargeur());
        System.out.println();
    }

    /**
     * Affiche la position d'un point de collision (tableau de donnée + envirionnement).
     * @param carte
     */
    private void collisionAffCoord(int [][]carte){
        System.out.println("Collision en "+ getDeplacementHauteur()/Parametre.TUILE_SIZE + " " + getDeplacementLargeur()/Parametre.TUILE_SIZE);
        System.out.println("case du tableau: " + carte[(int) (getDeplacementHauteur()/Parametre.TUILE_SIZE)][(int) (getDeplacementLargeur()/Parametre.TUILE_SIZE)]);
        System.out.println("coordonées réels: " + getDeplacementHauteur() + " " + getDeplacementLargeur());
        System.out.println();
    }

    /**
     * Affiche la position de link sur le tableau de donnée.
     * @param carte
     */
    private void posTab(int [][]carte){
        int cmptX=-1;
        int cmptY;
        for(int i=0;i<Parametre.LIGNE;i++){
            cmptX++;
            cmptY=0;
            System.out.print("Ligne"+(cmptX+1)+" : ");
            for(int j=0;j<Parametre.COLONNE;j++){
                if((getDeplacementHauteur()/Parametre.TUILE_SIZE)==cmptX&&(getDeplacementLargeur()/32)==cmptY){
                    System.out.print("*");
                }else{
                    System.out.print(carte[i][j]);
                }
                cmptY++;
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    public boolean cibleAtteignable(Personnage perso) {
        if(		(this.getDeplacementHauteur()-32<= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.getDeplacementHauteur()+32) &&
                (this.getDeplacementLargeur()-32<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur()+32)
        )
        {
            return true;
        }
        return false;
    }

    public boolean cibleSurLaGauche(Personnage perso) {
        if(		(this.getDeplacementHauteur()+Parametre.TUILE_SIZE>= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()>=this.getDeplacementHauteur()) &&
                (this.getDeplacementLargeur()-Parametre.TUILE_SIZE-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur())
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleSurLaDroite(Personnage perso) {
        if( // Attention on a enlevé 1 car le squelette était pile sur la bordure de la tuile
                (this.getDeplacementHauteur()+Parametre.TUILE_SIZE>= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.getDeplacementHauteur()) &&
                (this.getDeplacementLargeur()+Parametre.TUILE_SIZE-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur()+(Parametre.TUILE_SIZE*2)-1)
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleEnHaut(Personnage perso) {
        if(
                (this.getDeplacementHauteur()-Parametre.TUILE_SIZE<= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.getDeplacementHauteur()) &&
                (this.getDeplacementLargeur()-Parametre.TUILE_SIZE<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur())
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleEnBas(Personnage perso) {
        if( // Attention on a enlevé 1 car le squelette était pile sur la bordure de la tuile
                (this.getDeplacementHauteur() + Parametre.TUILE_SIZE <= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.getDeplacementHauteur()+(Parametre.TUILE_SIZE*2)) &&
                (this.getDeplacementLargeur()-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur()+Parametre.TUILE_SIZE-1)
        )
        {
            return true;
        }
        else {return false;}
    }

    public void attaquer() {// Attaque de zone
        double dommage = getPointAttaque() + this.armePrincipale.getPointAttaque();
        for (Personnage p : getEnv().getPerso()) {
            if (p instanceof Squelette && this.orientation =="monter") {
                if (cibleEnHaut(p)) {
                    p.perteDePv(dommage);
                }
            }
            if (p instanceof Squelette && this.orientation =="descendre") {
                System.out.println(this.getDeplacementHauteur() + Parametre.TUILE_SIZE);
                System.out.println(this.getDeplacementHauteur()+(Parametre.TUILE_SIZE*2));

                System.out.println("y du perso:");
                System.out.println(p.getDeplacementHauteur());
                if (cibleEnBas(p)) {
                    p.perteDePv(dommage);
                }
            }
            if (p instanceof Squelette && this.orientation =="gauche") {

                if (cibleSurLaGauche(p)) {
                    p.perteDePv(dommage);
                }
            }
            if (p instanceof Squelette && this.orientation =="droite") {
                if (cibleSurLaDroite(p)) {
                    p.perteDePv(dommage);
                }
            }
        }
    }

}
