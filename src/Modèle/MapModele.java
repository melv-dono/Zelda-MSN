package Modèle;

public class MapModele { // on associera à chaque map un id qui nous permettra de charger une map en fonction de son id
    private int idMap;
    private int[] tableau;

    public MapModele(int id, int[] tab){
        this.idMap=id;
        tableau=tab;
    }

    public int[] getTableau() {
        return tableau;
    }
}
