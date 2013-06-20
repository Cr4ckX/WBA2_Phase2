package ressourcen;

import java.io.File;

import generated.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import jaxb.Marshalling;
import jaxb.Unmarshalling;

@Path("sportgruppen/{spgId}/sportarten/{spaId}/veranstaltungen/{vstId}")
/**
 * Klasse für die HTTP-Methoden auf die Ressource 'Konkrete Veranstaltung' (innerhalb einer Veranstaltungs-Liste).
 * @author CrackX
 *
 */
public class VeranstaltungKonkret {
	
	Sportverzeichnis sv;	
	public VeranstaltungKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	/**
	 * Veranstaltung mittels HTTP-GET als JAXB-Object ('Veranstaltung') angefordert.
	 * Liefert eine konkrete Veranstaltung innerhalb einer Sportart, innerhalb einer Sportgruppe.
	 * 
	 * MIME-TYPE: application/xml.
	 * @param spgId Sportgruppen-ID in der sich die Sportart befindet.
	 * @param spaId Sportart-ID in der sich die Veranstaltung befindet.
	 * @param vstId Veranstaltung-ID welche die zu holende Veranstaltung identifiziert.
	 * @return Das JAXB-Object 'Veranstaltung'.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Veranstaltung getVeranstaltung(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, 
			@PathParam("vstId") String vstId){


		//Sportgruppen (mehrzahl)
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();
		
		if(Integer.valueOf(spgId) > sgm.getSportgruppe().size() -1){
			System.out.println("Die angeforderte Sporgruppen-ID ist nicht vorhanden.");
			return null;
		}
		//Sportgruppe (einzahl)
		Sportgruppe sg = sgm.getSportgruppe().get(Integer.parseInt(spgId));		
		//Sportarten (mehrzahl)
		SportartenM sm = sg.getSportartenM();
		
		if(Integer.valueOf(spaId) > sm.getSportart().size() -1 ){
			System.out.println("Die angeforderte Sporarten-ID ist nicht vorhanden.");
			return null;
		}
		//Sportarte (einzahl)
		Sportart s = sm.getSportart().get(Integer.parseInt(spaId));
		//Veranstaltungen (mehrzahl)
		VeranstaltungenM vm = s.getVeranstaltungenM();
		
		if(Integer.valueOf(vstId) > vm.getVeranstaltung().size() -1){
			System.out.println("Die angeforderte Veranstaltung-ID ist nicht vorhanden.");
			return null;
		}
		//Veranstaltung (einzahl)
		Veranstaltung v = vm.getVeranstaltung().get(Integer.parseInt(vstId));

		if(v.isDeleted() == false){
			return v;
		}
		else if(v.getId().equals(vstId)){
			System.out.println("Die Veranstaltung mit der id " + vstId + " ist gelöscht und daher nicht aufrufbar.");
			return null;
		}			
		
		System.out.println("Die angeforderte Sporgruppen-ID oder Sportart-ID oder Veranstaltung-ID ist nicht vorhanden");
		return null;
	}




	/**
	 * HTTP-PUT Methode zum Aktualisieren einer vorhandenen Veranstaltung innerhalb einer gewählten 
	 * Sportart (intern; innerhalb der zur Sportart gehörenden Veranstaltungs-Liste).
	 * 
	 * Es können lediglich Veranstaltungen geändert werden, welche nicht gelöscht sind.
	 * Wenn eine Veranstaltung aktualisiert wird, und die aktualisierte Veranstaltung das Attribut
	 * deleted auf 'true' gesetzt hat, so wird dieses automatisch wieder auf 'false' gesetzt. 
	 * Möchte man eine Veranstaltung löschen, so geschiet dies nicht mittels PUT und dem entsprechend
	 * gesetztdem Attribut, sondern dafür sollte die HTTP-DELETE Methode genutzt werden.
	 * 
	 * Die PUT-Übergabe muss die XML-Struktur einer Veranstaltung aufweisen (oder ein entsprechendes
	 * JAXB-Object übergeben):
	 *
	 * <Veranstaltung>
	 * 		<VBeschreibung>VeranstaltungAdd</VBeschreibung>
	 * 		<VDatum>2014-04-27</VDatum>
	 * 		<VUhrzeit>15:00:00</VUhrzeit>
	 * 		<GebaeudeIDRef>G00</GebaeudeIDRef>
	 * 		<VeranstalterIDRef>VT00</VeranstalterIDRef>
	 * </Veranstaltung>
	 * 
	 * MIME-TYPE: application/xml.
	 * 
	 * @param spgId Sportgruppen-ID in der sich die Sportart befindet. 
	 * @param spaId Sportart-ID in der sich die Veranstaltung befindet.
	 * @param vstId Veranstaltung-ID welche die zu aktualisierende Veranstaltung identifiziert.
	 * @param uebergabe XML-Dokument der Struktur von oben/JAXB-Object 'Veranstaltung'.
	 * @return String mit: "true", wenn Aktualisieren erfolgreich, "false", wenn Aktualisieren nicht erfolgreich.
	 * @throws Exception JAXB-Exception, wenn beim (Un)Marshallen ein Fehler auftritt.
	 */
	@PUT
	@Consumes (MediaType.APPLICATION_XML)
	public String putVeranstaltung(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, 
			@PathParam("vstId") String vstId, 
			Veranstaltung uebergabe) throws Exception {

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();
		
		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konkrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

			if (spgId.equals(sg.getId())) {
						
				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					if (spaId.equals(s.getId()) && s.getVeranstaltungenM().getVeranstaltung().size() > 0) {
						for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++) {
							// Konkrete Veranstaltungen (Veranstaltungsliste 1x für gewisse Sportart):
							Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							
							int aktuelleVeranstaltungId = Integer.parseInt(v.getId());
							aktuelleVeranstaltungId = aktuelleVeranstaltungId + 1;
							System.out.print(aktuelleVeranstaltungId);
							if (v.getId().equals(vstId) && v.isDeleted() == false){
								
								 //ID wird vom Client bestimmt.
								 uebergabe.setId(vstId);
								 
								 s.getVeranstaltungenM().getVeranstaltung().set(m, uebergabe);
								 //Attribut "deleted" ist auf false gesetzt.
								 v.setDeleted(false);
								 
								 JAXBContext jc = JAXBContext.newInstance("generated");
								 Marshaller marshaller = jc.createMarshaller();
								 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
								 //Output
								 marshaller.marshal(sv, new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
								 //marsh.xmlMarshallen();
								 
								 return "true";
								//return "Veranstaltung mit der ID " +vstId+ " aktualisiert";
							}	
						}	
					}
				}
			}
		}
		return "false";
		//return "Das aktualisieren der Veranstaltung mit der ID " + vstId + " hat nicht funktioniert.";	
	}

	//DELETE - Lösche Veranstaltung
	/**
	 * 
	 * "Löscht" die gewünschte Veranstaltung -> Das 'deleted' Attribut wird auf true gesetzt. Die Veranstaltung
	 * bleibt weiterhin vorhanden, jedoch wird sie bei der Abfrage nicht mehr gelistet, da dort nur Veranstaltungen
	 * mit dem Attribut 'deleted=false' behandelet werden (es sei denn, der entsprechende Query Parameter ist bei der
	 * GET-Anfrage auf true gesetzt. 
	 * 
	 * Kaskadierendes Löschen (Gebäude, Veranstalter) ist aufgrund mangelnder Umsetztung
	 * nicht berücksichtigt.
	 * 
	 * MIME-TYPE: application/xml.
	 * @param spgId Sportgruppen-ID in der sich die Sportart befindet. 
	 * @param spaId Sportart-ID in der sich die Veranstaltung befindet.
	 * @param vstId Veranstaltung-ID welche die zu löschende Veranstaltung identifiziert.
	 * @return String mit: "true", wenn Aktualisieren erfolgreich, "false", wenn Aktualisieren nicht erfolgreich.
	 * @throws Exception Exception JAXB-Exception, wenn beim (Un)Marshallen ein Fehler auftritt.
	 */
	@DELETE
	public String deleteVeranstaltung(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, 
			@PathParam("vstId") String vstId) throws Exception{

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konkrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);


			if (spgId.equals(sg.getId())) {
				
				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					if(spaId.equals(s.getId()))
					{	
						 for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++){
							 Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							 
							 System.out.println("Übergebene ID: "+ vstId + ", Veranstaltung-ID: " + v.getId());
							 if (vstId.equals(v.getId()))
							 {
								 //Remove erwartet Index, nicht ID ! (Deswegen rm(m)!)
								 //s.getVeranstaltungenM().getVeranstaltung().remove(m);
								 
								 v.setDeleted(true);
								 
								 JAXBContext jc = JAXBContext.newInstance("generated");
								 Marshaller marshaller = jc.createMarshaller();
								 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
								
								 //Output: Konsole
								 marshaller.marshal(sv, new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
								 
								 //marsh.xmlMarshallen();
								 return "true";
								 //return "Veranstaltung mit der id " + vstId + " wurde gelöscht."; //Kaskadierendes Löschen fehlt noch.
							 }
						 }		
					}
				}
			}
		}
		return "false";
		//return "Fehlgeschlagen die Veranstaltungs-ID " + vstId + " zu löschen";
	}		
}
