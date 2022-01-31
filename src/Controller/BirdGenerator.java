package Controller;

import Model.BirdView;
/** thread qui ajoute des oiseaux du decors */
public class BirdGenerator extends  Thread{
    /** Attributs */
    private BirdView birdview;

    public BirdGenerator(BirdView birdview){
        //initialisation des attributs
        this.birdview=birdview;
    }
    @Override
    /**méthode run dans laquelle on va boucler et generer des oiseaux chaque 5 secondes*/
    public void run(){
        while(true){
            //generation des oiseaux
            this.birdview.generateBird();
            //pause de 5 secondes
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
