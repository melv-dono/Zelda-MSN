package Modèle;

import java.io.*;
import java.util.StringTokenizer;

public class MapModele { // on associera à chaque map un id qui nous permettra de charger une map en fonction de son id
    private String nomMap;
    private int[][] tableau; // Contient les données de la map chargées par ce modèle.
    private StringBuilder s; // Contient les lignes du ficher dont les données sont chargées par le modèle.
    private static int resolutionEcran = Parametre.COLONNE * Parametre.LIGNE; // Taile de la fenêtre de jeu.

    public MapModele(String nom) {
        this.nomMap = nom;
        this.tableau = new int[Parametre.LIGNE][Parametre.COLONNE];
        s = null;
        generationMap();
    }

    /**
     * Charge les données d'une map dans le modèle.
     * Envoie un tableau avec les données chargées d'une map.
     * @return tableau 2D de donnée d'une map
     */
    public void generationMap() {
        //File fichier=new File("Modèle/testMap.txt");
        int[] donnee = new int[resolutionEcran];
        BufferedReader br;
        StringTokenizer line;
        int hauteur=0;
        int largeur=0;
        try {

            br = new BufferedReader(new FileReader("src/Modèle/"+this.nomMap+".txt"));
            String ligneLue = "";

            do {
                ligneLue = br.readLine();
                if (ligneLue != null) {
                    line = new StringTokenizer(ligneLue, ",");
                    while (line.hasMoreTokens()) {
                        this.tableau[hauteur][largeur] = Integer.parseInt(line.nextToken());
                        largeur++;
                    }
                    largeur=0;
                    hauteur++;
                }
            }
            while (ligneLue != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][] getTableau() {
        return this.tableau;
    }

    /**
     * Envoie le nom de la map que contient le modèle.
     * @return nom d'une map
     */
    public String getNomMap() {
        return nomMap;
    }
}
