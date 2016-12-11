
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhwgt.dionarap.controller.DionaRapController;

public class ListenerBewegung implements ActionListener {
	private DionaRap_Hauptfenster fenster;

	public ListenerBewegung(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
	}

	@Override
	public void actionPerformed(ActionEvent event) { // Ausloeser des ActionEvent
														// besorgen
		String befehl = event.getActionCommand();
		DionaRapController drc = fenster.getController();

		//System.out.println(befehl);
		switch (befehl) {

		case "links":
			drc.movePlayer(4);
			break;
		case "oben":
			drc.movePlayer(8);
			break;
		case "unten":
			drc.movePlayer(2);
			break;
		case "rechts":
			drc.movePlayer(6);
			break;

		case "links_oben":
			drc.movePlayer(7);
			break;
		case "links_unten":
			drc.movePlayer(1);
			break;
		case "rechts_oben":
			drc.movePlayer(9);
			break;
		case "rechts_unten":
			drc.movePlayer(3);
			break;

		case "shiessen":
			drc.shoot();
			break;

		}

		fenster.requestFocus(); // um KeyListener aktiv zu lassen

	}

}
