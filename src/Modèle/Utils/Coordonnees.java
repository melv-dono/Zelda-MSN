package Modèle.Utils;

import java.util.ArrayList;

import java.util.Objects;

public class Coordonnees {
    private int colonne;
    private int ligne;
    private boolean isMarked; // Marque si le point a été visité
    private static ArrayList<Coordonnees> tree = new ArrayList<Coordonnees>(); // Fait office de graphe sous la forme d'un arbre

    public Coordonnees(int c, int l) {
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

    public static void setTree(Coordonnees c) {
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

    public boolean isEqual(Coordonnees c) {
        return c.colonne == this.colonne && c.ligne == this.ligne;
    }

    /**
     * Crée progressivement une liste des points adjacents.
     * Reutilise les points du graphe déjà connu s'il y en a.
     * Cree de nouveau voisins s'il n'exsite pas encore.
     * Les nouveaux voisins sont automatiquement insérés au graphe.
     * @return La liste des points adjacents au point courant.
     */

    public ArrayList<Coordonnees> voisins() {
        Coordonnees haut, bas ,gauche, droite;
        ArrayList<Coordonnees> voisins = new ArrayList<Coordonnees>();
        boolean dejaAjouter = false; //Permet de vérifier si le point n'existe pas déjà dans la liste

        if (this.ligne -1 >= 0) { // Vérifie que les Coordonneesdonnées du point ne sortent pas de la bordure
            haut = new Coordonnees(this.colonne, this.ligne-1);
            voisins.add(haut);
//            for (Coordonnees c : tree ) { // Regarde sur le graphe si le point n'existe pas déjà
//                if (haut.isEqual(c)) {
//                    voisins.add(c);
//                    dejaAjouter = true;
//                }
//            }
//            if (!dejaAjouter) { // Cas ou il s'agit d'un nouveau point
//                voisins.add(haut);
//                tree.add(haut);
//            }
//            dejaAjouter =false;

        }

        if (this.colonne+1 < Parametre.COLONNE) {
            droite = new Coordonnees(this.colonne+1, this.ligne);
            voisins.add(droite);
//
//            for (Coordonnees c : tree ) {
//                if (droite.isEqual(c)) {
//                    voisins.add(c);
//                    dejaAjouter = true;
//                }
//            }
//            if (!dejaAjouter) {
//                voisins.add(droite);
//                tree.add(droite);
//            }
//            dejaAjouter =false;

        }

        if (this.ligne+1 < Parametre.LIGNE) {
            bas = new Coordonnees(this.colonne, this.ligne+1);
            voisins.add(bas);
//
//            for (Coordonnees c : tree ) {
//                if (bas.isEqual(c)) {
//                    voisins.add(c);
//                    dejaAjouter = true;
//                }
//            }
//            if (!dejaAjouter) {
//                voisins.add(bas);
//                tree.add(bas);
//            }
//            dejaAjouter =false;

        }

        if (this.colonne - 1 >=0) {
            gauche = new Coordonnees(this.colonne-1, this.ligne);
            voisins.add(gauche);

//            for (Coordonnees c : tree ) {
//                if (gauche.isEqual(c)) {
//                    voisins.add(c);
//                    dejaAjouter = true;
//                }
//            }
//            if (!dejaAjouter) {
//                voisins.add(gauche);
//                tree.add(gauche);
//            }
        }

//        System.out.println(voisins.size());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordonnees that = (Coordonnees) o;
        return colonne == that.colonne && ligne == that.ligne;
    }

    public boolean isInside(double rayon, Coordonnees centre) {
        double dx, dy, distance;
        dx = centre.getColonne() - this.colonne;
        dy = centre.getLigne() - this.ligne;
        distance = (dx*dx + dy*dy);
        return distance <= rayon*rayon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colonne, ligne, isMarked);
    }
}
