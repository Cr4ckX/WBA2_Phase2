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
 * Diese Klasse dient dazu, die Operationen zur Verf�gung zu stellen, welche von der GUI vom
 * Interessent genutzt werden d�rfen. Dabei werden beide Services ber�cksichtigt, sodass sowohl
 * der REST-Service, als auch der XMPP-Service miteinbezogen werden.
 * 
 * Orte (Geb�ude) und Veranstalter wurden zun�chst nicht implementiert, da die Refernzierung
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
	 * Initialisiert die Verbindung zum XMPP-Server und stellt die Item-Listener f�r die
	 * Subscriptions wieder her.<br/><br/>
	 * 
	 * Es wird, wenn die Initialisierung fehlschl�gt bis zu 10 mal erneut versucht eine erfolgreiche
	 * Verbindung aufzubauen.
	 * @return true, wenn erfolgreich initialisiert.
	 * @throws InterruptedException wenn da
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
	 * @param leafNode Node von dem die Subscription gel�scht werden soll.
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
	 * Liefert Informationen �ber eine konkrete Sportgruppe.
	 * Diese Informationen k�nnen in einem daf�r vorgesehendem Textfeld angezeigt werden,
	 * wenn zuvor eine der vielen Sportgruppen ausgew�hlt wurden.
	 * @param spgId Sportgruppen-ID �ber welche mehr Informationen beschafft werden soll.
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
	 * Liefert Informationen �ber eine konkrete Sportart.
	 * Diese Informationen k�nnen in einem daf�r vorgesehendem Textfeld angezeigt werden,
	 * wenn zuvor eine der vielen Sportarten ausgew�hlt wurden.
	 * @param spgId Sportgruppen-ID der Sportgruppe, in der sich die Sportart befindert.
	 * @param spaId Sportarten-ID, der Sportart, �ber welche mehr Informationen beschafft werden soll.
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
	 * Liefert Informationen �ber eine konkrete Veranstaltung.
	 * Diese Informationen k�nnen in einem daf�r vorgesehendem Textfeld angezeigt werden,
	 * wenn zuvor eine der vielen Veranstaltungen ausgew�hlt wurden.
	 * 
	 * Wenn die optionalen Elemente leer gelassen werden, dann wird das Element gar nicht gelistet.
	 * @param spgId Sportgruppen-ID
	 * @param spaId Sportart-ID
	 * @param vstId Veranstaltung-ID, der Veranstaltung, �ber welche mehr Informationen beschafft werden soll.
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
