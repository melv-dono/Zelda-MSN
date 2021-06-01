package Controleur;

import Modèle.*;
import Vue.MapReader;
import Vue.ObjetVue;
import Vue.VueLink;
import Vue.VueSquelette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private Label labelNiveau;
    @FXML
    private ProgressBar ProgressBarExp;
    @FXML
    private Label ptDef;
    @FXML
    private Label ptExp;
    @FXML
    private Label ptAtt;
    @FXML
    private Label ptVie;

    @FXML
    private VBox menuPause;

    static int LARGEUR = 1280;
    static int HAUTEUR = 736;

    @FXML
    private Pane plateau;

    @FXML
    private TilePane map = new TilePane();
    @FXML
    private VBox LinkLife;

    @FXML
    private ListView<String> listViewInventaire;

    private Timeline gameLoop;



    private int cpt;


    /**
     * Rend automatique le déplacement du squelette au sein de l'environnement.
     * @param s
     */
    private void animation(Squelette s){
        cpt=0;
        gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
				Duration.seconds(0.01),
				(ev ->{
				    if(cpt < 150) {
                        s.monter();
                    }
				    else if(cpt >=150){
				        s.descendre();
                    }
                    if(cpt==298){
                        cpt=0;
                    }
                    cpt++;
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}

    /**
     * Charge tous les éléments présent sur la map de départ.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MapModele spawn = new MapModele("testMap");
        MapReader m  = new MapReader(map);
        m.chargerMap(spawn.getTableau());
        Environnement env = new Environnement(LARGEUR, HAUTEUR,spawn);
        Link p = new Link(env);
        VueLink vue = new VueLink(p);
        Objet potion=new Objet("potion",520,615,15,env);
        ObjetVue vuePotion=new ObjetVue(potion);
        ImageView imgPotion=vuePotion.CreerSpriteObjet();
        Inventaire inventaire=new Inventaire();
        Objet objBon=new Objet("potion",env);
        ImageView personnage = vue.creeSprite();
        this.ptVie.textProperty().bind(p.pv().asString());
        labelNiveau.textProperty().bind(p.niveau().asString());
        ProgressBarExp.setProgress(0.7);
        Squelette s = new Squelette("Squelette",env,"a");
        VueSquelette vueS = new VueSquelette(s);
        ImageView imageSquelette = vueS.creeSprite();
        p.decrementerPv(50);

        listViewInventaire.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(inventaire.getTailleInventaire()>=1){
                    if(listViewInventaire.getSelectionModel().getSelectedItem().equals("potion")&& p.pv().getValue()<100) {
                        p.augmenterPv(10);
                        inventaire.removeObjet(objBon);
                    }else{
                        System.out.println("ra");
                    }
                }
            }
        });
        listViewInventaire.setItems(inventaire.getListeObjets());
        plateau.getChildren().addAll(imageSquelette,imgPotion,personnage,menuPause);
        SceneEventGestion a = new SceneEventGestion(plateau,p,menuPause,potion,inventaire);
        plateau.setOnKeyPressed(a);
        animation(s);
        gameLoop.play();


    }



}
