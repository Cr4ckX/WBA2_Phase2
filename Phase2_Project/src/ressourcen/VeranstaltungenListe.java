package ressourcen;

import java.io.File;

import generated.*;

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


import jaxb.Marshalling;
import jaxb.Unmarshalling;


@Path("sportgruppen/{spgId}/sportarten/{spaId}/veranstaltungen")
/**
 * Klasse für die HTTP-Methoden auf die Ressource 'Veranstaltungen-Liste' (innerhalb einer Sportart).
 * @author CrackX
 *
 */
public class VeranstaltungenListe {
	
	Sportverzeichnis sv;
	Marshalling marsh = new Marshalling();
	
	public VeranstaltungenListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	//GET - Hole zu der konkreten Sportart die Veranstaltungs-Liste
	/**
	 * Veranstaltungs-Liste per GET angefordert.
	 * Liefert ein JAXB-Object 'VeranstaltungenM'.
	 * 
	 * Der Parameter 'deleted' wird als QueryParam bentutzt. Wenn deleted == true, dann werden nur
	 * Veranstaltungen, welche gelöscht sind, in der übergebenen Veranstaltungs-Liste zu finden sein. 
	 * Ist kein QueryParam geliefert bzw. deleted == false, dann wird die Veranstaltungs-Liste normal ausgegeben,
	 * d.h. es werden alle nicht gelöschten Veranstaltungen in der übergebenen Liste zu finden sein.
	 * 
	 * MIME-TYPE: application/xml.
	 * @param spgId Sportgruppen-ID in der sich die Sportart befindet.
	 * @param spaId Sportart-ID in der sich die gewünschte Veranstaltungsliste befindet.
	 * @param deleted true/false. 
	 * @return Die Veranstaltungs-Liste als JAXB-Object 'VeranstaltungenM'.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public VeranstaltungenM getVeranstaltungen(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId,
			@QueryParam("deleted") boolean deleted){

		//Sportgruppen (mehrzahl)
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();		
		//Sportgruppe (einzahl)
		
		if(Integer.valueOf(spgId) > sgm.getSportgruppe().size() -1){
			System.out.println("Die angeforderte Sporgruppen-ID ist nicht vorhanden.");
			return null;
		}
		Sportgruppe sg = sgm.getSportgruppe().get(Integer.parseInt(spgId));		
		//Sportarten (mehrzahl)
		SportartenM sm = sg.getSportartenM();
		//Sportarte (einzahl)
		
		if(Integer.valueOf(spaId) > sm.getSportart().size() -1 ){
			System.out.println("Die angeforderte Sporarten-ID ist nicht vorhanden.");
			return null;
		}
		Sportart s = sm.getSportart().get(Integer.parseInt(spaId));
		//Veranstaltungen (mehrzahl)
		VeranstaltungenM vm = s.getVeranstaltungenM();
		
		VeranstaltungenM vmUebergabe = new VeranstaltungenM();
	
		if(vm.getVeranstaltung().size() > 0){
			for (int i = 0; i < vm.getVeranstaltung().size(); i++){
				Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(i);
				

				if(deleted == true){
					if(v.isDeleted() == true){
						vmUebergabe.getVeranstaltung().add(v);
					}
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
		//Leere Veranstaltungsliste.
		return vmUebergabe;
	}


	//POST - Füge Veranstaltung hinzu
	/**
	 * HTTP-POST Methode zum Hinzufügen einer neuen Veranstaltung innerhalb einer gewählten Sportart (intern; innerhalb
	 * der zur Sportart gehörenden Veranstaltungs-Liste).
	 * Mittels HTTP-POST Methode wird lediglich das Hinzufügen realisiert. Die ID der Veranstaltung wird entsprechend
	 * vom Server/Service übernommen, unabhängig davon, ob eine ID mitgeliefert wurde oder nicht.
	 * Ebenfalls kann keine gelöschte Veranstaltung hinzugefügt werden; sobald eine Veranstaltung mittels POST
	 * angelegt wurde, ist das intern repräsentierte Attribut 'deleted' immer auf false gesetzt.
	 * 
	 * Die POST-Übergabe muss die XML-Struktur einer Veranstaltung aufweisen (oder ein entsprechendes
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
	 * @param spaId Sportart-ID zu welcher eine Veranstaltung hinzugefügt werden soll.
	 * @param uebergabe XML-Dokument/JAXB-Object 'Veranstaltung'.
	 * @return Im Erfolgsfall wird die ID der hinzugefügten Veranstaltung zurückgegeben, andernfalls -1.
	 * @throws Exception JAXB-Exception, wenn beim (Un)Marshallen ein Fehler auftritt.
	 */
	@POST
	@Consumes (MediaType.APPLICATION_XML)
	@Produces (MediaType.TEXT_PLAIN)
	public String postVeranstaltung(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, 
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
							
							//Output: Datei
							marshaller.marshal(sv, new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
//							marsh.xmlMarshallen();

							return Integer.toString(index);
							//return "Veranstaltung " + s.getVeranstaltungenM().getVeranstaltung()
								//	.get(index).getVBeschreibung() + " hinzugefügt.";				
					}						
				}			
			}
		}
		return "-1";
		//return "Hinzufügen fehlgeschlagen!";
		
	}
}
