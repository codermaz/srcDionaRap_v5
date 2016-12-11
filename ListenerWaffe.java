 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhwgt.dionarap.controller.DionaRapController;

public class ListenerWaffe implements ActionListener{
	private DionaRap_Hauptfenster fenster;
	DionaRapController drc;
	
	public ListenerWaffe(DionaRap_Hauptfenster _fenster) {
		this.fenster= _fenster;
		drc = fenster.getController();
	}
	
	@Override
	public void actionPerformed (ActionEvent event) {
		String befehl = event.getActionCommand();
		//System.out.println(befehl);
		drc.shoot();
		fenster.requestFocus(); // um KeyListener aktiv zu lassen
	}
	
}
