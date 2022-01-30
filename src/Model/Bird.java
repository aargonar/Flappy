package Model;

import java.util.Random;

public class Bird extends Thread{
    /** Attributs */
    //temps en milliseconds entre chauqe mise a jour de l'affichage pour oiseau
    private int delai;
    //permet der savoir dans quelle position est l'oiseau va de 0 a 7
    private int etat;
    //definit hauteur de oiseau dans la fenetre graphique
    private int hauteur;
    //definit l'abscisse
    private int position;
    public static final Random rand= new Random();


    public Bird(){
        this.position= 1000;
        this.etat=0;
        this.delai= rand.nextInt(600-400)+400;

        int max=Model.ScreenHeight-235;
        int min= (int) ((Model.ScreenHeight*0.6));
        int hauteur1= rand.nextInt(max-min)+min;

        int max2= (int) (Model.ScreenHeight*0.4-235);
        int min2= 0;
        int hauteur2= rand.nextInt(max2-min2)+min2;

        int[] randHauteur={hauteur1, hauteur2};
        this.hauteur= randHauteur[rand.nextInt(randHauteur.length)];

        this.start();
    }

    public int getPosition(){
        return this.position;
    }
    public int getHauteur(){
        return this.hauteur;
    }
    public int getEtat(){
        return this.etat;
    }
    public boolean isVisible(){
        return this.position>-300;
    }

    @Override
    public void run(){
        while(this.isVisible()){
            this.position-=100;
            this.etat= (this.etat+1)%8;
            try {
                sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
