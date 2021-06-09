package Controleur;

import Modèle.*;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LettreTyped implements EventHandler<KeyEvent> {

    @FXML
    private VBox menuPause;
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


    public LettreTyped(VBox vb, Pane map, Timeline t, Environnement environnement) {
        this.perso = environnement.getLink();
        menuPause=vb;
        plateau=map;
        menuPause.setVisible(false);
        this.gameloop = t;
        objetEnvironnement=environnement.getObjetEnvironnement();
        inventaire=environnement.getInventaire();
        env=environnement;

    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case I:
                menuPause.setVisible(true);
                break;
            case Q:
                menuPause.setVisible(false);
                break;
            case D:
                this.perso.getArmePrincipale().attaquer();
                this.perso.setMoving(true);
                break;
            case A:
                this.perso.getarmeSecondaire().creeBoule();
                break;
            case R:
                System.out.println(objetEnvironnement.size());
                if(objetEnvironnement.size()>=1){
                    for(int i=0;i<objetEnvironnement.size();i++){
                        if(((objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()>=-32 &&objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()<=32) && objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()==0) || ((objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()<=32&&objetEnvironnement.get(i).getPositionLargeur().getValue()-perso.getDeplacementLargeur()>=-32)&& objetEnvironnement.get(i).getPositionHauteur().getValue()-perso.getDeplacementHauteur()==0) ){
                            if(objetEnvironnement.get(i) instanceof ObjetRamassable){
                                inventaire.addObjet(objetEnvironnement.get(i));
                                objetEnvironnement.remove(objetEnvironnement.get(i));
                            }else if(objetEnvironnement.get(i) instanceof Rocher){
                                if(env.getInventaire().inventairePossede("pioche")){
                                    objetEnvironnement.remove(objetEnvironnement.get(i));
                                }
                            }else if(objetEnvironnement.get(i) instanceof Arbre){
                                if(((Arbre) objetEnvironnement.get(i)).getNbPomme()>0){
                                    if(objetEnvironnement.get(i).getPositionLargeur().getValue()- perso.getDeplacementLargeur()==32){
                                        Pomme pomme=new Pomme(objetEnvironnement.get(i).getPositionLargeur().getValue()+32, objetEnvironnement.get(i).getPositionHauteur().getValue());
                                        env.getObjetEnvironnement().add(pomme);
                                        ((Arbre) objetEnvironnement.get(i)).retirerPomme();
                                    }else if(objetEnvironnement.get(i).getPositionLargeur().getValue()- perso.getDeplacementLargeur()==-32){
                                        Pomme pomme=new Pomme(objetEnvironnement.get(i).getPositionLargeur().getValue()-32, objetEnvironnement.get(i).getPositionHauteur().getValue());
                                        env.getObjetEnvironnement().add(pomme);
                                        ((Arbre) objetEnvironnement.get(i)).retirerPomme();
                                    }else if(objetEnvironnement.get(i).getPositionHauteur().getValue()- perso.getDeplacementHauteur()==32){
                                        Pomme pomme=new Pomme(objetEnvironnement.get(i).getPositionLargeur().getValue(), objetEnvironnement.get(i).getPositionHauteur().getValue()+32);
                                        env.getObjetEnvironnement().add(pomme);
                                        ((Arbre) objetEnvironnement.get(i)).retirerPomme();
                                    }else{
                                        Pomme pomme=new Pomme(objetEnvironnement.get(i).getPositionLargeur().getValue(), objetEnvironnement.get(i).getPositionHauteur().getValue()-32);
                                        env.getObjetEnvironnement().add(pomme);
                                        ((Arbre) objetEnvironnement.get(i)).retirerPomme();
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
                                if(((PersoNonJouable) objetEnvironnement.get(i)).persoTiensObjet()){
                                    Alert alerte=new Alert(Alert.AlertType.INFORMATION);
                                    alerte.setHeaderText("Bob le paysan");
                                    alerte.setContentText("salut prends donc cette clé elle te sera utile");
                                    alerte.setGraphic(new ImageView("Vue/paysan.jpg"));
                                    alerte.show();
                                    inventaire.addObjet(((PersoNonJouable) objetEnvironnement.get(i)).donObjet());
                                }else{
                                    Alert alerte=new Alert(Alert.AlertType.INFORMATION);
                                    alerte.setHeaderText("Bob le paysan");
                                    alerte.setGraphic(new ImageView("Vue/paysan.jpg"));
                                    alerte.setContentText("écoute ... je n'ai plus rien à te donner");
                                    alerte.show();
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
