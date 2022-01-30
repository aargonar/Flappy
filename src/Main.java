import Controller.Controller;
import Model.Model;
import Model.Path;
import View.View;
import Model.BirdView;

import javax.swing.*;

/** Cette classe permet de créer une fenêtre avec un "affichage" dedans */
public class Main{

    /** le constructeur de la fenêtre */
    public Main() {
        JFrame window = new JFrame("Flappy !");
        Model flappy= new Model();
        BirdView decorativeBirds= new BirdView();
        Path flappyPath= new Path(flappy);
        View flappyView=  new View(flappy,flappyPath, decorativeBirds);

        //this call adds the mouse listener to the window
        Controller flappyController= new Controller(flappyView, window);
        /* ajout de l'affichage */
        window.add(flappyView);

        /* assemblage et fin */
        window.pack();
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** La fonction pour lancer le programme */
    public static void main(String [] args) {
        new Main();
    }

}