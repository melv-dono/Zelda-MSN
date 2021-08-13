package Modèle.Perso;

import Modèle.Arme.BaguetteMagique;
import Modèle.Arme.Epe;
import Modèle.Env.Environnement;
import Modèle.Item.Personnage;
import Modèle.Utils.Parametre;
import javafx.beans.property.*;

public class Link extends Personnage {

    private Epe armePrincipale;
    private BaguetteMagique armeSecondaire;
    private boolean isMoving; // Dit si le perso est en cours d'animation ou pas.
    private IntegerProperty animationProperty;
    private int casesSansColisions = 3; // On le met à 4 quand link aura la flute.

    public Link(Environnement env, Epe e, BaguetteMagique b) {
        super("Link", env, 100, 10, 0);
        this.isMoving = false;
        this.animationProperty = new SimpleIntegerProperty(Parametre.ATTAQUE_ANIMATION);
        this.armePrincipale = e;
        this.armeSecondaire = b;
    }

    /**
     * Méthode qui retourne l'arme actuelle du personnage Link
     * @return
     */
    public Epe getArmePrincipale() {
        return armePrincipale;
    }

    /**
     * Méthode qui retourne l'arme secondaire
     * @return
     */
    public BaguetteMagique getarmeSecondaire() {
        return armeSecondaire;
    }

    public int getAnimationProperty() {
        return animationProperty.get();
    }

    public IntegerProperty animationPropertyProperty() {
        return animationProperty;
    }

    public double getDommageArmePrincipale() {
        return this.getPointAttaque() + this.getArmePrincipale().getPointAttaque();
    }

    public DoubleProperty getDommageArmePrincipaleProperty(){
        return this.getArmePrincipale().getPointAttaqueProperty();
    }

    public double getDommageArmeSecondaire() {
        return this.getPointAttaque() + this.getarmeSecondaire().getPointAttaque();
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setAnimationProperty(int animationProperty) {
        this.animationProperty.set(animationProperty);
    }

    public void setCasesSansColisionsDe1(){
        this.casesSansColisions++;
    }

    public void declencherAnimation() {
        if (this.isMoving && this.animationProperty.getValue() != 0) {
            this.animationProperty.setValue(this.animationProperty.getValue() - 1);
        }
        if (this.animationProperty.getValue() == 0) {
            this.isMoving = false;
            setAnimationProperty(Parametre.ATTAQUE_ANIMATION);
        }
    }

    public boolean collisionExterneEnv(double l,double h){
        for(int i=0;i<getEnv().getObjEnvAct().size();i++){
            if(getEnv().getObjEnvAct().size()>0){ //si obj dans l'env'
                if(getEnv().getObjEnvAct().get(i).positionH().getValue()==h && getEnv().getObjEnvAct().get(i).positionL().getValue()==l){ // coord identique
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Déplace Link vers le haut
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void monter() {
        int[][] carte = getEnv().getMapActuelle().getTableau();
        if((getDeplacementHauteur()/Parametre.TUILE_SIZE)-1<0){
        }else if (carte[(int) ((getDeplacementHauteur()/Parametre.TUILE_SIZE)-1)][(int) (getDeplacementLargeur()/Parametre.TUILE_SIZE)]
                <= casesSansColisions && collisionExterneEnv(getDeplacementLargeur(),
                getDeplacementHauteur()-Parametre.TUILE_SIZE)==true) {//deplacement vers le haut bloquer
            setDeplacementHauteur(getDeplacementHauteur() - Parametre.TUILE_SIZE);
        }
        super.setOrientation("monter");
    }

    /**
     * Déplace Link vers le bas
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void descendre() {
        int[][] carte = getEnv().getMapActuelle().getTableau();
        if(((getDeplacementHauteur()/Parametre.TUILE_SIZE)+1)>=Parametre.LIGNE){
        }else if (carte[(int) ((getDeplacementHauteur()/Parametre.TUILE_SIZE)+1)][(int) (getDeplacementLargeur()/Parametre.TUILE_SIZE)]
                <= casesSansColisions&&collisionExterneEnv(getDeplacementLargeur(),
                getDeplacementHauteur()+Parametre.TUILE_SIZE)==true)  {//deplacement vers le bas bloquer

            setDeplacementHauteur(getDeplacementHauteur() + Parametre.TUILE_SIZE);
        }
        super.setOrientation("descendre");
    }

    /**
     * Déplace Link vers la gauche
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void gauche() {
        int[][] carte = getEnv().getMapActuelle().getTableau();

        if((getDeplacementLargeur()/Parametre.TUILE_SIZE)-1<0){
        }else if (carte[(int) (getDeplacementHauteur()/Parametre.TUILE_SIZE)][(int) ((getDeplacementLargeur()/Parametre.TUILE_SIZE)-1)]
                <= casesSansColisions&&collisionExterneEnv(getDeplacementLargeur()-Parametre.TUILE_SIZE,
                getDeplacementHauteur())==true) {

            setDeplacementLargeur(getDeplacementLargeur() - Parametre.TUILE_SIZE);
        }
        super.setOrientation("gauche");
    }

    /**
     * Déplace Link vers la droite
     * Affiche les collisions si besoin
     * Affiche les déplacements
     */
    public void droite() {
        int[][] carte = getEnv().getMapActuelle().getTableau();
        if(((getDeplacementLargeur()/Parametre.TUILE_SIZE)+1)>=40){
        }else if (carte[(int) (getDeplacementHauteur()/Parametre.TUILE_SIZE)][(int) ((getDeplacementLargeur()/Parametre.TUILE_SIZE)+1)]
                <= casesSansColisions&&collisionExterneEnv(getDeplacementLargeur()+Parametre.TUILE_SIZE,
                getDeplacementHauteur())==true) {
            setDeplacementLargeur(getDeplacementLargeur() + Parametre.TUILE_SIZE);
        }
        super.setOrientation("droite");
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
                        (this.getDeplacementLargeur()+Parametre.TUILE_SIZE-1<= perso.getDeplacementLargeur() && perso.getDeplacementLargeur() <=
                                this.getDeplacementLargeur()+(Parametre.TUILE_SIZE*2)-1)
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
        if(this.getEnv().getEnnemiMap().size()>0){
            for(int i = 0; i<this.getEnv().getEnnemiMap().size(); i++ ){
                if (getEnv().getEnnemiMap().get(i) instanceof Squelette && this.getOrientation() =="monter") {
                    if (cibleEnHaut(getEnv().getEnnemiMap().get(i))) {
                        getEnv().getEnnemiMap().get(i).perteDePv(getDommageArmePrincipale());
                        if(getEnv().getEnnemiMap().get(i).retirerEnv()){
                            i--;
                        }
                    }
                }
                if (getEnv().getEnnemiMap().get(i) instanceof Squelette && this.getOrientation() =="descendre") {
                    if (cibleEnBas(getEnv().getEnnemiMap().get(i))) {
                        getEnv().getEnnemiMap().get(i).perteDePv(getDommageArmePrincipale());
                        if(getEnv().getEnnemiMap().get(i).retirerEnv()){
                            i--;
                        }
                    }
                }
                if (getEnv().getEnnemiMap().get(i) instanceof Squelette && this.getOrientation() =="gauche") {

                    if (cibleSurLaGauche(getEnv().getEnnemiMap().get(i))) {
                        getEnv().getEnnemiMap().get(i).perteDePv(getDommageArmePrincipale());
                        if(getEnv().getEnnemiMap().get(i).retirerEnv()){
                            i--;
                        }
                    }
                }
                if (getEnv().getEnnemiMap().get(i) instanceof Squelette && this.getOrientation() =="droite") {
                    if (cibleSurLaDroite(getEnv().getEnnemiMap().get(i))) {
                        getEnv().getEnnemiMap().get(i).perteDePv(getDommageArmePrincipale());
                        if(getEnv().getEnnemiMap().get(i).retirerEnv()){
                            i--;
                        }
                    }
                }
            }
        }
    }

}
