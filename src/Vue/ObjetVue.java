package Vue;

import javafx.scene.image.ImageView;

public class ObjetVue {
    private ImageView img;
    private int id;
    public ObjetVue(String url,int id){
        img=new ImageView(url);
        this.id=id;
    }
    public ImageView getImg(){return img;}
    public int getId(){
        return id;
    }
}
