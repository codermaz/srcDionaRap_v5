
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.model.objects.Destruction;
import de.fhwgt.dionarap.model.objects.Obstacle;
import de.fhwgt.dionarap.model.objects.Opponent;
import de.fhwgt.dionarap.model.objects.Player;
import de.fhwgt.dionarap.model.objects.Vortex;

/**
 * 
 * Dieses Fenster besitzt ein Kindfenster vom Typ JWindow
 *
 */

public class Spielfeld extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int spaltenA = SpielfeldEigenschaften.SPALTEN_ANZAHL;
	private static int zeilenA = SpielfeldEigenschaften.ZEILEN_ANZAHL;

	private Container spielFlaeche;
	private JPanel spielBrett = new JPanel();
	private static JLabel[][] spielFelder = new JLabel[spaltenA][zeilenA];
	private static String theme;

	private DionaRap_Hauptfenster fenster;
	private ListenerMouse listenerMouse;

	/**
	 * Konstruktor der Klasse <br>
	 * Erzeugt ein Kindfenster vom Typ JWindow
	 */

	public Spielfeld(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		
		fenster.setPreferredSize(new Dimension(spaltenA * SpielfeldEigenschaften.LABEL_DIMENSION,
				zeilenA * SpielfeldEigenschaften.LABEL_DIMENSION));
		fenster.setLocationRelativeTo(null);

		spielBrett.setLayout(new GridLayout(zeilenA, spaltenA, 0, 0));
		spielFlaeche = fenster.getContentPane();
		spielFlaeche.setLayout(new BorderLayout());

		zeichneErstesBrett();
		initListener();
	}

	public JPanel getSpielBrett() {
		return spielBrett;
	}

	public void setTheme(String _theme){
		theme=_theme;
	}
	
	private void initListener() {
		listenerMouse = new ListenerMouse(fenster);
		fenster.addMouseListener(listenerMouse);
		ListenerKeyPressed listenerKeyPressed = new ListenerKeyPressed(fenster);
		fenster.addKeyListener(listenerKeyPressed);
	}

	public void nullBrett() {
		spielBrett.removeAll();
	}

	public void leereBrett() {
		for (int i = 0; i < zeilenA; i++) // für Zeilen
			for (int j = 0; j < spaltenA; j++) // für Spalten
				spielFelder[j][i].setIcon(null);
	}

	public void setzeAllePawns() {
		DionaRapModel drm = fenster.getDrm();

		// Brett vorbreiten
		AbstractPawn[] allePawns = drm.getAllPawns();
		for (int i = 0; i < allePawns.length; i++) {
			if (allePawns[i] instanceof Obstacle) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "obstacle.gif");
			} else if (allePawns[i] instanceof Opponent) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "opponent.gif");
			} else if (allePawns[i] instanceof Player) {
				Player player = (Player) allePawns[i];
				String dir = Integer.toString(player.getViewDirection());
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "player" + dir + ".gif");
			} else if (allePawns[i] instanceof Vortex) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "vortex.gif");
			} else if (allePawns[i] instanceof Destruction) {
				setzeFigur(allePawns[i].getX(), allePawns[i].getY(), "destruction.gif");
			}
		}
	}

	private static Color getInverseFarbe(Color in) {
		return new Color(255 - in.getRed(), 255 - in.getGreen(), 255 - in.getBlue(), in.getAlpha());
	}

	public void setzeFigur(int spalteX, int spalteY, String figur) {
		String fs = File.separator;
		theme = SpielfeldEigenschaften.THEME;
		ImageIcon image = new ImageIcon(System.getProperty("user.dir") + fs + "images" + fs + theme + fs + figur);
		if (figur == "vortex.gif")
			spielFelder[spalteX][spalteY].setBackground(Color.red);

		spielFelder[spalteX][spalteY].setIcon(image);
	
		// spielFelder[spalteX][spalteY].setBorder(BorderFactory.createEtchedBorder());
		// spielFelder[spalteX][spalteY].setForeground(getInverseFarbe(spielFelder[spalteX][spalteY].getBackground()));
		// spielFelder[spalteX][spalteY].setText(figur);
	}

	public void zeichneErstesBrett() {
		// Lables fuer Brett vorbreiten
		int farbeWechsel = 0;
		for (int i = 0; i < zeilenA; i++) { // fuer Zeilen
			for (int j = 0; j < spaltenA; j++) { // fuer Spalten
				spielFelder[j][i] = new JLabel("", JLabel.CENTER);
				if (farbeWechsel == 1) {
					spielFelder[j][i].setBackground(SpielfeldEigenschaften.BRETT_COLOR1);
					farbeWechsel = 0;
				} else {
					spielFelder[j][i].setBackground(SpielfeldEigenschaften.BRETT_COLOR2);
					farbeWechsel = 1;
				}
				spielFelder[j][i].setOpaque(true);
				//spielFelder[j][i].addMouseListener(listenerMouse);
				spielBrett.add(spielFelder[j][i]);
			}

			// nochmal die Hintergrundfarbe wechseln, wenn die Spaltenanzahl
			// ungerade ist.
			if ((spaltenA % 2) == 0) {
				if (farbeWechsel == 0)
					farbeWechsel = 1;
				else
					farbeWechsel = 0;
			}
		}
	}

}
