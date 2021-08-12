package Modèle.test;

import Modèle.Environnement;
import Modèle.Parametre;
import Modèle.perso.Personnage;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkTest {
    @Test
    public void deplacementTest() throws Exception {
        Environnement env = new Environnement(1,"map1");
        Personnage pers = new Personnage("perso1", env,  20, 0) {
            @Override
            public void monter() {
                setDeplacementHauteur(getDeplacementHauteur() - Parametre.TUILE_SIZE);
            }

            @Override
            public void descendre() {
                setDeplacementHauteur(getDeplacementHauteur() + Parametre.TUILE_SIZE);
            }

            @Override
            public void gauche() {
                setDeplacementLargeur(getDeplacementLargeur() - Parametre.TUILE_SIZE);
            }

            @Override
            public void droite() {
                setDeplacementLargeur(getDeplacementLargeur() + Parametre.TUILE_SIZE);
            }
        };

        pers.descendre();
        assertEquals(64,pers.getDeplacementHauteur());
        pers.gauche();
        pers.gauche();
        assertEquals(456, pers.getDeplacementLargeur());
        pers.descendre();
        pers.descendre();
        pers.descendre();
        pers.monter();
        assertEquals(128, pers.getDeplacementHauteur());
        pers.droite();
        assertEquals(488, pers.getDeplacementLargeur());

    }
}
