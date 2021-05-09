package Vue;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;

import java.io.*;

public class MapReader {
    @FXML
    private TilePane plateau;
    public static void lireFichier() throws Exception {
        File fichier=new File("C:/Users/jujus/IdeaProjects/ZeldaProject/src/sample/tileTry.txt");
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

}
