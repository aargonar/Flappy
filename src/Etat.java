public class Etat {
    //Variables dont hauteur de flappy
    public int hauteur=300;
    public int largeur=100;


    //Getter de hauteur
    public int getHauteur() {
        return this.hauteur;
    }
    //Modifies hauteur de flappy si les contraintes definies sur affichage verif
    public void jump() {
        int delta= hauteur-Affichage.Saut;
        if (delta < Affichage.Height && delta >0 ) {
            hauteur=delta;
        }
    }

}