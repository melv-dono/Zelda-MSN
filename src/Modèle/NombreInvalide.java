package Modèle;

public class NombreInvalide extends Exception{
    public NombreInvalide(){
        super("Une valeur n'est pas correcte. 0<LARGEUR<1200 // 0<HAUTEUR<720");
    }
    public NombreInvalide(String msg){
        super(msg);
    }
}
