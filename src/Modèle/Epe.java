package Modèle;

public class Epe extends Arme{
    private String nom;
    private double pointAttaque;
    private Link perso;

    public Epe(String nom, double degat, Link perso) {
        super(nom, degat);
        this.perso = perso;
    }

    @Override
    public void attaquer() {
        double dommage = getPointAttaque() + this.perso.getPointAttaque();
        for (Personnage p : this.perso.getEnv().getPerso()) {
            if (p instanceof Squelette && this.perso.getOrientation() =="monter") {
                if (cibleEnHaut(p)) {
                    p.perteDePv(dommage);
                }
            }
            if (p instanceof Squelette && this.perso.getOrientation() =="descendre") {
                if (cibleEnBas(p)) {
                    p.perteDePv(dommage);
                }
            }
            if (p instanceof Squelette && this.perso.getOrientation() =="gauche") {

                if (cibleSurLaGauche(p)) {
                    p.perteDePv(dommage);
                }
            }
            if (p instanceof Squelette && this.perso.getOrientation() =="droite") {
                if (cibleSurLaDroite(p)) {
                    p.perteDePv(dommage);
                }
            }
        }
    }

    public boolean cibleSurLaGauche(Personnage perso) {
        if(		(this.perso.getDeplacementHauteur()+Parametre.TUILE_SIZE>= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()>=this.perso.getDeplacementHauteur()) &&
                (this.perso.getDeplacementLargeur()-Parametre.TUILE_SIZE-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.perso.getDeplacementLargeur())
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleSurLaDroite(Personnage perso) {
        if( // Attention on a enlevé 1 car le squelette était pile sur la bordure de la tuile
                (this.perso.getDeplacementHauteur()+Parametre.TUILE_SIZE>= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.perso.getDeplacementHauteur()) &&
                        (this.perso.getDeplacementLargeur()+Parametre.TUILE_SIZE-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.perso.getDeplacementLargeur()+(Parametre.TUILE_SIZE*2)-1)
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleEnHaut(Personnage perso) {
        if(
                (this.perso.getDeplacementHauteur()-Parametre.TUILE_SIZE<= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.perso.getDeplacementHauteur()) &&
                        (this.perso.getDeplacementLargeur()-Parametre.TUILE_SIZE<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.perso.getDeplacementLargeur())
        )
        {
            return true;
        }
        else {return false;}
    }

    public boolean cibleEnBas(Personnage perso) {
        if( // Attention on a enlevé 1 car le squelette était pile sur la bordure de la tuile
                (this.perso.getDeplacementHauteur() + Parametre.TUILE_SIZE <= perso.getDeplacementHauteur() && perso.getDeplacementHauteur()<=this.perso.getDeplacementHauteur()+(Parametre.TUILE_SIZE*2)) &&
                        (this.perso.getDeplacementLargeur()-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur()<=this.perso.getDeplacementLargeur()+Parametre.TUILE_SIZE-1)
        )
        {
            return true;
        }
        else {return false;}
    }
}
