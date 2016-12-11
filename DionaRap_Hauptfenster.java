
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.*;

public class DionaRap_Hauptfenster extends JFrame {

	/** 
	 *  
	 */
	// TODO KeyListener Nummer 5 nicht funktioniert

	private static final long serialVersionUID = 1L;

	private static int spaltenA = SpielfeldEigenschaften.SPALTEN_ANZAHL;
	private static int zeilenA = SpielfeldEigenschaften.ZEILEN_ANZAHL;
	private static int gegnerA = SpielfeldEigenschaften.GEGNER_ANZAHL;
	private static int hindernisA = SpielfeldEigenschaften.HINDERNIS_ANZAHL;

	private Navigator navisFenster;
	private MenuLeiste menuLeiste;
	private ToolBarMenu toolBarMenu;
	private String toolbarLocation = "Oben";
	private Point fensterLocation = null;

	private DionaRapModel drm;
	private DionaRapController controller;
	private ListenerModel listenerModel;
	private Spielfeld spielFeld;

	public DionaRap_Hauptfenster(String toolbarLocation, Point fensterLocation) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("DionaRap");
		setResizable(false);

		// Model und Controller
		drm = new DionaRapModel(zeilenA, spaltenA, gegnerA, hindernisA);
		controller = new DionaRapController(drm);
		listenerModel = new ListenerModel(this);
		drm.addModelChangedEventListener(listenerModel);

		// Brett initialisieren
		spielFeld = new Spielfeld(this);
		add(spielFeld.getSpielBrett(), BorderLayout.CENTER);
	

		// Navigator initialisieren
		navisFenster = new Navigator(this);
		this.addComponentListener(new ListenerSpielBrett(navisFenster));
		spielFeld.setzeAllePawns();

		// Menuleiste initialisieren
		menuLeiste = new MenuLeiste(this);
		this.setJMenuBar(menuLeiste);

		// ToolBar initialisieren
		toolBarMenu = new ToolBarMenu(this);
		setToolbarPosition(toolbarLocation);

		pack();
		if (fensterLocation != null)
			setLocation(fensterLocation);
		else
			setLocationRelativeTo(null);
		setVisible(true);

		// für KeyListener
		this.requestFocus();
		addWindowFocusListener();
	}

	private void addWindowFocusListener() {
		addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				DionaRap_Hauptfenster.this.requestFocus(); // ***
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
			}
		});
	}

	public void spielStart() {
		navisFenster.dispose();
		this.dispose();
		fensterLocation = getLocation();
		new DionaRap_Hauptfenster(toolbarLocation, fensterLocation);

	}

	public void navigatorSichtWechsel() {
		navisFenster.setVisible(!navisFenster.isVisible());
	}

	public void setLookAndFeel(String lookAndFeel) {
		try {

			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(this);
			SwingUtilities.updateComponentTreeUI(this.navisFenster);
			pack();
		} catch (Exception e) {
			System.out.println("setLookAndFeel Exception " + e);
		}

	}

	public DionaRapModel getDrm() {
		return drm;
	}

	public DionaRapController getController() {
		return controller;
	}

	public Spielfeld getSpielfeld() {
		return spielFeld;
	}

	public void setToolbarPosition(String aktionBefehl) {
		toolbarLocation = aktionBefehl;
		if (toolbarLocation == "Oben")
			add(toolBarMenu, BorderLayout.NORTH);
		else
			add(toolBarMenu, BorderLayout.SOUTH);
	}

	public ToolBarMenu getToolBarMenu() {
		return toolBarMenu;
	}

	DionaRap_Hauptfenster() {
		this("Oben", null);
	}

	public static void main(String[] args) {
		new DionaRap_Hauptfenster();

	}
}
