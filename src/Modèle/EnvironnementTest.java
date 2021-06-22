package Modèle;

import Modèle.Env.Arbre;
import Modèle.Env.ElementMap;
import Modèle.Item.Pioche;
import Modèle.Item.Potion;
import Modèle.Env.Rocher;
import org.junit.jupiter.api.Test;
//import org.junit.Before;
//import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EnvironnementTest {
    private ArrayList<ElementMap> objetEnvironnement = new ArrayList<>();

//    @Before
    public void init() throws Exception {
        Pioche pioche=new Pioche(840,160,"map1");
        Potion potion=new Potion(520,608,"map1");
        Rocher rocher = new Rocher(392,608,"map1");
        objetEnvironnement.add(pioche);
        objetEnvironnement.add(potion);
        objetEnvironnement.add(rocher);
    }

    @Test
    public void testTaille(){
        assertEquals(3,objetEnvironnement.size());
    }

    @Test
    public void testAjout() throws Exception {
        Arbre arbre=new Arbre(488,160,3,"map1");
        objetEnvironnement.add(arbre);
        assertEquals(4,objetEnvironnement.size());
    }

    @Test
    public void testSuppression(){
        objetEnvironnement.remove(1);
        assertEquals(2,objetEnvironnement.size());
    }
}
