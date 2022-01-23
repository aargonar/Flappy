package View;

import Model.Model;
import Model.Path;

import java.awt.*;
import javax.swing.JPanel;

/** Cette classe permet de dessiner */
public class View extends JPanel{


    private static final long serialVersionUID = 1L;
    /** Les constantes */
    public static final int ScreenHeight =600;
    public static final int ScreenWidth =1000;
    public Model model;
    public Path path;

    /** Le constructeur par défaut crée un panel vide */
    public View(Model e) {
        this.model = e;
        this.path= new Path(this);
        setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        (new RefreshView(this)).start();
    }
    /**Methode paint qui est appelé quand on veut reafficher la view */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        /** Draws Flappy ! */
        g.drawOval(model.getFlappyX(), model.getFlappyY(),model.FlappyWidth,model.FlappyHeight);
        /** Draws the line */
        g.setColor(Color.RED);
        //Dessine jusqu'a la coord en dehors du window pour simuler continuation de la ligne vers l'infinie
        for(int i=0; i<this.path.getPointList().size()-1; i++){
            g.drawLine(this.path.getPointList().get(i).x,this.path.getPointList().get(i).y,this.path.getPointList().get(i+1).x,this.path.getPointList().get(i+1).y);
        }
    }

}
