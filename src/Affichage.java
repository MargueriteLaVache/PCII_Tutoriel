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
    	listPoint = Parcours.getParcours();
    	lpLen = listPoint.size();

    	/**Point p2 = listPoint.get(0);
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
    	}*/
		Point p0 = listPoint.get(0);
		Point p1 = listPoint.get(1);
		if (p0.x < 0){
			float a = (p1.y- p0.y)/(float)(p1.x - p0.x);
			float b = p1.y - a*p1.x;
			g.drawLine(0, (int)b, p1.x, p1.y);
		} else {
			g.drawLine(p0.x, p0.y, p1.x, p1.y);
		}
		for(int i = 1; i < lpLen - 1; i++){
			p0 = listPoint.get(i);
			p1 = listPoint.get(i+1);
			g.drawLine(p0.x,p0.y,p1.x,p1.y);
		}
    }


}
