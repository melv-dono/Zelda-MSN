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

    /**
     * CONSTRUCTEUR
     */
    public Objet(String nom,int posLargeur,int posHauteur){
        this.nom=nom;
        positionLargeur=new SimpleIntegerProperty(posLargeur);
        positionHauteur=new SimpleIntegerProperty(posHauteur);
    }
    public Objet(String nom,Environnement e){
        this.nom=nom;
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
