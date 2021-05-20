package Modèle;

import java.io.*;
import java.util.StringTokenizer;

public class MapModele { // on associera à chaque map un id qui nous permettra de charger une map en fonction de son id
    private String nomMap;
    private int[] tableau;
    private StringBuilder s;
    private static int resolutionEcran = 40 * 23;

    public MapModele(String nom) {
        this.nomMap = nom;
        tableau = new int[resolutionEcran];
        s = null;
    }

    public int[] getTableau() {
        //File fichier=new File("Modèle/testMap.txt");
        int[] donnee = new int[resolutionEcran];
        BufferedReader br;
        StringTokenizer line;
        int i = 0;
        try {

            br = new BufferedReader(new FileReader("src/Modèle/"+this.nomMap+".txt"));
            String ligneLue = "";

            do {
                ligneLue = br.readLine();
                if (ligneLue != null) {
                    line = new StringTokenizer(ligneLue, ",");
                    while (line.hasMoreTokens()) {
                        donnee[i] = Integer.parseInt(line.nextToken());
                        i++;
                    }
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
        return donnee;
    }


    //int[] coordoneesCarte = {};
}
