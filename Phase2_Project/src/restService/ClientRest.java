package restService;

import java.util.Date;
import java.util.GregorianCalendar;

import generated.*;

import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ClientRest {

	private String xml = MediaType.APPLICATION_XML;
	private String uri = "http://localhost:8080/";

	
	/**
	 * Holt die Liste aller Sportgruppen (mehrzahl!) als JAXB-Object: 'SportgruppenM'.
	 * 
	 * Mittels dem Rueckgabewert (SportgruppenM) kann dann auf die Liste
	 * zugeriffen werden, diese mit einer for-Schleife durchlaufen werden
	 * damit dann die einzelenen Sportgruppen erfasst werden können.
	 * 
	 * @return Die Liste der Sportgruppen / null wenn falsche Anfrage.
	 */
	public SportgruppenM getSportgruppen(){
		WebResource sportgruppenListe = Client.create().resource(uri).path("sportgruppen");
		sportgruppenListe.accept(xml);
		return sportgruppenListe.get(SportgruppenM.class);
	}	

	/**
	 * Holt die angefordertere Sportgruppe als JAXB-Object: 'Sportgruppe'.
	 * (einzahl)
	 * 
	 * @param sportgruppeId angeforderte Sportgruppe-IDs
	 * @return angeforderterte Sporgruppe als JAXB-Object / null wenn falsche Anfrage.
	 */
	public Sportgruppe getSportgruppe(String sportgruppeId){
		
		WebResource sportgruppeKonkret = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId);
		
		sportgruppeKonkret.accept(xml);
		return sportgruppeKonkret.get(Sportgruppe.class);
		

	}
	
	/** 
	 * Holt die Sportarten Liste (mehrzahl) der uebergebenen Sportgruppe als JAXB-Object: 'SportartenM'.
	 * @param sportgruppeId Sportgruppe aus der die Sportarten gezogen werden sollen.
	 * @return Die Sportarten-Liste als JAXB-Object / null wenn falsche Anfrage.
	 */
	public SportartenM getSportarten(String sportgruppeId){
		
		WebResource sportartenListe = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId)
				.path("sportarten");
		
		sportartenListe.accept(xml);
		return sportartenListe.get(SportartenM.class);
	}
		
	/**
	 * Holt eine konkrete Sportart einer konkreten Sportgruppe als JAXB-Object: 'Sportart'.
	 * @param sportgruppeId ID der Sportgruppe.
	 * @param sportartId ID der Sportart.
	 * @return Die Sportart als JAXB-Object / null wenn falsche Anfrage.
	 */
	public Sportart getSportart(String sportgruppeId, String sportartId){
		
		WebResource sportartKonkret = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId)
				.path("sportarten")
				.path(sportartId);
		
		sportartKonkret.accept(xml);

		return sportartKonkret.get(Sportart.class);	
	}
	
	/**
	 * Holt die Veranstaltungsliste einer konkreten Sportart als JAXB-Object: 'VeranstaltungenM' (mit QueryParameter).
	 * @param sportgruppeId ID der Sportgruppe.
	 * @param sportartId ID der Sportart.
	 * @param deleted QueryParam: Wenn == true, werden nur gelöschte Veranstaltungen gelistet.
	 * Wenn == false, werden nur nichtgelöschte Veranstaltungen gelistet.
	 * @return Die Veranstaltungs-Liste als JAXB-Object / null wenn falsche Anfrage.
	 */
	public VeranstaltungenM getVeranstaltungen(String sportgruppeId, String sportartId, boolean deleted){
		
		String strbool = new Boolean(deleted).toString();
		WebResource veranstaltungenListe = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId)
				.path("sportarten")
				.path(sportartId)
				.path("veranstaltungen")
				.queryParam("deleted", strbool);
		
		veranstaltungenListe.accept(xml);
		
		return veranstaltungenListe.get(VeranstaltungenM.class);	
	}
	
	/**
	 * Holt die Veranstaltungsliste einer konkreten Sportart als JAXB-Object: 'VeranstaltungenM'. Ohne QueryParameter.
	 * Es werden alle Veranstaltungen zurückgegeben, die nicht gelöscht sind.
	 * (Gleicher Effekt wie mit QueryParam = false)
	 * @param sportgruppeId
	 * @param sportartId
	 * @return Die Veranstaltungs-Liste als JAXB-Object / null wenn falsche Anfrage.
	 */
	public VeranstaltungenM getVeranstaltungen(String sportgruppeId, String sportartId){
		

		WebResource veranstaltungenListe = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId)
				.path("sportarten")
				.path(sportartId)
				.path("veranstaltungen");
		
		veranstaltungenListe.accept(xml);
		
		return veranstaltungenListe.get(VeranstaltungenM.class);	
	}
	/**
	 * Holt eine konkrete Veranstaltung einer Sportart als JAXB-Object: 'Veranstaltung'.
	 * @param sportgruppeId ID der Sportgruppe.
	 * @param sportartId ID der Sportart.
	 * @param veranstaltungId ID der Veranstaltung die geholt werden soll. 
	 * @return Die konkrete Veranstaltung als JAXB-Object / null wenn falsche Anfrage.
	 */
	public Veranstaltung getVeranstaltung(String sportgruppeId, String sportartId, String veranstaltungId){
		
		WebResource veranstaltungKonkret = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId)
				.path("sportarten")
				.path(sportartId)
				.path("veranstaltungen")
				.path(veranstaltungId);
		
		veranstaltungKonkret.accept(xml);
		return veranstaltungKonkret.get(Veranstaltung.class);
	}
	
	/**
	 * Holt die Liste aller Orte (mehrzahl!) als JAXB-Object: 'OrteM'.
	 * @return Die Orte-Liste als JAXB-Object.
	 */
	public OrteM getOrte(){
		WebResource orteListe = Client.create().resource(uri).path("orte");
		orteListe.accept(xml);
		
		return orteListe.get(OrteM.class);

	}
	/**
	 * Holt ein konkreten Ort (einzahl) als JAXB-Object: 'Ort'.
	 * @param ortId ID des zu holenden Ortes.
	 * @return Der Ort als JAXB-Object / null wenn falsch Anfrage.
	 */
	public Ort getOrt(String ortId){
		WebResource ortKonkret = Client.create().resource(uri)
				.path("orte")
				.path(ortId);
		
		ortKonkret.accept(xml);
		
		return ortKonkret.get(Ort.class);
	}
	
	/**
	 * Holt die Liste aller Veranstalter (mehrzahl) als JAXB-Object: 'VeranstalterM'.
	 * @return Die Veranstalter-Liste als JAXB-Object.
	 */
	public VeranstalterM getVeranstalterM(){
		WebResource veranstalterListe = Client.create().resource(uri).path("veranstalter");
		
		veranstalterListe.accept(xml);
		return veranstalterListe.get(VeranstalterM.class);
		
	}
	
	/**
	 * Holt einen konkreten Veranstalter als JAXB-Object: 'Veranstalter'.
	 * @param vrId ID des zu holenden Veranstalters.
	 * @return Den Veranstalter als JAXB-Object / null wenn falsche Anfrage.
	 */
	public Veranstalter getVeranstalter(String vrId){
		WebResource veranstalterKonkret = Client.create().resource(uri)
				.path("veranstalter")
				.path(vrId);
		
		veranstalterKonkret.accept(xml);
		return veranstalterKonkret.get(Veranstalter.class);
	}
	//Vielleicht noch anpassen, falls das Schema neu geparst wird (GebaeudeRef mit benoetigtem Ort)
	public Veranstaltung buildVeranstaltung(
			String beschreibung, 
			String info,
			XMLGregorianCalendar datum,
			//XMLGregorianCalendar time,
			XMLGregorianCalendar time,
			String niveau,
			String vorraussetzungen,
			String gebaeudeIdRef,
			String veranstalterIdRef){
		
		Veranstaltung uebergabe = new Veranstaltung();
		uebergabe.setVBeschreibung(beschreibung);
		uebergabe.setVInfo(info);
		uebergabe.setVDatum(datum);
		uebergabe.setVUhrzeit(time);
		uebergabe.setVNiveau(niveau);
		uebergabe.setVVorraussetzungen(vorraussetzungen);
		uebergabe.setGebaeudeIDRef(gebaeudeIdRef);
		uebergabe.setVeranstalterIDRef(veranstalterIdRef);
		
		return uebergabe;
	}
	public XMLGregorianCalendar buildXMLTime(int stunde, int minute) throws DatatypeConfigurationException{
		
		Date time = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(time);

		gc.set(gc.HOUR_OF_DAY, stunde);
		gc.set(gc.MINUTE, minute);
		
		DatatypeFactory df = DatatypeFactory.newInstance();
		XMLGregorianCalendar xmlGregorianCal = df.newXMLGregorianCalendar(gc);
		return xmlGregorianCal;
		
	}
	public XMLGregorianCalendar buildXMLDate(int jahr, int monat, int tag) throws DatatypeConfigurationException{
		
		GregorianCalendar gc = new GregorianCalendar (jahr, monat, tag);
		DatatypeFactory df = DatatypeFactory.newInstance();
		XMLGregorianCalendar xmlGregorianCal = df.newXMLGregorianCalendar(gc);
		return xmlGregorianCal;
	}
	
	public void putVeranstaltung(
			String sportgruppeId,
			String sportartId,
			String veranstaltungId,
			Veranstaltung neueVeranstaltung){
		
		WebResource veranstaltungKonkret = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId)
				.path("sportarten")
				.path(sportartId)
				.path("veranstaltungen")
				.path(veranstaltungId);
		
		veranstaltungKonkret.accept(xml);
		veranstaltungKonkret.put(neueVeranstaltung);		
	}
	
	public void deleteVeranstaltung(String sportgruppeId, String sportartId, String veranstaltungId){
		
		WebResource veranstaltungKonkret = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId)
				.path("sportarten")
				.path(sportartId)
				.path("veranstaltungen")
				.path(veranstaltungId);
		veranstaltungKonkret.accept(xml);
		veranstaltungKonkret.delete();
	}
	
	public void postVeranstaltung(String sportgruppeId, String sportartId, Veranstaltung neueVeranstaltung){
		
		WebResource veranstaltungKonkret = Client.create().resource(uri)
				.path("sportgruppen")
				.path(sportgruppeId)
				.path("sportarten")
				.path(sportartId)
				.path("veranstaltungen");
		
		veranstaltungKonkret.accept(xml);
		veranstaltungKonkret.post(neueVeranstaltung);			
	}
	

	
}
