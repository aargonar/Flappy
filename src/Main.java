import javax.swing.*;

/** Cette classe permet de créer une fenêtre avec un "affichage" dedans */
public class Main{

    /** le constructeur de la fenêtre */
    public Main() {
        JFrame window = new JFrame("titre de la fenêtre");
        Affichage flappy=  new Affichage(new Etat());

        /* ajout de l'affichage */
        window.add(flappy);

        /* assemblage et fin */
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** La fonction pour lancer le programme */
    public static void main(String [] args) {
        new Main();
    }

}