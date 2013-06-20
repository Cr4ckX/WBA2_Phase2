package servicesCombined;

import java.util.ArrayList;
import java.util.List;

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
 * Interessent genutzt werden dürfen. Dabei werden beide Services berücksichtigt, sodass sowohl
 * der REST-Service, als auch der XMPP-Service miteinbezogen werden.
 * 
 * Orte (Gebäude) und Veranstalter wurden zunächst nicht implementiert, da die Refernzierung
 * bisher aufrund mangelnder Zeit noch nicht umgesetzt worden ist.
 * @author CrackX
 *
 */
public class CombinedServicesInteressent{
	private ClientRest cr = new ClientRest();
	private XmppManager xm = new XmppManager();
	private boolean initialized, pubSubCheck, connCheck, authCheck, subCheck = false;
	private int connectionTries = 10;
	
	/**
	 * Initialisiert die Verbindung zum XMPP-Server und stellt die Item-Listener für die
	 * Subscriptions wieder her.<br/><br/>
	 * 
	 * Es wird, wenn die Initialisierung fehlschlägt bis zu 10 mal erneut versucht eine erfolgreiche
	 * Verbindung aufzubauen.
	 * @return true, wenn erfolgreich initialisiert.
	 * @throws InterruptedException wenn das System nicht pausiert werden kann.
	 */
	public boolean initialize() throws InterruptedException{
		
		while(initialized == false && connectionTries > 0){
	
			if(connCheck == false){
				connCheck = xm.verbinden();
			}
			if(connCheck == true){
				pubSubCheck = xm.managePubSub();
			}
			
			if(pubSubCheck == true){
				authCheck = xm.login("interessent", "interessent");
			}
			
			if (authCheck == true){ 				
				subCheck = xm.restoreSubscriptions();
			}
			
			if(authCheck || connCheck || authCheck || subCheck){
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
	/**
	 * Den aktuellen Benutzer zu dem gegebenen Leaf subscriben lassen.
	 * @param leafNode Node zu dem subscribed werden soll.
	 * @return true im Erfolgsfall
	 */
	public boolean subscribeLeaf(String leafNode){
		return xm.subscribeLeaf(leafNode);
	}
	/**
	 * Den aktuellen Benutzer von dem gegebenen Leaf unsubscriben lassen.
	 * @param leafNode Node von dem die Subscription gelöscht werden soll.
	 * @return true im Erfolgsfall
	 */
	public boolean unSubscribe(String leafNode){
		return xm.unSubscribe(leafNode);
	}
	/**
	 * Alle laufenden Subscriptions des Benutzers anzeigen lassen.
	 * @return die Liste der Subscriptions.
	 */
	public List<String> showSubscriptions(){
		return xm.showSubscriptions();
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
	 * Liefert alle Sportgruppen als XML (JAXB) Element.
	 * @return Die Sportgruppenliste also JAXB-Object.
	 */
	public SportgruppenM getSportgruppenMElement(){
		return cr.getSportgruppen();
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
	 * Liefert das konkrete Sportgruppen-XML (JAXB) Element.
	 * @param spgId Sportgruppen-ID, der Sportgruppe, welche übermittelt werden soll.
	 * @retrun Die Sportgruppe als JAXB-Object.
	 */
	public Sportgruppe getSportgruppeElement(String spgId){
		return cr.getSportgruppe(spgId);
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
	 * Liefert das konkrete Sportarten-XML (JAXB) Element.
	 * @param spgId Sportgruppen-ID, der Sportgruppe in der sich die Sportart befindet.
	 * @param spaId Sportarten-ID, welche übermittelt werden soll.
	 * @return Die Sportart als JAXB-Object.
	 */
	public Sportart getSportartElement(String spgId, String spaId){
		return cr.getSportart(spgId, spaId);
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
		
		if(vst.getVInfo() == null)
			info = "";
		else
			info = "Informationen: " + vst.getVInfo() + "\r\n";	
		
		if(vst.getVNiveau() == null)
			niveau = "";
		else
			niveau = "Niveau: " + vst.getVNiveau() + "\r\n";
		
		if(vst.getVVorraussetzungen() == null)
			voraussetzungen = "";
		else
			voraussetzungen = "Voraussetzungen: " + vst.getVVorraussetzungen() + "\r\n";
		
		ausgabe = "Beschreibung: " + vst.getVBeschreibung() + "\r\n" +
				info +
				"Datum: " + vst.getVDatum() + "\r\n" +
				"Uhrzeit: " + vst.getVUhrzeit() + "\r\n" +
				niveau  +
				voraussetzungen;
		
		return ausgabe;
	}
	
	
	/**
	 * Liefert das konkrete Veranstaltung-XML (JAXB) Element.
	 * @param spgId Sportgruppen-ID, der Sportgruppe in der sich die Sportart befindet.
	 * @param spaId Sportarten-ID, der Sportart, in der sich die Veranstaltung befindet.
	 * @param vstId Veranstaltungs-ID, der Veranstaltung, welche übertragen werden soll.
	 * @return Die Veranstaltung als JAXB-Object.
	 */
	public Veranstaltung getVeranstaltungElement(String spgId, String spaId, String vstId){
		return cr.getVeranstaltung(spgId, spaId, vstId);
	}
	
	/**
	 * Erfragt, ob der aktuelle Benutzer zu dem übergebenen Leaf-Node subscribed ist.
	 * @param leafNode Leaf-Node zu dem die Subscription abgefragt werden soll.
	 * @return true wenn subscribed.
	 */
	public boolean isSubscribed(String leafNode){
		return xm.isSubscribed(leafNode);
	}
	/**
	 * Schließt die Verbindung zum XMPP-Server.
	 */
	public void logout(){
		xm.disconnect();
	}
	
}
