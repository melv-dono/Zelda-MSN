package Controleur;

import Modèle.Environnement;
import Modèle.Inventaire;
import Modèle.Objet;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class InventaireGestion implements EventHandler<MouseEvent> {
    private Inventaire inventaire;
    private ListView<String>listViewInventaire;
    private Environnement env;
    private Objet potion;

    public InventaireGestion(Inventaire inventaire, ListView<String>list, Environnement e,Objet potion){
        this.inventaire=inventaire;
        listViewInventaire=list;
        env=e;
        this.potion=potion;
    }
    public void ajoutPotion(Objet obj){
        inventaire.addObjet(obj);
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(inventaire.getTailleInventaire()>=1){
            if(listViewInventaire.getSelectionModel().getSelectedItem()==null){
                System.out.println("erroor");
            }else if(listViewInventaire.getSelectionModel().getSelectedItem().equals("potion")&& env.getLink().pv()<100) {
                env.getLink().augmenterPv(10);
                inventaire.removeObjet(potion);
            }else{
                System.out.println("ra");
            }
        }
    }
}
