  

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JWindow;

public class Navigator extends JWindow {

	private JPanel panelMitRand;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der Klasse <br>
	 * <br>
	 * Die Groesse wird fest eingestellt. Die Position relativ zu fenster wird
	 * berechnet. Das NavigationsFenster enhält ein Panel mit einem Rand - damit
	 * wird dem NavigationsFenster ein Rand gegeben
	 * 
	 * @param fenster
	 *            Das Vaterfenster zu diesem Object
	 */

	public Navigator(DionaRap_Hauptfenster fenster) {
		super(fenster); // JWindow ist Kindfester zum JFrame. Es entfällt ein
						// Windowlistener !
		// Feststellen, ob das GraphicsDevice Transparenz und Formen f�r Fenster
		// erlaubt
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		boolean isUniformTranslucencySupported = gd
				.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT);

		boolean isShapedWindowSupported = gd
				.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT);


        //If shaped windows aren't supported, exit.
        if (!isShapedWindowSupported) {
            System.err.println("Shaped windows are not supported");
            System.exit(0);
        }

        //If translucent windows aren't supported, 
        //create an opaque window.
        if (!isUniformTranslucencySupported) {
            System.out.println(
                "Translucency is not supported, creating an opaque window");
        } else {
        	this.setOpacity((float) 0.8);
        }

        
		//this.setUndecorated(true);
		

		// Fenster muss so gross sein, damit das Dreieck vollständig dargestellt werden kann
		Achteck polygon = new Achteck(SpielfeldEigenschaften.BUTTONS_GROESSE);
		this.setShape(polygon);
		// Das ist die Grösse des Fensters und wird für setSize() gebraucht
		Rectangle umschiessendesrechteck = polygon.getBounds();
		this.setSize(umschiessendesrechteck.width, umschiessendesrechteck.height);
		
		this.setLocation((int) fenster.getLocation().getX() + fenster.getWidth()
			+ SpielfeldEigenschaften.ENTFERNUNG_ZUM_SPIELBRETT, (int) fenster.getLocation().getY());
		
		panelMitRand = new JPanel();
		panelMitRand.setLayout(new BorderLayout());
		panelMitRand.add(new Tastatur (fenster, Color.red));
		this.getContentPane().add(panelMitRand);
		
		this.setVisible(true);
		super.requestFocus();

	}

}
