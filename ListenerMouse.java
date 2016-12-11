import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerMouse implements MouseListener {

	DionaRap_Hauptfenster fenster;
	
	ListenerMouse (DionaRap_Hauptfenster _fenster) {
		fenster= _fenster;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {
			System.out.println("button 1");
			 
			 
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			 PopupMenu menu = new PopupMenu (fenster);
			 menu.show(e.getComponent(), e.getX(), e.getY());
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
