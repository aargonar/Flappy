package Controller;

import Model.BirdView;

public class BirdGenerator extends  Thread{
    private BirdView birdview;
    public BirdGenerator(BirdView birdview){
        this.birdview=birdview;
    }
    @Override
    public void run(){
        while(true){
            this.birdview.generateBird();
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
