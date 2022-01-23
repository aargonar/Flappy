package Model;
import View.View;

import java.awt.*;
import java.util.ArrayList;

public class Model {
    /**Attributs: coordonnées de flappy*/
    private int FlappyX =View.ScreenWidth/2-FlappyWidth/2;
    private int FlappyY =View.ScreenHeight/2-FlappyHeight/2;

    /**Variables: hauteur et largeur de flappy*/
    public static final int FlappyHeight =100;
    public static final int FlappyWidth =200;

    /**Variables: Saut et Descente de flappy*/
    public static final int Jump =50;
    public static final int Fall =25;

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

    public void moveDownn(){
        int delta= FlappyY + Fall;
        if (delta < View.ScreenHeight-FlappyHeight) {
            FlappyY =delta;
        }
    }

}