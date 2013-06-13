package ressourcen;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;
import generated.Veranstaltung;
import generated.VeranstaltungenM;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jaxb.Unmarshalling;


@Path("sportgruppen/{spgId}/sportarten/{spaId}/veranstaltungen")
public class VeranstaltungenListe {
	
	Sportverzeichnis sv;
	
	public VeranstaltungenListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	//GET - Hole zu der konkreten Sportart die Veranstaltungs-Liste
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public VeranstaltungenM getVeranstaltungen(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId,
			@QueryParam("deleted") boolean deleted) throws Exception {

		//Sportgruppen (mehrzahl)
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();		
		//Sportgruppe (einzahl)
		Sportgruppe sg = sgm.getSportgruppe().get(Integer.parseInt(spgId));		
		//Sportarten (mehrzahl)
		SportartenM sm = sg.getSportartenM();
		//Sportarte (einzahl)
		Sportart s = sm.getSportart().get(Integer.parseInt(spaId));
		//Veranstaltungen (mehrzahl)
		VeranstaltungenM vm = s.getVeranstaltungenM();
		
		VeranstaltungenM vmUebergabe = new VeranstaltungenM();

		if(spgId.equals(sg.getId()) && spaId.equals(s.getId())){	
			if(vm.getVeranstaltung().size() > 0){
				for (int i = 0; i < vm.getVeranstaltung().size(); i++){
					Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(i);
					
					//true
					if(deleted == true){
						if(v.isDeleted() == true)
							vmUebergabe.getVeranstaltung().add(v);
					}
					
					//Kein QueryParam / false
					else if(deleted == false){
						if (v.isDeleted() == false)
							vmUebergabe.getVeranstaltung().add(v);
					}

					//Falscher QueryParam
					else if(deleted != true && deleted != false){
						System.out.println("Query Param fehlerhaft!");
						return null;
					}
	
				}
				return vmUebergabe;
			}
			System.out.println("Keine Veranstaltungen zu der Sportart.");
			return null;	
		}
		System.out.println("Die angeforderte Sporgruppen-ID oder Sportart-ID ist nicht vorhanden.");
		return null;
	}


	//POST - Füge Veranstaltung hinzu
	/**
	 * 	 
	 *Die POST-Übergabe muss die XML-Struktur einer Veranstaltung aufweisen:
	 * <Veranstaltung>
	 * 		<VBeschreibung>VeranstaltungAdd</VBeschreibung>
	 * 		<VDatum>2014-04-27</VDatum>
	 * 		<VUhrzeit>15:00:00</VUhrzeit>
	 * 		<GebaeudeIDRef>G00</GebaeudeIDRef>
	 * 		<VeranstalterIDRef>VT00</VeranstalterIDRef>
	 * </Veranstaltung>
	 * 
	 * TODO - Text anpassen:"Die Ressource wird vom Server gegeben."
	 * @param spgId
	 * @param spaId
	 * @param uebergabe
	 * @return
	 * @throws Exception
	 */
	@POST
	@Consumes (MediaType.APPLICATION_XML)
	@Produces (MediaType.APPLICATION_XML)
	public String postVeranstaltung(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, 
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
			
							//Index: Die wievielte Veranstaltung in zu dieser Sportart + 1
							int index = s.getVeranstaltungenM().getVeranstaltung().size();
							
							//Setze die übergebene Veranstaltung an der Position index ein.
							s.getVeranstaltungenM().getVeranstaltung().add(index, uebergabe);
							
							//Setze die ID noch richtig (POST = Server setzt ID/Ressource)
							s.getVeranstaltungenM().getVeranstaltung().get(index).setId(String.valueOf(index));
							
							//Attribut "deleted" noch auf false setzen
							s.getVeranstaltungenM().getVeranstaltung().get(index).setDeleted(false);
							
							JAXBContext jc = JAXBContext.newInstance("generated");
							Marshaller marshaller = jc.createMarshaller();
							marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							
							//Output: Console
							marshaller.marshal(sv, System.out);

							return "Veranstaltung " + s.getVeranstaltungenM().getVeranstaltung()
									.get(index).getVBeschreibung() + " hinzugefügt.";
				
					}						
				}			
			}
		}
		return "Hinzufügen fehlgeschlagen!";
		
	}
}
