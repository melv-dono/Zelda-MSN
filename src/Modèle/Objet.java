package Modèle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Objet {
    /**
     * ATTRIBUTS
     */
    private String nom;
    private IntegerProperty positionLargeur;
    private IntegerProperty positionHauteur;
    private int pointsRegeneration;
    private Environnement env;

    /**
     * CONSTRUCTEUR
     */
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
    /**
     * GETTERS
     */

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
    /**
     * SETTERS
     */
    public void setPositionLargeur(double val){
        positionLargeur.setValue(val);
    }
    public void setPositionHauteur(double val){
        positionHauteur.setValue(val);
    }

    /**
     * METHODES
     */

    /**
     * FONCTION
     */
    public boolean ramasserObjet(double posLargeurLink,double posHauteurLink){
        if(positionLargeur.getValue()-posLargeurLink<=Parametre.PAS && positionHauteur.getValue()==posHauteurLink){
            return true;
        }else{
            System.out.println("vous ne pouvez ramassez aucun objet");
            return false;
        }
    }
    /**
     * TOSTRING
     */
    public String toString(){
        return nom;
    }


}
