package Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VuePnj {
    private int orientation;
    private ImageView imgPnj;
    public VuePnj(String url){
        imgPnj=new ImageView(url);
        orientation=1;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        if(this.orientation==1){
            imgPnj.setImage(new Image("Vue/pnjFace.png"));
        }else if(this.orientation==2){
            imgPnj.setImage(new Image());
        }else if(this.orientation==3){
            imgPnj.setImage();
        }else{
            imgPnj.setImage();
        }
    }
}
