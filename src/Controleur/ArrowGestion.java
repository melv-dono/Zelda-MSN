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
        if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.Z) {
            this.perso.setY(this.perso.getY() - 2);
            System.out.println(perso.getY());
        }
        if (keyEvent.getCode().equals(KeyCode.DOWN) || keyEvent.getCode() == KeyCode.S) {
            System.out.println("sheesh");
            this.perso.setY(this.perso.getY() + 2);
            System.out.println(perso.getY());

        }
        if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.Q) {
            this.perso.setX(this.perso.getX() - 2);
            System.out.println(perso.getX());

        }
        if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
            this.perso.setX(this.perso.getX() + 2);
            System.out.println(perso.getX());
        }
    }
}
