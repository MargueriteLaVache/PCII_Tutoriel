import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;

/** Classe Affichage
 * Construit une nouvelle fenêtre d'affichage graphique
 * extends JPanel
 */
public class Affichage extends JPanel {

    /* Constantes du panel */
    public static final int LARG = 600;
    public static final int HAUT = 400;
	/* Constantes de l'oval/oiseau */
    public static final int xCenterOval = 50;
    public static final int widthOval = 50;
    public static final int heightOval = 150;
    /* Constantes de manipulation des points du parcours */
    public static  ArrayList<Point> listPoint;
    public static int lpLen;

    /* Variable pour l'état du jeu */
    private static Etat etat;

    /** Constructeur de la classe */
    public Affichage(Etat e) {
    		etat = e;
            setPreferredSize(new Dimension(LARG, HAUT));
    }


	/** Méthode paint (override d'une méthode de base)
	 * Gère l'affichage des différents éléments dans la fenêtre
	 */
    @Override
    public void paint(Graphics g) {
    	/* Appel de la méthode du JPanel pour nettoyer l'écran */
    	super.paint(g);
    	/* Appel des méthodes d'affichages personnalisées */
    	dessinOval(g);
    	dessinParcours(g);
    	dessinScore(g);


    }

	/** Méthode dessinOval
	 * gère l'affichage de l'oiseau (qui est représenté par un oval)
	 */
	public void dessinOval(Graphics g) {
		/* Dessin de l'oval qui représente l'oiseau */
		g.drawOval(xCenterOval, etat.getHauteur(), widthOval, heightOval);
	}


	/** Méthode dessinParcours
	 * gère l'affichage du parcours, sous forme de ligne entre les points
	 */
	public void dessinParcours(Graphics g) {
		/* On récupère la liste des points et sa taille*/
		listPoint = Parcours.getParcours();
		lpLen = listPoint.size();

		/* On récupère les premiers points*/
		Point p0 = listPoint.get(0);
		Point p1 = listPoint.get(1);

		/* Cas où le point est hors de l'écran à gauche (déjà passé),
		* on ne pourrait donc pas dessiner depuis hors de l'écran*/
		if (p0.x < 0){
			float temp1 = (p1.y - p0.y) / (float)(p1.x - p0.x);	// Il s'agit de la pente entre le point 0 et le point 1
			float temp2 = p1.y - temp1 * p1.x;	// On calcule la hauteur du projeté d'abcisse 0
			g.drawLine(0, (int)temp2, p1.x, p1.y);
		}

		else {
			g.drawLine(p0.x, p0.y, p1.x, p1.y);
		}

		/* On dessine le reste des lignes*/
		for(int i = 1; i < lpLen - 1; i++){
			p0 = listPoint.get(i);
			p1 = listPoint.get(i+1);
			g.drawLine(p0.x,p0.y,p1.x,p1.y);
		}
	}



	/** Méthode dessinScore
	 * gère l'affichage du score
	 */
	public void dessinScore(Graphics g){
		/* Dessin d'un rectangle avec en texte le score */
		if (etat.testPerdu()){
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);

		}
		g.fillRect(0, 0, 120, 40);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(Parcours.score),15, 30);
	}



}
