package Controleur;

import Modèle.Environnement;
import Modèle.Inventaire;
import Modèle.ElementMap;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class InventaireGestion implements EventHandler<MouseEvent> {
    private Inventaire inventaire;
    private ListView<String>listViewInventaire;
    private Environnement env;


    public InventaireGestion(ListView<String>list, Environnement e){
        this.inventaire=e.getInventaire();
        listViewInventaire=list;
        env=e;
    }
    public void ajoutPotion(ElementMap obj){
        inventaire.addObjet(obj);
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(inventaire.getTailleInventaire()>=1){
            if(listViewInventaire.getSelectionModel().getSelectedItem()==null){
                System.out.println("");
            }else if(listViewInventaire.getSelectionModel().getSelectedItem().equals("potion")&& env.getLink().pv()<100) {
                env.getLink().augmenterPv(10);
                inventaire.deleteObjetString("potion");
            }else if(listViewInventaire.getSelectionModel().getSelectedItem().equals("pomme")&& env.getLink().pv()<100){
                env.getLink().augmenterPv(5);
                inventaire.deleteObjetString("pomme");
            }
            else if(listViewInventaire.getSelectionModel().getSelectedItem().equals("bouclier")){
                System.out.println("ekiped");
                inventaire.deleteObjetString("bouclier");
            }else{
                System.out.println("Controleur.InventaireGestion.RAAAAAAAAAAAAAAAAA");
            }
        }
    }
}
