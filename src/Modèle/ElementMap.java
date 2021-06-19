package Modèle;

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
    private String mapAction;
    static int num=0;

    /**
     * CONSTRUCTEUR
     */
    public ElementMap(String nom, double posLargeur, double posHauteur,String mapAction){
        this.nom=nom;
        positionLargeur=new SimpleDoubleProperty(posLargeur);
        positionHauteur=new SimpleDoubleProperty(posHauteur);
        this.mapAction=mapAction;
        id=num;
        num++;
    }
    public ElementMap(String nom, double posLargeur, double posHauteur){
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
    public String getMapAction(){
        return mapAction;
    }
}
