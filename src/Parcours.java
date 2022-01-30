import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class Parcours {
    public static final Random rand = new Random();
    public static ArrayList<Point> pointList = new ArrayList<Point>();
    public static final int nbPointsCtrler = 5;
    public static int position = Affichage.xCenterOval;

    public Parcours(){
        int x = 0;
        int y;
        while (x < Affichage.LARG) {
            x = x + rand.nextInt(Affichage.LARG / nbPointsCtrler);
            y = rand.nextInt(Affichage.HAUT);
            pointList.add(new Point (x, y));
        }
		System.out.println(pointList);
    }

    public static ArrayList<Point> getParcours() {
    	ArrayList<Point> pointList2 = new ArrayList<Point>();
    	Point p, p2;
    	// p2 = new Point();
    	int xp, yp;
    	for (int i = 0; i < pointList.size(); i ++) {
    		p = pointList.get(i);
    		xp = (int)p.getX() - position;
    		yp = (int)p.getY();
    		// p2.setLocation(xp, yp);
    		p2 = new Point(xp, yp);
    		if (xp >= 0) {
    			pointList2.add(p2);
    		}
    	}
    	//pointList2 = pointList;	//atm les lignes marchent pas sinon
        return pointList2;
    }
}
