import java.awt.Point;
import java.util.ArrayList;


public class Parcours {
    public static ArrayList<Point> pointList = new ArrayList<Point>();
    //public static final int nbPointsCtrler = 5;
    public static double position = 0;
    public static double incrementP = 0.001;

    public Parcours(){
        /** x = 0;
        int y, penteMax, incrX;
        penteMax = (int) ((float)Etat.vDown / (float)incrementP) + 1;
        System.out.println(penteMax);
        while (x < Affichage.LARG) {
            incrX = Main.random.nextInt(Affichage.LARG / nbPointsCtrler);
            x += incrX;
            // On veut tester si la pente n'est pas trop forte par rapport à la vitesse de chute
            // donc on règle le bound du rand.nextInt() en fonction
            y = incrX * penteMax + Main.random.nextInt(Affichage.HAUT - incrX * penteMax);
            pointList.add(new Point (x, y));
        }
		//System.out.println(pointList);*/

        /* des variables pour stocker des infos sur les points */
        /* on crée la liste de points et on ajoute un point initial */
        pointList.add(new Point(- 10,Affichage.HAUT / 2));
        /* tant qu'on est encore dans l'écran, on ajoute un nouveau point */
        while(pointList.get(pointList.size() - 1).x < Affichage.LARG) {
            /* on ajoute un nouveau point à la liste */
            addPoint();
        }
        /* on affiche la liste dans le terminal */
        for (Point p:pointList) {
            System.out.println(p);

        }

    }

    /** Méthode addPoint
     * ajoute un point à la liste points
     */
    public static void addPoint(){
        int x1, y1;
        /* on calcule une nouvelle valeur de x à partir du point précédent plus un écart qui dépend de la largeur de la fenêtre et de la distance parcourue */
        x1 = (int) (pointList.get(pointList.size() - 1).x + Affichage.LARG / 3. * Math.max(1, (1 - position / 150000.) * (1 + Main.random.nextFloat()) ));

        /* on calcule une nouvelle valeur de dans la partie centrale de la fenêtre, entre HAUT/4 et 3*HAUT/4 */
        y1 = Affichage.HAUT / 4 + Main.random.nextInt(Affichage.HAUT / 2);

        /* on ajoute le point à la liste, le coefficient directeur maximale en descente est de (HAUT/2)/((LARG/3)*1.15) = 200/230 = 20/23 < 1
         * Sachant qu'on avance en x au rythme de 0,001 pixel toutes les 1000 millisecondes
         * et en y de 0,2 toutes les 10 millisecondes,
         * on a un coefficient de vitesse de 2/1, donc l'oiseau chute 2 fois plus vite qu'il n'avance,
         * il est donc impossible avec ces paramètres sur les points que l'oiseau ne puisse pas suivre la pente descendante
         * */
            pointList.add(new Point(x1, y1));
    }




    public static ArrayList<Point> getParcours() {
        for (Point p:pointList) {
            /* on veut soustraire la position actuelle à l'abscisse des points,
             en pratique on fait diminuer cette dernière progressivement (on avance de 1 pixel par seconde avec ces valeurs */
            p.x -= incrementP * 1000;
        }
        if (pointList.get(pointList.size() - 1).x < Affichage.LARG + 10) {
            /* si le dernier point est sur le point d'entrer dans la fenêtre, on en ajoute un en plus */
            addPoint();
            /* on incrémente le score à chaque fois qu'on ajoute un point au parcours, ça fonctionne correctement, car le moment où l'oiseau franchi un point se passe quasiment au même au moment où on en ajoute un */
            //score++;
        }
        if (pointList.get(1).x < 0)
            /* on retire les points qui sont sortis de la fenêtre */
            pointList.remove(0);
        return pointList;
    	/**ArrayList<Point> pointList2 = new ArrayList<Point>();
    	Point p, p2;
    	int xp;
    	for (int i = 0; i < pointList.size(); i ++) {
    		p = pointList.get(i);
    		xp = (int)p.getX() - position;
    		p2 = new Point(xp, (int)p.getY());
    		if (xp >= 0) {
    			pointList2.add(p2);
    		}
    	}*/
    	//pointList2 = pointList;	//atm les lignes marchent pas sinon
        //return pointList;
    }

    public static double getPosition() {
        return position;
    }

    public static void setPosition() {
        position += incrementP;
    }
}


class Avancer extends Thread {
    Parcours p;
    public static final int msX = 10;
    /** constructeur */
    public Avancer(Parcours parcours){
        super();
        p = parcours;
    }

    /** Méthode run
     * Méthode que va exécuter le thread Voler
     */
    @Override
    public void run(){
        while(Main.gameRunning){
            /* on fait descendre un peu l'oiseau */
            Parcours.setPosition();
            //System.out.println("cc1");
            /* on met le thread en pause pendant 10 millisecondes*/
            try {
                sleep(msX);
                //System.out.println("cc2");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


}