import java.awt.Point;
import java.util.ArrayList;

public class Etat {
	/**Constantes*/
	/**Constante hauteur, y du centre de l'ovale*/
	public static int hauteur;
	/**Constante vJump, valeur du jump*/
	public static int vJump = 50;
	/**Constante vDown, valeur de la descente*/
	public static int vDown = 10;
	///**Constante vDown, valeur de la descente*/
	public static ArrayList<Point> listParcours;
	
	static Affichage affichage;
	Parcours parcours;
	
	public Etat(Affichage a, Parcours p) {
		affichage = a;
		parcours = p;
		hauteur = affichage.yCenterOval;
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
		affichage.repaint(Affichage.xCenterOval, 0, 2 * Affichage.widthOval, Affichage.HAUT);
	}
	


}
