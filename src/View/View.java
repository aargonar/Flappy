package View;

import Model.Model;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/** Cette classe permet de dessiner */
public class View extends JPanel{


    private static final long serialVersionUID = 1L;
    /** Les constantes */
    public static final int ScreenHeight =600;
    public static final int ScreenWidth =600;
    public Model model;

    /** Le constructeur par défaut crée un panel vide */
    public View(Model e) {
        this.model = e;
        setPreferredSize(new Dimension(ScreenHeight, ScreenWidth));
        (new RefreshView(this)).start();
    }
    /**Methode paint qui est appelé quand on veut reafficher la view */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(model.getFlappyY(), model.getFlappyX(),model.FlappyWidth,model.FlappyHeight);
    }

}
