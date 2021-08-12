package Vue;

import javafx.scene.image.ImageView;

public class VueBouleDeFeu {

    private ImageView bouleImg;

    private String idBoule;

    public VueBouleDeFeu(String id,String url){
        bouleImg=new ImageView(url);
        idBoule=id;
    }
    public ImageView getBouleImg(){
        return bouleImg;
    }
    public String getIdBoule(){
        return idBoule;
    }


    /**
     * Crée l'image de Link et l'associe au modèle.
     * @return
     */
}
