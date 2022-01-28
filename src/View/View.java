package View;

import Model.Model;
import Model.Path;

import java.awt.*;
import javax.swing.JPanel;

/** Cette classe permet de dessiner */
public class View extends JPanel{


    private static final long serialVersionUID = 1L;
    /** Les constantes */
    public Model model;
    public Path path;

    /** Le constructeur par défaut crée un panel vide */
    public View(Model e, Path p) {
        this.model = e;
        this.path=p;
        //TODO ask prof about this
        setPreferredSize(new Dimension(Model.ScreenWidth, Model.ScreenHeight));
        (new RefreshView(this)).start();
    }

    private void drawOval(Graphics g){
        g.drawOval(model.getFlappyX(), model.getFlappyY(), Model.FlappyWidth, Model.FlappyHeight);
    }

    private void drawPath(Graphics g){
        g.setColor(Color.RED);
        //Dessine jusqu'a la coord en dehors du window pour simuler continuation de la ligne vers l'infinie
        for(int i=0; i<this.path.getPointList().size()-1; i++){
            g.drawLine(this.path.getPointList().get(i).x,this.path.getPointList().get(i).y,this.path.getPointList().get(i+1).x,this.path.getPointList().get(i+1).y);
        }
    }

    private void drawFlappyTittle(){

    }

    private void drawText(Graphics g, boolean gameStarted) {
        if (!gameStarted){
            g.setColor(Color.WHITE);
        }
            g.drawString("SCORE:" + this.path.getScore(), 50, 50);
            g.drawString("COLLISIONS: "+this.model.getCollisions(), 200, 50);
    }

    /**Methode paint qui est appelé quand on veut reafficher la view */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0,0,1000,600);

        /** Draws Flappy ! */
        drawOval(g);
        /** Draws the line */
        drawPath(g);
        /**Affichage du score*/
        drawText(g, this.path.isGameStarted());

    }

}
