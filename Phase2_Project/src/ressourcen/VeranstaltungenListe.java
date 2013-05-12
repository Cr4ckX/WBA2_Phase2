package ressourcen;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;
import generated.Veranstaltung;

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


@Path("sportgruppen/{spgId}/sportarten/{spaId}/veranstaltungen")
public class VeranstaltungenListe {
	
	//GET - Hole zu der konkreten Sportart die Veranstaltungs-Liste
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getVeranstaltungen(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId,
			@QueryParam("deleted") boolean deleted) throws Exception {

		String ausgabe = "";
		
		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
		
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
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
							
							
							if (deleted == true){
								if (v.isDeleted() == true){
									ausgabe += v.getId() + " " + v.getVBeschreibung();
									
									//Damit am Ende nicht noch ein "\n" angef�gt wird.
									if(m+1<s.getVeranstaltungenM().getVeranstaltung().size())
										ausgabe += "\n";
								}
							}
							else if (deleted == false){
								if (v.isDeleted() == false){
									ausgabe += v.getId() + " " + v.getVBeschreibung();
									
									//Damit am Ende nicht noch ein "\n" angef�gt wird.
									if(m+1<s.getVeranstaltungenM().getVeranstaltung().size())
										ausgabe += "\n";
								}
							}
							
							//Standard/kein Query-Parameter.
							else{
								if (v.isDeleted() == false){
									ausgabe += v.getId() + " " + v.getVBeschreibung();
									
										//Damit am Ende nicht noch ein "\n" angef�gt wird.
										if(m+1<s.getVeranstaltungenM().getVeranstaltung().size())
											ausgabe += "\n";
								}
							}
						}
						return ausgabe;
					}					
				}
			}
		}
	
		return "Keine Veranstaltungen zu der Sportart/Falsche Sportart";
	}

	//POST - F�ge Veranstaltung hinzu
	/**
	 * 	 
	 *Die POST-�bergabe muss die XML-Struktur einer Veranstaltung aufweisen:
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
		

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
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
							
							//Setze die �bergebene Veranstaltung an der Position index ein.
							s.getVeranstaltungenM().getVeranstaltung().add(index, uebergabe);
							
							//Setze die ID noch richtig (POST = Server setzt ID/Ressource)
							s.getVeranstaltungenM().getVeranstaltung().get(index).setId(String.valueOf(index));
							
							//Attribut "deleted" noch auf false setzen
							s.getVeranstaltungenM().getVeranstaltung().get(index).setDeleted(false);
							
							Marshaller marshaller = jc.createMarshaller();
							marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							
							//Output: Console
							marshaller.marshal(sv, System.out);

							return "Veranstaltung " + s.getVeranstaltungenM().getVeranstaltung()
									.get(index).getVBeschreibung() + " hinzugef�gt.";
				
					}						
				}			
			}
		}
		return "Hinzuf�gen fehlgeschlagen!";
		
	}
}