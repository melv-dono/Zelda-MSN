package Modèle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MapModele { // on associera à chaque map un id qui nous permettra de charger une map en fonction de son id
    private String nomMap;
    private int[] tableau;
    private StringBuilder s;
    private static int resolutionEcran = 40*23;

    public MapModele(String nom){
        this.nomMap=nom;
        tableau = new int[resolutionEcran];
        s = null;
    }

    public void lectureCoordonnees() {
        //File fichier=new File("Modèle/testMap.txt");
        try {
            FileReader fr = new FileReader("testMap.txt"); // va permettre d'obtenir les caractères d'un fichier dans le système de fichiers
            BufferedReader br = new BufferedReader(fr); // permet à un autre lecteur de mettre les caractères en mémoire tampon(données utilisé pr un certains laps de temps). Btw bufferReader ne peut que lire des données à partir d'un autre lecteur de données ici fileReader
            String line;
            while((line = br.readLine()) != null)
            {
                // ajoute la ligne au buffer
                s.append(line);
            }
            fr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertionTab() {
        lectureCoordonnees();
        int cpt=0;
        for (int i=0;i<resolutionEcran;i+=2){
            tableau[cpt]=(int)s.charAt(i);
            cpt++;
        }
    }

    public int[] getTableau() {
        convertionTab();
        return tableau;
    }


    //int[] coordoneesCarte = {};
}
