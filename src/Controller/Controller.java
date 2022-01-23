package Controller;

import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {
    /**Attributs de la clase */
    View view;
    Model model;
    /**Constructeur qui permet d'ajoute le mouseListener a la JFrame (definie dans le main)
     *  où celui ci sera appellé */
    public Controller(View a, JFrame window) {
        window.addMouseListener(this);
        this.view =a;
        this.model = view.model;
        /**Adds a model fly thread to modify its position constantly*/
        (new Fly(this.model)).start();
    }
    /**Unique evenement utile pour ce projet:
     * Il fait sauter flappy puis reaffiche la view **/
    @Override
    public void mouseClicked(MouseEvent e) {
        model.jump();
        view.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
