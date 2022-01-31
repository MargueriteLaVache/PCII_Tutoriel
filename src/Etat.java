import java.awt.Point;
import java.util.ArrayList;

public class Etat {
	/**Constantes*/
	/**Constante hauteur, y du centre de l'ovale*/
	public static int hauteur = 50;
	/**Constante vJump, valeur du jump*/
	public static int vJump = 50;
	/**Constante vDown, valeur de la descente*/
	public static int vDown = 2;
	///**Constante vDown, valeur de la descente*/
	public static ArrayList<Point> listParcours;

	Parcours parcours;
	
	public Etat(Parcours p) {
		parcours = p;
		listParcours = p.getParcours();
		//System.out.println(listParcours);
		//System.out.println(listParcours.get(0));
		
	}
	
	
	public static int getHauteur() {
		return hauteur;
	}
	
	public static void jump() {
		hauteur -= vJump;
	}

	public static void moveDown() {
		if (hauteur + Affichage.heightOval < Affichage.HAUT) {
			hauteur += vDown;
		}
		Main.affichage.repaint(Affichage.xCenterOval, 0, 2 * Affichage.widthOval, Affichage.HAUT);
	}
	


}



class Voler extends Thread {

	public static final int sleepTime = 20;
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