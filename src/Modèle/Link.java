package Modèle;

import javafx.scene.input.KeyEvent;

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
        int positionX = (int) (getX()/32);
        int positionY = (int) (getY()/32);
        if (carte[positionX+1][positionY] == 2) {//deplacement vers la droite bloquer

        }
    }

    public void monter() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if (carte[(int) (getX()/32)][(int) (getY()/32)] == 1) {//deplacement vers le haut bloquer
            setY(getY() - 4);
            System.out.println("case du tableau: " + carte[(int) (getX()/32)][(int) (getY()/32)]);
            System.out.println("coordonées réels: " + getX() + " " + getY());
        }
        else {
            System.out.println("Collision en "+ getX()/32 + " " + getY()/32);
           // System.out.println("pas de recul");
            //descendre();
        }
    }
    public void descendre() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if (carte[(int) (getX()/32)][(int) (getY()/32)+1] == 1) {//deplacement vers le bas bloquer
            setY(getY() + 4);
            System.out.println("case du tableau: " + carte[(int) (getX()/32)][(int) (getY()/32)]);
            System.out.println("coordonées réels: " + getX() + " " + getY());
        }
        else {
            System.out.println("Collision en "+ getX()/32 + " " + getY()/32);
            // System.out.println("pas de recul");
            //descendre();
        }


    }
    public void gauche() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if (carte[(int) (getX()/32)][(int) (getY()/32)] == 1) {//deplacement vers la gauche bloquer
            setX(getX() - 4);
        }
        else {
            System.out.println("Collision en "+ getX()/32 + " " + getY()/32);
            //System.out.println("pas de recul");
            //droite();
        }
    }
    public void droite() {
        int[][] carte = getEnv().getCoordonneDecors(getEnv().getNomMapCourante());
        if (carte[(int) (getX()/32)+1][(int) (getY()/32)] == 1) {//deplacement vers droite bloquer
            setX(getX() + 4);
        }
        else {
            System.out.println("Collision en "+ getX()/32 + " " + getY()/32);
            // System.out.println("pas de recul");
            //descendre();
        }
    }

}
