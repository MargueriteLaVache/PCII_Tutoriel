import javax.swing.JFrame;

public class Main {
	public static void main(String [] args) {
        JFrame test = new JFrame("Flappy flappo");
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Affichage affichage = new Affichage();
        Parcours parcours = new Parcours();
        Etat etat = new Etat(affichage, parcours);
        Voler voler = new Voler(etat);
        voler.start();
        Control control = new Control(affichage);
        affichage.addMouseListener(control);
        test.add(affichage);
        test.pack();
        test.setVisible(true);
	}
}
