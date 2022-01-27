package Model;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Path {
    /**Attributs */
    //Liste de points
    ArrayList<Point> pointList= new ArrayList<>();
    //Position or Score
    private int position;
    //Le score basé sur le nombre de points de la ligne brisée dépasés par Flappy
    private int score;
    //TODO update screen dimension live

    /**Variables */
    //Distance entre chaque point de la liste pointList
    public int PointSpacing = Model.ScreenWidth/5;
    //Distance entre chaque moveForward de Flappy
    public int StepDistance= PointSpacing/25;
    //tirage aleatoire
    public static final Random rand= new Random();

    /**Constructeur initialise le Path avec les PointSpacing entre chaque point et cree le Thread qui va refraichir le path*/
    public Path(){
        //initialisation de pointList
        for(int x = PointSpacing; x<=Model.ScreenWidth+ PointSpacing; x+= PointSpacing){
            int y= rand.nextInt((int) ((Model.ScreenHeight*0.6-Model.ScreenHeight*0.4)+1))+Model.ScreenHeight*3/8;
            this.pointList.add(new Point(x,y));
        }
        //initialise les attributs
        this.score=-2;
        this.position=0;
        //Creation de la thread qui gere le refraichissement du path
        (new RefreshPath(this)).start();
    }
    /**Getter de PointList*/
    public ArrayList<Point> getPointList(){
        return this.pointList;
    }
    public int getScore(){return this.score;}
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

            //Add the new point at the end, sa coordonnee en x est celle du ancien dernier element+ PointSpacing
            int x= this.pointList.get(this.pointList.size()-1).x +PointSpacing;
            int y= rand.nextInt((Model.ScreenHeight*3/4-Model.ScreenHeight*1/4)+1)+Model.ScreenHeight*1/4;
            visiblePoints.add(new Point(x,y));
        }
        this.pointList=new ArrayList<>(visiblePoints);

    }

    public void moveForward(){
        //Augmente le score de 1 à chaque point de la ligne brisée dépasé.
        this.position+=StepDistance;
        //Moves all the points backwards
        for(Point p: this.pointList){
            p.x-=StepDistance;
        }
    }

}
