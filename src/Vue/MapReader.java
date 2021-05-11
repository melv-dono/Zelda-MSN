package Vue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;


import java.io.*;

public class MapReader {
    @FXML
    private Pane map;

    public MapReader(Pane t){
        this.map = t;
    }
    /*
    public static void lireFichier() throws Exception {
        File fichier=new File("C:/Users/Minh-Tri/IdeaProjects/ZeldaMSN/src/TileMap/map1");
        FileReader fr = new FileReader(fichier); // va permettre d'obtenir les caractères d'un fichier dans le système de fichiers
        BufferedReader br = new BufferedReader(fr); // permet à un autre lecteur de mettre les caractères en mémoire tampon(données utilisé pr un certains laps de temps). Btw bufferReader ne peut que lire des données à partir d'un autre lecteur de données ici fileReader
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null)
        {
            // ajoute la ligne au buffer
            sb.append(line);
            sb.append("\n");
        }
        fr.close();
        System.out.println("Contenu du fichier: ");
        System.out.println(sb);
    }
*/

    public void chargerMap(int[] coordoneesCarte){
        int cases =0;
        for (int i=0; i<(coordoneesCarte.length/40); i++){
            for (int j=0; j<(coordoneesCarte.length/23); j++) {
                switch (coordoneesCarte[cases]){
                    case 1:
                        ImageView affichage = new ImageView("Vue/herbe32x32.png");
                        affichage.setX(j*32);
                        affichage.setY(i*32);
                        map.getChildren().add(affichage);
                        break;
                    case 2:
                        ImageView affichage2 = new ImageView("Vue/terre32x32.png");
                        affichage2.setX(j*32);
                        affichage2.setY(i*32);
                        map.getChildren().add(affichage2);
                        break;
                    default:
                        break;
                }
                cases++;
            }
        }
    }
}
