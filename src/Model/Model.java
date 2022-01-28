package Model;

import java.awt.*;

/** Le modele décrit le modèle (la fenetre) et Flappy */
public class Model {
    /**Attributs: coordonnées de flappy*/
    private int FlappyX =ScreenWidth/2-FlappyWidth/2;
    private int FlappyY =ScreenHeight/2-FlappyHeight/2;
    private Path path;
    private int collisions;

    /**Variables: hauteur et largeur de flappy*/
    public static final int ScreenHeight=600;
    public static final int ScreenWidth=1000;
    public static final int FlappyHeight =100;
    public static final int FlappyWidth =150;

    /**Variables: Saut et Descente de flappy*/
    public static final int Jump =50;
    public static final int Fall =10;

    /**Constructor */
    public Model(Path p){
        this.path=p;
    }

    /**Getter de coordonne en X de flappy*/
    public int getFlappyX() {
        return this.FlappyX;
    }
    /**Getter de coordonne en Y de flappy*/
    public int getFlappyY() {
        return this.FlappyY;
    }

    public int getCollisions(){return this.collisions;}

    public void checkCollision() {
        /**Checks for collisions only if the game has started
         *   REMINDER: game start is detected with a positive score from the path.getScore function
         *   REMINDER: the score is initialized at -5, when it reaches 0 the game begins
         */
        if (this.path.getScore() > 0){
            //On obtient nos points 2 points: celui avant et celui apres flappy
            // pour ensuite calculer la collision avec un calcul de droite
            int i = 0;
            while (this.path.getPointList().get(i).x < this.FlappyX) {
                i++;
            }
            Point pointAhead = new Point(this.path.getPointList().get(i));
            Point pointBehind = new Point(this.path.getPointList().get(i - 1));
            //System.out.println("Flappy x: "+FlappyX);
            //System.out.println("Coord behind and ahead:"+pointBehind.x +","+ pointAhead.x);
            float p = (pointAhead.y - pointBehind.y) / (float) (pointAhead.x - pointBehind.x);
            float y = pointBehind.y + p * (getFlappyX() - pointBehind.x);
            boolean collisionCondition = y > (getFlappyY()) && y < (getFlappyY() + FlappyHeight);
            if (!collisionCondition) {
                this.collisions += 1;
            }
        }
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
        if (delta < ScreenHeight-FlappyHeight) {
            FlappyY =delta;
        }
    }

}