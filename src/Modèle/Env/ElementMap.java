package Modèle.Env;

import Modèle.Utils.Parametre;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class ElementMap {
    /**
     * ATTRIBUTS
     */

    private String nom;
    private DoubleProperty positionLargeur;
    private DoubleProperty positionHauteur;
    private int id;
    private boolean interaction;
    private String mapAction;

    static int num=0;

    /**
     * CONSTRUCTEUR
     */
    public ElementMap(String nom, double posLargeur, double posHauteur,String mapAction){
        this.nom=nom;
        positionLargeur=new SimpleDoubleProperty(posLargeur);
        positionHauteur=new SimpleDoubleProperty(posHauteur);
        interaction=false;
        this.mapAction=mapAction;
        id=num;
        num++;
    }
    public ElementMap(String nom, double posLargeur, double posHauteur){
        this.nom=nom;
        positionLargeur=new SimpleDoubleProperty(posLargeur);
        positionHauteur=new SimpleDoubleProperty(posHauteur);
        interaction=false;
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
        if(positionLargeur.getValue()-posLargeurLink<= Parametre.PAS && positionHauteur.getValue()==posHauteurLink){
            return true;
        }else{
            System.out.println("vous ne pouvez ramassez aucun objet");
            return false;
        }
    }
    public void setInteraction(){
        interaction=true;
    }


    public String getMapAction(){
        return mapAction;
    }
}
