package servicesCombined;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Veranstaltung;
import generated.VeranstaltungenM;
import restService.ClientRest;
import xmppService.XmppManager;

/**
 * Diese Klasse dient dazu, die Operationen zur Verfügung zu stellen, welche von der GUI vom
 * Veranstalter genutzt werden dürfen. Dabei werden beide Services berücksichtigt, sodass sowohl
 * der REST-Service, als auch der XMPP-Service miteinbezogen werden.
 * 
 * Orte (Gebäude) und Veranstalter wurden zunächst nicht implementiert, da die Refernzierung
 * bisher aufrund mangelnder Zeit noch nicht umgesetzt worden ist.
 * @author CrackX
 *
 */
public class CombinedServicesVeranstalter{
	private ClientRest cr = new ClientRest();
	private XmppManager xm = new XmppManager();
	private boolean initialized, pubSubCheck, connCheck, authCheck = false;
	private int connectionTries = 10;
	
	
	public boolean initialize() throws InterruptedException{
		
		while(initialized == false && connectionTries > 0){
	
			if(connCheck == false){
				connCheck = xm.verbinden();
			}
			if(connCheck == true){
				pubSubCheck = xm.managePubSub();
			}
			
			if(pubSubCheck == true){
				authCheck = xm.login("veranstalter", "veranstalter");
			}
			
//			if (authCheck == true){ 				//SubCheck nur beim Interessent
//				subCheck = xm.restoreSubscriptions();
//			}
			
			if(authCheck || connCheck || authCheck){
				initialized = true;
			}

			else{
				System.out.println("Verbindung fehlgeschlagen, es wird noch " + connectionTries + " mal versucht.");
				connectionTries--;
				Thread.sleep(2000);
			}
		}

		if(initialized == true){
			System.out.println("Erfolgreich eingeloggt!");
			return true;
		}
		
		else{
			System.out.println("Die Verbindung konnte nicht erfolgreich zum Server aufgebaut werden.");
			return false;
		}
	}
	
	//A = add (post), R = refresh(put), D = delete, N = Sportgruppe refresh, wenn Veranst. gelöscht.
	
	/**
	 * Fügt eine neue Veranstaltung zu einer Sporgruppenliste hinzu.
	 * Zudem wird ein LeafNode fuer diese Veranstaltung erzeugt.
	 * @param sportgruppeId Sportgruppen-ID in der sich die Sportart befindet.
	 * @param sportartId Sportart-ID zu der eine neue Veranstaltung erstellt werden soll.
	 * @param neueVeranstaltung 
	 * @return true, wenn Veranstaltung erfolgreich hinzugefügt + gepublished + neuer Node für
	 * neue Veranstaltung erstellt.
	 * 
	 * TODO: Anpassen, wenn Marshalling eingefügt.
	 */
	public boolean postVeranstaltung(String sportgruppeId, String sportartId, Veranstaltung neueVeranstaltung){
		int neueVeranstaltungsID;
		String payload;
		
		if(initialized == true){
			neueVeranstaltungsID = cr.postVeranstaltung(sportgruppeId, sportartId, neueVeranstaltung);	
			if(neueVeranstaltungsID != -1){
				payload = "<A"+sportgruppeId+sportartId+neueVeranstaltungsID+"/>";
				
				//Publish: Sportart hat neue Veranstaltung. 
				xm.publishToLeaf(sportgruppeId+sportartId+"Sportart", payload, false);
				//xm.createLeaf(sportgruppeId+sportartId+neueVeranstaltungsID+"Veranstaltung"); Erst wenn Marshalling eingefuegt
				return true;
			}
		}
		return false;
	}
	/**
	 * Löscht die angegebene Veranstaltung.
	 * Es wird sowohl die konkrete Veranstaltung gelöscht, als auch der ensprchende Leaf-Node.
	 * Zudem wird der Abonnent der konkreten Veranstaltung Benachrichtigt, dass daraufhin sein Abonnement auf
	 * dieser Veranstaltung aufgehoben wird. Der Sportgruppen-Interessierte, in dem sich diese Veranstaltung
	 * befindet, bekommt ebenfalls eine Nachricht, dass der Veranstaltungsliste dieser Sportart eine Veranstaltung
	 * gelöscht wurde.
	 * 
	 * @param sportgruppeId Sportgruppen-ID
	 * @param sportartId Sportart-ID (in der sich die gelöschte Veranstaltung befand)
	 * @param veranstaltungId Veranstaltung-ID (gelöschte Veranstaltung)
	 * @return true, wenn erfolgreich Veranstaltung gelöscht + gepublished + Leaf gelöscht.
	 */
	public boolean deleteVeranstaltung(String sportgruppeId, String sportartId, String veranstaltungId){
		boolean deleted;
		String payloadVeranstaltung;
		String payloadSportart;
		
		if(initialized == true){		
			deleted = cr.deleteVeranstaltung(sportgruppeId, sportartId, veranstaltungId);
			System.out.println(deleted);
			if(deleted == true){
				payloadVeranstaltung = "<D"+sportgruppeId+sportartId+veranstaltungId+"/>";
				payloadSportart = "<N"+sportgruppeId+sportartId+veranstaltungId+"/>";
				//Publish: Node wird deleted
				xm.publishToLeaf(sportgruppeId+sportartId+"Sportart", payloadSportart, false);
				xm.publishToLeaf(sportgruppeId+sportartId+veranstaltungId+"Veranstaltung", payloadVeranstaltung, false);
				return true; // Erst wenns geht! xm.deleteLeaf(sportgruppeId+sportartId+veranstaltungId+"Veranstaltung"); 
			}
		}
		return false;	
	}
	
	/**
	 * Ändert die angegebene Veranstaltung.
	 * Abonnenten dieser Veranstaltung bekommen eine Benachrichtigung.
	 * 
	 * @param sportgruppeId Sportgruppen-ID In der sich die enstprechende Sportart befindet.
	 * @param sportartId Sportart-ID in der sich die zu ändernde Veranstaltung befindet.
	 * @param veranstaltungId Veranstaltung-ID der zu ändernden Veranstaltung.
	 * @param neueVeranstaltung Veranstaltungsobjekt, welches die bestehende Veranstaltung ersetzt und damit ändert.
	 * Diese sollte zuvor mit dem Builder zusammengestellt werden. 
	 * @return true wenn Veranstaltung geändert und Abonnenten dieser benachrichtigt wurden.
	 */
	public boolean putVeranstaltung(String sportgruppeId, String sportartId, String veranstaltungId, Veranstaltung neueVeranstaltung){
		boolean updated;
		String payload;
		
		if(initialized == true){
			updated = cr.putVeranstaltung(sportgruppeId, sportartId, veranstaltungId, neueVeranstaltung);
			if(updated == true){
				payload = "<R"+sportgruppeId+sportartId+veranstaltungId+"/>";
				//Veranstaltung wurde geändert
				xm.publishToLeaf(sportgruppeId+sportartId+veranstaltungId+"Veranstaltung", payload, false);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Erzeugt ein JAXB-Veranstaltungsobjekt, weches zum erstellen einer Veranstaltung genutzt werden sollte.
	 * 
	 * @param beschreibung Die Beschreibung der Veranstaltung.
	 * @param info (optional) Weitergehende Informationen zu der Veranstaltung.
	 * @param datum Das Datum, an dem die Veranstaltung ausgetragen wird. Am besten buildXMLDate benutzen.
	 * @param time Die Uhrzeit, an dem die Veranstaltung ausgetragen wird. Am besten buildXMLTime benutzen.
	 * @param niveau (optional) Niveau der Veranstaltung.
	 * @param vorraussetzungen Vorraussetzungen, welche gegeben werden müssen, damit an der Veranstaltung teilgenommen werden kann.
	 * @param gebaeudeIdRef (noch Fehlerhaft) Gebäude-ID, in welchem die Veranstaltung ausgetragen werden soll.
	 * @param veranstalterIdRef Veranstalter, welcher die Veranstaltung austrägt.
	 * @return Das JAXB-Veranstaltungsobjekt.
	 */
	public Veranstaltung buildVeranstaltung(String beschreibung, String info, XMLGregorianCalendar datum,
			XMLGregorianCalendar time, String niveau, String vorraussetzungen, String gebaeudeIdRef,
			String veranstalterIdRef){
		return cr.buildVeranstaltung(beschreibung, info, datum, time, niveau, vorraussetzungen, gebaeudeIdRef, 
				veranstalterIdRef);
	}
	
	/** 
	 * Erzeugt ein XMLGregorianCalendar, welcher für das Datum an der die Veranstaltung ausgetragen wird,
	 * genutzt werden sollte.
	 * 
	 * @param jahr Das Jahr, an der die Veranstaltung ausgetragen wird.
	 * @param monat Der Monat, an der die Veranstaltung ausgetragen wird.
	 * @param tag Der Tag, an der die Veranstaltung ausgetragen wird.
	 * @return Das Datum-XMLGC Objekt.
	 * @throws DatatypeConfigurationException Wenn falsche Werte übergeben werden (z.B. Monat 13).
	 */
	public XMLGregorianCalendar buildXMLDate(int jahr, int monat, int tag) throws DatatypeConfigurationException{
		return cr.buildXMLDate(jahr, monat, tag);
	}
	
	/**
	 * Erzeugt ein XMLGregorianCalendar, welcher für die Uhrzeit der Veranstaltung genutzt werden sollte.
	 * 
	 * @param stunde Stunde in der die Veranstaltung ausgetragen wird.
	 * @param minute Minute in der die Veranstaltung ausgetragen wird.
	 * @return Das Uhrzeit-XMLGC Objekt.
	 * @throws DatatypeConfigurationException Wenn ungültige Werte übergeben werden (z.B. Tag 32).
	 */
	public XMLGregorianCalendar buildXMLTime(int stunde, int minute) throws DatatypeConfigurationException{
		return cr.buildXMLTime(stunde, minute);
	}
	
	/**
	 * Liefert alle Sportgruppen als String-Liste.
	 * Diese Liste kann z.B. in einem Dropdown-Feld angezeigt werden.
	 * @return die Sportgruppen-Liste.
	 */
	public List<String> getSportgruppen(){
		List<String> sportgruppenListe = new ArrayList<String>();
		SportgruppenM sgm = cr.getSportgruppen();
		for(Sportgruppe sportgruppeKonkret : sgm.getSportgruppe()){
			sportgruppenListe.add(sportgruppeKonkret.getSGName());
		}
		return  sportgruppenListe;
	}
	
	/**
	 * Liefert Informationen über eine konkrete Sportgruppe.
	 * Diese Informationen können in einem dafür vorgesehendem Textfeld angezeigt werden,
	 * wenn zuvor eine der vielen Sportgruppen ausgewählt wurden.
	 * @param spgId Sportgruppen-ID über welche mehr Informationen beschafft werden soll.
	 * @return Die Informationen der Sportgruppe als String.
	 */
	public String getSportgruppe(String spgId){
		Sportgruppe sg = cr.getSportgruppe(spgId);
		return "Name: " + sg.getSGName() + "\r\n" +
				"Beschreibung: " + sg.getSGBeschreibung() + "\r\n";			
	}
	/**
	 * Liefert alle Sportarten innerhalb einer angegebenen Sportgruppe als String-Liste.
	 * Diese Liste kann z.B. in einem Dropdown-Feld angezeigt werden.
	 * @param spgId Sportgruppen-ID, in welcher sich die Sportarten befinden.
	 * @return die Sportarten-Liste.
	 */
	public List<String> getSportarten(String spgId){
		List<String> sportartenListe = new ArrayList<String>();
		SportartenM sm = cr.getSportarten(spgId);
		for(Sportart sportartKonkret : sm.getSportart()){
			sportartenListe.add(sportartKonkret.getSName());
		}
		return sportartenListe;
	}
	/**
	 * Liefert Informationen über eine konkrete Sportart.
	 * Diese Informationen können in einem dafür vorgesehendem Textfeld angezeigt werden,
	 * wenn zuvor eine der vielen Sportarten ausgewählt wurden.
	 * @param spgId Sportgruppen-ID der Sportgruppe, in der sich die Sportart befindert.
	 * @param spaId Sportarten-ID, der Sportart, über welche mehr Informationen beschafft werden soll.
	 * @return Die Informationen der Sportart als String.
	 */
	public String getSportart(String spgId, String spaId){
		Sportart s = cr.getSportart(spgId, spaId);
		return "Name: " + s.getSName() + "\r\n" +
				"Beschreibung: " + s.getSBeschreibung() + "\r\n" +
				"Voraussetzungen: " + s.getSVorraussetzung() +  "\r\n" +
				"Herkunft: " + s.getSHerkunft() + "\r\n" +
				"Regeln: " + s.getSRegeln() + "\r\n";
	}
	/**
	 * Liefert alle Veranstaltungen innerhalb einer angegebenen Sportart als String-Liste.
	 * Diese Liste kann z.B. in einem Dropdown-Feld angezeigt werden.
	 * 
	 * @param spgId Sportgruppen-ID
	 * @param spaId Sportart-ID in welcher sich die Veranstaltungen befinden.
	 * @return
	 */
	public List<String> getVeranstaltungen(String spgId, String spaId){
		List<String> veranstaltungenListe = new ArrayList<String>();
		VeranstaltungenM vm = cr.getVeranstaltungen(spgId, spaId);
		for(Veranstaltung veranstaltungKonkret : vm.getVeranstaltung()){
			veranstaltungenListe.add(veranstaltungKonkret.getVBeschreibung());
		}
		return veranstaltungenListe;
	}

	/**
	 * Liefert Informationen über eine konkrete Veranstaltung.
	 * Diese Informationen können in einem dafür vorgesehendem Textfeld angezeigt werden,
	 * wenn zuvor eine der vielen Veranstaltungen ausgewählt wurden.
	 * 
	 * Wenn die optionalen Elemente leer gelassen werden, dann wird das Element gar nicht gelistet.
	 * @param spgId Sportgruppen-ID
	 * @param spaId Sportart-ID
	 * @param vstId Veranstaltung-ID, der Veranstaltung, über welche mehr Informationen beschafft werden soll.
	 * @return Die Informationen der Sportart als String.
	 */
	public String getVeranstaltung(String spgId, String spaId, String vstId){
		Veranstaltung vst = cr.getVeranstaltung(spgId, spaId, vstId);
		String ausgabe, info, niveau, voraussetzungen;
		
		if(vst.getVInfo().isEmpty())
			info = "";
		else
			info = "Informationen: " + vst.getVInfo();	
		
		if(vst.getVNiveau().isEmpty())
			niveau = "";
		else
			niveau = "Niveau: " + vst.getVNiveau();
		
		if(vst.getVVorraussetzungen().isEmpty())
			voraussetzungen = "";
		else
			voraussetzungen = "Voraussetzungen: " + vst.getVVorraussetzungen();
		
		ausgabe = "Beschreibung: " + vst.getVBeschreibung() + "\r\n" +
				info + "\r\n" +
				"Datum: " + vst.getVDatum() + "\r\n" +
				"Uhrzeit: " + vst.getVUhrzeit() + "\r\n" +
				niveau + "\r\n" +
				voraussetzungen + "\r\n";
		
		return ausgabe;
	}
	
}
