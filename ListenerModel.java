  
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.DionaRapListener;

public class ListenerModel implements DionaRapListener{
	
	private DionaRap_Hauptfenster fenster;
	private int gegnerAnfang= SpielfeldEigenschaften.GEGNER_ANZAHL;
	private int gegnerAktuell;
	private int gegnerProzent;
	private int munitionAnzahl;
	
	
	public ListenerModel(DionaRap_Hauptfenster _fenster) {
		fenster= _fenster;
 	}
	
	@Override
	public void modelChanged(DionaRapChangedEvent arg0) {	
		fenster.getSpielfeld().leereBrett();	
		fenster.getSpielfeld().setzeAllePawns();	
		DionaRapModel drm = fenster.getDrm(); 
		ToolBarMenu tBar = fenster.getToolBarMenu();
		
		// JToolBar tBar = fenster.getSpielSteuern().getToolBarMenu().getToolBar();
		// ((ToolBarMenu) tBar).setPunkteStand(Integer.toString(Punkte));
		//PunkteStand bei ToolBarMenu aktualisieren
		int Punkte=drm.getScore();
		tBar.setPunkteStand(Integer.toString(Punkte));		
		
		//ProgressStand bei ToolBarMenu aktualisieren
		gegnerAktuell= drm.getOpponentCount();
		gegnerProzent=(int) (100-((double)gegnerAktuell / (double)gegnerAnfang)*100);
		tBar.setSpielfortschritWert(gegnerProzent);
		
		//TODO Munition Anzahl aktualisieren
		munitionAnzahl= drm.getAmmoValue();
		tBar.setMunitionAnzahl(munitionAnzahl);
		System.out.println(" Munitionsanzahl : " +munitionAnzahl);
	}

 	@Override
	public void statusChanged(GameStatusEvent arg0) {
 		
 		DialogWonOver dwo = null;
 		
		if (arg0.isGameWon()) 
			dwo = new DialogWonOver(fenster, 1);
	
		if (arg0.isGameOver()) 
			dwo = new DialogWonOver(fenster, 0);
		
		if (dwo.getAuswahl()==0)
			fenster.spielStart();
		else 
			fenster.getToolBarMenu().setButtonNeuEnabled(true);
	}
}
