package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Objet {
    private String nom;
    private IntegerProperty positionLargeur;
    private IntegerProperty positionHauteur;
    private int pointsRegeneration;
    private Environnement env;

    public Objet(String nom,int posLargeur,int posHauteur,int ptRegen,Environnement e){
        this.nom=nom;
        positionLargeur=new SimpleIntegerProperty(posLargeur);
        positionHauteur=new SimpleIntegerProperty(posHauteur);
        pointsRegeneration=ptRegen;
        env=e;
    }
    public Objet(String nom,Environnement e){
        this.nom=nom;
        this.env=e;
    }
    public String getNom(){
        return nom;
    }
    public IntegerProperty getPositionLargeur(){
        return positionLargeur;
    }
    public IntegerProperty getPositionHauteur(){
        return positionHauteur;
    }
    public int getPointsRegeneration(){
        return pointsRegeneration;
    }
    public boolean ramasserObjet(double posLargeurLink,double posHauteurLink){
        if(positionLargeur.getValue()-posLargeurLink<=32 && positionHauteur.getValue()==posHauteurLink){
            return true;
        }else{
            System.out.println("vous ne pouvez ramassez aucun objet");
            return false;
        }
    }

    public String toString(){
        return nom;
    }


}
