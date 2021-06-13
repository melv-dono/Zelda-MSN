package Controleur.clavier;

import Modèle.*;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LettreTyped implements EventHandler<KeyEvent> {

    @FXML
    private VBox menuPause;
    @FXML
    private VBox menuAide;
    @FXML
    private Link perso;
    @FXML
    private Pane plateau;

    private Timeline gameloop;
    private ObservableList<ElementMap> objetEnvironnement;

    @FXML
    private Environnement env;
    @FXML
    private Inventaire inventaire;


    public LettreTyped(VBox vb, Pane map, Timeline t, Environnement environnement, VBox vb2) {
        this.perso = environnement.getLink();
        menuPause=vb;
        menuAide=vb2;
        plateau=map;
        menuPause.setVisible(false);
        menuAide.setVisible(true);
        this.gameloop = t;
        objetEnvironnement=environnement.getObjEnvAct();
        inventaire=environnement.getInventaire();
        env=environnement;

    }

    @Override
    public void handle(KeyEvent keyEvent) {
        double x = this.perso.getDeplacementLargeur();
        double y = this.perso.getDeplacementHauteur();
        String o = this.perso.getOrientation();
        switch (keyEvent.getCode()) {
            case S:
                menuAide.setVisible(true);
                break;
            case J:
                menuAide.setVisible(false);
                break;
            case I:
                menuPause.setVisible(true);
                break;
            case Q:
                menuPause.setVisible(false);
                break;
            case D:
                this.perso.coupEpe();
                this.perso.setMoving(true);
                break;
            case A:
                this.perso.getarmeSecondaire().creeBoule(x, y, o);
                break;
            case R:
                if(objetEnvironnement.size()>=1){
                    for(int i=0;i<objetEnvironnement.size();i++){
                        if(((objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()>=-32
                                &&objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()<=32)
                                && objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()==0)
                                || ((objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()<=32
                                &&objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()>=-32)
                                &&objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()==0) ){
                            if(objetEnvironnement.get(i) instanceof ObjetRamassable){
                                inventaire.addObjet(objetEnvironnement.get(i));
                                objetEnvironnement.remove(objetEnvironnement.get(i));
                            }else if(objetEnvironnement.get(i) instanceof Rocher){
                                if(env.getInventaire().inventairePossede("pioche")){
                                    objetEnvironnement.remove(objetEnvironnement.get(i));
                                }else{
                                    Alert alerte=new Alert(Alert.AlertType.INFORMATION);
                                    alerte.setHeaderText("Link");
                                    alerte.setGraphic(new ImageView("Vue/images/link/linkito.jpg"));
                                    alerte.setContentText("hum... ce caillou me bloque la route mais il semble être cassable");
                                    alerte.show();
                                }
                            }else if(objetEnvironnement.get(i) instanceof Arbre){
                                if(((Arbre) objetEnvironnement.get(i)).getNbPomme()>0){
                                    if(objetEnvironnement.get(i).getPositionLargeur().getValue()- perso.getDeplacementLargeur()==32){
                                        Pomme pomme=new Pomme(objetEnvironnement.get(i).getPositionLargeur().getValue()+32,
                                                objetEnvironnement.get(i).getPositionHauteur().getValue());
                                        env.getObjEnvAct().add(pomme);
                                        ((Arbre) objetEnvironnement.get(i)).retirerPomme();
                                    }else if(objetEnvironnement.get(i).getPositionLargeur().getValue()- perso.getDeplacementLargeur()==-32){
                                        Pomme pomme=new Pomme(objetEnvironnement.get(i).getPositionLargeur().getValue()-32,
                                                objetEnvironnement.get(i).getPositionHauteur().getValue());
                                        env.getObjEnvAct().add(pomme);
                                        ((Arbre) objetEnvironnement.get(i)).retirerPomme();
                                    }else if(objetEnvironnement.get(i).getPositionHauteur().getValue()- perso.getDeplacementHauteur()==32){
                                        Pomme pomme=new Pomme(objetEnvironnement.get(i).getPositionLargeur().getValue(),
                                                objetEnvironnement.get(i).getPositionHauteur().getValue()+32);
                                        env.getObjEnvAct().add(pomme);
                                        ((Arbre) objetEnvironnement.get(i)).retirerPomme();
                                    }else{
                                        Pomme pomme=new Pomme(objetEnvironnement.get(i).getPositionLargeur().getValue(),
                                                objetEnvironnement.get(i).getPositionHauteur().getValue()-32);
                                        env.getObjEnvAct().add(pomme);
                                        ((Arbre) objetEnvironnement.get(i)).retirerPomme();
                                    }
                                }
                            }else if(objetEnvironnement.get(i) instanceof PersoNonJouable ){
                                if(objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()==32){
                                    ((PersoNonJouable) objetEnvironnement.get(i)).setOrientation(4);
                                }else if(objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()==-32){
                                    ((PersoNonJouable) objetEnvironnement.get(i)).setOrientation(3);
                                }else if(objetEnvironnement.get(i).getPositionHauteur().getValue()- perso.getDeplacementHauteur()==32){
                                    ((PersoNonJouable) objetEnvironnement.get(i)).setOrientation(2);
                                }else{
                                    ((PersoNonJouable) objetEnvironnement.get(i)).setOrientation(1);
                                }
                                if(((PersoNonJouable) objetEnvironnement.get(i)).tiensObjet()){
                                    if(objetEnvironnement.get(i).getMapAction()=="map1"){
                                        Alert alerte=new Alert(Alert.AlertType.INFORMATION);
                                        alerte.setHeaderText("Bob le paysan");
                                        alerte.setContentText("salut prends donc cette clé elle te sera utile");
                                        alerte.setGraphic(new ImageView("Vue/images/pnj/paysan.jpg"));
                                        alerte.show();
                                        inventaire.addObjet(((PersoNonJouable) objetEnvironnement.get(i)).donObjet());
                                    }else if(objetEnvironnement.get(i).getMapAction()=="map3"){
                                        if(env.taillePersoMapActu()>0){
                                            Alert alerte=new Alert(Alert.AlertType.INFORMATION);
                                            alerte.setHeaderText("Jacob le paysan");
                                            alerte.setContentText("stp aide moi à me débarasser des squelettes en échange je te donnerai un objet spéciale");
                                            alerte.setGraphic(new ImageView("Vue/images/pnj/paysan.jpg"));
                                            alerte.show();
                                        }else{
                                            Alert alerte=new Alert(Alert.AlertType.INFORMATION);
                                            alerte.setHeaderText("Jacob le paysan");
                                            alerte.setContentText("Merci prends donc ça");
                                            alerte.setGraphic(new ImageView("Vue/images/pnj/paysan.jpg"));
                                            alerte.show();
                                            inventaire.addObjet(((PersoNonJouable) objetEnvironnement.get(i)).donObjet());
                                            this.perso.setCasesSansColisionsDe1();
                                        }

                                    }

                                }else{
                                    if(objetEnvironnement.get(i).getMapAction()=="map1"){
                                        Alert alerte=new Alert(Alert.AlertType.INFORMATION);
                                        alerte.setHeaderText("Bob le paysan");
                                        alerte.setGraphic(new ImageView("Vue/images/pnj/paysan.jpg"));
                                        alerte.setContentText("écoute ... je n'ai plus rien à te donner. Continue ta route, " +
                                                "au passage tu peux taper dans les arbres pour faire tomber des pommes");
                                        alerte.show();
                                        System.out.println(((PersoNonJouable) objetEnvironnement.get(i)).tiensObjet());
                                    }else if(objetEnvironnement.get(i).getMapAction()=="map3"){
                                        Alert alerte=new Alert(Alert.AlertType.INFORMATION);
                                        alerte.setHeaderText("Jacob le paysan");
                                        alerte.setGraphic(new ImageView("Vue/images/pnj/paysan.jpg"));
                                        alerte.setContentText("satané squelette");
                                        alerte.show();
                                        System.out.println(((PersoNonJouable) objetEnvironnement.get(i)).tiensObjet());

                                    }

                                }
                            }else if(objetEnvironnement.get(i) instanceof Coffre) {
                                if (env.getInventaire().inventairePossede("key")) {
                                    if (((Coffre) objetEnvironnement.get(i)).tiensObjet()) {
                                        inventaire.addObjet(((Coffre) objetEnvironnement.get(i)).donObjet());
                                        objetEnvironnement.remove(objetEnvironnement.add(new Pomme(800, 800)));
                                    } else {
                                        System.out.println("ce coffre à déjà été ouvert");
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case P:
                perso.decrementerPv(10);
            break;
            case O:
                perso.augmenterPv(10);
            break;
            default:
                break;
        }
    }

}
