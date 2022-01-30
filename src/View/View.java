package View;

import Model.Model;
import Model.BirdView;
import Model.Path;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/** Cette classe permet de dessiner */
public class View extends JPanel{


    private static final long serialVersionUID = 1L;
    /** Les constantes */
    public Model model;
    public Path path;
    public BirdView birdview;

    /** Le constructeur par défaut crée un panel vide */
    public View(Model e, Path p, BirdView deco) {
        this.birdview=deco;
        this.model = e;
        this.path=p;
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

    private void drawMarkers(Graphics g, boolean gameStarted) {
        //si le jeu n'as pas commencé cacher le score avec la couleur blanche
        if (!gameStarted){
            g.setColor(Color.WHITE);
        }
        else{
            g.setColor(Color.DARK_GRAY);
        }
        //Affiche le score et le
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        g.drawString("SCORE:" + this.path.getScore(),50,50);
        g.drawString("COLLISIONS: "+this.path.getCollisions(), 250, 50);
    }

    private void drawGameStart(Graphics g, int score){
        if(score==-4){
            g.setColor(new Color(154,205,50) );
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.drawString("GAME STARTS IN 3 !",400,450);
        }
        else if(score==-3){
            g.setColor(Color.ORANGE);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.drawString("GAME STARTS IN 2 !",400,490);
        }
        else if(score==-2){
            g.setColor(new Color(70,130,180));
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.drawString("GAME STARTS IN 1 !",400,530);
        }
        else if(score==-1){
            g.setColor(new Color(0,100,0));
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.drawString(" GOOOOO !",500,490);
        }
        else if(this.path.isGameEnded()){
            g.setColor(Color.RED);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
            g.drawString(" YOU LOST !",500,300);
        }
        else{
            g.setColor(Color.WHITE);

        }

    }

    /**Methode paint qui est appelé quand on veut reafficher la view */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, 1000, 600);
        /** Draws Flappy ! */
        drawOval(g);
        /** Draws the line */
        drawPath(g);
        /**Affichage du score*/
        drawMarkers(g, this.path.isGameStarted());
        /**Affichage du texte du debut */
        drawGameStart(g, this.path.getScore());
        /**Draw the decorative birds */
        this.birdview.drawBirds(g);
    }

}
