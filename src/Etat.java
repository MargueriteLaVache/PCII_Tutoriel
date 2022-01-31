import java.awt.Point;
import java.util.ArrayList;

/** Classe Etat
 * Défini l'état actuel du jeu
 * et le met à jour lors d'appels de méthodes
 */
public class Etat {
	/** Constantes */
	/* Constante hauteur, y du centre de l'ovale */
	public static int hauteur = 200;
	/* Constante vJump, valeur du jump */
	public static int vJump = 50;
	/* Constante vDown, valeur de la descente */
	public static int vDown = 1;

	/* Variable pour le parcours */

	Parcours parcours;

	/** Constructeur de la classe */
	public Etat(Parcours p) {
		parcours = p;
		
	}


	/** Getter pour la hauteur actuelle */
	public static int getHauteur() {
		return hauteur;
	}

	/** Méthode jump :
	 * calcule l'effet d'un saut,
	 * appelée lors d'un clic de la souris dans la fenêtre
	 */
	public static void jump() {
		/* On vérifie que l'on ne sort pas par en haut */
		if (hauteur - vJump < 0) {
			hauteur = 0;
		}
		else
			hauteur -= vJump;
	}


	/** Méthode moveDown:
	 * fait redescendre l'oiseau (au fur à mesure que le temps passe grâce aux appels)
	 */
	public static void moveDown() {
		/* On s'assure que l'on ne "tombe" pas hors de la fenêtre */
		if (hauteur + Affichage.heightOval < Affichage.HAUT) {
			hauteur += vDown;
		}
		Main.affichage.revalidate();
		Main.affichage.repaint();
	}



	/** Fonction qui teste si l'oiseau (le oval) est sorti de la ligne
	 *
	 * @return true s'il est sorti, false sinon
	 */
	public boolean testPerdu(){
		/* on récupère les données des points du parcours */
		ArrayList<Point> points = Parcours.getParcours();
		Point p1 = new Point(0,0), p2;
		/* on récupère la position actuelle de l'oiseau en abscisse,
		c'est à dire la position dans le parcours plus la largeur de l'oval */
		double birdAbs = (Parcours.getPosition() + Affichage.xCenterOval);
		int i = 0;
		/* on va cherche le point situé avant la position actuelle de l'oiseau en x */
		while (points.get(i).x <= birdAbs) {
			p1 = points.get(i);
			i++;
		}
		/* on récupère également le point situé après*/
		p2 = points.get(i);

		/* on calcule la pente */
		float temp1 = (p2.y - p1.y) / (float)(p2.x - p1.x);
		/* on calcule l'ordonnée à la position actuelle */
		float temp2 = (float) (p1.y + temp1 * ((birdAbs) - p1.x));

		/* on renvoie un test de la différence entre le centre de l'oiseau et le point du parcours, comparé avec la moitié de la hauteur de l'oiseau */
		return Math.abs(Etat.getHauteur() + Affichage.heightOval/2. - temp2) < Affichage.heightOval/2.;

	}

}


/**Classe Voler
 * gère la chute de l'oiseau
 * extends Thread
 */
class Voler extends Thread {

	public static final int sleepTime = 10;
	Etat etat;

	/** Constructeur de la classe */
	public Voler(Etat e) {
		super();
		etat = e;
	}
	/** Méthode run
	 * ce que va exécuter le thread Voler
	 */
	@Override
	public void run() {
		while (Main.gameRunning) {
			/* Descente de l'oval/oiseau */
			etat.moveDown();
			/* Mise en pause du thread */
			try { sleep(sleepTime); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}