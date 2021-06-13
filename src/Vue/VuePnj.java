package Vue;

import javafx.scene.image.Image;

public class VuePnj extends ObjetVue{
    private int orientation;
    public VuePnj(String url,int id){
        super(url,id);
        orientation=1;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        if(this.orientation==1){
            super.getImg().setImage(new Image("Vue/pnjFace.png"));
        }else if(this.orientation==2){
            super.getImg().setImage(new Image("Vue/pnj-back.png"));
        }else if(this.orientation==3){
            super.getImg().setImage(new Image("Vue/pnj-right.png"));
        }else{
            super.getImg().setImage(new Image("Vue/pnj-left.png"));
        }
    }
}
