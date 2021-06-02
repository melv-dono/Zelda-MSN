package Controleur;

import Modèle.*;
import Vue.MapReader;
import Vue.VueBouleDeFeu;
import Vue.ObjetVue;
import Vue.VueLink;
import Vue.VueSquelette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
    @FXML
    private Pane plateau;
    @FXML
    private TilePane map = new TilePane();
    @FXML
    private VBox LinkLife;
    @FXML
    private ListView<String> listViewInventaire=new ListView<>();
    private Timeline gameLoop;
    private Environnement env;
    private ArrowGestion arrow;
    private LettreTyped action;

    /**
     * Rend automatique le déplacement du squelette au sein de l'environnement.
     * @param s
     */
//    private void animation(Squelette s, VueLink vue){
    private void animation(Squelette s, Timeline gameLoop, VueLink vue){ //L'animation du suqellete marche plus vu qu'il est considéré comme un perso
        KeyFrame kf = new KeyFrame(
				Duration.seconds(0.017),
				(ev ->{
                    s.animation1();
                    vue.orientation();
                    this.env.faireUntour();
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
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        this.env = new Environnement(Parametre.LARGEUR, Parametre.HAUTEUR);
        this.env.init();
        MapReader m  = new MapReader(map);
        m.chargerMap(env.getMapActuelle().getTableau());
        Objet potion=new Objet("potion",520,608,15,env);
        ObjetVue vuePotion=new ObjetVue(potion);
        ImageView imgPotion=vuePotion.CreerSpriteObjet();
        Inventaire inventaire=new Inventaire();
        env.ajouterCoordElementExt(potion);
        Objet objBon=new Objet("potion",env);
        env.getLink().decrementerPv(50);
        listViewInventaire.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if(inventaire.getTailleInventaire()>=1){
                    if(listViewInventaire.getSelectionModel().getSelectedItem()==null){
                        System.out.println("erroor");
                    }else if(listViewInventaire.getSelectionModel().getSelectedItem().equals("potion")&& env.getLink().pv()<100) {
                        env.getLink().augmenterPv(10);
                        inventaire.removeObjet(objBon);
                    }else{
                        System.out.println("ra");
                    }
                }
            }
        });
        listViewInventaire.setItems(inventaire.getListeObjets());
        affichage();
        connexion(potion,inventaire,imgPotion);
        gestionBouleDeFeu();
        plateau.setOnKeyReleased(action);
        plateau.setOnKeyPressed(arrow);
        plateau.getChildren().add(menuPause);
        animation((Squelette) env.getPerso().get(1), gameLoop, (VueLink) this.plateau.lookup("#"+this.env.getLink().getNom()));
        gameLoop.play();
    }
    public void affichage() {
        for (Personnage p : this.env.getPerso()) {
            if (p instanceof Link) {
                VueLink l = new VueLink((Link) p);
                plateau.getChildren().add(l.creeSprite());
            }
            if (p instanceof Squelette) {
                VueSquelette s = new VueSquelette(p);
                plateau.getChildren().add(s.creeSprite());
            }
        }
    }
    public void connexion(Objet obj,Inventaire inventaire,ImageView imgPotion) {
        arrow = new ArrowGestion(env.getLink());
        action = new LettreTyped(env.getLink(),menuPause,plateau,gameLoop,env,obj, inventaire,imgPotion );
        this.ptVie.textProperty().bind(env.getLink().pvProperty().asString());
        labelNiveau.textProperty().bind(env.getLink().niveau().asString());
        ProgressBarExp.setProgress(0.7);
    }
    public void gestionBouleDeFeu() {
        ObservateaurBouleDeFeu obs1 = new ObservateaurBouleDeFeu(plateau);
        this.env.getLink().getarmeSecondaire().getBoules().addListener(obs1);
    }
}
