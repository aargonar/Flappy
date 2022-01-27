package Model;

/**Cette classe est une Thread qui descends le modele flappy en X
 * quand l'utilisateur click pas sur l'ecran*/
public class RefreshPath extends Thread{
    /**Attribut*/
    private Path path;
    /**Constructeur qui prend une view et extrait son attribut model*/
    public RefreshPath(Path pathToModify){
        this.path= pathToModify;
    }

    @Override
    public void run(){
        /**Moves flappy downwards contantly*/
        while(true){
            path.moveForward();
            //TODO check for collision
            path.updatePath();
            /**Attends quelques secondes avant de descendre flappy*/
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}