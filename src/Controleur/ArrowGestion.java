package Controleur;

import Modèle.Personnage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;


public class ArrowGestion implements EventHandler<KeyEvent> {

    @FXML
    private Personnage perso;

    public ArrowGestion(Personnage p) {
        this.perso = p;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if (keyEvent.getCode() == KeyCode.UP) {
            this.perso.setY(this.perso.getY() - 1);
            System.out.println(perso.getY());
        }
        if (keyEvent.getCode().equals(KeyCode.DOWN)) {
            System.out.println("sheesh");
            this.perso.setY(this.perso.getY() + 1);
            System.out.println(perso.getY());

        }
        if (keyEvent.getCode() == KeyCode.LEFT) {
            this.perso.setX(this.perso.getX() - 1);
            System.out.println(perso.getX());

        }
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            this.perso.setX(this.perso.getX() + 1);
            System.out.println(perso.getX());
        }
    }
}
