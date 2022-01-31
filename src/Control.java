import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Classe Control
 * Gère les interactions avec l'utilisateur
 * implemente MouseListener qui vérifie les actions de la souris
 */
public class Control implements MouseListener  {

	Affichage affichage;

    /** Constructeur de la classe Control
     * Initialise un controlleur pour notre jeu
     * @param a une instance de la fenêtre du jeu
     */
    public Control(Affichage a) {
    	affichage = a;
        /* On ajoute le control en Mouse Listener à la fenêtre */
        affichage.addMouseListener(this);
    }

    /** Méthode mouseClicked
     * On redéfinit la méthode de MouseListener pour appeler la méthode jump et actualiser l'affichage
     */
    public void mouseClicked(MouseEvent e) {
        Etat.jump();
        affichage.repaint(Affichage.xCenterOval, 0, 2 * Affichage.widthOval, Affichage.HAUT);
    }
    
    
    public void mousePressed(MouseEvent e) {}
    //Invoked when a mouse button has been pressed on a component.

    public void mouseReleased(MouseEvent e) {}
    //Invoked when a mouse button has been released on a component.

    public void mouseEntered(MouseEvent e) {}
    //Invoked when the mouse enters a component.

    public void mouseExited(MouseEvent e) {}
    //Invoked when the mouse exits a component.
}
