package Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class VueLink implements VueAnimation{

    private int id;
    private ImageView img;

    public VueLink(int id,String url){
        this.id=id;
        this.img=new ImageView(url);
    }

    /**
     * Crée l'image de Link et l'associe au modèle.
     * @return
     */

    public void animation(String direction) {
        if (direction == "gauche") {
            setImg("Vue/images/link/link_swing8.gif");
        }
        if (direction == "droite") {
            setImg("Vue/images/link/link_swing4.gif");
        }
        if (direction == "monter") {
            setImg("Vue/images/link/link_swing5.gif");
        }
        if (direction == "descendre") {
            setImg("Vue/images/link/link_swing.gif");
        }
    }


    public void orientation(String direction){
        switch (direction){
            case "monter" :
                setImg("Vue/images/link/link_back.gif");
                break;

            case "descendre" :
                setImg("Vue/images/link/link_front2.gif");
                break;

            case "gauche" :
                setImg("Vue/images/link/link_left.gif");
                break;

            case "droite" :
                setImg("Vue/images/link/link_right.gif");
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
