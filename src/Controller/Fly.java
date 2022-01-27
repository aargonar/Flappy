package Controller;

import Model.Model;

/**Cette classe est une Thread qui descends le modele flappy en X
 * quand l'utilisateur click pas sur l'ecran*/
public class Fly extends Thread{
    /**Attribut*/
    private Model model;
    /**Constructeur qui prend une view et extrait son attribut model*/
    public Fly(Model modelToModify){
        this.model= modelToModify;
    }

    @Override
    public void run(){
        /**Moves flappy downwards contantly*/
        while(true){
            model.moveDownn();
            //model.testLost();
            //TODO check for collision
            /**Attends quelques secondes avant de descendre flappy*/
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
