package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;

public class Soldat extends Ennemi{
    static int deplacementComplet;
    private IntegerProperty animationProperty;


    public Soldat(Environnement env, double x, double y, String m) {
        super("Soldat",x,y, env, 100, 15, 5, m);
        deplacementComplet=0;
        this.animationProperty = new SimpleIntegerProperty(30);
    }

    public int getAnimationProperty() {
        return animationProperty.get();
    }

    public IntegerProperty animationPropertyProperty() {
        return animationProperty;
    }

    public void setAnimationProperty(int animationProperty) {
        this.animationProperty.set(animationProperty);
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

        if (!depart.isEqual(cible)) {
            HashMap<Coordonnees, Coordonnees> chemin = getEnv().bfs(cible);
            if (deplacementComplet % 16 == 0 ) {
                Coordonnees prochainPas = chemin.get(depart);

                if (prochainPas != null && !collisionLink(prochainPas)) {
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

    public void declencherAnimation() {
        if (this.animationProperty.getValue() != 0) {
            this.animationProperty.setValue(this.animationProperty.getValue() - 1);
        }
        if (this.animationProperty.getValue() == 0) {
            coupEpe();
            setAnimationProperty(60);
        }
    }

    public boolean collisionLink(Coordonnees pointeur){
        int x = (int) getEnv().getLink().getDeplacementLargeur()/32;
        int y = (int) getEnv().getLink().getDeplacementHauteur()/32;
        Coordonnees link = new Coordonnees(x,y);
        return link.isEqual(pointeur) ;
    }

    @Override
    public void agir() {
        seDeplacer();
        declencherAnimation();
    }


}
