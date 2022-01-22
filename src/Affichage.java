import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/** Cette classe permet de dessiner */
public class Affichage extends JPanel{


    private static final long serialVersionUID = 1L;
    /* Les constantes */
    public static final int Height=600;
    public static final int Width=600;
    public static final int Saut=50;
    public Etat state;

    /** Le constructeur par défaut crée un panel vide */
    public Affichage(Etat e) {
        this.state= e;
        setPreferredSize(new Dimension(Height,Width));
        /**create a local control to add a listener here */
        Control c= new Control(this, this.state);
        addMouseListener(c);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(state.largeur,state.hauteur,200,100);
    }

}
