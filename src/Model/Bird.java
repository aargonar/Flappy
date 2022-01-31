package Model;

import java.util.Random;
/**Classe de l'objet oiseau du décors */
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

/** Constructeur */
    public Bird(){
        //intialisation des attributs
        this.position= 1000;
        this.etat=0;
        this.delai= rand.nextInt(600-400)+400;
        //Calcul d'un random entre deux intervalles: ici l'intervalle hauteur1 et l'intervalle hauteur2
        //La valeur trouvée sera la nouvelle valeur de l'ordonnée de l'oiseau
        int max=Model.ScreenHeight-235;
        int min= (int) ((Model.ScreenHeight*0.6));
        int hauteur1= rand.nextInt(max-min)+min;

        int max2= (int) (Model.ScreenHeight*0.4-235);
        int min2= 0;
        int hauteur2= rand.nextInt(max2-min2)+min2;
        //deux intervalles mis dans un tableau, on va en suite prendre un des deux aleatoirement
        int[] randHauteur={hauteur1, hauteur2};
        //initialisation de l'hauteur a la valeur trouvée
        this.hauteur= randHauteur[rand.nextInt(randHauteur.length)];

        this.start();
    }
/**Getters */
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
    /** méthode run du thread qui va declancher la boucle infinie*/
    public void run(){
        while(this.isVisible()){
            //l'oiseau est décalé de 100 pixels a chaque appel
            this.position-=100;
            //l'etat parcours les valeur entre 0 et 7, il passe a l'etat suivant en chaque appel
            this.etat= (this.etat+1)%8;
            try {
                sleep(delai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
