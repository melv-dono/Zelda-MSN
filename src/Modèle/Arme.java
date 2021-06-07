package Modèle;

public abstract class Arme {
    private String nom;
    private double pointAttaque;

    public Arme(String nom, double pointAttaque) {
        this.nom = nom;
        this.pointAttaque = pointAttaque;
    }

    public String getNom() {
        return nom;
    }

    public double getPointAttaque() {
        return pointAttaque;
    }
}
