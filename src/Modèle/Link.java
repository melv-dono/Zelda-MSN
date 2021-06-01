package Modèle;

public class Link extends Personnage{

    private String orientation;

    public Link(Environnement e) {
        super("Link", e);
        //this.orientation.equals("descendre");
        orientation="descendre";
    }

    public String getOrientation(){
        return this.orientation;
    }

    public void sens(String s){
        this.orientation = s;
    }

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
        }else if (carte[(int) ((getDeplacementHauteur()/32)-1)][(int) (getDeplacementLargeur()/32)] == 1) {//deplacement vers le haut bloquer
            setDeplacementHauteur(getDeplacementHauteur() - Parametre.PAS);
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
        }else if (carte[(int) ((getDeplacementHauteur()/Parametre.TUILE_SIZE)+1)][(int) (getDeplacementLargeur()/Parametre.TUILE_SIZE)] == 1)  {//deplacement vers le bas bloquer
            setDeplacementHauteur(getDeplacementHauteur() + Parametre.PAS);
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
        }else if (carte[(int) (getDeplacementHauteur()/32)][(int) ((getDeplacementLargeur()/Parametre.TUILE_SIZE)-1)] == 1) {
            setDeplacementLargeur(getDeplacementLargeur() - Parametre.PAS);
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
        }else if (carte[(int) (getDeplacementHauteur()/Parametre.TUILE_SIZE)][(int) ((getDeplacementLargeur()/Parametre.TUILE_SIZE)+1)] == 1) {
            setDeplacementLargeur(getDeplacementLargeur() + Parametre.PAS);
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

}
