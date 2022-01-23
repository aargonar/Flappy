package Model;
import View.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Path {
    /**Attributs */
    //Liste de points
    ArrayList<Point> pointList= new ArrayList<>();
    //Position or Score
    private int position=0;
    //View associée, utilisée pour les dimensions du window
    //TODO update screen dimension live
    protected View view;
    private int ScreenWidth= this.view.ScreenWidth;

    /**Variables */
    //Distance entre chaque point de la liste pointList
    public int PointSpacing = this.ScreenWidth/10;
    //Distance entre chaque moveForward de Flappy
    public int StepDistance= PointSpacing /10;
    //tirage aleatoire
    public static final Random rand= new Random();

    /**Constructeur initialise le Path avec les PointSpacing entre chaque point et cree le Thread qui va refraichir le path*/
    public Path(View view){
        this.view= view;
        for(int x = PointSpacing; x<=ScreenWidth+ PointSpacing; x+= PointSpacing){
            int y= rand.nextInt(this.view.ScreenHeight+1);
            this.pointList.add(new Point(x,y));
        }
        (new RefreshPath(this)).start();
    }
    /**Getter de PointList*/
    public ArrayList<Point> getPointList(){
        return this.pointList;
    }

    /**Methode update path appelée dans le thread RefreshPath: elle met a jour l'attribut pointList pour qu'il ne contienne que
     * les points qui sont affichables à l'ecran après chaque appel a moveForward
     */
    public void updatePath(){
        //Liste des points visibles qui sera la nouvelle pointList
       ArrayList<Point> visiblePoints= (ArrayList<Point>) this.pointList.clone();

        //Si flappy se trouve dans un point alors on ajoute un point a la fin de la liste et on elimine le premier
        //Flappy se trouve dans un point si la position de flappy est un multiple de PointSpacing
        if((this.position%PointSpacing)==0){

           //Removes the first element of the array
            visiblePoints.remove(0);

            //Add the new point at the end, sa coordonnee en x est celle du ancien dernier element+ PointSpacing
            int x= this.pointList.get(this.pointList.size()-1).x +PointSpacing;
            int y= rand.nextInt(this.view.ScreenHeight+1);
            visiblePoints.add(new Point(x,y));
        }
        this.pointList=new ArrayList<>(visiblePoints);

    }

    public void moveForward(){
        this.position+=StepDistance;
        //Moves all the points backwards
        for(Point p: this.pointList){
            p.x-=StepDistance;
        }
    }

}
