import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Control implements MouseListener  {

	Affichage affichage;

    public Control(Affichage a) {
    	this.affichage = a;     
    }

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
