package Controleur;

import Modèle.*;
import Vue.MapReader;
import Vue.ObjetVue;
import Vue.VueLink;
import Vue.VueSquelette;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private ImageView coeur1, coeur2, coeur3, coeur4, coeur5;
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
    private VBox menuAide;
    @FXML
    private Pane plateau;
    @FXML
    private TilePane map = new TilePane();
    @FXML
    private ListView<String> listViewInventaire=new ListView<>();
    private Timeline gameLoop;
    private Environnement env;
    private ArrowGestion arrow;
    private LettreTyped action;
    private ArrayList<Environnement> listeEnv=new ArrayList<>();
    private int autoIncrementation = 1;
    private String nom = "map";
    private String nomMapActu = nom + autoIncrementation;

    /**
     * Rend automatique le déplacement du squelette au sein de l'environnement.
     * @param s
     */
//    private void animation(Squelette s, VueLink vue){
    private void animation(Squelette s, Timeline gameLoop, VueLink vue, Link l, MapReader m){ //L'animation du suqellete marche plus vu qu'il est considéré comme un perso
        KeyFrame kf = new KeyFrame(
				Duration.seconds(0.017),
				(ev ->{
                    vue.orientation();
                    this.env.faireUntour();
                    if(((l.getDeplacementHauteur()>=320 && l.getDeplacementHauteur()<=448) && l.getDeplacementLargeur()==8)){
                        chargerNouvelleMap(l, m);
                    }
                    if((l.getDeplacementHauteur()>=320 && l.getDeplacementHauteur()<=448) && l.getDeplacementLargeur()==1256){
                        chargerAncienneMap(l,m);
                    }
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
        this.env = new Environnement(Parametre.LARGEUR, Parametre.HAUTEUR,autoIncrementation,nomMapActu);
        this.listeEnv.add(this.env);
        this.env.init();
        MapReader m  = new MapReader(map);
        m.chargerMap(env.getMapActuelle().getTableau());
        InventaireGestion inventaireGestion=new InventaireGestion(listViewInventaire,env);
        listViewInventaire.setOnMouseClicked(inventaireGestion);
        listViewInventaire.setItems(env.getInventaire().getListeObjets());
        affichage();
        miseEnPlaceObjet();
        connexion();
        gestionBouleDeFeu();
        plateau.setOnKeyReleased(action);
        plateau.setOnKeyPressed(arrow);
        plateau.getChildren().add(menuPause);
        animation((Squelette) env.getPerso().get(1), gameLoop, (VueLink) this.plateau.lookup("#"+this.env.getLink().getNom()), this.env.getLink(), m);
        gameLoop.play();
    }

    public void chargement(){
        this.env.init();
        miseEnPlaceObjet();
        connexion();
        gestionBouleDeFeu();
    }

    public void affichage() {
        for (Personnage p : this.env.getPerso()) {
            if (p instanceof Link) {
                VueLink l = new VueLink((Link) p);
                AnimationGestion anim = new AnimationGestion(l);
                ((Link) p).animationPropertyProperty().addListener(anim);
                plateau.getChildren().add(l.creeSprite());
            }
            if (p instanceof Squelette) {
                VueSquelette s = new VueSquelette(p,"Vue/bad_skeleton.gif");
                plateau.getChildren().add(s.getImgSquelette());
            }
        }
        ObservateurObjet obsObj=new ObservateurObjet(plateau,env, listeEnv);
        ObservateurEnvironnement obsEnv=new ObservateurEnvironnement(obsObj,env);
        env.getObjetEnvironnement().addListener(obsObj);
        env.getTheID().addListener(obsEnv);
    }
    public void connexion() {
        arrow = new ArrowGestion(env.getLink());
        action = new LettreTyped(menuPause,plateau,gameLoop,env,menuAide);
        this.ptVie.textProperty().bind(env.getLink().pvProperty().asString());
        labelNiveau.textProperty().bind(env.getLink().niveau().asString());
        ProgressBarExp.setProgress(0.0);
        GestionCoeur apparitionCoeur=new GestionCoeur(coeur1,coeur2,coeur3,coeur4,coeur5,env);
        env.getLink().pvProperty().addListener(apparitionCoeur);
    }
    public void gestionBouleDeFeu() {
        ObservateaurBouleDeFeu obs1 = new ObservateaurBouleDeFeu(plateau);
        this.env.getLink().getarmeSecondaire().getBoules().addListener(obs1);
    }
    public void miseEnPlaceObjet(){
        if(this.nomMapActu.equals("map1")){
            env.miseEnPlaceObjetFirstMap();
        }
    }

    public void chargerNouvelleMap(Link l, MapReader m){
        autoIncrementation++;
        nomMapActu = nom + autoIncrementation;
        if(this.listeEnv.size() < autoIncrementation){
            this.env.setId(autoIncrementation);
            this.env.deleteAllPerso();
            this.env.setMapActuelle(nomMapActu);
            env.retirerCollision();
        }
        else{
            for (Environnement e : this.listeEnv) {
                if(e.getId()==autoIncrementation){
                    this.env = e;
                }
            }
        }
        //l.setEnv(this.env);
        //chargement();
        l.setDeplacementHauteur(352);
        l.setDeplacementLargeur(1224);
        m.reset();
        m.chargerMap(env.getMapActuelle().getTableau());
    }

    public void chargerAncienneMap(Link l, MapReader m){
        autoIncrementation--;
        nomMapActu = nom + autoIncrementation;
        for (Environnement e : this.listeEnv) {
            if(e.getId()==autoIncrementation){
                this.env = e;
            }
        }
        //l.setEnv(this.env);
        //chargement();
        l.setDeplacementHauteur(352);
        l.setDeplacementLargeur(40);
        m.reset();
        m.chargerMap(env.getMapActuelle().getTableau());
    }

}
