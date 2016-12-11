import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DialogWonOver {
	private DionaRap_Hauptfenster fenster;
	private String spielerAuswahl[] = { "Neues Spiel", "Abbrechen" };
	private int spielerAusgewaehlt; // 0 fur Neues Spiel , 1 fuer Abbrechen
	private int status;
	private String spielStatusMsg[] = { "Game Over", "Sie haben verloren!", "Gewonnen", "Sie haben gewonnen :)",
			"gameover.gif", "gewonnen.gif" };
	private String spielStatus[] = new String[3];

	DialogWonOver(DionaRap_Hauptfenster _fenster, int _status) {
		fenster = _fenster;
		status = _status;
		auswahlNeuSpiel();
	}

	public void auswahlNeuSpiel() {

		switch (status) {
		case 0: // verloren
			spielStatus[0] = spielStatusMsg[0];
			spielStatus[1] = spielStatusMsg[1];
			spielStatus[2] = spielStatusMsg[4];
			break;
		case 1: // gewinnen
			spielStatus[0] = spielStatusMsg[2];
			spielStatus[1] = spielStatusMsg[3];
			spielStatus[2] = spielStatusMsg[5];
			break;
		}

		String fs = File.separator;
		ImageIcon image = new ImageIcon(System.getProperty("user.dir") + fs + "images" + fs + spielStatus[2]);
		spielerAusgewaehlt = JOptionPane.showOptionDialog(fenster, spielStatus[1], spielStatus[0],
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, image, spielerAuswahl, spielerAuswahl[0]);

	}

	public int getAuswahl() {
		return spielerAusgewaehlt;
	}

}
