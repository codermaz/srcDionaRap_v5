import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DionaRap_Hauptfenster fenster;

	private JMenuItem[] miThemes = new JMenuItem[7];
	private String[] nThemes = { "alien", "dracula", "helsing", "luke", "spacewars", "squarehead", "vader" };

	PopupMenu(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		initMenuItems();
	}

	private void initMenuItems() {
		for (int i = 0; i < nThemes.length; i++) {
			createTheme(nThemes[i], i);
		}

	}

	private void createTheme(String name, int i) {
		miThemes[i] = new JMenuItem(name);
		miThemes[i].setActionCommand(name);
		miThemes[i].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String theme =  e.getActionCommand();
				SpielfeldEigenschaften.THEME = theme;
				fenster.getSpielfeld().setzeAllePawns();
				fenster.getToolBarMenu().initPanelMunition();
			}
		});
		add(miThemes[i]);
	}

}
