package Modèle;

import java.util.HashMap;

public class Breteur extends Ennemi{
    private HashMap<Coordonnees, Coordonnees> chenim;
    public Breteur(String n,Environnement env) {
        super(n,1000,256, env, 100, 5, 5);
        chenim = new HashMap<Coordonnees,Coordonnees>();
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
            System.out.println(chemin);
            Coordonnees prochainPas = chemin.get(depart);
            System.out.println("Point de départ:" + depart);
            System.out.println(prochainPas);

            if (prochainPas != null) {
                setDeplacementLargeur(prochainPas.getColonne()*Parametre.TUILE_SIZE);
                setDeplacementHauteur(prochainPas.getLigne()*Parametre.TUILE_SIZE);
            }
        }

//        Coordonnees prochaineCase = chemin.get(depart);
//        System.out.println(prochaineCase);
    }

    @Override
    public void agir() {
        seDeplacer();
    }


}
