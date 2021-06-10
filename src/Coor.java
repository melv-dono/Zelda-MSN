import Modèle.Parametre;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Coor {
    private int colonne;
    private int ligne;
    private boolean isMarked; // Marque si le point a été visité
    private static ArrayList<Coor> tree = new ArrayList<Coor>(); // Fait office de graphe sous la forme d'un arbre

    public Coor(int c, int l) {
        this.colonne=c;
        this.ligne=l;
        this.isMarked = false;

    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public static void setTree(Coor c) {
        tree.add(c);
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void marked() {
        this.isMarked = true;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public boolean isEqual(Coor c) {
        return c.colonne == this.colonne && c.ligne == this.ligne;
    }

    /**
     * Crée progressivement une liste des points adjacents.
     * Reutilise les points du graphe déjà connu s'il y en a.
     * Cree de nouveau voisins s'il n'exsite pas encore.
     * Les nouveaux voisins sont automatiquement insérés au graphe.
     * @return La liste des points adjacents au point courant.
     */

    public ArrayList<Coor> voisins() {
        Coor haut, bas ,gauche, droite;
        ArrayList<Coor> voisins = new ArrayList<Coor>();
        boolean dejaAjouter = false; //Permet de vérifier si le point n'existe pas déjà dans la liste

        if (this.ligne -1 >= 0) { // Vérifie que les coordonnées du point ne sortent pas de la bordure
            haut = new Coor(this.colonne, this.ligne-1);
            for (Coor c : tree ) { // Regarde sur le graphe si le point n'existe pas déjà
                if (haut.isEqual(c)) {
                    voisins.add(c);
                    dejaAjouter = true;
                }
            }
            if (!dejaAjouter) { // Cas ou il s'agit d'un nouveau point
                voisins.add(haut);
                tree.add(haut);
            }
            dejaAjouter =false;

        }

        if (this.colonne+1 < 4) {
            droite = new Coor(this.colonne+1, this.ligne);
            for (Coor c : tree ) {
                if (droite.isEqual(c)) {
                    voisins.add(c);
                    dejaAjouter = true;
                }
            }
            if (!dejaAjouter) {
                voisins.add(droite);
                tree.add(droite);
            }
            dejaAjouter =false;

        }

        if (this.ligne+1 < 4) {
            bas = new Coor(this.colonne, this.ligne+1);
            for (Coor c : tree ) {
                if (bas.isEqual(c)) {
                    voisins.add(c);
                    dejaAjouter = true;
                }
            }
            if (!dejaAjouter) {
                voisins.add(bas);
                tree.add(bas);
            }
            dejaAjouter =false;

        }

        if (this.colonne - 1 >=0) {
            gauche = new Coor(this.colonne-1, this.ligne);
            for (Coor c : tree ) {
                if (gauche.isEqual(c)) {
                    voisins.add(c);
                    dejaAjouter = true;
                }
            }
            if (!dejaAjouter) {
                voisins.add(gauche);
                tree.add(gauche);
            }
        }

        return voisins;

    }

    @Override
    public String toString() {
        return "(" +
                colonne +
                "," +
                ligne +
                ')';
    }

    public static HashMap<Coor, Coor> bfs(int[][] donnne) {
        Queue<Coor> frontiere = new ArrayDeque<Coor>();
//        ArrayList<HashMap<Coor, Coor>> provenances = new ArrayList<HashMap<Coor, Coor>>();
        Coor depart = new Coor(0,3);
        Coor.setTree(depart);
        frontiere.add(depart);
        HashMap<Coor, Coor> antecedent =new HashMap<Coor, Coor>();
        antecedent.put(depart, null);
//        provenances.add(origine);
        int cpt=0;

        while (!frontiere.isEmpty()) {

            System.out.println("Voisins n° " + cpt + " Queue: " + frontiere);
            Coor pointCourant = frontiere.poll();
            pointCourant.marked();
            System.out.println("GET " + pointCourant);

            if (donnne[pointCourant.getLigne()][pointCourant.getColonne()] == 8) {
                System.out.println("Trouvé");
                break;
            }
            else {

                for (Coor voisins : pointCourant.voisins()) {
                    if (!voisins.isMarked() && !antecedent.containsKey(voisins)) {
                        frontiere.add(voisins);
//                        lien.put(voisins, pointCourant);
                        antecedent.put(voisins, pointCourant);
                    }
                }
            }
            cpt++;
        }
        return antecedent;
    }
}