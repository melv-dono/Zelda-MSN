package Modèle;

public class Epe extends Arme{
    private String nom;
    private double pointAttaque;
    private Link perso;

    public Epe(String nom, double degat, Link perso) {
        super(nom, degat);
        this.perso = perso;
    }

}
