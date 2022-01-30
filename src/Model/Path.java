package Model;
import Controller.RefreshPath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Path {
    /**Attributs */
    //Liste de points
    private ArrayList<Point> pointList= new ArrayList<>();
    //Position or Score
    private int position;
    //Le score basé sur le nombre de points de la ligne brisée dépasés par Flappy
    private int score;
    //Attribut du model
    private Model model;
    private int collisions;

    /**Variables */
    //Distance entre chaque point de la liste pointList
    public static int PointSpacing = Model.ScreenWidth/5;
    //Distance entre chaque moveForward de Flappy
    public static int StepDistance= PointSpacing/25;
    //tirage aleatoire
    public static final Random rand= new Random();
    //Nombre de collisons avant d'arreter le jeu
    public static int maxNumberOfCollisions= 25;


    /**Constructeur initialise le Path avec les PointSpacing entre chaque point et cree le Thread qui va refraichir le path*/
    public Path(Model m){
        //Initialisation des attributs
        this.model=m;
        this.collisions=0;

        //initialisation de pointList
        for(int x = -PointSpacing; x<=model.ScreenWidth+ PointSpacing; x+= PointSpacing){
            int y= (int) (rand.nextInt((int) ((model.ScreenHeight*0.6-model.ScreenHeight*0.4)+1))+model.ScreenHeight*0.4);
            this.pointList.add(new Point(x,y));
        }
        //initialise les attributs
        this.score=-5;

        this.position=0;
        //Creation du thread qui gere le rafraichissement du path
        (new RefreshPath(this)).start();
    }
    /**Getter de PointList*/
    public ArrayList<Point> getPointList(){
        return this.pointList;
    }

    /**Getter du score
     * Si le score est inferieur a 0 on return
     * */
    public int getScore(){return this.score;}

    public int getCollisions(){return this.collisions;}


    /**Methode update path appelée dans le thread RefreshPath: elle met a jour l'attribut pointList pour qu'il ne contienne que
     * les points qui sont affichables à l'ecran après chaque appel a moveForward
     */
    public void updatePath(){
        //Liste des points visibles qui sera la nouvelle pointList
       ArrayList<Point> visiblePoints= (ArrayList<Point>) this.pointList.clone();

        //Si flappy se trouve dans un point alors on ajoute un point a la fin de la liste et on elimine le premier
        //Flappy se trouve dans un point si la position de flappy est un multiple de PointSpacing
        if((this.position%PointSpacing)==0){
            this.score+=1;
           //Removes the first element of the array
            visiblePoints.remove(0);

            //Add the new point at the end, ses coordonnées en x est celle de l'ancien dernier element+ PointSpacing
            int x= this.pointList.get(this.pointList.size()-1).x +PointSpacing;
            int y= rand.nextInt((int) ((model.ScreenHeight*0.6-model.ScreenHeight*0.4)+1))+model.ScreenHeight*1/4;
            visiblePoints.add(new Point(x,y));
        }
        this.pointList=new ArrayList<>(visiblePoints);

    }

    /** Cette fonction bouge le path vers la gauche pour donnner une sensation de deplacement */
    public void moveForward(){
        //Augmente le score de 1 à chaque point de la ligne brisée dépasé.
        this.position+=StepDistance;
        //Moves all the points backwards
        for(Point p: this.pointList){
            p.x-=StepDistance;
        }
    }
    /** Termine le jeu:
     * out: false si l'oval a collisioné plus un certain nombre de fois
     *      true sinon
     **/
    public boolean isGameEnded(){
        return this.collisions>=maxNumberOfCollisions;
    }
    /** REMINDER: game start is detected with a positive score from the path.getScore function
     *  REMINDER: the score is initialized at -5,
     *  when it reaches 0 the game begins
     *  */
    public boolean isGameStarted(){
        return this.score>=0;
    }
    public void checkCollisions(){
        /**Checks for collisions only if the game has started and has not ended */
        if (isGameStarted() && !isGameEnded()){
            //On obtient nos points 2 points: celui avant et celui apres flappy
            // pour ensuite calculer la collision avec un calcul d'intersection de flappy
            // et la droite forme par ses deux points
            int i = 0;
            while (this.pointList.get(i).x < this.model.getFlappyX()) {
                i++;
            }
            Point pointAhead = new Point(this.pointList.get(i));
            Point pointBehind = new Point(this.pointList.get(i - 1));
            int flappyCenterX=this.model.getFlappyX()+this.model.FlappyWidth/2;
            int flappyCenterY=this.model.getFlappyY()+this.model.FlappyHeight/2;

            float p = (pointAhead.y - pointBehind.y) / (float) (pointAhead.x - pointBehind.x);
            float y = pointBehind.y + p * (flappyCenterX - pointBehind.x);
            boolean collisionCondition = y > (flappyCenterY-this.model.FlappyHeight/2) && y < (flappyCenterY+this.model.FlappyHeight/2);
            if (!collisionCondition) {
                this.collisions += 1;
            }
        }
    }


}
