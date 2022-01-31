import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Point;
import java.util.ArrayList;


public class Affichage extends JPanel {

    /* Constantes */
    public static final int LARG = 600;
    public static final int HAUT = 400;
    public static final int xCenterOval = 50;
    public static final int yCenterOval = 250;
    public static final int widthOval = 50;
    public static final int heightOval = 150;
    public static  ArrayList<Point> listPoint;
    public static int lpLen;
    private static Etat etat;

    /** Constructeur */
    public Affichage(Etat e) {
    		etat = e;
            setPreferredSize(new Dimension(LARG, HAUT));
    }

    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	g.drawOval(xCenterOval, etat.getHauteur(), widthOval, heightOval);
    	
    	/** ça marche plus ou moins, les points devraient être bons, mais c'est turbo moche le code */
    	listPoint = Etat.listParcours;
    	lpLen = listPoint.size();
    	Point p2;
    	int xp1, yp1, xp2, yp2;
    	p2 = listPoint.get(0);
		//xp2 = 0;
		//yp2 = Affichage.HAUT;
    	xp2 = (int)p2.getX();
    	yp2 = (int)p2.getY();
    	for (int i = 0; i < lpLen; i ++) {
    		p2 = listPoint.get(i);
    		xp1 = xp2;
    		yp1 = yp2;
    		xp2 = (int)p2.getX();
    		yp2 = (int)p2.getY();
    		g.drawLine(xp1, yp1, xp2, yp2);
    	}
    }


}
