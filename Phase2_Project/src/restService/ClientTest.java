package restService;

import generated.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.RequestBuilder;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class ClientTest {

	String xml = MediaType.APPLICATION_XML;
	String uri = "http://localhost:8080/";
	
	
	public static void main(String[] args) {

		ClientTest ct = new ClientTest();
		System.out.println(ct.getSportgruppen());
		
		//SportgruppenM spg = ct.getSportgruppen();
		//spg.getSportgruppe().size();  -> Um Groesse festzustellen.
		
		System.out.println(ct.getSportgruppe("3"));
		System.out.println(ct.getSportarten("0"));
		System.out.println(ct.getSportart("0", "0"));
		
		//VeranstaltungenM vm = ct.getVeranstaltungen("0", "0", true);
		VeranstaltungenM vm = ct.getVeranstaltungen("0", "0", false);
		for(int i = 0; i < vm.getVeranstaltung().size(); i++){
			System.out.println(vm.getVeranstaltung().get(i).getVBeschreibung());
		}
		System.out.println(ct.getVeranstaltung("0", "0", "0"));
		System.out.println(ct.getOrte());
		System.out.println(ct.getOrt("0"));
	}
	
	/**
	 * Holt die Liste aller Sportgruppen (mehrzahl!)
	 * 
	 * Mittels dem Rueckgabewert (SportgruppenM) kann dann auf die Liste
	 * zugeriffen werden, diese mit einer for-Schleife durchlaufen werden
	 * damit dann die einzelenen Sportgruppen erfasst werden kšnnen.
	 * 
	 * @return Die Liste der Sportgruppen / null wenn falsche Anfrage.
	 */
	public SportgruppenM getSportgruppen(){
		WebResource sportgruppenListe = Client.create().resource(uri).path("sportgruppen");
		sportgruppenListe.accept(xml);
		return sportgruppenListe.get(SportgruppenM.class);
	}	
/*
		SportgruppenM sgm = new SportgruppenM();
		
		ClientResponse sportgruppenRes = Client.create().resource(
		          "http://localhost:8080/sportgruppen/").
		          accept(MediaType.APPLICATION_XML).
		          get(ClientResponse.class);
		
		if(sportgruppenRes.hasEntity()){
			sgm = sportgruppenRes.getEntity(SportgruppenM.class);
			return sgm;
		}
		System.err.println("Fehler beim holen der Sportgruppen");
		return null;
 */


	
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
		/*
		Sportgruppe sg = new Sportgruppe();
		Builder b = sR.path(sportgruppeId).accept(MediaType.APPLICATION_XML);
		return b.get(Sportgruppe.class);
		
		ClientResponse sportgruppeRes = Client.create().resource(
				"http://localhost:8080/sportgruppen/"+sportgruppeId+"/").
				accept(MediaType.APPLICATION_XML).
				get(ClientResponse.class);
		
		if(sportgruppeRes.hasEntity()){
			sg = sportgruppeRes.getEntity(Sportgruppe.class);
			return sg;
		}
		System.out.println("Fehler beim holen der Sportgruppe.");
		return null;
 
		 */
	
	/** 
	 * Holt die Sportarten Liste der uebergebenen Sportgruppe.
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
	
		/*
		SportartenM sm = new SportartenM();
		ClientResponse sportartenRes = Client.create().resource(
				"http://localhost:8080/sportgruppen/"+sportgruppeId+"/sportarten").
				accept(MediaType.APPLICATION_XML).
				get(ClientResponse.class);
		
		if(sportartenRes.hasEntity()){
			sm = sportartenRes.getEntity(SportartenM.class);
			return sm;
		}
		System.out.println("Fehler beim holen der Sportarten.");
		return null;
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
	
	public OrteM getOrte(){
		WebResource orteListe = Client.create().resource(uri).path("orte");
		orteListe.accept(xml);
		
		return orteListe.get(OrteM.class);

	}
	
	public Ort getOrt(String ortId){
		WebResource ortKonkret = Client.create().resource(uri)
				.path("orte")
				.path(ortId);
		
		ortKonkret.accept(xml);
		
		return ortKonkret.get(Ort.class);
	}
	
}
