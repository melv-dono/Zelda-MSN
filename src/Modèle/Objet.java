package Modèle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Objet {
    /**
     * ATTRIBUTS
     */

    private String nom;
    private DoubleProperty positionLargeur;
    private DoubleProperty positionHauteur;
    private int id;
    static int num=0;

    /**
     * CONSTRUCTEUR
     */
    public Objet(String nom,double posLargeur,double posHauteur){
        this.nom=nom;
        positionLargeur=new SimpleDoubleProperty(posLargeur);
        positionHauteur=new SimpleDoubleProperty(posHauteur);
        id=num;
        num++;

    }
    /**
     * GETTERS
     */
    public int getId(){
        return id;
    }

    public String getNom(){
        return nom;
    }
    public DoubleProperty getPositionLargeur(){
        return positionLargeur;
    }
    public DoubleProperty getPositionHauteur(){
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
    public void changeCoord(double posL,double posH){
        positionLargeur.setValue(posL);
        positionHauteur.setValue(posH);
    }

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




}
