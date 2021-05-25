package Modèle;

public class Link extends Personnage{
    public Link(Environnement e) {
        super("Link", e);
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
            setDeplacementHauteur(getDeplacementHauteur() - 32);
            depAffCoord(carte);
        }
        else {
            collisionAffCoord(carte);
        }
    }

    /**
     * Déplace Link vers le bas
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void descendre() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if(((getDeplacementHauteur()/32)+1)>=23){
            System.out.println("bordure de map");
            collisionAffCoord(carte);
        }else if (carte[(int) ((getDeplacementHauteur()/32)+1)][(int) (getDeplacementLargeur()/32)] == 1)  {//deplacement vers le bas bloquer
            setDeplacementHauteur(getDeplacementHauteur() + 32);
            depAffCoord(carte);
        }
        else {
            collisionAffCoord(carte);
        }
    }

    /**
     * Déplace Link vers la gauche
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void gauche() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if((getDeplacementLargeur()/32)-1<0){
            System.out.println("bordure de map");
            collisionAffCoord(carte);
        }else if (carte[(int) (getDeplacementHauteur()/32)][(int) ((getDeplacementLargeur()/32)-1)] == 1) {
            setDeplacementLargeur(getDeplacementLargeur() - 32);
            depAffCoord(carte);
        }
        else {
            collisionAffCoord(carte);
        }
    }

    /**
     * Déplace Link vers la droite
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void droite() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if(((getDeplacementLargeur()/32)+1)>=40){
            System.out.println("bordure de map");
            collisionAffCoord(carte);
        }else if (carte[(int) (getDeplacementHauteur()/32)][(int) ((getDeplacementLargeur()/32)+1)] == 1) {
            setDeplacementLargeur(getDeplacementLargeur() + 32);
            depAffCoord(carte);
        }
        else {
            collisionAffCoord(carte);
        }
    }

    /**
     * Affiche la position de Link sur le tableau mais aussi dans l'environnement.
     * @param carte
     */
    private void depAffCoord(int[][]carte){
        System.out.println("case du tableau: " + carte[(int) (getDeplacementHauteur()/32)][(int) (getDeplacementLargeur()/32)]);
        System.out.println("coordonées réels: " + getDeplacementHauteur() + " " + getDeplacementLargeur());
        System.out.println();
    }

    /**
     * Affiche la position d'un point de collision (tableau de donnée + envirionnement).
     * @param carte
     */
    private void collisionAffCoord(int [][]carte){
        System.out.println("Collision en "+ getDeplacementHauteur()/32 + " " + getDeplacementLargeur()/32);
        System.out.println("case du tableau: " + carte[(int) (getDeplacementHauteur()/32)][(int) (getDeplacementLargeur()/32)]);
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
        for(int i=0;i<23;i++){
            cmptX++;
            cmptY=0;
            System.out.print("Ligne"+(cmptX+1)+" : ");
            for(int j=0;j<40;j++){
                if((getDeplacementHauteur()/32)==cmptX&&(getDeplacementLargeur()/32)==cmptY){
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
