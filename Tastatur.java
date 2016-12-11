
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Tastatur extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DionaRap_Hauptfenster fenster;
	private JButton[] tastatur = new JButton[9];
	private String[] buttonText = { "7", "8", "9", "4", "5", "6", "1", "2", "3" };

	Tastatur(DionaRap_Hauptfenster _fenster, Color randColor) {
		this.fenster = _fenster;
		TastaturInsFenster(randColor);
	}

	public void TastaturInsFenster(Color c) {
		// Rand fuer JWindow mit Panel ermoeglichen
		// panel.setBorder(BorderFactory.createEtchedBorder(c, c));
		// panel.setOpaque(true);
		this.setLayout(new GridLayout(3, 3, 0, 0));
		// Buttons hinzufuegen
		setTastatur(buttonText);

	}

	// getters und setters
	public void setTastatur(String[] buttonText) {
		String fs = File.separator;
		this.removeAll();
		for (int i = 0; i < 9; i++) {
			// tastatur[i] = new JButton(buttonText[i]);
			tastatur[i] = new JButton();
			ImageIcon imageT = new ImageIcon(System.getProperty("user.dir") + fs + "images" + fs + "navigator" + fs
					+ "taste" + buttonText[i] + ".gif");
			tastatur[i].setIcon(imageT);

			// tastatur[i].setPreferredSize(
			// new Dimension(SpielBrettEigenschaften.BUTTONS_GROESSE,
			// SpielBrettEigenschaften.BUTTONS_GROESSE));
			tastatur[i].setMargin(new Insets(0, 0, 0, 0));
			this.add(tastatur[i]);
			// ActionListener fuer die taste erzeugen und bei der taste
			// registrieren
			switch (i + 1) {
			case 1:
				tastatur[i].setActionCommand("links_oben");
				break;
			case 2:
				tastatur[i].setActionCommand("oben");
				break;
			case 3:
				tastatur[i].setActionCommand("rechts_oben");
				break;
			case 4:
				tastatur[i].setActionCommand("links");
				break;

			case 6:
				tastatur[i].setActionCommand("rechts");
				break;
			case 7:
				tastatur[i].setActionCommand("links_unten");
				break;
			case 8:
				tastatur[i].setActionCommand("unten");
				break;
			case 9:
				tastatur[i].setActionCommand("rechts_unten");
				break;
			}
			if ((i + 1) == 5) {
				tastatur[i].setActionCommand("schiessen");
				tastatur[i].addActionListener(new ListenerWaffe(fenster));
			} else
				tastatur[i].addActionListener(new ListenerBewegung(fenster));
		}
	}

}
