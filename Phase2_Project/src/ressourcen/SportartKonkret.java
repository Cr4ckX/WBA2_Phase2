package ressourcen;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import jaxb.Unmarshalling;

@Path("sportgruppen/{spgId}/sportarten/{spaId}/")
public class SportartKonkret {
	
	Sportverzeichnis sv;
	
	public SportartKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	

	//Hole zu der konkreten Sportart die zugehörigen Informationen
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	/**
	 * 
	 * @param spgId spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @param spaId spgId Sportarten URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return Gibt die Informationen der angeforderteren Sportart zurück. Ist die URI-Id nicht vorhanden, 
	 * so wird eine Fehlermeldung ausgegeben.
	 * @throws Exception
	 * 
	 * Konkrete Sportart per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Elemente der Sportarteninformationen. 
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 */
	public String getSportart(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId) throws Exception {

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
					
					if (spaId.equals(s.getId())) {
						
						return "Sportart: " + s.getSName() +
							   "\nBeschreibung: " + s.getSBeschreibung() + 
							   "\nHerkunft: " + s.getSHerkunft() +
							   "\nVorraussetzungen: " + s.getSVorraussetzung() + 
							   "\nRegeln: " + s.getSRegeln();
					}				
				}
			}

		}

		return "Keine Informationen zu der Sportart/Falsche Sportart";
	}

	
}
