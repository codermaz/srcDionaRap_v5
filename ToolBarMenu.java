import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;



public class ToolBarMenu extends JToolBar {

	private static final long serialVersionUID = 1L;
	private DionaRap_Hauptfenster fenster;
	
	private JPanel pNeuSpiel = new JPanel();
	private JPanel pPunkteStand = new JPanel();
	private JPanel pMunition = new JPanel();
	private JPanel pSpielFortschritt = new JPanel();
	private JPanel pSettings = new JPanel();

	private float panelBreite = SpielfeldEigenschaften.SPALTEN_ANZAHL * SpielfeldEigenschaften.LABEL_DIMENSION
			/ SpielfeldEigenschaften.TOOLBAR_PANEL_ANZAHL;
	private int panelHeight = SpielfeldEigenschaften.TOOLBAR_HEIGHT;
	private Dimension pDim = new Dimension((int) panelBreite, panelHeight);
	private Color PR_FARBE_blau = new Color(0x185BAF);  // PanelRandfarbe himmelblau
	private Color PR_FARBE_gruen = new Color(0x6DB45D);  // PanelRandfarbe grün
	private Font panelFont = new Font("times new roman",Font.PLAIN,12);
	
	private JButton bNeuSpiel;
	private JButton bSettings = new JButton("Settings");
	private JTextField tPunkte = new JTextField("0");
	private JLabel lMunition[] = new JLabel[3];
	private ImageIcon imageMunition;
	private int dimensionXLabelMunition=30;
	private int dimensionYLabelMunition=30;
	private JProgressBar pbSpielFortschritt = new JProgressBar(0,100);

	ToolBarMenu(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		initToolBar();
		
		initPanelNeuSpiel();
		initPanelPunkteStand();
		initPanelMunition();
		initPanelFortschritt();
		initPanelSettings();
		
		setLayout(new GridLayout(1, 5));
		add(pNeuSpiel);
		add(pPunkteStand);
		add(pMunition);
		add(pSpielFortschritt);
		add(pSettings);
	}
	
	public JToolBar getToolBarMenu() {
		return this;
	}

	private void initPanelNeuSpiel() {
		bNeuSpiel = new JButton("Neues Spiel");
		bNeuSpiel.setActionCommand("NeuSpiel");
		bNeuSpiel.setEnabled(false);
		bNeuSpiel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bNeuSpiel.setFont(panelFont);
		bNeuSpiel.addActionListener(new ListenerToolBarButtons(fenster));
		
		pNeuSpiel.setLayout(new BoxLayout(pNeuSpiel, BoxLayout.Y_AXIS));
		pNeuSpiel.setPreferredSize(pDim);

		pNeuSpiel.add(Box.createGlue());
		pNeuSpiel.add(bNeuSpiel);
		pNeuSpiel.add(Box.createGlue());
	}
	
	public void setButtonNeuEnabled(Boolean b) {
		bNeuSpiel.setEnabled(b);
	}

	private void initPanelPunkteStand() {
		tPunkte.setAlignmentX(Component.CENTER_ALIGNMENT);
		tPunkte.setEditable(false);
		tPunkte.setColumns(5);
		tPunkte.setHorizontalAlignment(JTextField.CENTER);
		tPunkte.setForeground(PR_FARBE_blau);
		
		pPunkteStand.setLayout(new BoxLayout(pPunkteStand, BoxLayout.Y_AXIS));
		pPunkteStand.setBorder(BorderFactory.createTitledBorder(null,"Punktestand",
						TitledBorder.CENTER, TitledBorder.TOP, 
						panelFont, PR_FARBE_blau));

		pPunkteStand.setPreferredSize(pDim);

		pPunkteStand.add(Box.createGlue());
		pPunkteStand.add(tPunkte);
		pPunkteStand.add(Box.createGlue());
	}
	
	public void setPunkteStand(String Punkte) {
		tPunkte.setText(Punkte);
	}

	public void initPanelMunition() {
		String fs= File.separator;
		String theme=SpielfeldEigenschaften.THEME;
		String figur = "ammo.png";
		
		//redimension for Icon
		lMunition[0] = new JLabel();
		lMunition[0].setPreferredSize(new Dimension(dimensionXLabelMunition,dimensionYLabelMunition));
		imageMunition= new ImageIcon(System.getProperty("user.dir") 
				+ fs +"images" + fs + theme + fs + figur) {
		private static final long serialVersionUID = 1L;

			//Resize the icon to fit a JLabel
			@Override
			public void paintIcon( Component c, Graphics g, int x, int y ) {
				g.drawImage(getImage(), 0, 0, lMunition[0].getWidth(),lMunition[0].getHeight(), c);
			}
		};

		for (int i = 0; i < 3; i++) {
			lMunition[i] = new JLabel();
			lMunition[i].setIcon(imageMunition);
			lMunition[i].setBorder(BorderFactory.createLineBorder(PR_FARBE_gruen));
		}
		pMunition.setLayout(new GridLayout(1,3,2,2));

		pMunition.setBorder(BorderFactory.createTitledBorder(null,"Munition",
				TitledBorder.CENTER, TitledBorder.TOP, 
				panelFont, PR_FARBE_blau));
		pMunition.setPreferredSize(pDim);
		pMunition.setToolTipText("aktueller Munitionsvorrat");
		
		pMunition.removeAll();
		for (int i = 0; i < 3; i++) 
			pMunition.add(lMunition[i]);
		
		setMunitionAnzahl(fenster.getDrm().getAmmoValue());
	}

	public void setMunitionAnzahl(int Anzahl) {
		//Munitionsanzeige entleeren
		for (int i = 0; i < 3; i++) 
			lMunition[i].setIcon(null);
		lMunition[0].setText(null);
		
		//Munitionsanzeige wieder füllen
		switch (Anzahl) {
		case 3: 
			lMunition[0].setIcon(imageMunition);
			break;
		case 2:
			lMunition[1].setIcon(imageMunition);
			break;
		case 1:
			lMunition[2].setIcon(imageMunition);
			break;
		}
		if (Anzahl>3) {
			lMunition[0].setIcon(null);
			lMunition[0].setText("*"+Integer.toString(Anzahl));
			lMunition[0].setHorizontalAlignment(SwingConstants.CENTER); 
			for (int i = 1; i < 3; i++) 
				lMunition[i].setIcon(imageMunition);
		}

		pMunition.updateUI();
	}
	
	private void initPanelFortschritt() {
		pbSpielFortschritt.setAlignmentX(Component.CENTER_ALIGNMENT);
		pSpielFortschritt.setLayout(new BoxLayout(pSpielFortschritt, BoxLayout.Y_AXIS));
		pSpielFortschritt.setBorder(BorderFactory.createTitledBorder(null,"Spielfortschritt",
				TitledBorder.CENTER, TitledBorder.TOP, 
				panelFont, PR_FARBE_blau));

		pSpielFortschritt.add(Box.createGlue());
		pSpielFortschritt.add(pbSpielFortschritt);
		pSpielFortschritt.add(Box.createGlue());
		pSpielFortschritt.setPreferredSize(pDim);
		
		pbSpielFortschritt.setStringPainted(true);
	}
	
	public void setSpielfortschritWert(int Wert) {
		pbSpielFortschritt.setValue(Wert);
	}

	private void initPanelSettings() {
		bSettings.setActionCommand("Settings");
		bSettings.setEnabled(true);
		bSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		bSettings.setFont(panelFont);
		bSettings.addActionListener(new ListenerToolBarButtons(fenster));
		
		pSettings.setLayout(new BoxLayout(pSettings, BoxLayout.Y_AXIS));
		pSettings.setPreferredSize(pDim);

		pSettings.add(Box.createGlue());
		pSettings.add(bSettings);
		pSettings.add(Box.createGlue());
	}

	private void initToolBar() {
		setPreferredSize(new Dimension(SpielfeldEigenschaften.SPALTEN_ANZAHL * SpielfeldEigenschaften.LABEL_DIMENSION,
				SpielfeldEigenschaften.TOOLBAR_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setFloatable(false);
	}
	
}
