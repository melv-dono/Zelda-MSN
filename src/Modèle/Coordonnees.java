package Modèle;

import java.util.ArrayList;

import Modèle.Parametre;

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


    public int getLigne() {
        return ligne;
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

        if (this.ligne -1 >= 0) { // Vérifie que les Coordonneesdonnées du point ne sortent pas de la bordure
            haut = new Coordonnees(this.colonne, this.ligne-1);
            voisins.add(haut);

        }

        if (this.colonne+1 < Parametre.COLONNE) {
            droite = new Coordonnees(this.colonne+1, this.ligne);
            voisins.add(droite);

        }

        if (this.ligne+1 < Parametre.LIGNE) {
            bas = new Coordonnees(this.colonne, this.ligne+1);
            voisins.add(bas);

        }

        if (this.colonne - 1 >=0) {
            gauche = new Coordonnees(this.colonne-1, this.ligne);
            voisins.add(gauche);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordonnees that = (Coordonnees) o;
        return colonne == that.colonne && ligne == that.ligne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colonne, ligne, isMarked);
    }
}
