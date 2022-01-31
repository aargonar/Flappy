package Model;

import Controller.BirdGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class BirdView {
    /**Attributs */
    private ArrayList<Bird> birds;
    /** Variables */
    private static final Random rand= new Random();
    private static final int scaleHeight= 156;
    private static final int scaleWidth= 200;




    /** comstructeur de la classe */
    public BirdView(){
        //initialise l'arraylist
        this.birds= new ArrayList<>();
        //demarre le thread chrger d'ajouter des nouveaux oiseaux
        (new BirdGenerator(this)).start();
    }

    /** elimine les oiseaux non visibles de la liste attribut birds */
    public void updateBirds(){
        ListIterator<Bird> iter = this.birds.listIterator();
        while(iter.hasNext()){
            if(!iter.next().isVisible()){
                iter.remove();
            }
        }
    }
    /** AJoute un nouveau oiseau a la liste birds avec un probabilité de 0.7,
     cette methode est appele chaque 5 secondes*/
    public void generateBird(){
        int random = rand.nextInt(10);
        if (random>3){
            this.birds.add(new Bird());
        }
    }

    /**methode qui dessine les images des oiseaux selon leur etat,
     * elle est appelé par la méthode paint dans View
     */
    public void drawBirds(Graphics g) {
        updateBirds();
        if(!this.birds.isEmpty()) {
        for(Bird b: this.birds){
                try {
                    Image bird = ImageIO.read(new File("./resources/frame_" + b.getEtat() + ".jpg"));
                    Image newBird = bird.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_DEFAULT);
                    g.drawImage(newBird, b.getPosition(), b.getHauteur(), null);
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
        }

        }
    }
}
