package Vue;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ObjetVue {
    @FXML
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
