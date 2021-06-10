import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class test {
    public static void main (String[] args) {
        Queue<Coor> queue = new ArrayDeque<Coor>();
        int[][] data = new int[4][4];
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<4; j++) {
                if (i == j && i!= 2) {
                    data[i][j] = 2;
                }
                else if (i==0 && j ==3) {
                    data[i][j] = 8;
                }
                else {
                    data[i][j]= 1;
                }
            }
        }

//        int cpt=0;
//        Coor cible = new Coor(0,2);
//        for (Coor voisins : cible.voisins()){
//            HashMap<Coor, Coor> child = new HashMap<Coor, Coor>(cible.voisins().size(), cible.voisins().size());
//            child.put(cible, voisins);
//            System.out.println(child.get(cible));
//        }
//        search(data);
        System.out.println(search2(data));
//        System.out.println(constituionDuChemin(search2(data)));


    }

    public static void search(int[][] donnne) {
        Queue<Coor> frontiere = new ArrayDeque<Coor>();
        ArrayList<Coor> marked = new ArrayList<Coor>();
        Coor depart = new Coor(0,3);
        Coor.setTree(depart);
        frontiere.add(depart);
        int cpt=0;
        while (!frontiere.isEmpty()) {

            System.out.println("Voisins n° " + cpt + " Queue: " + frontiere);
            Coor pointCourant = frontiere.poll();
            pointCourant.marked();
            System.out.println("GET " + pointCourant);

            if (donnne[pointCourant.getLigne()][pointCourant.getColonne()] == 8) {
                System.out.println("Trouvé");
                break;
            }
            else {
                for (Coor voisins : pointCourant.voisins()) {
                    if (!voisins.isMarked() && !marked.contains(voisins)) {
                        frontiere.add(voisins);
                        marked.add(voisins);
                    }
                }
            }
            cpt++;

        }
    }

    public static HashMap<Coor, Coor> search2(int[][] donnne) {
        Queue<Coor> frontiere = new ArrayDeque<Coor>();
//        ArrayList<HashMap<Coor, Coor>> provenances = new ArrayList<HashMap<Coor, Coor>>();
        Coor depart = new Coor(0,3);
        Coor.setTree(depart);
        frontiere.add(depart);
        HashMap<Coor, Coor> antecedent =new HashMap<Coor, Coor>();
        antecedent.put(depart, null);
//        provenances.add(origine);
        int cpt=0;

        while (!frontiere.isEmpty()) {

            System.out.println("Voisins n° " + cpt + " Queue: " + frontiere);
            Coor pointCourant = frontiere.poll();
            pointCourant.marked();
            System.out.println("GET " + pointCourant);

            if (donnne[pointCourant.getLigne()][pointCourant.getColonne()] == 8) {
                System.out.println("Trouvé");
                break;
            }
            else {

                for (Coor voisins : pointCourant.voisins()) {
                    if (!voisins.isMarked() && !antecedent.containsKey(voisins)) {
                        frontiere.add(voisins);
//                        lien.put(voisins, pointCourant);
                        antecedent.put(voisins, pointCourant);
                    }
                }
            }
            cpt++;
        }
        return antecedent;
    }


    public static ArrayList<Coor> constituionDuChemin(HashMap<Coor, Coor> antecedent) {
        Coor arrive = new Coor(3, 0);
        ArrayList<Coor> chemin = new ArrayList<Coor>();
        while (!arrive.isEqual(new Coor(0,3))) {
            chemin.add(arrive);
            arrive = antecedent.get(arrive);

            // regarde d'où provient arrive
//            if (provenances.containsValue(arrive)) {
//                System.out.println();
//                arrive = provenances.get(arrive);
//            }
        }
        return chemin;
    }
}
