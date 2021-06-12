package Modèle;

import java.util.HashMap;

public class Breteur extends Ennemi{
    static int deplacementComplet;
    private int tpsChargement;

    public Breteur(String n,Environnement env) {
        super(n,1000,256, env, 100, 15, 5);
        deplacementComplet=0;
        this.tpsChargement = 30;
    }

    /**
     * Déplace le squelette vers le haut
     */
    public void monter(){
        this.setDeplacementHauteur(this.getDeplacementHauteur()-Parametre.PAS);
    }

    /**
     * Déplace le squelette vers le bas
     */
    public void descendre(){
        this.setDeplacementHauteur(this.getDeplacementHauteur()+Parametre.PAS);
    }

    /**
     * Déplace le squelette vers la gauche
     */
    @Override
    public void gauche() {
        this.setDeplacementLargeur(this.getDeplacementLargeur()-Parametre.PAS);
    }

    /**
     * Déplace le squelette vers la droite
     */
    @Override
    public void droite() {
        this.setDeplacementLargeur(this.getDeplacementLargeur()+Parametre.PAS);
    }

    public void seDeplacer() {
        int cibleX = (int) getEnv().getLink().getDeplacementLargeur()/32;
        int cibleY = (int) getEnv().getLink().getDeplacementHauteur()/32;
        Coordonnees depart = new Coordonnees((int)getDeplacementLargeur()/32, (int) getDeplacementHauteur()/32);
        Coordonnees cible = new Coordonnees(cibleX, cibleY);



//        System.out.println("Coordonne cible:" + cible);
//        System.out.println("Coordonne perso:" + depart);
        if (!depart.isEqual(cible)) {
            HashMap<Coordonnees, Coordonnees> chemin = getEnv().bfs(cible);
            if (deplacementComplet % 16 == 0 ) {
                Coordonnees prochainPas = chemin.get(depart);

                if (prochainPas != null) {
                    setDeplacementLargeur((prochainPas.getColonne() * Parametre.TUILE_SIZE) + (Parametre.TUILE_SIZE/16));
                    setDeplacementHauteur((prochainPas.getLigne() * Parametre.TUILE_SIZE) + (Parametre.TUILE_SIZE/16));
                }
            }
            else {
                setDeplacementLargeur((depart.getColonne() * Parametre.TUILE_SIZE) + (Parametre.TUILE_SIZE/16));
                setDeplacementHauteur((depart.getLigne() * Parametre.TUILE_SIZE) + (Parametre.TUILE_SIZE/16));
            }
            deplacementComplet++;
        }

//        Coordonnees prochaineCase = chemin.get(depart);
//        System.out.println(prochaineCase);
    }

    public boolean cibleSurLaGauche(Personnage perso) {
        if(		(this.getDeplacementHauteur()+Parametre.TUILE_SIZE>= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()>=this.getDeplacementHauteur()) &&
                (this.getDeplacementLargeur()-Parametre.TUILE_SIZE-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur())
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleSurLaDroite(Personnage perso) {
        if( // Attention on a enlevé 1 car le squelette était pile sur la bordure de la tuile
                (this.getDeplacementHauteur()+Parametre.TUILE_SIZE>= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.getDeplacementHauteur()) &&
                        (this.getDeplacementLargeur()+Parametre.TUILE_SIZE-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur()+(Parametre.TUILE_SIZE*2)-1)
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleEnHaut(Personnage perso) {
        if(
                (this.getDeplacementHauteur()-Parametre.TUILE_SIZE<= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.getDeplacementHauteur()) &&
                        (this.getDeplacementLargeur()-Parametre.TUILE_SIZE<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur())
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleEnBas(Personnage perso) {
        if( // Attention on a enlevé 1 car le squelette était pile sur la bordure de la tuile
                (this.getDeplacementHauteur() + Parametre.TUILE_SIZE <= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.getDeplacementHauteur()+(Parametre.TUILE_SIZE*2)) &&
                        (this.getDeplacementLargeur()-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.getDeplacementLargeur()+Parametre.TUILE_SIZE-1)
        )
        {
            return true;
        }
        else {return false;}
    }

    public void coupEpe() {
        for (Personnage p : this.getEnv().getPerso()) {
            if (p instanceof Link && this.getOrientation() =="monter") {
                if (cibleEnHaut(p)) {
                    p.perteDePv(getPointAttaque());
                }
            }
            if (p instanceof Link && this.getOrientation() =="descendre") {
                if (cibleEnBas(p)) {
                    p.perteDePv(getPointAttaque());
                }
            }
            if (p instanceof Link && this.getOrientation() =="gauche") {
                if (cibleSurLaGauche(p)) {
                    p.perteDePv(getPointAttaque());
                }
            }
            if (p instanceof Link && this.getOrientation() =="droite") {
                if (cibleSurLaDroite(p)) {
                    p.perteDePv(getPointAttaque());
                }
            }
        }
    }

    @Override
    public void agir() {
        seDeplacer();
        if (this.tpsChargement == 0) {
            coupEpe();
            this.tpsChargement=30;
        }
        this.tpsChargement--;
    }


}
