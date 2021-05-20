package Modèle;

import java.io.*;
import java.util.StringTokenizer;

public class MapModele { // on associera à chaque map un id qui nous permettra de charger une map en fonction de son id
    private String nomMap;
    private int[][] tableau;
    private StringBuilder s;
    private static int resolutionEcran = 40 * 23;

    public MapModele(String nom) {
        this.nomMap = nom;
        this.tableau = new int[23][40];
        s = null;
    }

    public int[][] getTableau() {
        //File fichier=new File("Modèle/testMap.txt");
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

        /*while((line = br.readLine()) != null)
        {
            // ajoute la ligne au buffer
            s.append(line);
        }*/
        return this.tableau;
    }

    public String getNomMap() {
        return nomMap;
    }

    //int[] coordoneesCarte = {};
}
