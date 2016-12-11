import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MenuLeiste extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu mAnsicht, mPosition, mNavigator, mLaF;
	private JRadioButtonMenuItem rbMenuItem;
	private JCheckBoxMenuItem cbMenuItem;
	private DionaRap_Hauptfenster fenster;
	private ListenerMenuLeiste listenerMenu;

	MenuLeiste(DionaRap_Hauptfenster _fenster) {
		fenster = _fenster;
		listenerMenu = new ListenerMenuLeiste(fenster);

		// Menu Ansicht
		mAnsicht = new JMenu("Ansicht");
		mAnsicht.setMnemonic(KeyEvent.VK_A);
		this.add(mAnsicht);

		initSubmenuToolbar();
		initSubmenuNavigator();
		initSubmenuLaF();

	}

	private void initSubmenuToolbar() {
		// Submenu ToolBar
		mPosition = new JMenu("ToolBar");
		mPosition.setMnemonic(KeyEvent.VK_T);
		mAnsicht.add(mPosition);

		// RadioButtons
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("am oberen Rand positionieren");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		rbMenuItem.setActionCommand("Oben");
		rbMenuItem.addActionListener(listenerMenu);
		group.add(rbMenuItem);
		mPosition.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("am unteren Rand positionieren");
		rbMenuItem.setMnemonic(KeyEvent.VK_U);
		rbMenuItem.setActionCommand("Unten");
		rbMenuItem.addActionListener(listenerMenu);
		group.add(rbMenuItem);
		mPosition.add(rbMenuItem);

	}

	private void initSubmenuNavigator() {
		// MenuItem Navigator
		mNavigator = new JMenu("Navigator anzeigen");
		mNavigator.setMnemonic(KeyEvent.VK_N);
		mAnsicht.add(mNavigator);

		cbMenuItem = new JCheckBoxMenuItem();
		cbMenuItem.setMnemonic(KeyEvent.VK_E);
		cbMenuItem.setSelected(true);
		if (!cbMenuItem.isSelected()) {
			cbMenuItem.setText("Navigator ist ausgeblendet.");
		} else {
			cbMenuItem.setText("Navigator ist eingeblendet.");
		}

		cbMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenster.navigatorSichtWechsel();
				if (!cbMenuItem.isSelected())
					cbMenuItem.setText("Navigator ist ausgeblendet.");
				else
					cbMenuItem.setText("Navigator ist eingeblendet.");
			}
		});

		mNavigator.add(cbMenuItem);
	}

	private void initSubmenuLaF() {
		// Menu- Look and Feel
		mAnsicht.addSeparator();
		mLaF = new JMenu("Look and Feel");
		mLaF.setMnemonic(KeyEvent.VK_L);
		mAnsicht.add(mLaF);

		// RadioButtons- Look and Feel
		LookAndFeelInfo[] laFInfo = UIManager.getInstalledLookAndFeels();
		ButtonGroup gLaF = new ButtonGroup();
		String laf = UIManager.getLookAndFeel().getName();
		for (int i = 0; i < laFInfo.length; i++) {
			mLaF.add(this.createRadioMenuItem(laFInfo[i].getName(), laFInfo[i].getClassName(), gLaF,
					laf.equals(laFInfo[i].getName())));
		}

	}

	private JRadioButtonMenuItem createRadioMenuItem(String title, String command, ButtonGroup group,
			boolean selected) {
		rbMenuItem = new JRadioButtonMenuItem(title);
		rbMenuItem.setActionCommand(command);
		rbMenuItem.setSelected(selected);
		rbMenuItem.addActionListener(listenerMenu);
		group.add(rbMenuItem);
		return rbMenuItem;
	}

}
