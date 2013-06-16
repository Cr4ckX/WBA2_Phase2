package servicesCombined;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Veranstaltung;
import restService.ClientRest;
import xmppService.XmppManager;

/**
 * Diese Klasse dient dazu, die Operationen zur Verfügung zu stellen, welche von der GUI vom
 * Veranstalter genutzt werden dürfen. Dabei werden beide Services berücksichtigt, sodass sowohl
 * der REST-Service, als auch der XMPP-Service miteinbezogen werden.
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
			
			if(authCheck || connCheck || authCheck == true){
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
	 * Diese Methode fügt eine neue Veranstaltung zu einer Sporgruppenliste hinzu.
	 * Gleichzeitig wird ein LeafNode fuer diese Veranstaltung erzeugt.
	 * @param sportgruppeId Sportgruppen-ID in der sich die Sportart befindet.
	 * @param sportartId Sportart-ID zu der eine neue Veranstaltung erstellt werden soll.
	 * @param neueVeranstaltung 
	 */
	public boolean postVeranstaltung(String sportgruppeId, String sportartId, Veranstaltung neueVeranstaltung){
		int neueVeranstaltungsID;
		
		if(initialized == true){
			neueVeranstaltungsID = cr.postVeranstaltung(sportgruppeId, sportartId, neueVeranstaltung);	
			if(neueVeranstaltungsID != -1){
				//Publish: Sportart hat neue Veranstaltung.
				xm.createLeaf(sportgruppeId+sportartId+neueVeranstaltungsID+"Veranstaltung");
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteVeranstaltung(String sportgruppeId, String sportartId, String veranstaltungId){
		boolean deleted;
		
		if(initialized == true){		
			deleted = cr.deleteVeranstaltung(sportgruppeId, sportartId, veranstaltungId);
			if(deleted == true){
				//Publish: Node wird deleted.
				return xm.deleteLeaf(sportgruppeId+sportartId+veranstaltungId+"Veranstaltung");
			}
		}
		return false;
		
		
	}
	
	public boolean putVeranstaltung(String sportgruppeId, String sportartId, String veranstaltungId, Veranstaltung neueVeranstaltung){
		boolean updated;
		
		if(initialized == true){
			updated = cr.putVeranstaltung(sportgruppeId, sportartId, veranstaltungId, neueVeranstaltung);
			if(updated == true){
				//Veranstaltung wurde geändert (an den Leaf Publishen!)
				return true;
			}
			
		}
		return false;
	}
	
	public Veranstaltung buildVeranstaltung(String beschreibung, String info, XMLGregorianCalendar datum,
			XMLGregorianCalendar time, String niveau, String vorraussetzungen, String gebaeudeIdRef,
			String veranstalterIdRef){
		return cr.buildVeranstaltung(beschreibung, info, datum, time, niveau, vorraussetzungen, gebaeudeIdRef, 
				veranstalterIdRef);
	}
	
	public XMLGregorianCalendar buildXMLDate(int jahr, int monat, int tag) throws DatatypeConfigurationException{
		return cr.buildXMLDate(jahr, monat, tag);
	}
	
	public XMLGregorianCalendar buildXMLTime(int stunde, int minute) throws DatatypeConfigurationException{
		return cr.buildXMLTime(stunde, minute);
	}
	
	public List<String> getSportgruppen(){
		List<String> sportgruppenListe = new ArrayList<String>();
		SportgruppenM sgm = cr.getSportgruppen();
		for(Sportgruppe sportgruppeKonkret : sgm.getSportgruppe()){
			sportgruppenListe.add(sportgruppeKonkret.getSGName());
		}
		return  sportgruppenListe;
	}
	
	public String getSportgruppe(String spgId){
		Sportgruppe sg = cr.getSportgruppe(spgId);
		return "Name: " + sg.getSGName() + "\r\n" +
				"Beschreibung: " + sg.getSGBeschreibung() + "\r\n";			
	}

}
