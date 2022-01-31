import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class Parcours {
    public static final Random rand = new Random();
    public static ArrayList<Point> pointList = new ArrayList<Point>();
    public static final int nbPointsCtrler = 5;
    public static int position = Affichage.xCenterOval;
    public static int incrementP = 3;

    public Parcours(){
        int x = 0;
        int y, penteMax, incrX;
        penteMax = (int) ((float)Etat.vDown / (float)incrementP) + 1;
        System.out.println(penteMax);
        while (x < Affichage.LARG) {
            incrX = rand.nextInt(Affichage.LARG / nbPointsCtrler);
            x += incrX;
            // On veut tester si la pente n'est pas trop forte par rapport à la vitesse de chute
            // donc on règle le bound du rand.nextInt() en fonction
            y = incrX * penteMax + rand.nextInt(Affichage.HAUT - incrX * penteMax);
            pointList.add(new Point (x, y));
        }
		//System.out.println(pointList);
    }

    public static ArrayList<Point> getParcours() {
    	ArrayList<Point> pointList2 = new ArrayList<Point>();
    	Point p, p2;
    	int xp;
    	for (int i = 0; i < pointList.size(); i ++) {
    		p = pointList.get(i);
    		xp = (int)p.getX() - position;
    		p2 = new Point(xp, (int)p.getY());
    		if (xp >= 0) {
    			pointList2.add(p2);
    		}
    	}
    	//pointList2 = pointList;	//atm les lignes marchent pas sinon
        return pointList2;
    }

    public static int getPosition() {
        return position;
    }

    public static void avancerPosition() {
        position += incrementP;
    }
}
