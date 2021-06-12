package Vue;

import Modèle.Link;
import Modèle.Personnage;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueLink{

    private int id;
    private ImageView img;

    public VueLink(int id,String url){
        this.id=id;
        img=new ImageView(new Image(url));
    }

    /**
     * Crée l'image de Link et l'associe au modèle.
     * @return
     */

    public void animation(String direction) {
        if (direction == "gauche") {
            setImg("Vue/link_swing8.gif");
        }
        if (direction == "droite") {
            setImg("Vue/link_swing4.gif");
        }
        if (direction == "monter") {
            setImg("Vue/link_swing5.gif");
        }
        if (direction == "descendre") {
            setImg("Vue/link_swing.gif");
        }
    }


    public void orientation(String direction){
        switch (direction){
            case "monter" :
                setImg("Vue/link_back.gif");
                break;

            case "descendre" :
                setImg("Vue/link_front2.gif");
                break;

            case "gauche" :
                setImg("Vue/link_left.gif");
                break;

            case "droite" :
                setImg("Vue/link_right.gif");
                break;
        }
    }
    public ImageView getImg(){
        return img;
    }
    public void setImg(String url){
        this.img.setImage(new Image(url));
    }
}
