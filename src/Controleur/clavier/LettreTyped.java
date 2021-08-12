package Controleur.clavier;

import Modèle.*;
import Modèle.Objet.objExt.Arbre;
import Modèle.Objet.objExt.Coffre;
import Modèle.Objet.objExt.Rocher;
import Modèle.Objet.ramassable.ObjetRamassable;
import Modèle.Objet.ramassable.Pomme;
import Modèle.perso.Link;
import Modèle.perso.PersoNonJouable;
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

    private ObservableList<ElementMap> objetEnvironnement;

    @FXML
    private Environnement env;
    @FXML
    private Inventaire inventaire;


    public LettreTyped(VBox vb, Environnement environnement, VBox vb2) {
        this.perso = environnement.getLink();
        menuPause=vb;
        menuAide=vb2;
        menuPause.setVisible(false);
        menuAide.setVisible(true);
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
                                    alertInfo("Link","Vue/images/link/linkito.jpg","hum... ce caillou me bloque la route mais il semble être cassable");
                                }
                            }else if(objetEnvironnement.get(i) instanceof Arbre){
                                if(((Arbre) objetEnvironnement.get(i)).getNbPomme()>0){
                                    if(objetEnvironnement.get(i).getPositionLargeur().getValue()- perso.getDeplacementLargeur()==32){
                                        gestionPomme(objetEnvironnement.get(i).getPositionLargeur().getValue()+32,objetEnvironnement.get(i).getPositionHauteur().getValue(), (Arbre) objetEnvironnement.get(i));
                                    }else if(objetEnvironnement.get(i).getPositionLargeur().getValue()- perso.getDeplacementLargeur()==-32){
                                        gestionPomme(objetEnvironnement.get(i).getPositionLargeur().getValue()-32,objetEnvironnement.get(i).getPositionHauteur().getValue(), (Arbre) objetEnvironnement.get(i));
                                    }else if(objetEnvironnement.get(i).getPositionHauteur().getValue()- perso.getDeplacementHauteur()==32){
                                        gestionPomme(objetEnvironnement.get(i).getPositionLargeur().getValue(),objetEnvironnement.get(i).getPositionHauteur().getValue()+32, (Arbre) objetEnvironnement.get(i));
                                    }else{
                                        gestionPomme(objetEnvironnement.get(i).getPositionLargeur().getValue(),objetEnvironnement.get(i).getPositionHauteur().getValue()-32, (Arbre) objetEnvironnement.get(i));
                                    }
                                }
                            }else if(objetEnvironnement.get(i) instanceof PersoNonJouable){
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
                                        alertInfo("Bob le paysan","Vue/images/pnj/paysan.jpg","salut prends donc cette clé elle te sera utile");
                                        inventaire.addObjet(((PersoNonJouable) objetEnvironnement.get(i)).donObjet());
                                    }else if(objetEnvironnement.get(i).getMapAction()=="map3"){
                                        if(env.taillePersoMapActu()>0){
                                            alertInfo("Jacob le paysan","Vue/images/pnj/paysan.jpg","stp aide moi à me débarasser des squelettes en échange je te donnerai un objet spéciale");
                                        }else{
                                            alertInfo("Jacob le Paysan","Vue/images/pnj/paysan.jpg","Merci prends donc ça");
                                            inventaire.addObjet(((PersoNonJouable) objetEnvironnement.get(i)).donObjet());
                                            this.perso.setCasesSansColisionsDe1();
                                        }

                                    }

                                }else{
                                    if(objetEnvironnement.get(i).getMapAction()=="map1"){
                                        alertInfo("Bob le paysan","Vue/images/pnj/paysan.jpg","je n'ai plus rien à te donner, tape dans les arbres si tu veux des pommes");
                                    }else if(objetEnvironnement.get(i).getMapAction()=="map3"){
                                        alertInfo("Jacob le paysan","Vue/images/pnj/paysan.jpg","satané squelette");

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
            default:
                break;
        }
    }


    private void alertInfo(String nom,String url,String txt){
        Alert alerte=new Alert(Alert.AlertType.INFORMATION);
        alerte.setHeaderText(nom);
        alerte.setGraphic(new ImageView(url));
        alerte.setContentText(txt);
        alerte.show();
    }

    private void gestionPomme(double posL,double posH,Arbre obj){
        Pomme pomme=new Pomme(posL,posH);
        env.getObjEnvAct().add(pomme);
        obj.retirerPomme();
    }

}
