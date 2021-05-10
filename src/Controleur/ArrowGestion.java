package Controleur;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;


public class ArrowGestion implements EventHandler<KeyEvent> {

    @FXML
    private Circle cercle;

    public ArrowGestion(Circle c) {
        this.cercle = c;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if (keyEvent.getCode() == KeyCode.UP) {
            cercle.setTranslateY(cercle.getTranslateY() + 0.1);
            System.out.println(cercle.getCenterY());
        }
        if (keyEvent.getCode().equals(KeyCode.DOWN)) {
            System.out.println("sheesh");
            cercle.setTranslateY(cercle.getCenterY() - 0.1);
        }
        if (keyEvent.getCode() == KeyCode.LEFT) {
            cercle.setTranslateX(cercle.getTranslateX() - 0.1);
        }
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            cercle.setTranslateX(cercle.getTranslateX() + 0.1);
        }
    }
}
