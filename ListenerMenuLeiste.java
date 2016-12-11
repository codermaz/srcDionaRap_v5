import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerMenuLeiste implements ActionListener {

	DionaRap_Hauptfenster fenster;
	
	ListenerMenuLeiste (DionaRap_Hauptfenster _fenster) {
		fenster= _fenster;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String aktionBefehl = e.getActionCommand();
		if (aktionBefehl.contains("swing.")){
            fenster.setLookAndFeel(aktionBefehl);
            return;
		}    
		if (aktionBefehl.contains("Unten") || aktionBefehl.contains("Oben")){
            fenster.setToolbarPosition(aktionBefehl);
            fenster.pack();
            return;
		}    
	}
	
}
