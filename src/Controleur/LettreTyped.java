package Controleur;

import Modèle.Link;
import Modèle.Personnage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LettreTyped implements EventHandler<KeyEvent> {

    @FXML
    private VBox menuPause;
    @FXML
    private Personnage perso;

    public LettreTyped(Link p, VBox vb) {
        this.perso = p;
        menuPause=vb;
        menuPause.setVisible(false);
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCharacter()) {
            case "i":
                menuPause.setVisible(true);
                break;
            case "q":
                menuPause.setVisible(false);
                break;
            case "a":
                this.perso.attaquer();
                break;
            default:
                break;
        }
    }
}
