import java.awt.Point;
import java.util.ArrayList;

public class Etat {
	/**Constantes*/
	/**Constante hauteur, y du centre de l'ovale*/
	public static int hauteur = 200;
	/**Constante vJump, valeur du jump*/
	public static int vJump = 50;
	/**Constante vDown, valeur de la descente*/
	public static int vDown = 1;
	///**Constante vDown, valeur de la descente*/

	Parcours parcours;
	
	public Etat(Parcours p) {
		parcours = p;
		//System.out.println(listParcours);
		//System.out.println(listParcours.get(0));
		
	}
	
	
	public static int getHauteur() {
		return hauteur;
	}
	
	public static void jump() {
		if (hauteur - vJump < 0) {
			hauteur = 0;
		}
		else
			hauteur -= vJump;
	}

	public static void moveDown() {
		if (hauteur + Affichage.heightOval < Affichage.HAUT) {
			hauteur += vDown;
		}
		Main.affichage.revalidate();
		Main.affichage.repaint();
	}
	


}



class Voler extends Thread {

	public static final int sleepTime = 10;
	Etat etat;

	public Voler(Etat e) {
		super();
		etat = e;
	}

	@Override
	public void run() {
		while (Main.gameRunning) {
			etat.moveDown();
			try { sleep(sleepTime); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}