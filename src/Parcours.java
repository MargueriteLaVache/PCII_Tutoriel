import java.awt.Point;
import java.util.ArrayList;

/** Classe Parcours
 * s'occupe de générer le parcours pour l'oiseau/oval
 */
public class Parcours {
    /* Variable pour la liste des points du parcours */
    public static ArrayList<Point> pointList = new ArrayList<Point>();
    /* Variable pour la position dans le parcours */
    public static double position = 0;
    /* Variable pour la vitesse de défilement du parcours */
    public static double incrementP = 0.001;
    /* Variable pour stocker le score */
    public static int score;


    /** Constructeur de la classe */
    public Parcours(){
        /* on prend la liste (vide) des points et on ajoute un point initial */
        pointList.add(new Point(- 10,Affichage.HAUT / 2));
        /* tant qu'on ne sort pas de l'écran, on ajoute un nouveau point */
        while(pointList.get(pointList.size() - 1).x < Affichage.LARG) {
            /* on ajoute un nouveau point à la liste */
            addPoint();
        }
    }

    /** Méthode addPoint
     * ajoute un point à la liste pointList
     */
    public static void addPoint(){
        int x1, y1;
        /* on calcule une nouvelle valeur de x à partir du point précédent,
         plus un écart qui dépend de la largeur de la fenêtre et de la distance parcourue (pour la croissance de la difficulté) */
        x1 = (int) (pointList.get(pointList.size() - 1).x + Affichage.LARG / 3. * Math.max(1, (1 - position / 150000.) * (1 + Main.random.nextFloat()) ));

        /* on calcule une nouvelle valeur de l'abscisse dans la partie centrale de la fenêtre, c'est à dire entre HAUT/4 et 3*HAUT/4 */
        y1 = Affichage.HAUT / 4 + Main.random.nextInt(Affichage.HAUT / 2);

        /* on ajoute le point à la liste
         * le coefficient directeur maximal en descente est de (HAUT/2)/((LARG/3)) < 1
         * Sachant qu'on avance en x au rythme de 1 pixel toutes les secondes,
         * et en y de 1 toutes les 1 secondes,
         * on a un coefficient de vitesse de 1, donc l'oiseau chute aussi vite qu'il avance,
         * il est donc impossible avec ces paramètres sur les points que l'oiseau ne puisse pas suivre la pente descendante
         * */
            pointList.add(new Point(x1, y1));
    }



    /** Méthode getParcours
     * renvoie la liste des points à afficher
     * @return pointList, les points à afficher
     */
    public static ArrayList<Point> getParcours() {
        for (Point p:pointList) {
            /* on veut soustraire la position actuelle à l'abscisse des points,
             en pratique on fait diminuer cette dernière progressivement (on avance de 1 pixel par seconde avec ces valeurs */
            p.x -= incrementP * 1000;
        }
        if (pointList.get(pointList.size() - 1).x < Affichage.LARG + 10) {
            /* si le dernier point est sur le point d'entrer dans la fenêtre, on en ajoute un en plus */
            addPoint();
            /* on incrémente le score à chaque fois qu'on ajoute un point au parcours */
            score++;
        }
        if (pointList.get(1).x < 0)
            /* on retire les points qui sont sortis de la fenêtre */
            pointList.remove(0);
        return pointList;
    }

    /** Fonction getPostition
     * Rend la position du parcours
     * @return position, la position du parcours
     */
    public static double getPosition() {
        return position;
    }

    /**Méthode setPostion
     * met à jour la position du parcours en fonction de la vitesse
     */
    public static void setPosition() {
        position += incrementP;
    }
}

/** Classe Avancer
 * gère le déplacement du parcours
 * extends Thread
 */
class Avancer extends Thread {
    Parcours p;
    public static final int msX = 10;
    /** constructeur */
    public Avancer(Parcours parcours){
        super();
        p = parcours;
    }

    /** Méthode run
     * Méthode que va exécuter le thread Avancer
     */
    @Override
    public void run(){
        while(Main.gameRunning){
            /* on fait descendre un peu l'oiseau/oval */
            Parcours.setPosition();
            /* on met le thread en pause pendant 10 millisecondes*/
            try {
                sleep(msX);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


}