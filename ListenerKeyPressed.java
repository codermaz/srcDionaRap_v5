import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.objects.Player;

public class ListenerKeyPressed implements KeyListener {

	DionaRap_Hauptfenster fenster;
	
	ListenerKeyPressed (DionaRap_Hauptfenster _fenster) {
		fenster= _fenster;
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		DionaRapController drc = fenster.getController();
		
		//System.out.println("keyCode :"+  e.getKeyCode());
		switch (keyCode) {  
		case KeyEvent.VK_UP: case 73: case 104: case 224: // 73=i  Contoller.dir= 8
			//System.out.println("oben");
			drc.movePlayer(8);
			break;
		case KeyEvent.VK_DOWN: case 78: case 98: case 225: // 78=n
			//System.out.println("unten");
			drc.movePlayer(2);
			break;
		case KeyEvent.VK_LEFT: case 72: case 100: case 226: // 72=h 
			//System.out.println("links");
			drc.movePlayer(4);
			break;   
		case KeyEvent.VK_RIGHT: case 75: case 102: case 227: // 75=k
			//System.out.println("rechts");
			drc.movePlayer(6);
			break;
		 case 85: case 36: case 103:  // 85=u 
			//System.out.println("links_oben");
			drc.movePlayer(7);
			break;
		case 66: case 35: case 97: // 66=b
			//System.out.println("links_unten");
			drc.movePlayer(1);
			break;
		 case 79: case 33: case 105: // 79=o 
			//System.out.println("rechts_oben");
			drc.movePlayer(9);
			break;
		 case 77: case 34: case 99: // 77=m
			//System.out.println("rechts_unten");
			drc.movePlayer(3);
			break;	
		 case 32: case 74: case 101: case 65368: // 32=space  und 74=j
			//System.out.println("shiessen");
			drc.shoot();
			break;	
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}
