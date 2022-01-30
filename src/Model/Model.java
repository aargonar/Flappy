package Model;

import java.awt.*;

/** Le modele décrit le modèle (la fenetre) et Flappy */
public class Model {
    /**Attributs: coordonnées de flappy*/
    private int FlappyX;
    private int FlappyY;

    /**Variables: hauteur et largeur de flappy*/
    public static final int ScreenHeight=600;
    public static final int ScreenWidth=1000;
    public static final int FlappyHeight =100;
    public static final int FlappyWidth =150;

    /**Variables: Saut et Descente de flappy*/
    public static final int Jump =50;
    public static final int Fall =10;

    /**Constructor */
    public Model(){
        FlappyX =ScreenWidth/5 - FlappyWidth/2;
        FlappyY =ScreenHeight/2-FlappyHeight/2;
    }

    /**Getter de coordonne en X de flappy*/
    public int getFlappyX() {
        return this.FlappyX;
    }
    /**Getter de coordonne en Y de flappy*/
    public int getFlappyY() {
        return this.FlappyY;
    }

    /** Methode qui modifie hauteur de flappy si les contraintes definies sur affichage verif*/
    public void jump() {
        int delta= FlappyY - Jump;
        if (delta >=0 ) {
            FlappyY =delta;
        }
    }
//TODO: ADD COMMENT HERE
    public void moveDown(){
        int delta= FlappyY + Fall;
        if (delta < ScreenHeight-FlappyHeight) {
            FlappyY =delta;
        }
    }

}