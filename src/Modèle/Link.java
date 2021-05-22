package Modèle;

public class Link extends Personnage{
    public Link(Environnement e) {
        super("Link", e);
    }

    /**
     * Cette méthode permet de connaître la position du personnage sur une map
     * En fonction de si le personnage est sur un obstacle @Deplacement peut être true ou false
     * @return boolean
     */
    public void position() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        int positionX = (int) (getDeplacementHauteur()/32);
        int positionY = (int) (getDeplacementLargeur()/32);
/*        if (carte[positionX+1][positionY] == 2) {//deplacement vers la droite bloquer

        }*/
    }

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
    private void depAffCoord(int[][]carte){
        System.out.println("case du tableau: " + carte[(int) (getDeplacementHauteur()/32)][(int) (getDeplacementLargeur()/32)]);
        System.out.println("coordonées réels: " + getDeplacementHauteur() + " " + getDeplacementLargeur());
        System.out.println();
    }
    private void collisionAffCoord(int [][]carte){
        System.out.println("Collision en "+ getDeplacementHauteur()/32 + " " + getDeplacementLargeur()/32);
        System.out.println("case du tableau: " + carte[(int) (getDeplacementHauteur()/32)][(int) (getDeplacementLargeur()/32)]);
        System.out.println("coordonées réels: " + getDeplacementHauteur() + " " + getDeplacementLargeur());
        System.out.println();
    }
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
