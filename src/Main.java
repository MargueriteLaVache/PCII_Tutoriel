import javax.swing.JFrame;
import java.util.Random;

public class Main {
    static boolean gameRunning;
    static Affichage affichage;
    static Random random;
    /**Méthode main
     * Le coeur du programme, elle s'occupe d'initialiser et de lancer les différents éléments
     * @param args, les arguments du programme
     */
	public static void main(String [] args) {
	    /* On crée de nouvelles instances pour toutes nos classes */
	    random = new Random();

        Parcours parcours = new Parcours();
        Etat etat = new Etat(parcours);
        affichage = new Affichage(etat);
        Control control = new Control(affichage);

        gameRunning = true;

        /* On crée une nouvelle JFrame, avec une opération de fermeture par défaut */
        JFrame flappy_flappo = new JFrame("Flappy flappo");
        flappy_flappo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* On y ajoute notre affichage (ie notre fenêtre) */
        flappy_flappo.add(affichage);

        /* On affiche l'affichage */
        flappy_flappo.pack();
        flappy_flappo.setVisible(true);


        /* On crée et  lance nos threads (Voler et Avancer) */
        Voler voler = new Voler(etat);
        Avancer avancer = new Avancer(parcours);
        voler.start();
        avancer.start();
	}
}
