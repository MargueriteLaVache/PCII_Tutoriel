import javax.swing.JFrame;
import java.util.Random;

public class Main {
    static boolean gameRunning;
    static Affichage affichage;
    static Random random;

	public static void main(String [] args) {
	    random = new Random();
        Parcours parcours = new Parcours();
        Etat etat = new Etat(parcours);
        affichage = new Affichage(etat);
        Control control = new Control(affichage);

        gameRunning = true;

        JFrame test = new JFrame("Flappy flappo");
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        test.add(affichage);

        test.pack();
        test.setVisible(true);


        /* on crée et  lance nos threads (Voler et Avancer) */
        Voler voler = new Voler(etat);
        Avancer avancer = new Avancer(parcours);
        voler.start();
        avancer.start();
	}
}
