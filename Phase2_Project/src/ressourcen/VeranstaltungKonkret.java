package ressourcen;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;
import generated.Veranstaltung;

import java.io.File;

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
import javax.xml.bind.Unmarshaller;

import jaxb.Unmarshalling;

@Path("sportgruppen/{spgId}/sportarten/{spaId}/veranstaltungen/{vstId}")
public class VeranstaltungKonkret {
	
	Sportverzeichnis sv;
	
	public VeranstaltungKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	//GET - Hole Infos zu der konkreten Veranstaltung
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getVeranstaltung(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, @PathParam("vstId") String vstId) throws Exception {


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
							// Konkrete Veranstaltungen 
							Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							
							
							if (v.getId().equals(vstId) && v.isDeleted() == false){
								return "Beschreibung: " + v.getVBeschreibung() +
										"\nInfo: " + v.getVInfo() + 
										"\nDatum " + v.getVDatum() +
										"\nUhrzeit: " + v.getVUhrzeit() + 
										"\nNiveau: " + v.getVNiveau() + 
										"\nVorraussetzungen: " + v.getVVorraussetzungen();
							}
							else if(v.getId().equals(vstId)){
								return "Die Veranstaltung mit der id " + vstId + " ist gel�scht und daher nicht aufrufbar.";
							}
						}							
					}					
				}			
			}
		}

		return "Keine Infos zu der Veranstaltung/falsche Veranstaltung";
	}

	//PUT - Setze Veranstaltungselemente
	@PUT
	@Consumes (MediaType.APPLICATION_XML)
	@Produces (MediaType.APPLICATION_XML)
	public String putVeranstaltung(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, 
			@PathParam("vstId") String vstId, 
			Veranstaltung uebergabe) throws Exception{

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
							// Konkrete Veranstaltungen (Veranstaltungsliste 1x f�r gewisse Sportart):
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
								 marshaller.marshal(sv, System.out);
								 
								return "Veranstaltung mit der ID " +vstId+ " aktualisiert";
							}	
						}	
						//TODO: Veranstaltung mittels Put hinzuf�gen
					}
				}
			}
		}
		return "Das aktualisieren der Veranstaltung mit der ID " + vstId + " hat nicht funktioniert.";	
	}

	//DELETE - L�sche Veranstaltung
	/**
	 * 
	 * "L�scht" die gew�nschte Veranstaltung - Das 'deleted' Attribut wird auf true gesetzt. Die Veranstaltung
	 * bleibt weiterhin vorhanden, jedoch wird sie bei der Abfrage nicht mehr gelistet, da dort nur Veranstaltungen
	 * mit dem Attribut 'deleted=false' behandelet werden. 
	 * Kaskadierendes L�schen ist noch nicht ber�cksichtigt.
	 * Die Returnwerte m�ssen noch angepasst werden und am besten mit Exceptions behandelt werden
	 * 
	 * @param spgId - In welcher Sportgruppe befindet sich die Veranstaltung?
	 * @param spaId - Zu welcher Sportart wird die Veranstaltung ausgetragen?
	 * @param vstId - Um welche Veranstaltung handelt es sich genau? (Zu l�schende Veranstaltung)
	 * @return Gel�scht - Wenn die gew�nschte Veranstaltung erfolgreich gel�scht wird.
	 * Fehlgeschlagen.. - Wenn die Veranstaltung aufgrund von Fehlern nicht gel�scht wird.
	 * @throws Exception - F�r das unmarshallen. (TOODOO: Sollte noch bearbeitet werden)
	 */
	@DELETE
	public String deleteVeranstaltung(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, @PathParam("vstId") String vstId) throws Exception {

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
							 
							 System.out.println("�bergebene ID: "+ vstId + ", Veranstaltung-ID: " + v.getId());
							 if (vstId.equals(v.getId()))
							 {
								 //Remove erwartet Index, nicht ID ! (Deswegen rm(m)!)
								 //s.getVeranstaltungenM().getVeranstaltung().remove(m);
								 
								 v.setDeleted(true);
								 
								 JAXBContext jc = JAXBContext.newInstance("generated");
								 Marshaller marshaller = jc.createMarshaller();
								 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
								
								 //Output: Konsole
								 marshaller.marshal(sv, System.out);
								return "Veranstaltung mit der id " + vstId + " wurde gel�scht."; //Kaskadierendes L�schen fehlt noch.
							 }
						 }		
					}
				}
			}
		}

		return "Fehlgeschlagen die Veranstaltungs-ID " + vstId + " zu l�schen";
	}		
}
