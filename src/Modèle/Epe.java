package Modèle;

public class Epe {
    private String nom;
    private double pointAttaque;

    public Epe(String nom, double degat) {
        this.nom = nom;
        this.pointAttaque = degat;
    }

    public String getNom() {
        return nom;
    }

    public double getPointAttaque() {
        return pointAttaque;
    }
}
