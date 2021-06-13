package Controleur;

import Modèle.*;
import Vue.*;
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
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private ImageView coeur1, coeur2, coeur3, coeur4, coeur5;
    @FXML
    private ImageView bouclier;
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
    private int autoIncrementation = 1;
    private String nom = "map";
    private String nomMapActu = nom + autoIncrementation;

    /**
     * Rend automatique le déplacement du squelette au sein de l'environnement.
     */
//    private void animation(Squelette s, VueLink vue){
    private void animation(Timeline gameLoop, MapReader m){ //L'animation du suqellete marche plus vu qu'il est considéré comme un perso
        KeyFrame kf = new KeyFrame(
				Duration.seconds(0.017),
				(ev ->{
                    this.env.faireUntour();
                    // essayer de changer d'endroit ce qui se trouve ci-dessous
                    if(((env.getLink().getDeplacementHauteur()>=320 && env.getLink().getDeplacementHauteur()<=448) && env.getLink().getDeplacementLargeur()==8)){
                        chargerNouvelleMap(m);
                    }
                    if((env.getLink().getDeplacementHauteur()>=320 && env.getLink().getDeplacementHauteur()<=448) && env.getLink().getDeplacementLargeur()==1256){
                        chargerAncienneMap(m);
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
        this.env = new Environnement(autoIncrementation,nomMapActu);
        this.env.init();
        MapReader m  = new MapReader(map);
        m.chargerMap(env.getMapActuelle().getTableau());
        inventaireSetUp();
        miseEnPlaceElementAll();
        affichage();
        ProgressBarExp.setProgress(0);
        connexion();
        gestionBouleDeFeu();
        miseEnPlaceEnvExt();
        setOn();
        animation(gameLoop, m);
        gameLoop.play();
        this.bouclier.setVisible(false);
    }
    public void setOn(){
        plateau.setOnKeyReleased(action);
        plateau.setOnKeyPressed(arrow);
        plateau.getChildren().add(menuPause);
    }

    public void inventaireSetUp(){
        InventaireGestion inventaireGestion=new InventaireGestion(listViewInventaire,env,bouclier);
        listViewInventaire.setOnMouseClicked(inventaireGestion);
        listViewInventaire.setItems(env.getInventaire().getListeObjets());
    }
    public void miseEnPlaceElementAll(){
        env.miseEnPlaceEnnemi();
        env.chargerTousLesObj();
    }
    public void affichage() {
        ObservateurPersonnage obsPerso=new ObservateurPersonnage(plateau,env);
        env.getPerso().addListener(obsPerso);
        VueLink l = new VueLink(env.getLink().getId(),"Vue/link_front2.gif");
        l.getImg().translateXProperty().bind(env.getLink().getDeplacementLargeurProperty());
        l.getImg().translateYProperty().bind(env.getLink().getDeplacementHauteurProperty());
        ObservateurVueLink o = new ObservateurVueLink(l);
        env.getLink().orientationProperty().addListener(o);
        AnimationGestion anim = new AnimationGestion(l,env.getLink());
        env.getLink().animationPropertyProperty().addListener(anim);
        plateau.getChildren().add(l.getImg());
        ObservateurObjet obsObj=new ObservateurObjet(plateau,env,bouclier);
        ObservateurEnvironnement obsEnv=new ObservateurEnvironnement(obsObj,obsPerso);
        env.getObjEnvAct().addListener(obsObj);
        env.getTheID().addListener(obsEnv);
    }
    public void connexion() {
        arrow = new ArrowGestion(env.getLink());
        action = new LettreTyped(menuPause,plateau,gameLoop,env,menuAide);
        this.ptVie.textProperty().bind(env.getLink().pvProperty().asString());
        this.ptAtt.textProperty().bind((env.getLink().getPointAttaqueProperty().add(env.getLink().getArmePrincipale().getPointAttaqueProperty()).asString()));
        this.ptDef.textProperty().bind(env.getLink().getPointDefenseProperty().asString());
        labelNiveau.textProperty().bind(env.getLink().niveau().asString());
        GestionCoeur apparitionCoeur=new GestionCoeur(coeur1,coeur2,coeur3,coeur4,coeur5,env);
        env.getLink().pvProperty().addListener(apparitionCoeur);

    }
    public void gestionBouleDeFeu() {
        ObservateaurBouleDeFeu obs1 = new ObservateaurBouleDeFeu(plateau);
        this.env.getLink().getarmeSecondaire().getBoules().addListener(obs1);
    }
    public void miseEnPlaceEnvExt(){
        if(this.nomMapActu.equals("map1")){
            env.chargerObjMap("map1");

        }else if(this.nomMapActu.equals("map2")){
            env.chargerObjMap("map2");
            env.retirerSquelette();
            env.chargerEnnemiMap("map2");

        }else if(this.nomMapActu.equals("map3")){
            env.chargerObjMap("map3");
            env.chargerEnnemiMap("map3");

        }else if(this.nomMapActu.equals("map4")){
            env.retirerSquelette();
        }
    }


    public void chargerNouvelleMap(MapReader m){
        autoIncrementation++;
        chargementMap(m);
        env.getLink().setDeplacementHauteur(352);
        env.getLink().setDeplacementLargeur(1224);
    }

    public void chargerAncienneMap(MapReader m){
        autoIncrementation--;
        chargementMap(m);
        env.getLink().setDeplacementHauteur(352);
        env.getLink().setDeplacementLargeur(40);
    }
    public void chargementMap(MapReader m){
        this.env.setId(autoIncrementation);
        nomMapActu = nom + autoIncrementation;
        this.env.deleteAllPerso();
        this.env.setMapActuelle(nomMapActu);
        env.retirerCollision();
        miseEnPlaceEnvExt();
        m.reset();
        m.chargerMap(env.getMapActuelle().getTableau());
    }

}